package com.plop.cubeplus.common.block;

import com.plop.cubeplus.common.property.RoofMat;
import com.plop.cubeplus.common.property.cpProperty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Half;
import net.minecraft.world.level.block.state.properties.StairsShape;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import java.util.Random;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;

public class cpRoof extends Block implements SimpleWaterloggedBlock
{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<StairsShape> SHAPE = BlockStateProperties.STAIRS_SHAPE;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<RoofMat> MATERIAL = cpProperty.ROOF_MAT;
    private final Block modelBlock;
    private final BlockState modelState;

    //Steps
    private static final AABB STAIR_1STEP = new AABB(0, 0, 0, 1, 0.25f, 1);
    private static final AABB STAIR_2STEP_S = new AABB(0.00f, 0.25f, 0.00f, 1.00f, 0.50f, 0.75f);
    private static final AABB STAIR_3STEP_S = new AABB(0.00f, 0.50f, 0.00f, 1.00f, 0.75f, 0.50f);
    private static final AABB STAIR_4STEP_S = new AABB(0.00f, 0.75f, 0.00f, 1.00f, 1.00f, 0.25f);
    private static final AABB STAIR_2STEP_O = new AABB(0.00f, 0.25f, 0.00f, 0.75f, 0.50f, 0.75f);
    private static final AABB STAIR_3STEP_O = new AABB(0.00f, 0.50f, 0.00f, 0.50f, 0.75f, 0.50f);
    private static final AABB STAIR_4STEP_O = new AABB(0.00f, 0.75f, 0.00f, 0.25f, 1.00f, 0.25f);
    private static final AABB STAIR_2STEP_I = new AABB(0.00f, 0.25f, 0.75f, 0.75f, 0.50f, 1.00f);
    private static final AABB STAIR_3STEP_I = new AABB(0.00f, 0.50f, 0.50f, 0.50f, 0.75f, 1.00f);
    private static final AABB STAIR_4STEP_I = new AABB(0.00f, 0.75f, 0.25f, 0.25f, 1.00f, 1.00f);

    public cpRoof(BlockState state, Properties properties)
    {
        super(properties.noOcclusion());
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(SHAPE, StairsShape.STRAIGHT)
                .setValue(MATERIAL, RoofMat.SLATES)
                .setValue(WATERLOGGED, Boolean.FALSE)
        );
        this.modelBlock = state.getBlock();
        this.modelState = state;
    }

    private VoxelShape RotateShape(BlockState state, AABB Step )
    {
        Direction d     = state.getValue(FACING);
        StairsShape s   = state.getValue(SHAPE);

        AABB rotShape = Step;

        int rot = 0;
        if (d == Direction.EAST)
            rot = 1;
        else if (d == Direction.SOUTH)
            rot = 2;
        else if (d == Direction.WEST)
            rot = 3;

        for (int n = 0; n < rot; n++)
            rotShape = new AABB(1-rotShape.minZ, rotShape.minY, rotShape.minX, 1-rotShape.maxZ, rotShape.maxY, rotShape.maxX);

        if (s == StairsShape.INNER_RIGHT || s == StairsShape.OUTER_RIGHT)
            rotShape = new AABB(1-rotShape.minZ, rotShape.minY, rotShape.minX, 1-rotShape.maxZ, rotShape.maxY, rotShape.maxX);

        return Shapes.create(rotShape);
    }

    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context)
    {
        VoxelShape FirstStep	= RotateShape( state, STAIR_1STEP );
        VoxelShape SecondStep, ThirdStep, FourthStep;

        if ( state.getValue(SHAPE) == StairsShape.OUTER_LEFT || state.getValue(SHAPE) == StairsShape.OUTER_RIGHT )
        {
            SecondStep	= RotateShape( state, STAIR_2STEP_O );
            ThirdStep	= RotateShape( state, STAIR_3STEP_O );
            FourthStep	= RotateShape( state, STAIR_4STEP_O );
        }
        else
        {
            SecondStep	= RotateShape( state, STAIR_2STEP_S );
            ThirdStep	= RotateShape( state, STAIR_3STEP_S );
            FourthStep	= RotateShape( state, STAIR_4STEP_S );

            if ( state.getValue(SHAPE) == StairsShape.INNER_LEFT || state.getValue(SHAPE) == StairsShape.INNER_RIGHT )
            {
                SecondStep	= Shapes.joinUnoptimized(SecondStep, RotateShape( state, STAIR_2STEP_I ), BooleanOp.NOT_SAME);
                ThirdStep	= Shapes.joinUnoptimized(ThirdStep, RotateShape( state, STAIR_3STEP_I ), BooleanOp.NOT_SAME);
                FourthStep	= Shapes.joinUnoptimized(FourthStep, RotateShape( state, STAIR_4STEP_I ), BooleanOp.NOT_SAME);
            }
        }

        VoxelShape lowerShape = Shapes.joinUnoptimized(FirstStep, SecondStep, BooleanOp.NOT_SAME);
        VoxelShape upperShape = Shapes.joinUnoptimized(ThirdStep, FourthStep, BooleanOp.NOT_SAME);
        return Shapes.joinUnoptimized(lowerShape, upperShape, BooleanOp.NOT_SAME);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        Direction direction = context.getClickedFace();
        BlockPos blockpos = context.getClickedPos();
        Player player = context.getPlayer();
        Half h = direction != Direction.DOWN && (direction == Direction.UP || !(context.getClickLocation().y - (double)blockpos.getY() > 0.5D)) ? Half.BOTTOM : Half.TOP;

        FluidState ifluidstate = context.getLevel().getFluidState(blockpos);
        BlockState blockstate = this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection())
                .setValue(MATERIAL, RoofMat.SLATES)
                .setValue(WATERLOGGED, ifluidstate.getType() == Fluids.WATER);
        return blockstate.setValue(SHAPE, getShapeProperty(blockstate, context.getLevel(), blockpos));
    }

    // ----------------------------------------------------------------
    // ----------------------------------------------------------------
    // ----------------------------------------------------------------

    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    /**
     * Called periodically clientside on blocks near the player to show effects (like furnace fire particles). Note that
     * this method is unrelated to  and , and will always be called regardless
     * of whether the block can receive random update ticks
     */
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, Random rand) {
        this.modelBlock.animateTick(stateIn, worldIn, pos, rand);
    }

    public void attack(BlockState state, Level worldIn, BlockPos pos, Player player) {
        this.modelState.attack(worldIn, pos, player);
    }

    /**
     * Called after a player destroys this Block - the posiiton pos may no longer hold the state indicated.
     */
    public void destroy(LevelAccessor worldIn, BlockPos pos, BlockState state) {
        this.modelBlock.destroy(worldIn, pos, state);
    }

    /**
     * Returns how much this block can resist explosions from the passed in entity.
     */
    public float getExplosionResistance() {
        return this.modelBlock.getExplosionResistance();
    }

    /**
     * How many world ticks before ticking
     */
    public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (state.getBlock() != state.getBlock()) {
            this.modelState.neighborChanged(worldIn, pos, Blocks.AIR, pos, false);
            this.modelBlock.onPlace(this.modelState, worldIn, pos, oldState, false);
        }
    }

    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            this.modelState.onRemove(worldIn, pos, newState, isMoving);
        }
    }

    /**
     * Called when the given entity walks on this Block
     */
    public void stepOn(Level worldIn, BlockPos pos, BlockState state, Entity entityIn) {
        this.modelBlock.stepOn(worldIn, pos, state, entityIn);
    }

    /**
     * Called when this Block is destroyed by an Explosion
     */
    public void wasExploded(Level worldIn, BlockPos pos, Explosion explosionIn) {
        this.modelBlock.wasExploded(worldIn, pos, explosionIn);
    }

    /**
     * Update the provided state given the provided neighbor facing and neighbor state, returning a new state.
     * For example, fences make their connections to the passed in state if possible, and wet concrete powder immediately
     * returns its solidified counterpart.
     * Note that this method should ideally consider only the specific face passed in.
     */
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (stateIn.getValue(WATERLOGGED)) {
            worldIn.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(worldIn));
        }

        return facing.getAxis().isHorizontal() ? stateIn.setValue(SHAPE, getShapeProperty(stateIn, worldIn, currentPos)) : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    /**
     * Returns a stair shape property based on the surrounding stairs from the given blockstate and position
     */
    private static StairsShape getShapeProperty(BlockState state, BlockGetter worldIn, BlockPos pos)
    {
        Direction direction = state.getValue(FACING);
        BlockState blockstate = worldIn.getBlockState(pos.relative(direction));

        if (isBlockStairs(blockstate)) {
            Direction direction1 = blockstate.getValue(FACING);
            if (direction1.getAxis() != state.getValue(FACING).getAxis() && isDifferentStairs(state, worldIn, pos, direction1.getOpposite())) {
                if (direction1 == direction.getCounterClockWise()) {
                    return StairsShape.OUTER_LEFT;
                }

                return StairsShape.OUTER_RIGHT;
            }
        }

        BlockState blockstate1 = worldIn.getBlockState(pos.relative(direction.getOpposite()));
        if (isBlockStairs(blockstate1)) {
            Direction direction2 = blockstate1.getValue(FACING);
            if (direction2.getAxis() != state.getValue(FACING).getAxis() && isDifferentStairs(state, worldIn, pos, direction2)) {
                if (direction2 == direction.getCounterClockWise()) {
                    return StairsShape.INNER_LEFT;
                }

                return StairsShape.INNER_RIGHT;
            }
        }

        return StairsShape.STRAIGHT;
    }

    private static boolean isDifferentStairs(BlockState state, BlockGetter worldIn, BlockPos pos, Direction face) {
        BlockState blockstate = worldIn.getBlockState(pos.relative(face));
        return !isBlockStairs(blockstate) || blockstate.getValue(FACING) != state.getValue(FACING);
    }

    public static boolean isBlockStairs(BlockState state) {
        return state.getBlock() instanceof cpRoof;
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     * @deprecated call via  whenever possible. Implementing/overriding is
     * fine.
     */
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     * @deprecated call via whenever possible. Implementing/overriding is fine.
     */
    public BlockState mirror(BlockState state, Mirror mirrorIn)
    {
        Direction direction = state.getValue(FACING);
        StairsShape stairsshape = state.getValue(SHAPE);
        switch(mirrorIn) {
            case LEFT_RIGHT:
                if (direction.getAxis() == Direction.Axis.Z) {
                    switch(stairsshape) {
                        case INNER_LEFT:
                            return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_RIGHT);
                        case INNER_RIGHT:
                            return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_LEFT);
                        case OUTER_LEFT:
                            return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_LEFT);
                        default:
                            return state.rotate(Rotation.CLOCKWISE_180);
                    }
                }
                break;
            case FRONT_BACK:
                if (direction.getAxis() == Direction.Axis.X) {
                    switch(stairsshape) {
                        case INNER_LEFT:
                            return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_LEFT);
                        case INNER_RIGHT:
                            return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.INNER_RIGHT);
                        case OUTER_LEFT:
                            return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_RIGHT);
                        case OUTER_RIGHT:
                            return state.rotate(Rotation.CLOCKWISE_180).setValue(SHAPE, StairsShape.OUTER_LEFT);
                        case STRAIGHT:
                            return state.rotate(Rotation.CLOCKWISE_180);
                    }
                }
        }

        return super.mirror(state, mirrorIn);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, SHAPE, MATERIAL, WATERLOGGED);
    }

    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
        return false;
    }
}