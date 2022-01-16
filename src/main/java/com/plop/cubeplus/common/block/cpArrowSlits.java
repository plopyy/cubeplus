package com.plop.cubeplus.common.block;

import com.plop.cubeplus.common.property.cpProperty;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;

public class cpArrowSlits extends Block
{
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty SNEAKING = cpProperty.SNEAKING;

	public static final AABB ARROW_SLIT1 = new AABB(0.00f, 0.00f, 0.00f, 1.00f, 0.125f, 1.00f);
	public static final AABB ARROW_SLIT2 = new AABB(0.00f, 0.125f, 0.00f, 0.3125f, 0.8125f, 1.00f);
	public static final AABB ARROW_SLIT3 = new AABB(0.6875f, 0.125f, 0.00f, 1.00f, 0.8125f, 1.00f);
	public static final AABB ARROW_SLIT4 = new AABB(0.00f, 0.8125f, 0.00f, 1.00f, 1.00f, 1.00f);

	public static final AABB ARROW_SLIT_ROUND1 = new AABB(0.00f, 0.00f, 0.00f, 1.00f, 0.125f, 1.00f);
	public static final AABB ARROW_SLIT_ROUND2 = new AABB(0.00f, 0.125f, 0.00f, 0.25f, 0.8125f, 1.00f);
	public static final AABB ARROW_SLIT_ROUND3 = new AABB(0.75f, 0.125f, 0.00f, 1.00f, 0.8125f, 1.00f);
	public static final AABB ARROW_SLIT_ROUND4 = new AABB(0.00f, 0.8125f, 0.00f, 1.00f, 1.00f, 1.00f);

	public static final AABB ARROW_SLIT_ROUND5 = new AABB(0.25f, 0.75f, 0.00f, 0.375f, 0.8125f, 1.00f);
	public static final AABB ARROW_SLIT_ROUND6 = new AABB(0.625f, 0.75f, 0.00f, 0.75f, 0.8125f, 1.00f);

	public static final AABB ARROW_SLIT_ROUND7 = new AABB(0.25f, 0.625f, 0.00f, 0.3125f, 0.75f, 1.00f);
	public static final AABB ARROW_SLIT_ROUND8 = new AABB(0.6875f, 0.625f, 0.00f, 0.75f, 0.75f, 1.00f);

	private final Block modelBlock;
	private final BlockState modelState;
	private final int SLIT_TYPE;

	public cpArrowSlits(int type, BlockState state, Properties prop)
	{
		super(prop.noOcclusion());
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(FACING, Direction.NORTH)
				.setValue(SNEAKING, Boolean.FALSE)
		);
		this.modelBlock = state.getBlock();
		this.modelState = state;
		this.SLIT_TYPE = type;
	}

	private VoxelShape Rotate(BlockState state, AABB Step)
	{
		Direction d = state.getValue(FACING);
		Boolean sneak = state.getValue(SNEAKING);

		AABB rotShape = Step;

		int rot = 0;
		if (d == Direction.EAST)
			rot = 1;
		else if (d == Direction.SOUTH)
			rot = 2;
		else if (d == Direction.WEST)
			rot = 3;

		if (sneak == Boolean.TRUE)
		{
			rotShape = new AABB(rotShape.minX, rotShape.minZ, rotShape.minY, rotShape.maxX, rotShape.maxZ, rotShape.maxY);
			rot += 2;
		}
		for (int n = 0; n < rot; n++)
			rotShape = new AABB(1 - rotShape.minZ, rotShape.minY, rotShape.minX, 1 - rotShape.maxZ, rotShape.maxY, rotShape.maxX);

		return Shapes.create(rotShape);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context)
	{
		if (this.SLIT_TYPE == 1)
		{
			VoxelShape shape1 = Shapes.joinUnoptimized(Rotate(state, ARROW_SLIT1), Rotate(state, ARROW_SLIT2), BooleanOp.NOT_SAME);
			VoxelShape shape2 = Shapes.joinUnoptimized(Rotate(state, ARROW_SLIT3), Rotate(state, ARROW_SLIT4), BooleanOp.NOT_SAME);
			return Shapes.joinUnoptimized(shape1, shape2, BooleanOp.NOT_SAME);
		}
		else
		{
			VoxelShape shape1 = Shapes.joinUnoptimized(Rotate(state, ARROW_SLIT_ROUND1), Rotate(state, ARROW_SLIT_ROUND2), BooleanOp.NOT_SAME);
			VoxelShape shape2 = Shapes.joinUnoptimized(Rotate(state, ARROW_SLIT_ROUND3), Rotate(state, ARROW_SLIT_ROUND4), BooleanOp.NOT_SAME);
			VoxelShape shape3 = Shapes.joinUnoptimized(Rotate(state, ARROW_SLIT_ROUND5), Rotate(state, ARROW_SLIT_ROUND6), BooleanOp.NOT_SAME);
			VoxelShape shape4 = Shapes.joinUnoptimized(Rotate(state, ARROW_SLIT_ROUND7), Rotate(state, ARROW_SLIT_ROUND8), BooleanOp.NOT_SAME);
			VoxelShape shape5 = Shapes.joinUnoptimized(shape1, shape2, BooleanOp.NOT_SAME);
			VoxelShape shape6 = Shapes.joinUnoptimized(shape3, shape4, BooleanOp.NOT_SAME);

			return Shapes.joinUnoptimized(shape5, shape6, BooleanOp.NOT_SAME);
		}
	}

	public BlockState getStateForPlacement(BlockPlaceContext context)
	{
		BlockPos blockpos = context.getClickedPos();
		Player player = context.getPlayer();
		Boolean sneak = Boolean.FALSE;

		if (player != null && player.isCrouching())
			sneak = Boolean.TRUE;

		BlockState blockstate = this.defaultBlockState()
				.setValue(FACING, context.getHorizontalDirection())
				.setValue(SNEAKING, sneak);

		return blockstate;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
	{
		builder.add(FACING, SNEAKING);
	}
}

