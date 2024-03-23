package org.example.examplefabricmod.entity.BlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.example.examplefabricmod.recipe.CustomRecipe.RelicsBenchBlockRecipe;
import org.example.examplefabricmod.util.ImplementedInventory;
import org.example.examplefabricmod.util.ModNBTWriter;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class RelicsBenchBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);

    static ModNBTWriter modArmorNBTWriter;

    public RelicsBenchBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("relics_bench_block");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        // return new RelicsBenchBlockScreenHandler();
        return null;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
    }

    public static void tick(World world, BlockPos pos, BlockState state,RelicsBenchBlockEntity entity) {
        craftItem(entity);
    }

    private static boolean hasRecipe(RelicsBenchBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        for (int i = 0; i < entity.inventory.size(); i++) {
            inventory.setStack(i, entity.getStack(i));
        }
        assert world != null;
        Optional<RelicsBenchBlockRecipe> match = world.getRecipeManager()
                .getFirstMatch(RelicsBenchBlockRecipe.Type.INSTANCE, inventory, world);

        return match.isPresent() && canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
    }

    private static void craftItem(RelicsBenchBlockEntity entity) {
        World world = entity.world;
        SimpleInventory inventory = new SimpleInventory(entity.inventory.size());
        assert world != null;
        Optional<RelicsBenchBlockRecipe> match = world.getRecipeManager()
                .getFirstMatch(RelicsBenchBlockRecipe.Type.INSTANCE, inventory, world);
        if (match.isPresent()) {
            entity.removeStack(0, 1);
            entity.removeStack(1, 1);
            // 获取物品堆
            ItemStack relicsItemStack = entity.getStack(0);
            ItemStack armorItemStack= entity.getStack(1);
            {
                // 为护甲写入遗物的NBT数据
                modArmorNBTWriter.writeArmorNBT((ArmorItem) armorItemStack.getItem(),relicsItemStack.getItem());
            }
            entity.setStack(2, armorItemStack);
        }
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(2).isEmpty();
    }
}
