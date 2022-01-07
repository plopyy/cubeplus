package com.plop.cubeplus.common.block;

import com.plop.cubeplus.common.property.HSide;
import com.plop.cubeplus.common.property.cpProperty;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;

public class cpRoofTopBorder extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

	public static final AABB ROOF_BORDER = new AABB(0.00f, 0.00f, 0.00f, 1.00f, 1.00f, 0.25f);

	private final Block modelBlock;
	private final BlockState modelState;

	public cpRoofTopBorder(BlockState state, Properties prop)
	{
		super(prop.noOcclusion());
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(FACING, Direction.NORTH)
		);
		this.modelBlock = state.getBlock();
		this.modelState = state;
	}

	public boolean useShapeForLightOcclusion(BlockState state) {
		return true;
	}

	private VoxelShape Rotate(BlockState state, AABB Step)
	{
		Direction d = state.getValue(FACING);

		AABB rotShape = Step;

		int rot = 0;
		if (d == Direction.EAST)
			rot = 1;
		else if (d == Direction.SOUTH)
			rot = 2;
		else if (d == Direction.WEST)
			rot = 3;

		for (int n = 0; n < rot; n++)
			rotShape = new AABB(1 - rotShape.minZ, rotShape.minY, rotShape.minX, 1 - rotShape.maxZ, rotShape.maxY, rotShape.maxX);

		return Shapes.create(rotShape);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context)
	{
		return Rotate(state, ROOF_BORDER);
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

