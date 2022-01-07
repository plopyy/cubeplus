package com.plop.cubeplus.common.DynBlock;

import com.plop.cubeplus.CubePlus;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

import java.util.ArrayList;
import java.util.List;

public class FormMats
{
    public static List<FormMat> Materials = new ArrayList<>();

    public static void Init()
    {
        Materials.clear();

        // Rocks
        Materials.add(new FormMat("andesite", FormBlock.MATERIAL.ROCK, Blocks.ANDESITE));
        Materials.add(new FormMat("bricks", FormBlock.MATERIAL.ROCK, Blocks.BRICKS));
        Materials.add(new FormMat("cobblestone", FormBlock.MATERIAL.ROCK, Blocks.COBBLESTONE));
        Materials.add(new FormMat("diorite", FormBlock.MATERIAL.ROCK, Blocks.DIORITE));
        Materials.add(new FormMat("granite", FormBlock.MATERIAL.ROCK, Blocks.GRANITE));
        Materials.add(new FormMat("mossy_cobblestone", FormBlock.MATERIAL.ROCK, Blocks.MOSSY_COBBLESTONE));
        Materials.add(new FormMat("mossy_stone_bricks", FormBlock.MATERIAL.ROCK, Blocks.MOSSY_STONE_BRICKS));
        Materials.add(new FormMat("nether_bricks", FormBlock.MATERIAL.ROCK, Blocks.NETHER_BRICKS));
        Materials.add(new FormMat("polished_andesite", FormBlock.MATERIAL.ROCK, Blocks.POLISHED_ANDESITE));
        Materials.add(new FormMat("polished_diorite", FormBlock.MATERIAL.ROCK, Blocks.POLISHED_DIORITE));
        Materials.add(new FormMat("polished_granite", FormBlock.MATERIAL.ROCK, Blocks.POLISHED_GRANITE));
        Materials.add(new FormMat("quartz_block", FormBlock.MATERIAL.ROCK, Blocks.QUARTZ_BLOCK));
        Materials.add(new FormMat("red_nether_bricks", FormBlock.MATERIAL.ROCK, Blocks.RED_NETHER_BRICKS));
        Materials.add(new FormMat("red_sandstone", FormBlock.MATERIAL.ROCK, Blocks.RED_SANDSTONE));
        Materials.add(new FormMat("smooth_quartz", FormBlock.MATERIAL.ROCK, Blocks.SMOOTH_QUARTZ));
        Materials.add(new FormMat("smooth_red_sandstone", FormBlock.MATERIAL.ROCK, Blocks.SMOOTH_RED_SANDSTONE));
        Materials.add(new FormMat("smooth_sandstone", FormBlock.MATERIAL.ROCK, Blocks.SMOOTH_SANDSTONE));
        Materials.add(new FormMat("smooth_stone", FormBlock.MATERIAL.ROCK, Blocks.SMOOTH_STONE));
        Materials.add(new FormMat("stone_bricks", FormBlock.MATERIAL.ROCK, Blocks.STONE_BRICKS));
        Materials.add(new FormMat("stone", FormBlock.MATERIAL.ROCK, Blocks.STONE));
        Materials.add(new FormMat("sandstone", FormBlock.MATERIAL.ROCK, Blocks.SANDSTONE));
        Materials.add(new FormMat("prismarine", FormBlock.MATERIAL.ROCK, Blocks.PRISMARINE));
        Materials.add(new FormMat("prismarine_bricks", FormBlock.MATERIAL.ROCK, Blocks.PRISMARINE_BRICKS));
        Materials.add(new FormMat("dark_prismarine", FormBlock.MATERIAL.ROCK, Blocks.DARK_PRISMARINE));
        Materials.add(new FormMat("purpur_block", FormBlock.MATERIAL.ROCK, Blocks.PURPUR_BLOCK));
        Materials.add(new FormMat("end_stone", FormBlock.MATERIAL.ROCK, Blocks.END_STONE));
        Materials.add(new FormMat("end_stone_bricks", FormBlock.MATERIAL.ROCK, Blocks.END_STONE_BRICKS));
        Materials.add(new FormMat("blackstone", FormBlock.MATERIAL.ROCK, Blocks.BLACKSTONE)); //
        Materials.add(new FormMat("polished_blackstone", FormBlock.MATERIAL.ROCK, Blocks.POLISHED_BLACKSTONE));
        Materials.add(new FormMat("polished_blackstone_bricks", FormBlock.MATERIAL.ROCK, Blocks.POLISHED_BLACKSTONE_BRICKS));
        Materials.add(new FormMat("quartz_bricks", FormBlock.MATERIAL.ROCK, Blocks.QUARTZ_BRICKS));
        Materials.add(new FormMat("thatch", FormBlock.MATERIAL.ROCK, Blocks.HAY_BLOCK));

        Materials.add(new FormMat("deepslate", FormBlock.MATERIAL.ROCK, Blocks.DEEPSLATE));
        Materials.add(new FormMat("cobbled_deepslate", FormBlock.MATERIAL.ROCK, Blocks.COBBLED_DEEPSLATE));
        Materials.add(new FormMat("polished_deepslate", FormBlock.MATERIAL.ROCK, Blocks.POLISHED_DEEPSLATE));
        Materials.add(new FormMat("deepslate_bricks", FormBlock.MATERIAL.ROCK, Blocks.DEEPSLATE_BRICKS));
        Materials.add(new FormMat("cracked_deepslate_bricks", FormBlock.MATERIAL.ROCK, Blocks.CRACKED_DEEPSLATE_BRICKS));
        Materials.add(new FormMat("deepslate_tiles", FormBlock.MATERIAL.ROCK, Blocks.DEEPSLATE_TILES));
        Materials.add(new FormMat("cracked_deepslate_tiles", FormBlock.MATERIAL.ROCK, Blocks.CRACKED_DEEPSLATE_TILES));
        Materials.add(new FormMat("chiseled_deepslate", FormBlock.MATERIAL.ROCK, Blocks.CHISELED_DEEPSLATE));
        Materials.add(new FormMat("amethyst_block", FormBlock.MATERIAL.ROCK, Blocks.AMETHYST_BLOCK));
        Materials.add(new FormMat("dripstone_block", FormBlock.MATERIAL.ROCK, Blocks.DRIPSTONE_BLOCK));
        Materials.add(new FormMat("tuff", FormBlock.MATERIAL.ROCK, Blocks.TUFF));
        Materials.add(new FormMat("calcite", FormBlock.MATERIAL.ROCK, Blocks.CALCITE));

        // Metals
        Materials.add(new FormMat("iron_block", FormBlock.MATERIAL.METAL, Blocks.IRON_BLOCK));
        Materials.add(new FormMat("gold_block", FormBlock.MATERIAL.METAL, Blocks.GOLD_BLOCK));

        Materials.add(new FormMat("netherite_block", FormBlock.MATERIAL.METAL, Blocks.NETHERITE_BLOCK));
        Materials.add(new FormMat("raw_gold_block", FormBlock.MATERIAL.METAL, Blocks.RAW_GOLD_BLOCK));
        Materials.add(new FormMat("raw_copper_block", FormBlock.MATERIAL.METAL, Blocks.RAW_COPPER_BLOCK));
        Materials.add(new FormMat("waxed_copper_block", FormBlock.MATERIAL.METAL, Blocks.WAXED_COPPER_BLOCK));
        Materials.add(new FormMat("waxed_exposed_copper", FormBlock.MATERIAL.METAL, Blocks.WAXED_EXPOSED_COPPER));
        Materials.add(new FormMat("waxed_weathered_copper", FormBlock.MATERIAL.METAL, Blocks.WAXED_WEATHERED_COPPER));
        Materials.add(new FormMat("waxed_oxidized_copper", FormBlock.MATERIAL.METAL, Blocks.WAXED_OXIDIZED_COPPER));
        Materials.add(new FormMat("waxed_cut_copper", FormBlock.MATERIAL.METAL, Blocks.WAXED_CUT_COPPER));
        Materials.add(new FormMat("waxed_exposed_cut_copper", FormBlock.MATERIAL.METAL, Blocks.WAXED_EXPOSED_CUT_COPPER));
        Materials.add(new FormMat("waxed_weathered_cut_copper", FormBlock.MATERIAL.METAL, Blocks.WAXED_WEATHERED_CUT_COPPER));
        Materials.add(new FormMat("waxed_oxidized_cut_copper", FormBlock.MATERIAL.METAL, Blocks.WAXED_OXIDIZED_CUT_COPPER));


        // Woods
        Materials.add(new FormMat("acacia_planks", FormBlock.MATERIAL.WOOD, Blocks.ACACIA_PLANKS));
        Materials.add(new FormMat("birch_planks", FormBlock.MATERIAL.WOOD, Blocks.BIRCH_PLANKS));
        Materials.add(new FormMat("dark_oak_planks", FormBlock.MATERIAL.WOOD, Blocks.DARK_OAK_PLANKS));
        Materials.add(new FormMat("jungle_planks", FormBlock.MATERIAL.WOOD, Blocks.JUNGLE_PLANKS));
        Materials.add(new FormMat("oak_planks", FormBlock.MATERIAL.WOOD, Blocks.OAK_PLANKS));
        Materials.add(new FormMat("spruce_planks", FormBlock.MATERIAL.WOOD, Blocks.SPRUCE_PLANKS));
        Materials.add(new FormMat("crimson_planks", FormBlock.MATERIAL.WOOD, Blocks.CRIMSON_PLANKS));
        Materials.add(new FormMat("warped_planks", FormBlock.MATERIAL.WOOD, Blocks.WARPED_PLANKS));
    }

    public static List<FormMat> GetList(FormBlock.MATERIAL Mat)
    {
        List<FormMat> Mats = new ArrayList<>();

        for ( int i = 0; i < Materials.size(); i++ )
        {
            if (Mat == Materials.get(i).MATERIAL)
                Mats.add(Materials.get(i));
        }

        return Mats;
    }
}
