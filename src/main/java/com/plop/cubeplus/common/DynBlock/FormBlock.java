package com.plop.cubeplus.common.DynBlock;

import com.plop.cubeplus.common.RegistryHandler;
import com.plop.cubeplus.common.block.*;
import com.plop.cubeplus.common.config.CubeConfig;
import net.minecraft.world.level.block.Block;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FormBlock
{
    private static final Logger LOGGER = LogManager.getLogger();

    public enum MATERIAL { WOOD, ROCK, METAL }

    public enum FORM
    {
        PILLAR_1S,
        PILLAR_2S,
        PILLAR_4S,
        PILLAR_6S,
        PILLAR_10S,
        PILLAR_ROUND_6S,
        PILLAR_ROUND_8S,
        PILLAR_ROUND_12S,
        STAIRS_4STEPS,
        STAIRS_BASIC,
        BEAM,
        BEAM_SINGLE,
        BEAM_DOUBLE,
        BEAM_REINFORCED,
        BEAM_REINFORCED_SINGLE,
        BEAM_REINFORCED_DOUBLE,
        ARROW_SLIT,
        ARROW_SLIT_ROUNDED,
        SLATES_ROOF,
        THATCH_ROOF,
        TILES_ROOF,
        WOOD_ROOF,
        SLATES_ROOFTOP_SIDE,
        THATCH_ROOFTOP_SIDE,
        TILES_ROOFTOP_SIDE
    }

    public static void RegistryForm(FORM nForm, String szName, List<MATERIAL> Mats)
    {
        List<FormMat> Materials = new ArrayList<>();

        for ( int i = 0; i < Mats.size(); i++ )
            Materials.addAll(FormMats.GetList(Mats.get(i)));

        for ( int n = 0; n < Materials.size(); n++ )
        {
            String RegistryName = Materials.get(n).NAME + "_" + szName;
            Block BlockMat = Materials.get(n).BLOCK_MAT;

            switch(nForm)
            {
                case PILLAR_1S:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpPillar(1, Block.Properties.copy(BlockMat)));
                    break;
                case PILLAR_2S:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpPillar(2, Block.Properties.copy(BlockMat)));
                    break;
                case PILLAR_4S:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpPillar(4, Block.Properties.copy(BlockMat)));
                    break;
                case PILLAR_6S:
                case PILLAR_ROUND_6S:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpPillar(6, Block.Properties.copy(BlockMat)));
                    break;
                case PILLAR_ROUND_8S:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpPillar(8, Block.Properties.copy(BlockMat)));
                    break;
                case PILLAR_10S:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpPillar(10, Block.Properties.copy(BlockMat)));
                    break;
                case PILLAR_ROUND_12S:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpPillar(12, Block.Properties.copy(BlockMat)));
                    break;
                case STAIRS_4STEPS:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpStairs(BlockMat.defaultBlockState(), Block.Properties.copy(BlockMat)));
                    break;
                case STAIRS_BASIC:
                    if (CubeConfig.Basic_Stairs_Enalble.get() || IsNewBasicStairs(Materials.get(n).NAME) )
                        RegistryHandler.BLOCKS.register(RegistryName, () -> new cpBasicStairs(BlockMat.defaultBlockState(), Block.Properties.copy(BlockMat)));
                    break;
                case BEAM:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpBeamSupport(1, BlockMat.defaultBlockState(), Block.Properties.copy(BlockMat)));
                    break;
                case BEAM_SINGLE:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpBeamSupport(2, BlockMat.defaultBlockState(), Block.Properties.copy(BlockMat)));
                    break;
                case BEAM_DOUBLE:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpBeamSupport(3, BlockMat.defaultBlockState(), Block.Properties.copy(BlockMat)));
                    break;
                case BEAM_REINFORCED:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpBeamSupport2(1, BlockMat.defaultBlockState(), Block.Properties.copy(BlockMat)));
                    break;
                case BEAM_REINFORCED_SINGLE:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpBeamSupport2(2, BlockMat.defaultBlockState(), Block.Properties.copy(BlockMat)));
                    break;
                case BEAM_REINFORCED_DOUBLE:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpBeamSupport2(3, BlockMat.defaultBlockState(), Block.Properties.copy(BlockMat)));
                    break;
                case ARROW_SLIT:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpArrowSlits(1, BlockMat.defaultBlockState(), Block.Properties.copy(BlockMat)));
                    break;
                case ARROW_SLIT_ROUNDED:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpArrowSlits(2, BlockMat.defaultBlockState(), Block.Properties.copy(BlockMat)));
                    break;
                case SLATES_ROOF:
                case THATCH_ROOF:
                case TILES_ROOF:
                case WOOD_ROOF:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpRoof(BlockMat.defaultBlockState(), Block.Properties.copy(BlockMat)));
                    break;
                case SLATES_ROOFTOP_SIDE:
                case THATCH_ROOFTOP_SIDE:
                case TILES_ROOFTOP_SIDE:
                    RegistryHandler.BLOCKS.register(RegistryName, () -> new cpRoofTopSide(BlockMat.defaultBlockState(), Block.Properties.copy(BlockMat)));
                    break;
            }
        }
    }

    public static Boolean IsNewBasicStairs(String Material)
    {
        if (Objects.equals(Material, "iron_block")
            || Objects.equals(Material, "gold_block")
            || Objects.equals(Material, "end_stone")
            || Objects.equals(Material, "quartz_bricks")
            || Objects.equals(Material, "smooth_stone")
            || Objects.equals(Material, "thatch")
            || Objects.equals(Material, "deepslate")
            || Objects.equals(Material, "cracked_deepslate_bricks")
            || Objects.equals(Material, "cracked_deepslate_tiles")
            || Objects.equals(Material, "chiseled_deepslate")
            || Objects.equals(Material, "amethyst_block")
            || Objects.equals(Material, "dripstone_block")
            || Objects.equals(Material, "tuff")
            || Objects.equals(Material, "calcite")
            || Objects.equals(Material, "netherite_block")
            || Objects.equals(Material, "raw_gold_block")
            || Objects.equals(Material, "raw_copper_block")
            || Objects.equals(Material, "waxed_copper_block")
            || Objects.equals(Material, "waxed_exposed_copper")
            || Objects.equals(Material, "waxed_weathered_copper")
            || Objects.equals(Material, "waxed_oxidized_copper")
        )
            return Boolean.TRUE;

        return Boolean.FALSE;
    }
}
