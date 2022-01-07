package com.plop.cubeplus.common.blockentity;

import com.plop.cubeplus.common.RegistryHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class cpNewRoofEntity extends BlockEntity
{
    public cpNewRoofEntity(BlockPos pos, BlockState state)
    {
        super(RegistryHandler.NEWROOF_ENTITYBLOCK.get(), pos, state);
    }
}
