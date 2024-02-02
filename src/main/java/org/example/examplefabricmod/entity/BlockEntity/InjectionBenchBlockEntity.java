package org.example.examplefabricmod.entity.BlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.example.examplefabricmod.entity.ModBlockEntities;
import org.example.examplefabricmod.item.ModItems;
import org.example.examplefabricmod.util.ImplementedInventory;
import org.jetbrains.annotations.Nullable;

public class InjectionBenchBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    // 泛型为“物品栈”的List对象，用于设定我们的工作台的槽位
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    // 构造方法
    public InjectionBenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INJECTION_BENCH_BLOCK, pos, state);
    }

    // 获取展示名字
    @Override
    public Text getDisplayName() {
        // 返回翻译键
        return Text.literal("injection_bench_block");
    }

    // 屏幕处理对象，创建菜单
    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return null;
    }

    // 获得物品
    @Override
    public DefaultedList<ItemStack> getItems() {
        // 返回工作台槽位List对象
        return this.inventory;
    }

    // “刻”方法
    public static void tick(World world, BlockPos blockPos, BlockState blockState, InjectionBenchBlockEntity entity) {
        if (world.isClient()) {
            return;
        }
        // 物品配方合成的主体方法
        if (hasRecipe(entity) && hasNotReachedStackLimit(entity)) {
            craftItem(entity);
        }
    }

    // 读取nbt数据
    @Override
    public void readNbt(NbtCompound nbt) {
        // 读取玩家的物品栏
        Inventories.readNbt(nbt, inventory); // 直接调用“Inventories”类中的方法
        super.readNbt(nbt);
    }

    // 写入nbt数据
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        // 将读取到的玩家物品栏保存到标签中
        Inventories.writeNbt(nbt, inventory); // 直接调用“Inventories”类中的方法
    }

    // 物品制作方法
    private static void craftItem(InjectionBenchBlockEntity entity) {
        // 移除前三个槽位的物品
        entity.removeStack(0, 1);
        entity.removeStack(1, 1);
        entity.removeStack(2, 1);
        // 在第四个槽位中生成“紫水晶剑”
        entity.setStack(3, new ItemStack(ModItems.AMETHYST_SWORD,
                entity.getStack(3).getCount() + 1));
    }

    // 判断配方是否正确方法（简易配方判断）
    private static boolean hasRecipe(InjectionBenchBlockEntity entity) {
        // 判断前三个槽位中的物品是否为指定的物品
        boolean hasItemInFirstSlot = entity.getStack(0).getItem() == ModItems.FRAGMENT_OF_AMETHYST;
        boolean hasItemInSecondSlot = entity.getStack(1).getItem() == Items.DIAMOND_SWORD;
        boolean hasItemInThirdSlot = entity.getStack(2).getItem() == ModItems.AMETHYST_SWORD;
        return hasItemInFirstSlot && hasItemInSecondSlot && hasItemInThirdSlot;
    }

    // 判断输出槽是否达到上限
    private static boolean hasNotReachedStackLimit(InjectionBenchBlockEntity entity) {
        return entity.getStack(3).getCount() < entity.getStack(3).getMaxCount();
    }
}
