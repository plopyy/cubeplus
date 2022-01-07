package com.plop.cubeplus.common.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.plop.cubeplus.CubePlus;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

import java.io.File;

@Mod.EventBusSubscriber
public class Config
{
    private static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec config;

    static
    {
        CubeConfig.init(builder);

        config = builder.build();
    }

    public static void loadConfig(ForgeConfigSpec config, String path)
    {
        CubePlus.LOGGER.info("Loading config: " + path);
        final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        CubePlus.LOGGER.info("Build config: " + path);
        file.load();
        CubePlus.LOGGER.info("Loaded config: " + path);
        config.setConfig(file);
    }
}
