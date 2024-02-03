package org.example.examplefabricmod.Recipe;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.Recipe.CustomRecipe.InjectionBenchBlockRecipe;

public class ModRecipes {


    public static void registerModRecipes() {

        // 注入工作台类型与序列化处理器注册
        Registry.register(Registry.RECIPE_TYPE, new Identifier(ExampleFabricMod.MOD_ID, InjectionBenchBlockRecipe.Type.ID),
                InjectionBenchBlockRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(ExampleFabricMod.MOD_ID, InjectionBenchBlockRecipe.Serializer.ID),
                InjectionBenchBlockRecipe.Serializer.INSTANCE);

        ExampleFabricMod.LOGGER.info("Registering ModRecipes for " + ExampleFabricMod.MOD_ID);
    }
}
