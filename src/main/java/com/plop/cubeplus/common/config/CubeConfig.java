package com.plop.cubeplus.common.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CubeConfig
{
    public static ForgeConfigSpec.BooleanValue Basic_Stairs_Enalble;


    public static void init(ForgeConfigSpec.Builder builder)
    {
        builder.comment("cubeplus config");
        Basic_Stairs_Enalble = builder
                .comment("Activate new basic stairs replacement/Active le remplacement des escaliers de base")
                .define("cubeplus.basic_stairs_enable", true);
    }
}
