package org.example.examplefabricmod.recipe;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.recipe.CustomRecipe.InjectionBenchBlockRecipe;
import org.example.examplefabricmod.recipe.CustomRecipe.RelicsBenchBlockRecipe;

public class ModRecipes {


    public static void registerModRecipes() {

        // 注入工作台类型与序列化处理器注册
        Registry.register(Registry.RECIPE_TYPE, new Identifier(ExampleFabricMod.MOD_ID, InjectionBenchBlockRecipe.Type.ID),
                InjectionBenchBlockRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(ExampleFabricMod.MOD_ID, InjectionBenchBlockRecipe.Serializer.ID),
                InjectionBenchBlockRecipe.Serializer.INSTANCE);

        Registry.register(Registry.RECIPE_TYPE, new Identifier(ExampleFabricMod.MOD_ID, RelicsBenchBlockRecipe.Type.ID),
                RelicsBenchBlockRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(ExampleFabricMod.MOD_ID, RelicsBenchBlockRecipe.Serialize.ID),
                RelicsBenchBlockRecipe.Serialize.INSTANCE);

        ExampleFabricMod.LOGGER.info("Registering ModRecipes for " + ExampleFabricMod.MOD_ID);
    }
}
