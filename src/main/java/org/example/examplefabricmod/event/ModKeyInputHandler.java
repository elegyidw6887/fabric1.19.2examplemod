package org.example.examplefabricmod.event;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.example.examplefabricmod.network.ModPacketHandler;
import org.lwjgl.glfw.GLFW;

public class ModKeyInputHandler {

    public static final String KEY_CATEGORY_EXAMPLE = "key.category.examplefabricmod.example";
    public static final String KEY_DRINK_WATER = "key.examplefabricmod.drink_water";
    public static KeyBinding DrinkingKey;

    public static void registerKeyInputEvents() {
        // 实现按键功能的事件注册
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (DrinkingKey.wasPressed()) {
                // 发送一个空的缓冲区
                ClientPlayNetworking.send(ModPacketHandler.DRINKING_ID, PacketByteBufs.create());
            }
        });
    }

    public static void register() {
        // 喝水按键绑定
        DrinkingKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                // 翻译键
                KEY_DRINK_WATER,
                // 按键绑定类型
                InputUtil.Type.KEYSYM,
                // 选择要绑定的按键
                GLFW.GLFW_KEY_R,
                // 类别
                KEY_CATEGORY_EXAMPLE
        ));
        registerKeyInputEvents();
    }
}
