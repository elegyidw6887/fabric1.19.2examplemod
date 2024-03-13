package org.example.examplefabricmod.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.effect.ModEffects;

public class ModPotions {

    // 冰冻药水
    public static Potion FREEZE_POTION = registerPotion("freeze_potion",
            new Potion(new StatusEffectInstance(ModEffects.FREEZE, 200, 0)));

    private static Potion registerPotion(String name, Potion potion) {
        return Registry.register(Registry.POTION, new Identifier(ExampleFabricMod.MOD_ID, name), potion);
    }

    private static void registerPotionRecipes() { // 方法调用
        BrewingRecipeRegistry.registerPotionRecipe(
                /*
                参数1：输入药水
                参数2：炼药的物品
                参数3：输出药水
                 */
                Potions.AWKWARD, ModItems.AMETHYST, ModPotions.FREEZE_POTION);
    }

    public static void registerModPotions() {
        ExampleFabricMod.LOGGER.info("Registering ModPotions for " + ExampleFabricMod.MOD_ID);
        // 我们需要在接口方法中添加方法的调用
        registerPotionRecipes();
    }
}
