package org.example.examplefabricmod.event.CustomEvent;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.tutorial.TutorialManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.example.examplefabricmod.block.ModBlocks;
import org.example.examplefabricmod.item.CustomItem.BloodBottleItem;
import org.example.examplefabricmod.item.ModItems;
import org.example.examplefabricmod.util.CustomData.BloodyData;
import org.example.examplefabricmod.util.ModArmorMaterials;
import org.example.examplefabricmod.util.ModEntityDataSaver;
import org.example.examplefabricmod.util.ModFullArmorEffect;

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
