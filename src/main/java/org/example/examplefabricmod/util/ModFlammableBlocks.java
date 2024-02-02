package org.example.examplefabricmod.util;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.block.ModBlocks;

public class ModFlammableBlocks {

    private static void registerFlammableBlock() {

        FlammableBlockRegistry instance = FlammableBlockRegistry.getDefaultInstance();

        instance.add(ModBlocks.JACARANDA_LOG, 5, 5);
        instance.add(ModBlocks.JACARANDA_WOOD, 5, 5);
        instance.add(ModBlocks.STRIPPED_JACARANDA_LOG, 5, 5);
        instance.add(ModBlocks.STRIPPED_JACARANDA_WOOD, 5, 5);
        instance.add(ModBlocks.JACARANDA_PLANKS, 5, 20);
        instance.add(ModBlocks.JACARANDA_LEAVES, 30, 60);
    }

    public static void registerModFlammableBlocks() {
        registerFlammableBlock();
        ExampleFabricMod.LOGGER.info("Registering ModFlammableBlocks for " + ExampleFabricMod.MOD_ID);
    }
}
