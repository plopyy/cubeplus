package com.plop.cubeplus.common.recipe;

import com.plop.cubeplus.CubePlus;
import net.minecraft.resources.ResourceLocation;
//import net.minecraft.world.storage.loot.*;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber (modid = CubePlus.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class cpRecipes
{
    private static ResourceLocation grass = new ResourceLocation("minecraft", "blocks/grass");

    //@SubscribeEvent
    public static void registerLootTable(LootTableLoadEvent event)
    {
        if (!event.getName().equals(grass))
            return;

        //event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(CubePlus.MOD_ID, "blocks/granite_pillar_6s"))).build());
    }
}
