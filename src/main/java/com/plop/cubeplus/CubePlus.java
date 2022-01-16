package com.plop.cubeplus;

import com.plop.cubeplus.common.config.Config;
import com.plop.cubeplus.common.DynBlock.FormMats;
import com.plop.cubeplus.common.RegistryHandler;
import com.plop.cubeplus.common.item.cpItems;
//import com.plop.cubeplus.common.renderer.cpNewRoofRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Mod(CubePlus.MOD_ID)
@Mod.EventBusSubscriber (modid = CubePlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CubePlus
{
    public static final String MOD_ID = "cubeplus";

	// Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();

    public static final CreativeModeTab TAB = new CreativeModeTab("TabName")
    {
        @Override
        public ItemStack makeIcon(){return new ItemStack(cpItems.TROWEL);}
    };

    public CubePlus()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.config);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the setup method for modloading
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::onTextureStitch);
        // Register the enqueueIMC method for modloading
        modEventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        modEventBus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        modEventBus.addListener(this::doClientStuff);

        Config.loadConfig(Config.config, FMLPaths.CONFIGDIR.get().resolve("cubeplus.toml").toString());

        FormMats.Init();
        RegistryHandler.init();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        //MinecraftForge.EVENT_BUS.register(cpItems.INSTANCE);
    }

    private void onTextureStitch(TextureStitchEvent.Pre event)
    {
        if(!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS))
            return;

//        event.addSprite(cpNewRoofRenderer.TEXTURE1);
//        event.addSprite(cpNewRoofRenderer.TEXTURE2);
    }

    @SubscribeEvent
    public static void createBlockItems(final RegistryEvent.Register<Item> event)
    {
        final IForgeRegistry<Item> registry = event.getRegistry();

        RegistryHandler.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {

            final Item.Properties properties = new Item.Properties().tab(TAB);
            final BlockItem blockItem = new BlockItem(block, properties);
            ResourceLocation location = RegistryHandler.getBlockItemName(block.getRegistryName());
            blockItem.setRegistryName(location);

            registry.register(blockItem);
        });

        cpItems.init(registry);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
        RenderType rendertype = RenderType.cutoutMipped(); // Cutout Mipped

        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.ACACIA_PLANKS_WOOD_SIMPLE_WINDOW.get(), rendertype);
        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.ACACIA_PLANKS_WOOD_SQUARE_WINDOW.get(), rendertype);
        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.BIRCH_PLANKS_WOOD_SIMPLE_WINDOW.get(), rendertype);
        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.BIRCH_PLANKS_WOOD_SQUARE_WINDOW.get(), rendertype);
        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.DARK_OAK_PLANKS_WOOD_SIMPLE_WINDOW.get(), rendertype);
        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.DARK_OAK_PLANKS_WOOD_SQUARE_WINDOW.get(), rendertype);
        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.JUNGLE_PLANKS_WOOD_SIMPLE_WINDOW.get(), rendertype);
        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.JUNGLE_PLANKS_WOOD_SQUARE_WINDOW.get(), rendertype);
        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.OAK_PLANKS_WOOD_SIMPLE_WINDOW.get(), rendertype);
        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.OAK_PLANKS_WOOD_SQUARE_WINDOW.get(), rendertype);
        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.SPRUCE_PLANKS_WOOD_SIMPLE_WINDOW.get(), rendertype);
        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.SPRUCE_PLANKS_WOOD_SQUARE_WINDOW.get(), rendertype);
        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.CRIMSON_PLANKS_WOOD_SIMPLE_WINDOW.get(), rendertype);
        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.CRIMSON_PLANKS_WOOD_SQUARE_WINDOW.get(), rendertype);
        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.WARPED_PLANKS_WOOD_SIMPLE_WINDOW.get(), rendertype);
        ItemBlockRenderTypes.setRenderLayer(RegistryHandler.WARPED_PLANKS_WOOD_SQUARE_WINDOW.get(), rendertype);

        // do something that can only be done on the client
        //LOGGER.info("Got game settings {}", event.getIMCStream().);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo(MOD_ID, "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event)
    {
        //event.player.set
    }

    public static void log(String text)
    {
        String szPath = "debug.txt";
        text += "\n";
        File pFile = new File(szPath);

        try
        {
            if (pFile.exists() || pFile.createNewFile())
            {
                byte[] byData = Files.readAllBytes(Paths.get(szPath));
                byte[] byFinal = new byte[byData.length + text.getBytes().length];

                System.arraycopy(byData, 0, byFinal, 0, byData.length);
                System.arraycopy(text.getBytes(), 0, byFinal, byData.length, text.getBytes().length);

                Files.write(Paths.get(szPath), byFinal);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        LOGGER.info(text);
    }
}