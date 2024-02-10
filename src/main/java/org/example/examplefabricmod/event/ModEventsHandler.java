package org.example.examplefabricmod.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.example.examplefabricmod.ExampleFabricMod;

public class ModEventsHandler {

    public static void registerModServerEventsHandler() {

        // 注册刻事件对象实现口渴值的随机降低
        ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());

        ExampleFabricMod.LOGGER.info("Registering ModServerEventsHandler for " + ExampleFabricMod.MOD_ID);
    }

    public static void registerModClientEventsHandler() {

        // 模组快捷键注册
        ModKeyInputHandler.register();

        ExampleFabricMod.LOGGER.info("Registering ModClientEventsHandler for " + ExampleFabricMod.MOD_ID);
    }
}
