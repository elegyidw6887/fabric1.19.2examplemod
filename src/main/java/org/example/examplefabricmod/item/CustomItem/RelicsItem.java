package org.example.examplefabricmod.item.CustomItem;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;

public class RelicsItem extends Item {



    public RelicsItem(Settings settings) {
        super(settings);
    }

    public void writeItemNBT(Item item, String nbtKey, String string) {
        // 获取物品堆
        ItemStack itemStack = item.getDefaultStack();
        // 获取物品堆的NBT数据
        NbtCompound itemStackNbt = itemStack.getNbt();
        if (itemStackNbt != null) {
            itemStackNbt.putString(nbtKey+" ", string);
        }
    }

    public void writeArmorNBT(ArmorItem armorItem, Item relicsItem) {
        // 获取护甲与遗物物品的堆栈信息
        ItemStack armorItemStack = armorItem.getDefaultStack();
        ItemStack relicsItemStack = relicsItem.getDefaultStack();
        // 读取遗物的NBT数据
        NbtCompound nbtCompound =relicsItemStack.getNbt();
        if (armorItem instanceof ArmorItem) {
            if (nbtCompound != null) {
                // 将遗物的NBT数据写入到护甲中
                armorItemStack.writeNbt(nbtCompound);
            }
        }
    }
}
