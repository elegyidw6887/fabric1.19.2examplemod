package org.example.examplefabricmod.screen.InjectionBenchBlock;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.example.examplefabricmod.screen.ModScreenHandlers;
import org.example.examplefabricmod.screen.slot.ModFuelSlot;
import org.example.examplefabricmod.screen.slot.ModResultSlot;

public class InjectionBenchBlockScreenHandler extends ScreenHandler {

    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    // 构造方法1
    public InjectionBenchBlockScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory,
                // 此处的“size”与方块实体类中“inventory”对象的size相同
                new SimpleInventory(4),
                // 此处的“size”为后续添加内容中要追踪的属性委托数量相同
                new ArrayPropertyDelegate(4));
    }

    // 构造方法2
    public InjectionBenchBlockScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(ModScreenHandlers.INJECTION_BENCH_BLOCK_SCREEN_HANDLER, syncId);
        checkSize(inventory, 4);
        this.inventory = inventory;
        inventory.onOpen(playerInventory.player);
        this.propertyDelegate = propertyDelegate;
        // 在完成插槽类后添加的代码
        // 此处的数值均为GUI中对应槽位的xy值
        this.addSlot(new ModFuelSlot(inventory, 0, 18, 40));
        this.addSlot(new Slot(inventory, 1, 66, 16));
        this.addSlot(new Slot(inventory, 2, 66, 50));
        this.addSlot(new ModResultSlot(inventory, 3, 114, 33));
        // 物品栏与快捷栏添加
        addPlayerInventory(playerInventory);
        addPlayerHotbar(playerInventory);
        // 委托属性添加
        addProperties(propertyDelegate);
    }

    // 传输槽方法
    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        // 方法实现参考“AbstractFurnaceScreenHandler”，但是没有进行“originalStack”与“newStack”大小比较判断
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (index < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }
            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // 判断是否正在制作
    public boolean isCrafting() {
        return propertyDelegate.get(0) > 0;
    }

    // 判断是否有燃料
    public boolean hasFuel() {
        return propertyDelegate.get(2) > 0;
    }

    // 获取制作进度程度
    public int getScaledProgress() {
        int progress = this.propertyDelegate.get(0);
        int maxProgress = this.propertyDelegate.get(1);
        int progressArrowSize = 26; // 制作进度的像素大小（宽度）
        return maxProgress != 0 && progress != 0 ? progress * progressArrowSize / maxProgress : 0;
    }

    // 获取燃料进度程度
    public int getScaledFuelProgress() {
        int fuelProgress = this.propertyDelegate.get(2);
        int maxFuelProgress = this.propertyDelegate.get(3);
        int fuelProgressSize = 14; // 燃料进度的像素大小
        return maxFuelProgress != 0 ? (int) (((float) fuelProgress / (float) maxFuelProgress) * fuelProgressSize) : 0;
    }

    // 用于读取玩家物品栏并添加到GUI中
    private void addPlayerInventory(PlayerInventory playerInventory) {
        for (int i = 0; i < 3; ++i) {
            for (int l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + i * 9 + 9, 8 + l * 18, 86 + i * 18));
            }
        }
    }

    // 用于读取玩家快捷栏并添加到GUI中
    private void addPlayerHotbar(PlayerInventory playerInventory) {
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 144));
        }
    }
}
