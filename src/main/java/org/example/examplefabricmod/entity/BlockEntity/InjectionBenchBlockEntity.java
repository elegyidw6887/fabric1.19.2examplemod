package org.example.examplefabricmod.entity.BlockEntity;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.example.examplefabricmod.Recipe.CustomRecipe.InjectionBenchBlockRecipe;
import org.example.examplefabricmod.entity.ModBlockEntities;
import org.example.examplefabricmod.screen.InjectionBenchBlock.InjectionBenchBlockScreenHandler;
import org.example.examplefabricmod.util.ImplementedInventory;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class InjectionBenchBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    // 泛型为“物品栈”的List对象，用于设定我们的工作台的槽位
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);

    protected final PropertyDelegate propertyDelegate; // 委托属性
    private int progress = 0; // 制作进度
    private int maxProgress = 64; // 最大制作进度（影响物品制作速度，数据绑定整个工作台）
    private int fuelTime = 0; // 燃料时间
    private int maxFuelTime = 0; // 最大燃料时间

    // 构造方法
    public InjectionBenchBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INJECTION_BENCH_BLOCK_ENTITY, pos, state);
        /*属性委托表示整数属性的索引列表。
         属性委托用于在屏幕中显示整数值，例如熔炉中的进度条。*/
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> InjectionBenchBlockEntity.this.progress;
                    case 1 -> InjectionBenchBlockEntity.this.maxProgress;
                    case 2 -> InjectionBenchBlockEntity.this.fuelTime;
                    case 3 -> InjectionBenchBlockEntity.this.maxFuelTime;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> InjectionBenchBlockEntity.this.progress = value;
                    case 1 -> InjectionBenchBlockEntity.this.maxProgress = value;
                    case 2 -> InjectionBenchBlockEntity.this.fuelTime = value;
                    case 3 -> InjectionBenchBlockEntity.this.maxFuelTime = value;
                }
            }

            @Override
            public int size() {
                return 4;
            }
        };
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
        return new InjectionBenchBlockScreenHandler(syncId, inv, this, this.propertyDelegate);
    }

    // 获得物品
    @Override
    public DefaultedList<ItemStack> getItems() {
        // 返回工作台槽位List对象
        return this.inventory;
    }

    // “刻”方法
    public static void tick(World world, BlockPos pos, BlockState state, InjectionBenchBlockEntity entity) {
        // 判断是否消耗燃料
        if (isConsumingFuel(entity)) {
            entity.fuelTime--;
        }
        // 判断是否存在配方
        if (hasRecipe(entity)) {
            // 判断燃料槽中是否有燃料以及是否消耗燃料
            if (hasFuelInFuelSlot(entity) && !isConsumingFuel(entity)) {
                // 判断结果为ture则进行燃料的消耗
                entity.consumeFuel();
            }
            if (isConsumingFuel(entity)) {
                // 制作进度++
                entity.progress++;
                // 如果制作进度大于最大进度则完成制作
                if (entity.progress > entity.maxProgress) {
                    // 调用制作代码来完成制作
                    craftItem(entity);
                }
            }
        } else {
            // 配方不存在则直接重置进度
            entity.resetProgress();
        }
    }

    // 读取nbt数据
    @Override
    public void readNbt(NbtCompound nbt) {
        // 读取玩家的物品栏
        Inventories.readNbt(nbt, inventory); // 直接调用“Inventories”类中的方法
        super.readNbt(nbt);
        // 读取方块实体中对应的数据
        progress = nbt.getInt("bench.progress");
        fuelTime = nbt.getInt("bench.fuelTime");
        maxFuelTime = nbt.getInt("bench.maxFuelTime");
    }

    // 写入nbt数据
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        // 将读取到的玩家物品栏保存到标签中
        Inventories.writeNbt(nbt, inventory); // 直接调用“Inventories”类中的方法
        // 向方块实体中写入对应的数据
        nbt.putInt("bench.progress", progress);
        nbt.putInt("bench.fuelTime", fuelTime);
        nbt.putInt("bench.maxFuelTime", maxFuelTime);
    }

    // 判断燃料槽中是否有燃料
    private static boolean hasFuelInFuelSlot(InjectionBenchBlockEntity entity) {
        return !entity.getStack(0).isEmpty();
    }

    // 判断是否需要消耗燃料
    private static boolean isConsumingFuel(InjectionBenchBlockEntity entity) {
        return entity.fuelTime > 0;
    }

    // 燃料消耗方法
    private void consumeFuel() {
        if (!getStack(0).isEmpty()) {
            // 获取燃料的燃料值
            this.fuelTime = FuelRegistry.INSTANCE.get(this.removeStack(0, 1).getItem());
            // 将获取到的燃料值设置为最大燃料值
            this.maxFuelTime = this.fuelTime;
        }
    }

    // 判断配方是否正确
    private static boolean hasRecipe(InjectionBenchBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        // 设置物品栈
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }
        // 获取配方并保存到一个容器对象中
        assert world != null;
        Optional<InjectionBenchBlockRecipe> match = world.getRecipeManager()
                .getFirstMatch(InjectionBenchBlockRecipe.Type.INSTANCE, inventory, world);
        // 返回获取到的结果
        /*
            1.是否获取到对象
            2.输出槽中存在的物品是否是目标配方的产物
            3.输出槽是否还能够继续存储物品
         */
        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
    }

    // 进行物品制作
    private static void craftItem(InjectionBenchBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }

        // 获取配方并保存到一个容器对象中
        assert world != null;
        Optional<InjectionBenchBlockRecipe> match = world.getRecipeManager()
                .getFirstMatch(InjectionBenchBlockRecipe.Type.INSTANCE, inventory, world);
        // 判断是否获取到配方
        if (match.isPresent()) {
            // 移除插槽1与2中的物品
            entity.removeStack(1, 1);
            entity.removeStack(2, 1);
            // 在插槽3中放置目标产物
            entity.setStack(3, new ItemStack(match.get().getOutput().getItem(),
                    entity.getStack(3).getCount() + 1));
            // 完成一次制作之后重置进度
            entity.resetProgress();
        }
    }

    // 重置进程
    private void resetProgress() {
        this.progress = 0;
    }

    // 是否可以将产物放入输出槽，判断输出槽中的物品是否为目标配方的产物
    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(3).getItem() == output.getItem() || inventory.getStack(3).isEmpty();
    }

    // 是否可以将多个产物放入输出槽
    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(3).getMaxCount() > inventory.getStack(3).getCount();
    }
}
