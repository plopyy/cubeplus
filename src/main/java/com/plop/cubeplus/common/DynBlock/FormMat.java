package com.plop.cubeplus.common.DynBlock;

import net.minecraft.world.level.block.Block;

public class FormMat
{
    public String NAME;
    public FormBlock.MATERIAL MATERIAL;
    public Block BLOCK_MAT;

    public FormMat(String szName, FormBlock.MATERIAL Mat, Block BlockMat)
    {
        NAME = szName;
        MATERIAL = Mat;
        BLOCK_MAT = BlockMat;
    }
}
