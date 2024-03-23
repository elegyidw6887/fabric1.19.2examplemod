package org.example.examplefabricmod.event.CustomEvent;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.example.examplefabricmod.item.ModItems;
import org.example.examplefabricmod.util.CustomData.BloodyData;
import org.example.examplefabricmod.util.ModArmorMaterials;
import org.example.examplefabricmod.util.ModEntityDataSaver;
import org.example.examplefabricmod.util.ModFullArmorEffect;

public class UseBloodyBottleHandler implements UseItemCallback {

    @Override
    public TypedActionResult<ItemStack> interact(PlayerEntity player, World world, Hand hand) {
        if (ModFullArmorEffect.getArmorEffect(player, ModArmorMaterials.AMETHYST)) {
            if (!world.isClient) {
                ItemStack heldItem = player.getStackInHand(hand);
                if (heldItem.getItem() == ModItems.BLOOD_BOTTLE) {
                    player.sendMessage(Text.literal("You feel some power inside you stirring!"), false);
                    BloodyData.addBloodyThirst((ModEntityDataSaver) player);
                }
            }
        } else {
            ItemStack heldItem = player.getStackInHand(hand);
            if (heldItem.getItem() == ModItems.BLOOD_BOTTLE) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1));
            }
        }
        return TypedActionResult.pass(ItemStack.EMPTY);
    }
}
