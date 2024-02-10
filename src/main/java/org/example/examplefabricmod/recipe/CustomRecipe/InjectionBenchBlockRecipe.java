package org.example.examplefabricmod.recipe.CustomRecipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class InjectionBenchBlockRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;

    // 构造方法
    public InjectionBenchBlockRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    // 匹配方法
    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        // 判断是否为客户端，如果为客户端则返回false
        if (world.isClient) {
            return false;
        }
        // 判断插槽1与2中的物品是否正确
        if (recipeItems.get(0).test(inventory.getStack(1))) {
            return recipeItems.get(1).test(inventory.getStack(2));
        }
        return false;
    }

    // 制作方法
    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return null;
    }

    // 适配方法
    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    // 获取输出方法
    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    // 获取ID方法
    @Override
    public Identifier getId() {
        return id;
    }

    // 获取串行器方法
    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    // 获取类型方法
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<InjectionBenchBlockRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();
        public static final String ID = "injection_bench"; // 在配方json中类型的ID
    }

    // 序列化程序内部类
    public static class Serializer implements RecipeSerializer<InjectionBenchBlockRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        // 配方json文件/文件夹所需要的ID
        // 若将该处的ID作为配方文件夹使用，其中的配方没有名字格式要求
        public static final String ID = "injection_bench";

        // 读取json文件的方法
        @Override
        public InjectionBenchBlockRecipe read(Identifier id, JsonObject json) {
            // 读取配方json中输出部分的内容
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            // 读取配方json中原料部分的内容，此处为数组因为原料为2个
            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(2, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }
            return new InjectionBenchBlockRecipe(id, output, inputs);
        }

        // 针对网络的读取方法（PacketByteBuf）
        @Override
        public InjectionBenchBlockRecipe read(Identifier id, @NotNull PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);
            inputs.replaceAll(ignored -> Ingredient.fromPacket(buf));
            ItemStack output = buf.readItemStack();
            return new InjectionBenchBlockRecipe(id, output, inputs);
        }

        // 针对网络的写入方法（PacketByteBuf）
        @Override
        public void write(@NotNull PacketByteBuf buf, @NotNull InjectionBenchBlockRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutput());
        }
    }
}
