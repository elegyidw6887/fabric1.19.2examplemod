package org.example.examplefabricmod.world.biome;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import org.example.examplefabricmod.ExampleFabricMod;

public class ModBiomes {

    // 自定义生物群系注册表项的创建
    public static final RegistryKey<Biome> THE_SAME_AS_PLAIN = RegistryKey.of(Registry.BIOME_KEY,
            new Identifier(ExampleFabricMod.MOD_ID, "the_same_as_plain"));

    public static final RegistryKey<Biome> BLOODY_CAVES = RegistryKey.of(Registry.BIOME_KEY,
            new Identifier(ExampleFabricMod.MOD_ID, "bloody_caves"));

    public static void registerModBiomes() {

        // 将自定义生物群系添加到注册表中
        Registry.register(BuiltinRegistries.BIOME, THE_SAME_AS_PLAIN.getValue(), ModOverworldBiomes.THE_SAME_AS_PLAIN());
        Registry.register(BuiltinRegistries.BIOME, BLOODY_CAVES.getValue(), ModOverworldBiomes.BLOODY_CAVES());

        ExampleFabricMod.LOGGER.info("Registering ModBiomes for " + ExampleFabricMod.MOD_ID);
    }
}
