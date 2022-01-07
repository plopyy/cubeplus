package com.plop.cubeplus.common;

import com.plop.cubeplus.CubePlus;
import com.plop.cubeplus.common.DynBlock.FormBlock;
import com.plop.cubeplus.common.block.*;
import com.plop.cubeplus.common.blockentity.cpNewRoofEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.Arrays;

import static com.plop.cubeplus.common.DynBlock.FormBlock.IsNewBasicStairs;

public class RegistryHandler
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CubePlus.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CubePlus.MOD_ID);
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, CubePlus.MOD_ID);

    public static void init()
    {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCK_ENTITIES.register(FMLJavaModLoadingContext.get().getModEventBus());

        // Forms
        FormBlock.RegistryForm(FormBlock.FORM.PILLAR_1S, "pillar_1s", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.PILLAR_2S, "pillar_2s", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.PILLAR_4S, "pillar_4s", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.PILLAR_6S, "pillar_6s", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.PILLAR_10S, "pillar_10s", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.PILLAR_ROUND_6S, "pillar_round_6s", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.PILLAR_ROUND_8S, "pillar_round_8s", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.PILLAR_ROUND_12S, "pillar_round_12s", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.STAIRS_4STEPS, "stairs_4steps", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.STAIRS_BASIC, "stairs_basic", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.BEAM_SINGLE, "beam_single", Arrays.asList(FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.BEAM, "beam", Arrays.asList(FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.BEAM_DOUBLE, "beam_double", Arrays.asList(FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.ARROW_SLIT, "arrow_slit", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.ARROW_SLIT_ROUNDED, "arrow_slit_rounded", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.SLATES_ROOF, "slates_roof", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.THATCH_ROOF, "thatch_roof", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.TILES_ROOF, "tiles_roof", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.SLATES_ROOFTOP_SIDE, "slates_rooftop_side", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.THATCH_ROOFTOP_SIDE, "thatch_rooftop_side", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        FormBlock.RegistryForm(FormBlock.FORM.TILES_ROOFTOP_SIDE, "tiles_rooftop_side", Arrays.asList(FormBlock.MATERIAL.ROCK, FormBlock.MATERIAL.METAL, FormBlock.MATERIAL.WOOD) );
        //FormBlock.RegistryForm(FormBlock.FORM.WOOD_ROOF, "wood_roof", Arrays.asList(FormBlock.MATERIAL.WOOD) );

        // Blocks
        BLOCKS.register("tiles_roof_border", () -> new cpRoofBorder(Blocks.RED_NETHER_BRICKS.defaultBlockState(), Block.Properties.copy(Blocks.RED_NETHER_BRICKS)));
        BLOCKS.register("thatch_roof_border", () -> new cpRoofBorder(Blocks.HAY_BLOCK.defaultBlockState(), Block.Properties.copy(Blocks.HAY_BLOCK)));
        BLOCKS.register("slates_roof_border", () -> new cpRoofBorder(Blocks.BLACKSTONE.defaultBlockState(), Block.Properties.copy(Blocks.BLACKSTONE)));
        BLOCKS.register("thatch_slab", () -> new SlabBlock(Block.Properties.copy(Blocks.HAY_BLOCK)));
        BLOCKS.register("slates_rooftop", () -> new cpRoofTop(Blocks.BLACKSTONE.defaultBlockState(), Block.Properties.copy(Blocks.BLACKSTONE)));
        BLOCKS.register("slates_rooftop_border", () -> new cpRoofTopBorder(Blocks.BLACKSTONE.defaultBlockState(), Block.Properties.copy(Blocks.BLACKSTONE)));
        BLOCKS.register("thatch_rooftop", () -> new cpRoofTop(Blocks.HAY_BLOCK.defaultBlockState(), Block.Properties.copy(Blocks.HAY_BLOCK)));
        BLOCKS.register("thatch_rooftop_border", () -> new cpRoofTopBorder(Blocks.HAY_BLOCK.defaultBlockState(), Block.Properties.copy(Blocks.HAY_BLOCK)));
        BLOCKS.register("tiles_rooftop", () -> new cpRoofTop(Blocks.RED_NETHER_BRICKS.defaultBlockState(), Block.Properties.copy(Blocks.RED_NETHER_BRICKS)));
        BLOCKS.register("tiles_rooftop_border", () -> new cpRoofTopBorder(Blocks.RED_NETHER_BRICKS.defaultBlockState(), Block.Properties.copy(Blocks.RED_NETHER_BRICKS)));

        // BlockEntities
    }

    public static ResourceLocation getBlockItemName(ResourceLocation location)
    {
        String szLocation = location.toString();
        szLocation = szLocation.replace("cubeplus:", "");

        if ( !szLocation.contains("_basic") )
            return location;

        if ( IsNewBasicStairs(szLocation.replace("_stairs_basic", "")) )
            return location;

        szLocation = szLocation.replace("_basic", "");
        szLocation = szLocation.replace("_planks", "");
        szLocation = szLocation.replace("bricks", "brick");
        szLocation = szLocation.replace("_block", "");
        szLocation = szLocation.replace("tiles", "tile");

        return new ResourceLocation("minecraft", szLocation);
    }

    // Blocks
    public static final RegistryObject<Block> STONE_STAIRS_4STEPS = BLOCKS.register("stone_stairs_4steps", () -> new cpStairs(Blocks.STONE.defaultBlockState(), Block.Properties.copy(Blocks.STONE) ));

    public static final RegistryObject<Block> ACACIA_PLANKS_WOOD_SIMPLE_WINDOW = BLOCKS.register("acacia_planks_wood_simple_window", () -> new cpWindows(Blocks.ACACIA_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.ACACIA_PLANKS)));
    public static final RegistryObject<Block> ACACIA_PLANKS_WOOD_SQUARE_WINDOW = BLOCKS.register("acacia_planks_wood_square_window", () -> new cpWindows(Blocks.ACACIA_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.ACACIA_PLANKS)));
    public static final RegistryObject<Block> BIRCH_PLANKS_WOOD_SIMPLE_WINDOW = BLOCKS.register("birch_planks_wood_simple_window", () -> new cpWindows(Blocks.BIRCH_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.BIRCH_PLANKS)));
    public static final RegistryObject<Block> BIRCH_PLANKS_WOOD_SQUARE_WINDOW = BLOCKS.register("birch_planks_wood_square_window", () -> new cpWindows(Blocks.BIRCH_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.BIRCH_PLANKS)));
    public static final RegistryObject<Block> DARK_OAK_PLANKS_WOOD_SIMPLE_WINDOW = BLOCKS.register("dark_oak_planks_wood_simple_window", () -> new cpWindows(Blocks.DARK_OAK_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.DARK_OAK_PLANKS)));
    public static final RegistryObject<Block> DARK_OAK_PLANKS_WOOD_SQUARE_WINDOW = BLOCKS.register("dark_oak_planks_wood_square_window", () -> new cpWindows(Blocks.DARK_OAK_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.DARK_OAK_PLANKS)));
    public static final RegistryObject<Block> JUNGLE_PLANKS_WOOD_SIMPLE_WINDOW = BLOCKS.register("jungle_planks_wood_simple_window", () -> new cpWindows(Blocks.JUNGLE_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.JUNGLE_PLANKS)));
    public static final RegistryObject<Block> JUNGLE_PLANKS_WOOD_SQUARE_WINDOW = BLOCKS.register("jungle_planks_wood_square_window", () -> new cpWindows(Blocks.JUNGLE_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.JUNGLE_PLANKS)));
    public static final RegistryObject<Block> OAK_PLANKS_WOOD_SIMPLE_WINDOW = BLOCKS.register("oak_planks_wood_simple_window", () -> new cpWindows(Blocks.OAK_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> OAK_PLANKS_WOOD_SQUARE_WINDOW = BLOCKS.register("oak_planks_wood_square_window", () -> new cpWindows(Blocks.OAK_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> SPRUCE_PLANKS_WOOD_SIMPLE_WINDOW = BLOCKS.register("spruce_planks_wood_simple_window", () -> new cpWindows(Blocks.SPRUCE_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.SPRUCE_PLANKS)));
    public static final RegistryObject<Block> SPRUCE_PLANKS_WOOD_SQUARE_WINDOW = BLOCKS.register("spruce_planks_wood_square_window", () -> new cpWindows(Blocks.SPRUCE_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.SPRUCE_PLANKS)));
    public static final RegistryObject<Block> CRIMSON_PLANKS_WOOD_SIMPLE_WINDOW = BLOCKS.register("crimson_planks_wood_simple_window", () -> new cpWindows(Blocks.CRIMSON_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.CRIMSON_PLANKS)));
    public static final RegistryObject<Block> CRIMSON_PLANKS_WOOD_SQUARE_WINDOW = BLOCKS.register("crimson_planks_wood_square_window", () -> new cpWindows(Blocks.CRIMSON_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.CRIMSON_PLANKS)));
    public static final RegistryObject<Block> WARPED_PLANKS_WOOD_SIMPLE_WINDOW = BLOCKS.register("warped_planks_wood_simple_window", () -> new cpWindows(Blocks.WARPED_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.WARPED_PLANKS)));
    public static final RegistryObject<Block> WARPED_PLANKS_WOOD_SQUARE_WINDOW = BLOCKS.register("warped_planks_wood_square_window", () -> new cpWindows(Blocks.WARPED_PLANKS.defaultBlockState(), Block.Properties.copy(Blocks.WARPED_PLANKS)));

    public static final RegistryObject<Block> NEWROOF_BLOCK = BLOCKS.register("new_roof", () -> new cpNewRoof(Block.Properties.copy(Blocks.BLACKSTONE)));
    public static final RegistryObject<BlockEntityType<cpNewRoofEntity>> NEWROOF_ENTITYBLOCK = BLOCK_ENTITIES.register("new_roof", () -> BlockEntityType.Builder.of(cpNewRoofEntity::new, NEWROOF_BLOCK.get()).build(null));
}
