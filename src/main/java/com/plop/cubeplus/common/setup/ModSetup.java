package com.plop.cubeplus.common.setup;

import com.plop.cubeplus.CubePlus;
import com.plop.cubeplus.common.RegistryHandler;
import com.plop.cubeplus.common.renderer.cpNewRoofRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber (modid = CubePlus.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup
{
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(ModModelLayers.NEW_ROOF, cpNewRoofRenderer::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerBlockEntityRenderer(RegistryHandler.NEWROOF_ENTITYBLOCK.get(), cpNewRoofRenderer::new);
    }
}
