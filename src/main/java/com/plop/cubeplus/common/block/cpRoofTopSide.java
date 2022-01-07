package com.plop.cubeplus.common.block;

import com.plop.cubeplus.common.property.HSide;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class cpRoofTopSide extends Block
{
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public static final AABB ROOF_TOP = new AABB(0.00f, 0.00f, 0.00f, 1.00f, 0.5f, 1.00f);

	private final Block modelBlock;
	private final BlockState modelState;

	public cpRoofTopSide(BlockState state, Properties prop)
	{
		super(prop.noOcclusion());
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(FACING, Direction.NORTH)
		);
		this.modelBlock = state.getBlock();
		this.modelState = state;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context)
	{
		return Shapes.create(ROOF_TOP);
	}

	public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(FACING);
	}
}

