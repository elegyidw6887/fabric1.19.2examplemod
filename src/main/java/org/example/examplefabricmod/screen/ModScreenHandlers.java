package org.example.examplefabricmod.screen;

import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.screen.InjectionBenchBlock.InjectionBenchBlockScreenHandler;

public class ModScreenHandlers {

    public  static ScreenHandlerType<InjectionBenchBlockScreenHandler> INJECTION_BENCH_BLOCK_SCREEN_HANDLER;

    public static void registerScreenHandlers() {

        INJECTION_BENCH_BLOCK_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(
                new Identifier(ExampleFabricMod.MOD_ID, "injection_bench_block"),
                InjectionBenchBlockScreenHandler::new);

        ExampleFabricMod.LOGGER.info("Registering ModScreenHandlers for " + ExampleFabricMod.MOD_ID);
    }
}
