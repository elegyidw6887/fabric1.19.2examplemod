package org.example.examplefabricmod.item;

import net.minecraft.item.FoodComponent;

public class ModFoods {

    public static final FoodComponent GRAPE = new FoodComponent.Builder().hunger(3).saturationModifier(0.3F).build();
    public static final FoodComponent BLOODY_BOTTLE = new FoodComponent.Builder().hunger(0).build();
}
