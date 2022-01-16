package com.plop.cubeplus.common.item;

/*import com.plop.cubeplus.CubePlus;
import com.plop.cubeplus.common.DynBlock.FormMats;
import com.plop.cubeplus.common.block.cpNewRoof;
import com.plop.cubeplus.common.blockentity.cpNewRoofEntity;*/
import com.plop.cubeplus.common.block.cpBeamSupport;
import com.plop.cubeplus.common.block.cpBeamSupport2;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import static com.plop.cubeplus.common.property.cpProperty.CENTERED;

public class cpTrowel extends Item
{
    public cpTrowel(String name, Properties properties)
    {
        super(properties);

        setRegistryName(name);

        cpItems.INSTANCE.items.add(this);
    }

    public InteractionResult useOn(UseOnContext context)
    {
        Level level = context.getLevel();
        Player player = context.getPlayer();
        BlockPos blockpos = context.getClickedPos();
        BlockState blockstate = level.getBlockState(blockpos);
        BlockEntity entity = level.getBlockEntity(blockpos);
        Block block = blockstate.getBlock();
        BlockState blockstate2 = null;
        if (block instanceof cpBeamSupport || block instanceof cpBeamSupport2)
        {
            if (blockstate.getValue(CENTERED) == Boolean.FALSE)
            {
                level.playSound(player, blockpos, SoundEvents.PISTON_EXTEND, SoundSource.BLOCKS, 1.0F, 1.0F);
                blockstate2 = blockstate.setValue(CENTERED, Boolean.TRUE);
            }
            else if (blockstate.getValue(CENTERED) == Boolean.TRUE)
            {
                level.playSound(player, blockpos, SoundEvents.PISTON_EXTEND, SoundSource.BLOCKS, 1.0F, 1.0F);
                blockstate2 = blockstate.setValue(CENTERED, Boolean.FALSE);
            }

            if (blockstate2 != null)
            {
                if (!level.isClientSide)
                    level.setBlock(blockpos, blockstate2, 11);

                return InteractionResult.sidedSuccess(level.isClientSide);
            }
            else
            {
                return InteractionResult.PASS;
            }
        }

        /*if (block instanceof cpNewRoof && entity instanceof cpNewRoofEntity)
        {
            CubePlus.LOGGER.info("trowel["+player.getOffhandItem().getItem().getRegistryName().getPath()+"]");
            if ( FormMats.IsMaterial(player.getOffhandItem().getItem().getRegistryName().getPath()) == Boolean.TRUE )
            {
                ((cpNewRoofEntity) entity).MATERIAL = "block/" + player.getOffhandItem().getItem().getRegistryName().getPath();
            }
        }*/

        return InteractionResult.PASS;
    }
}
