package org.example.examplefabricmod.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import org.example.examplefabricmod.ExampleFabricMod;

public class ModFuels {

    private static void registerFuel(Item item, Integer integer) {
        FuelRegistry registry = FuelRegistry.INSTANCE;
        registry.add(item, integer);
    }

    public static void registerModFuels() {
        registerFuel(ModItems.FRAGMENT_OF_AMETHYST, 2000);
        ExampleFabricMod.LOGGER.info("Registering ModIFuels for " + ExampleFabricMod.MOD_ID);
    }
}
