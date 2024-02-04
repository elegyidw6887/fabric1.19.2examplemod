package org.example.examplefabricmod.screen;

import net.minecraft.screen.ScreenHandlerType;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.screen.InjectionBenchBlock.InjectionBenchBlockScreenHandler;

public class ModScreenHandlers {

    public static ScreenHandlerType<InjectionBenchBlockScreenHandler> INJECTION_BENCH_BLOCK_SCREEN_HANDLER;

    public static void registerModScreenHandlers() {

        INJECTION_BENCH_BLOCK_SCREEN_HANDLER = new ScreenHandlerType<>(InjectionBenchBlockScreenHandler::new);

        ExampleFabricMod.LOGGER.info("Registering ModScreenHandlers for " + ExampleFabricMod.MOD_ID);
    }
}
