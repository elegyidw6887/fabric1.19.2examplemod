package org.example.examplefabricmod.util;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.example.examplefabricmod.util.ModArmorMaterials;

public class ModFullArmorEffect {

    public static boolean getArmorEffect(PlayerEntity player, ModArmorMaterials materials) {
        return isWearingFullArmor(player, materials.getName());
    }

    // 判断是否穿戴全套装备
    private static boolean isWearingFullArmor(PlayerEntity player, String string) {
        return hasArmorSetPiece(player, Registry.ITEM.get(new Identifier("examplefabricmod", string+"_helmet")).getDefaultStack())
                && hasArmorSetPiece(player, Registry.ITEM.get(new Identifier("examplefabricmod", string+"_chestplate")).getDefaultStack())
                && hasArmorSetPiece(player, Registry.ITEM.get(new Identifier("examplefabricmod", string+"_leggings")).getDefaultStack())
                && hasArmorSetPiece(player, Registry.ITEM.get(new Identifier("examplefabricmod", string+"_boots")).getDefaultStack());
    }

    // 判定穿戴的装备是否与目标装备一致
    private static boolean hasArmorSetPiece(PlayerEntity player, ItemStack armorItem) {
        return armorItem.getItem() instanceof ArmorItem
                && player.getEquippedStack(((ArmorItem) armorItem.getItem()).getSlotType()).getItem() == armorItem.getItem();
    }
}
