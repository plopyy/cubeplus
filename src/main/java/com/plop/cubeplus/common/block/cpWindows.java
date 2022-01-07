package com.plop.cubeplus.common.block;

import com.plop.cubeplus.common.property.cpProperty;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;

public class cpWindows extends Block {
	public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
	public static final BooleanProperty SNEAKING = cpProperty.SNEAKING;

	public static final AABB WINDOW = new AABB(0.00f, 0.00f, 0.40f, 1.00f, 1.00f, 0.60f);

	private final Block modelBlock;
	private final BlockState modelState;

	public cpWindows(BlockState state, Properties prop)
	{
		super(prop.noOcclusion());
		this.registerDefaultState(this.stateDefinition.any()
				.setValue(FACING, Direction.NORTH)
				.setValue(SNEAKING, Boolean.FALSE)
		);
		this.modelBlock = state.getBlock();
		this.modelState = state;
	}

	private VoxelShape Rotate(BlockState state, AABB Step)
	{
		Direction d = state.getValue(FACING);
		Boolean sneak = state.getValue(SNEAKING);

		AABB rotShape = Step;

		if (sneak == Boolean.TRUE)
		{
			rotShape = new AABB(rotShape.minX, rotShape.minZ, rotShape.minY, rotShape.maxX, rotShape.maxZ, rotShape.maxY);
		}
		else
		{
			int rot = 0;
			if (d == Direction.EAST)
				rot = 1;
			else if (d == Direction.SOUTH)
				rot = 2;
			else if (d == Direction.WEST)
				rot = 3;

			for (int n = 0; n < rot; n++)
				rotShape = new AABB(1 - rotShape.minZ, rotShape.minY, rotShape.minX, 1 - rotShape.maxZ, rotShape.maxY, rotShape.maxX);
		}

		return Shapes.create(rotShape);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context)
	{
		return Rotate(state, WINDOW);
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

