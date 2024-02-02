package org.example.examplefabricmod.screen.slot;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class ModResultSlot extends Slot {

    public ModResultSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    // 判断能否插入物品，输出结果槽理所当然不能插入物品
    @Override
    public boolean canInsert(ItemStack stack) {
        return false;
    }
}
