package org.example.examplefabricmod.event;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.event.CustomEvent.AttackEntityHandler;
import org.example.examplefabricmod.event.CustomEvent.KeyInputHandler;
import org.example.examplefabricmod.event.CustomEvent.PlayerTickHandler;
import org.example.examplefabricmod.event.CustomEvent.UseBloodyBottleHandler;

public class ModEventsHandler {

    public static void registerModServerEventsHandler() {

        // 注册刻事件对象实现口渴值的随机降低
        ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());
        // 注册攻击羊时发送文字的事件
        AttackEntityHandler.EVENT.register(new AttackEntityHandler());

        UseBloodyBottleHandler.EVENT.register(new UseBloodyBottleHandler());

        ExampleFabricMod.LOGGER.info("Registering ModServerEventsHandler for " + ExampleFabricMod.MOD_ID);
    }

    public static void registerModClientEventsHandler() {

        // 模组快捷键注册
        KeyInputHandler.register();

        ExampleFabricMod.LOGGER.info("Registering ModClientEventsHandler for " + ExampleFabricMod.MOD_ID);
    }
}
