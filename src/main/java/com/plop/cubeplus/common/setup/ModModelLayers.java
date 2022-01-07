package com.plop.cubeplus.common.setup;

import com.plop.cubeplus.CubePlus;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers
{
    public static final ModelLayerLocation NEW_ROOF = register("new_roof");

    private static ModelLayerLocation register( String str )
    {
        return register(str, "main");
    }

    private static ModelLayerLocation register( String str, String str2 )
    {
        return new ModelLayerLocation(new ResourceLocation(CubePlus.MOD_ID, str), str2);
    }

}