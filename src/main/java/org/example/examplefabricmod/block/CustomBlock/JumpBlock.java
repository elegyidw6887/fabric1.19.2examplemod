package org.example.examplefabricmod.block.CustomBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class JumpBlock extends Block {

    public JumpBlock(Settings settings) {
        super(settings);
    }

    // “onUse”方法调用条件为实体右键互动
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        // 同样判定是否为服务端以及互动的是否为主手
        if (!world.isClient() && hand == Hand.MAIN_HAND) {
            // 发送 一条消息
            player.sendMessage(Text.literal("Why you will right click this?"));
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    // “onSteppedOn”方法调用条件为实体站立在方块之上
    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {

        // 判定其上实体是否为有生命实体
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20));
        }
        super.onSteppedOn(world, pos, state, entity);
    }
}
