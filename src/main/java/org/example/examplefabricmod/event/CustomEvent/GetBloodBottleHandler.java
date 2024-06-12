package org.example.examplefabricmod.event.CustomEvent;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.tutorial.TutorialManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.example.examplefabricmod.block.ModBlocks;

public class GetBloodBottleHandler implements UseItemCallback {

    @Override
    public TypedActionResult<ItemStack> interact(PlayerEntity player, World world, Hand hand) {
        ItemStack heldItem = player.getStackInHand(hand);
        if (!world.isClient) {
            if (heldItem.getItem() == Items.GLASS_BOTTLE) {
                if (isBloodAroundPlayer((ServerPlayerEntity) player, (ServerWorld) world, 1)){
                    if (new TutorialManager(MinecraftClient.getInstance(), null).isInSurvival()) {

                    } else {

                    }
                }
            }
        }
        return TypedActionResult.pass(ItemStack.EMPTY);
    }

    private static boolean isBloodAroundPlayer(ServerPlayerEntity playerEntity, ServerWorld world, int size) {
        return BlockPos
                .stream(playerEntity.getVisibilityBoundingBox().expand(size))
                .map(world::getBlockState)
                .filter(blockState -> blockState.isOf(ModBlocks.BLOOD))
                .toArray()
                .length > 0;
    }
}
