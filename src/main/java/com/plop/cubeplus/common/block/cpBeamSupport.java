package com.plop.cubeplus.common.block;

import com.plop.cubeplus.common.property.HSide;
import com.plop.cubeplus.common.property.cpProperty;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class cpBeamSupport extends Block
{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final BooleanProperty SNEAKING = cpProperty.SNEAKING;
    public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
    public static final EnumProperty<HSide> HSIDE = cpProperty.HSIDE;
    public static final BooleanProperty CENTERED = cpProperty.CENTERED;
    private int BEAM_TYPE = 0;

    public static final AABB BEAM_RIGHT = new AABB(0.75f, 0.00f, 0.00f, 1.00f, 1.00f, 0.25f);
    public static final AABB TOP1_RIGHT = new AABB(0.00f, 0.75f, 0.00f, 0.75f, 1.00f, 0.25f);
    public static final AABB TOP2_RIGHT = new AABB(0.00f, 0.95f, 0.00f, 0.75f, 1.00f, 0.25f);

    public static final AABB BEAM_LEFT = new AABB(0.00f, 0.00f, 0.00f, 0.25f, 1.00f, 0.25f);
    public static final AABB TOP1_LEFT = new AABB(0.25f, 0.75f, 0.00f, 1.00f, 1.00f, 0.25f);
    public static final AABB TOP2_LEFT = new AABB(0.25f, 0.95f, 0.00f, 1.00f, 1.00f, 0.25f);

    public static final AABB TOP3_RIGHT = new AABB(0.75f, 0.75f, 0.25f, 1.00f, 1.00f, 1.00f);
    public static final AABB TOP3_LEFT = new AABB(0.00f, 0.75f, 0.25f, 0.25f, 1.00f, 1.00f);

    public static final AABB TOP4_RIGHT = new AABB(0.75f, 0.95f, 0.25f, 1.00f, 1.00f, 1.00f);
    public static final AABB TOP4_LEFT = new AABB(0.00f, 0.95f, 0.25f, 0.25f, 1.00f, 1.00f);

    public static final AABB TOP = new AABB(0.00f, 0.75f, 0.00f, 1.00f, 1.00f, 0.25f);
    public static final AABB TOP2 = new AABB(0.00f, 0.95f, 0.00f, 1.00f, 1.00f, 0.25f);

    public static final AABB BEAM_CENTER = new AABB(0.375f, 0.00f, 0.375f, 0.625f, 1.00f, 0.625f);
    public static final AABB BEAM_CENTER2 = new AABB(0.00f, 0.75f, 0.375f, 1.00f, 1.00f, 0.625f);
    public static final AABB CENTER_LEFT = new AABB(0.625f, 0.75f, 0.375f, 1.00f, 1.00f, 0.625f);
    public static final AABB CENTER_RIGHT = new AABB(0.00f, 0.75f, 0.375f, 0.375f, 1.00f, 0.625f);
    public static final AABB CENTER_BACK = new AABB(0.375f, 0.75f, 0.625f, 0.625f, 1.00f, 1.00f);
    public static final AABB CENTER_RIGHT2 = new AABB(0.625f, 0.95f, 0.375f, 1.00f, 1.00f, 0.625f);
    public static final AABB CENTER_LEFT2 = new AABB(0.00f, 0.95f, 0.375f, 0.375f, 1.00f, 0.625f);
    public static final AABB CENTER_BACK2 = new AABB(0.375f, 0.95f, 0.625f, 0.625f, 1.00f, 1.00f);

    private final Block modelBlock;
    private final BlockState modelState;

    public cpBeamSupport(int nType, BlockState state, Properties prop)
    {
        super(prop);

        BEAM_TYPE = nType;

        BlockState newState = this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, Boolean.FALSE)
                .setValue(SNEAKING, Boolean.FALSE)
                .setValue(HALF, Half.BOTTOM)
                .setValue(HSIDE, HSide.LEFT)
                .setValue(CENTERED, Boolean.FALSE);

        this.registerDefaultState(newState);
        this.modelBlock = state.getBlock();
        this.modelState = state;
    }

    private VoxelShape Rotate(BlockState state, AABB Step, Boolean Render)
    {
        Direction d     = state.getValue(FACING);
        Boolean sneak   = state.getValue(SNEAKING);
        Half h          = state.getValue(HALF);
        Boolean center  = state.getValue(CENTERED);

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
            if (center == Boolean.FALSE)
            {
                if (BEAM_TYPE == 1 && h == Half.BOTTOM)
                    rotShape = new AABB(1 - rotShape.minY, 1 - rotShape.minX, rotShape.minZ, 1 - rotShape.maxY, 1 - rotShape.maxX, rotShape.maxZ);
                else if (BEAM_TYPE == 1 && h == Half.TOP && Render == Boolean.FALSE)
                    rotShape = TOP;
                else if (BEAM_TYPE == 1 && h == Half.TOP && Render == Boolean.TRUE)
                    rotShape = TOP2;
                else if (BEAM_TYPE >= 2)
                    rotShape = new AABB(rotShape.minX, 1 - rotShape.minY, rotShape.minZ, rotShape.maxX, 1 - rotShape.maxY, rotShape.maxZ);
            }
            else
            {
                if (BEAM_TYPE == 1)
                {
                    rotShape = BEAM_CENTER2;

                    if (h == Half.TOP)
                        rotShape = new AABB(rotShape.minX, 1 - rotShape.minY, rotShape.minZ, rotShape.maxX, 1 - rotShape.maxY, rotShape.maxZ);
                }

                rotShape = new AABB(rotShape.minX, 1 - rotShape.minY, rotShape.minZ, rotShape.maxX, 1 - rotShape.maxY, rotShape.maxZ);
            }
        }

        for (int n = 0; n < rot; n++)
            rotShape = new AABB(1 - rotShape.minZ, rotShape.minY, rotShape.minX, 1 - rotShape.maxZ, rotShape.maxY, rotShape.maxX);

        return Shapes.create(rotShape);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context)
    {
        Boolean center = state.getValue(CENTERED);
        HSide side = state.getValue(HSIDE);

        if (center == Boolean.FALSE)
        {
            if (BEAM_TYPE >= 2)
            {
                VoxelShape shape;

                if (side == HSide.RIGHT) // Right
                    shape = Shapes.joinUnoptimized(Rotate(state, BEAM_RIGHT, Boolean.FALSE), Rotate(state, TOP1_RIGHT, Boolean.FALSE), BooleanOp.NOT_SAME);
                else // left
                    shape = Shapes.joinUnoptimized(Rotate(state, BEAM_LEFT, Boolean.FALSE), Rotate(state, TOP1_LEFT, Boolean.FALSE), BooleanOp.NOT_SAME);

                if (BEAM_TYPE == 3 && side == HSide.RIGHT)
                    shape = Shapes.joinUnoptimized(shape, Rotate(state, TOP3_RIGHT, Boolean.FALSE), BooleanOp.NOT_SAME);
                else if (BEAM_TYPE == 3 && side == HSide.LEFT)
                    shape = Shapes.joinUnoptimized(shape, Rotate(state, TOP3_LEFT, Boolean.FALSE), BooleanOp.NOT_SAME);

                return shape;
            }
            else
                return Rotate(state, BEAM_RIGHT, Boolean.FALSE);
        }
        else
        {
            if (BEAM_TYPE >= 2)
            {
                VoxelShape shape;

                if (side == HSide.RIGHT) // Right
                    shape = Shapes.joinUnoptimized(Shapes.create(BEAM_CENTER), Rotate(state, CENTER_RIGHT, Boolean.FALSE), BooleanOp.NOT_SAME);
                else // left
                    shape = Shapes.joinUnoptimized(Shapes.create(BEAM_CENTER), Rotate(state, CENTER_LEFT, Boolean.FALSE), BooleanOp.NOT_SAME);

                if (BEAM_TYPE == 3)
                    shape = Shapes.joinUnoptimized(shape, Rotate(state, CENTER_BACK, Boolean.FALSE), BooleanOp.NOT_SAME);

                return shape;
            }
            else
                return Rotate(state, BEAM_CENTER, Boolean.FALSE);
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context)
    {
        Boolean center = state.getValue(CENTERED);
        HSide side = state.getValue(HSIDE);

        if (center == Boolean.FALSE)
        {
            if (BEAM_TYPE >= 2)
            {
                VoxelShape shape;

                if (side == HSide.RIGHT) // Right
                    shape = Shapes.joinUnoptimized(Rotate(state, BEAM_RIGHT, Boolean.TRUE), Rotate(state, TOP2_RIGHT, Boolean.TRUE), BooleanOp.NOT_SAME);
                else // left
                    shape = Shapes.joinUnoptimized(Rotate(state, BEAM_LEFT, Boolean.TRUE), Rotate(state, TOP2_LEFT, Boolean.TRUE), BooleanOp.NOT_SAME);

                if (BEAM_TYPE == 3 && side == HSide.RIGHT)
                    shape = Shapes.joinUnoptimized(shape, Rotate(state, TOP4_RIGHT, Boolean.TRUE), BooleanOp.NOT_SAME);
                if (BEAM_TYPE == 3 && side == HSide.LEFT)
                    shape = Shapes.joinUnoptimized(shape, Rotate(state, TOP4_LEFT, Boolean.TRUE), BooleanOp.NOT_SAME);

                return shape;
            }
            else
                return Rotate(state, BEAM_RIGHT, Boolean.TRUE);
        }
        else
        {
            if (BEAM_TYPE >= 2)
            {
                VoxelShape shape;

                if (side == HSide.RIGHT) // Right
                    shape = Shapes.joinUnoptimized(Shapes.create(BEAM_CENTER), Rotate(state, CENTER_RIGHT2, Boolean.TRUE), BooleanOp.NOT_SAME);
                else // left
                    shape = Shapes.joinUnoptimized(Shapes.create(BEAM_CENTER), Rotate(state, CENTER_LEFT2, Boolean.TRUE), BooleanOp.NOT_SAME);

                if (BEAM_TYPE == 3)
                    shape = Shapes.joinUnoptimized(shape, Rotate(state, CENTER_BACK2, Boolean.TRUE), BooleanOp.NOT_SAME);

                return shape;
            }
            else
                return Rotate(state, BEAM_CENTER, Boolean.TRUE);
        }
    }

    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        BlockPos blockpos   = context.getClickedPos();
        Player player = context.getPlayer();
        Boolean sneak       = Boolean.FALSE;
        Half h              = Half.BOTTOM;
        HSide hside         = getHSideDirection(context);
        Direction direction = context.getClickedFace();

        if (player != null && player.isCrouching())
        {
            sneak = Boolean.TRUE;
            h = direction != Direction.DOWN && (direction == Direction.UP || !(context.getClickLocation().y - (double)blockpos.getY() > 0.5D)) ? Half.BOTTOM : Half.TOP;
        }

        FluidState ifluidstate = context.getLevel().getFluidState(blockpos);
        BlockState blockstate = this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection())
                .setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER)
                .setValue(SNEAKING, sneak)
                .setValue(HALF, h)
                .setValue(HSIDE, hside)
                .setValue(CENTERED, Boolean.FALSE);

        return blockstate;
    }

    private static HSide getHSideDirection(BlockPlaceContext context)
    {
        Direction direction = context.getClickedFace();
        BlockPos blockpos = context.getClickedPos();
        boolean bEye = false;

        if ( direction == Direction.NORTH )
            bEye = context.getClickLocation().x - (double)blockpos.getX() > 0.5D;
        else if ( direction == Direction.SOUTH )
            bEye = context.getClickLocation().x - (double)blockpos.getX() < 0.5D;
        else if ( direction == Direction.WEST )
            bEye = context.getClickLocation().z - (double)blockpos.getZ() < 0.5D;
        else if ( direction == Direction.EAST )
            bEye = context.getClickLocation().z - (double)blockpos.getZ() > 0.5D;

        if (bEye)
            return HSide.LEFT;
        else
            return HSide.RIGHT;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING, WATERLOGGED, SNEAKING, HSIDE, HALF, CENTERED);
    }
}

