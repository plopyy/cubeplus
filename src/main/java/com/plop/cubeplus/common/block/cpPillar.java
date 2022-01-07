package com.plop.cubeplus.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;

public class cpPillar extends Block implements SimpleWaterloggedBlock
{
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private int PILLAR_SIZE = 0;
	public static final AABB PILLAR_1S = new AABB(0.46875f, 0.00f, 0.46875f, 0.53125f, 1.00f, 0.53125f);
	public static final AABB PILLAR_2S = new AABB(0.4375f, 0.00f, 0.4375f, 0.5625f, 1.00f, 0.5625f);
	public static final AABB PILLAR_4S = new AABB(0.375f, 0.00f, 0.375f, 0.625f, 1.00f, 0.625f);
	public static final AABB PILLAR_6S = new AABB(0.3125f, 0.00f, 0.3125f, 0.6875f, 1.00f, 0.6875f);
	public static final AABB PILLAR_8S = new AABB(0.25f, 0.00f, 0.25f, 0.75f, 1.00f, 0.75f);
	public static final AABB PILLAR_10S = new AABB(0.1875f, 0.00f, 0.1875f, 0.8125f, 1.00f, 0.8125f);
	public static final AABB PILLAR_12S = new AABB(0.125f, 0.00f, 0.125f, 0.875f, 1.00f, 0.875f);

	public cpPillar(int nSize, Properties prop)
    {
        super(prop);
		this.registerDefaultState(this.defaultBlockState()
				.setValue(AXIS, Direction.Axis.Y)
				.setValue(WATERLOGGED, Boolean.FALSE)
		);
        PILLAR_SIZE = nSize;
    }

	private VoxelShape Rotate(Direction.Axis Axis, AABB Step )
	{
		if (Axis == Direction.Axis.Z)
			return Shapes.create(new AABB(Step.minX, Step.minZ, Step.minY, Step.maxX, Step.maxZ, Step.maxY));
		else if (Axis == Direction.Axis.X)
			return Shapes.create(new AABB(Step.minY, Step.minX, Step.minZ, Step.maxY, Step.maxX, Step.maxZ));
		else
			return Shapes.create(Step);
	}

	public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context)
	{
		switch (PILLAR_SIZE)
		{
			case 1:
				return Rotate( state.getValue(AXIS), PILLAR_1S );
			case 2:
				return Rotate( state.getValue(AXIS), PILLAR_2S );
			case 4:
				return Rotate( state.getValue(AXIS), PILLAR_4S );
			case 6:
				return Rotate( state.getValue(AXIS), PILLAR_6S );
			case 8:
				return Rotate( state.getValue(AXIS), PILLAR_8S );
			case 10:
				return Rotate( state.getValue(AXIS), PILLAR_10S );
			case 12:
				return Rotate( state.getValue(AXIS), PILLAR_12S );
			default: case 0:
				return null;
		}
	}

	public BlockState rotate(BlockState state, Rotation rot)
	{
		switch(rot) {
			case COUNTERCLOCKWISE_90:
			case CLOCKWISE_90:
				switch((Direction.Axis)state.getValue(AXIS))
				{
					case X:
						return state.setValue(AXIS, Direction.Axis.Z);
					case Z:
						return state.setValue(AXIS, Direction.Axis.X);
					default:
						return state;
				}
			default:
				return state;
		}
	}

	public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (stateIn.getValue(WATERLOGGED)) {
			worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
		}

		return facing.getAxis().isHorizontal() ? stateIn : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(AXIS, WATERLOGGED);
	}

	public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		BlockPos blockpos = context.getClickedPos();
		FluidState ifluidstate = context.getLevel().getFluidState(blockpos);
		return this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis()).setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER);
	}
}
