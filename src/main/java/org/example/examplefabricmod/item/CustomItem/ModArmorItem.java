package org.example.examplefabricmod.item.CustomItem;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ModArmorItem extends ArmorItem {

    public ModArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }

    public ArmorItem writeArmorNBT(ArmorItem armorItem, Item relicsItem) {
        // 获取护甲与遗物物品的堆栈信息
        ItemStack armorItemStack = armorItem.getDefaultStack();
        ItemStack relicsItemStack = relicsItem.getDefaultStack();
        // 读取遗物的NBT数据
        NbtCompound nbtCompound =relicsItemStack.getNbt();
        if (armorItem instanceof ArmorItem) {
            if (nbtCompound != null) {
                // 将遗物的NBT数据写入到护甲中
                armorItemStack.writeNbt(nbtCompound);
                // 返回修改后的护甲物品
                return (ArmorItem) armorItemStack.getItem();
            }
        }
        return null;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return stack.hasNbt();
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
    }
}
