package org.example.examplefabricmod.block.CustomBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class ModPlantBlocks extends Block {

    public ModPlantBlocks(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            ItemStack stack = player.getStackInHand(hand);
            if (stack.getItem() instanceof BlockItem && Block.getBlockFromItem(stack.getItem()) instanceof SaplingBlock) {
                // 在方块上放置树苗
                world.setBlockState(pos.up(), Block.getBlockFromItem(stack.getItem()).getDefaultState());
                // 播放放置方块音效
                world.playSound(null, pos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                // 触发方块更新事件
                world.emitGameEvent(player, GameEvent.BLOCK_PLACE , pos);
                // 如果玩家不处于创造模式，减少手持物品的数量
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
                return ActionResult.SUCCESS;
            }
            if (stack.getItem() instanceof BlockItem && Block.getBlockFromItem(stack.getItem()) instanceof FlowerBlock) {
                world.setBlockState(pos.up(), Block.getBlockFromItem(stack.getItem()).getDefaultState());
                world.playSound(null, pos, SoundEvents.BLOCK_GRASS_PLACE, SoundCategory.BLOCKS, 1.0f, 1.0f);
                world.emitGameEvent(player, GameEvent.BLOCK_PLACE , pos);
                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS;
    }
}
