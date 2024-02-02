package org.example.examplefabricmod.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.example.examplefabricmod.ExampleFabricMod;

public class ModSounds {

    public static SoundEvent BAR_BRAWL = registerSoundEvent("bar_brawl"); // 创建一个声音事件对象

    private static SoundEvent registerSoundEvent(String name) { // 返回值为SoundEvent的方法
        Identifier id = new Identifier(ExampleFabricMod.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id)); // 返回一个SoundEvent事件注册
    }
}
