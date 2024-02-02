package org.example.examplefabricmod.util;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.example.examplefabricmod.item.ModItems;

public class ModModelPredicateProvider {

    public static void registerModModels() {
        // 弓物品注册
        registerBow(ModItems.AMETHYST_BOW);
    }

    private static void registerBow(Item bow) {
        // 判定拉弓的程度
        ModelPredicateProviderRegistry.register(bow, new Identifier("pull"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0F;
                    } else {
                        return entity.getActiveItem() != stack ? 0.0F : (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.0F;
                    }
                });
        // 判定是否拉动
        ModelPredicateProviderRegistry.register(bow, new Identifier("pulling"),
                ((stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F));
    }
}
