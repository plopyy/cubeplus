package com.plop.cubeplus.common.DynBlock;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public class FormMat
{
    public String NAME;
    public FormBlock.MATERIAL MATERIAL;
    public Block BLOCK_MAT;
    public ResourceLocation TEXTURE;

    public FormMat(String szName, FormBlock.MATERIAL Mat, Block BlockMat)
    {
        NAME = szName;
        MATERIAL = Mat;
        BLOCK_MAT = BlockMat;
        TEXTURE = new ResourceLocation( "assets/minecraft/textures/block/"+szName+".png" );
    }
}
