package com.plop.cubeplus.common.event;


import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.plop.cubeplus.CubePlus;

@Mod.EventBusSubscriber(modid = CubePlus.MOD_ID)
public class cpForgeEvents
{
    @SubscribeEvent
    public static void onBlockBreak(final BlockEvent.BreakEvent event)
    {
        //CubePlus.log("[onBlockBreak] Debug 1");
    }

    @SubscribeEvent
    public static void onPlayerInteract( final PlayerInteractEvent.RightClickItem event)
    {
        //CubePlus.log("[onPlayerInteract] Debug 1");
    }

    @SubscribeEvent
    public static void onBlockPlace( final BlockEvent.EntityPlaceEvent event)
    {
        /*BlockSnapshot block = event.getBlockSnapshot();
        BlockState state = event.getPlacedBlock();

        //BlockRayTraceResult rayTrace = state.().rayTrace();

        if( state.getBlock() == Blocks.COBBLESTONE_STAIRS )
        {
            cpStairs stair = cpBlocks.COBBLESTONE_STAIRS_4STEPS;
            BlockState blockstate = stair.getDefaultState().with(FACING, state.get(FACING)).with(HALF, state.get(HALF)).with(WATERLOGGED, state.get(WATERLOGGED));

            replaceBlock(state, blockstate, event.getWorld(), block.getPos(), 3);
        }*/
    }
}
