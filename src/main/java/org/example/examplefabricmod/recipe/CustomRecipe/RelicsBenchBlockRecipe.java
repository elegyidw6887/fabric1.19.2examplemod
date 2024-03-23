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

public class RelicsBenchBlockRecipe implements Recipe<SimpleInventory> {

    private final Identifier id;
    private final ItemStack output;
    private final DefaultedList<Ingredient> recipeItems;

    public RelicsBenchBlockRecipe(Identifier id, ItemStack output, DefaultedList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient) {
            return false;
        }
        if (recipeItems.get(0).test(inventory.getStack(1))) {
            return recipeItems.get(1).test(inventory.getStack(2));
        }
        return false;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory) {
        return null;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return output.copy();
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serialize.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<RelicsBenchBlockRecipe> {

        private Type() { }

        public static final Type INSTANCE = new Type();
        public static final String ID = "relics_bench";
    }

    public static class Serialize implements RecipeSerializer<RelicsBenchBlockRecipe> {

        public static final Serialize INSTANCE = new Serialize();
        public static final String ID = "relics_bench";

        @Override
        public RelicsBenchBlockRecipe read(Identifier id, JsonObject json) {
            ItemStack output = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "output"));
            JsonArray ingredients = JsonHelper.getArray(json, "ingredients");
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(1, Ingredient.fromJson(ingredients));
            return new RelicsBenchBlockRecipe(id, output, inputs);
        }

        @Override
        public RelicsBenchBlockRecipe read(Identifier id, PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);
            inputs.replaceAll(ignored -> Ingredient.fromPacket(buf));
            ItemStack output = buf.readItemStack();
            return new RelicsBenchBlockRecipe(id, output, inputs);
        }

        @Override
        public void write(PacketByteBuf buf, RelicsBenchBlockRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());
            for (Ingredient ing : recipe.getIngredients()) {
                ing.write(buf);
            }
            buf.writeItemStack(recipe.getOutput());
        }
    }
}
