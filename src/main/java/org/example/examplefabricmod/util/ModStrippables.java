package org.example.examplefabricmod.util;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.block.ModBlocks;

public class ModStrippables {

    public static void registerStrippables() {
        StrippableBlockRegistry.register(ModBlocks.JACARANDA_LOG, ModBlocks.STRIPPED_JACARANDA_LOG);
        StrippableBlockRegistry.register(ModBlocks.JACARANDA_WOOD, ModBlocks.STRIPPED_JACARANDA_WOOD);
    }

    public static void registerModStrippables() {
        registerStrippables();
        ExampleFabricMod.LOGGER.info("Registering ModStrippables for " + ExampleFabricMod.MOD_ID);
    }
}
