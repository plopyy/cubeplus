package com.plop.cubeplus.common.block;

import com.plop.cubeplus.common.property.RoofShape;
import com.plop.cubeplus.common.property.cpProperty;
import net.minecraft.world.entity.player.Player;
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
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;

public class cpRoofTop extends Block implements SimpleWaterloggedBlock
{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<RoofShape> SHAPE = cpProperty.ROOF_SHAPE;
    private final Block modelBlock;
    private final BlockState modelState;
    private static final AABB ROOF_TOP = new AABB(0, 0, 0, 1, 0.5f, 1);

    public cpRoofTop(BlockState state, Properties properties)
    {
        super(properties.noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(SHAPE, RoofShape.NONE));
        this.modelBlock = state.getBlock();
        this.modelState = state;
    }

    public VoxelShape getShape(BlockState state, BlockGetter reader, BlockPos pos, CollisionContext context)
    {
        return Shapes.create(ROOF_TOP);
    }

    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        Direction direction = context.getClickedFace();
        BlockPos blockpos = context.getClickedPos();
        Player player = context.getPlayer();

        BlockState blockstate = this.defaultBlockState();
        return blockstate.setValue(SHAPE, getShapeProperty(blockstate, context.getLevel(), blockpos));
    }

    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        RoofShape shape = getShapeProperty(stateIn, worldIn, currentPos);

        BlockPos posNorth = currentPos.north();
        BlockState stateNorth = worldIn.getBlockState(posNorth);
        BlockPos posEast = currentPos.east();
        BlockState stateEast = worldIn.getBlockState(posEast);
        BlockPos posSouth = currentPos.south();
        BlockState stateSouth = worldIn.getBlockState(posSouth);
        BlockPos posWest = currentPos.west();
        BlockState stateWest = worldIn.getBlockState(posWest);

        if ( shape == RoofShape.ONE && isRoofTop(stateNorth) ) facing = Direction.EAST;
        else if ( shape == RoofShape.ONE && isRoofTop(stateEast) ) facing = Direction.SOUTH;
        else if ( shape == RoofShape.ONE && isRoofTop(stateSouth) ) facing = Direction.WEST;
        else if ( shape == RoofShape.ONE && isRoofTop(stateWest) ) facing = Direction.NORTH;

        if ( shape == RoofShape.TWO && isRoofTop(stateNorth) && isRoofTop(stateEast) ) facing = Direction.WEST;
        else if ( shape == RoofShape.TWO && isRoofTop(stateNorth) && isRoofTop(stateWest) ) facing = Direction.SOUTH;
        else if ( shape == RoofShape.TWO && isRoofTop(stateSouth) && isRoofTop(stateWest) ) facing = Direction.EAST;
        else if ( shape == RoofShape.TWO && isRoofTop(stateSouth) && isRoofTop(stateEast) ) facing = Direction.NORTH;

        if ( shape == RoofShape.THREE && !isRoofTop(stateNorth) ) facing = Direction.EAST;
        else if ( shape == RoofShape.THREE && !isRoofTop(stateEast) ) facing = Direction.SOUTH;
        else if ( shape == RoofShape.THREE && !isRoofTop(stateSouth) ) facing = Direction.WEST;
        else if ( shape == RoofShape.THREE && !isRoofTop(stateWest) ) facing = Direction.NORTH;

        if ( shape == RoofShape.SIDE && isRoofTop(stateNorth) ) facing = Direction.EAST;
        else if ( shape == RoofShape.SIDE && isRoofTop(stateEast) ) facing = Direction.NORTH;

        if ( shape == RoofShape.NONE || shape == RoofShape.FOUR ) facing = Direction.NORTH;

        return stateIn.setValue(SHAPE, shape).setValue(FACING, facing);
    }

    private static RoofShape getShapeProperty(BlockState state, BlockGetter worldIn, BlockPos pos)
    {
        Direction direction = state.getValue(FACING);

        BlockPos posNorth = pos.north();
        BlockState stateNorth = worldIn.getBlockState(posNorth);
        BlockPos posEast = pos.east();
        BlockState stateEast = worldIn.getBlockState(posEast);
        BlockPos posSouth = pos.south();
        BlockState stateSouth = worldIn.getBlockState(posSouth);
        BlockPos posWest = pos.west();
        BlockState stateWest = worldIn.getBlockState(posWest);
        int nSide = 0;

        if ( isRoofTop(stateNorth) ) nSide++;
        if ( isRoofTop(stateEast) ) nSide++;
        if ( isRoofTop(stateSouth) ) nSide++;
        if ( isRoofTop(stateWest) ) nSide++;

        if (nSide == 1)
            return RoofShape.ONE;
        if (nSide == 2)
        {
            if ( ( isRoofTop(stateNorth) && isRoofTop(stateSouth) ) || (isRoofTop(stateEast) && isRoofTop(stateWest)) )
                return RoofShape.SIDE;
            else
                return RoofShape.TWO;
        }
        if (nSide == 3)
            return RoofShape.THREE;
        if (nSide == 4)
            return RoofShape.FOUR;
        else
            return RoofShape.NONE;
    }

    public static boolean isRoofTop(BlockState state)
    {
        return state.getBlock() instanceof cpRoofTop || state.getBlock() instanceof cpRoofTopSide;
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
    {
        builder.add(FACING, SHAPE);
    }
}