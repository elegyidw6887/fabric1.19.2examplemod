package org.example.examplefabricmod.world.biome.TerraBlender;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import org.example.examplefabricmod.world.biome.ModBiomes;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class ModOverworldRegions extends Region {

    // 构造方法
    public ModOverworldRegions(Identifier name, RegionType type, int weight) {
        super(name, type, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, modifiedVanillaOverworldBuilder -> {
            // 将原版游戏中沙漠群系替换为自定义生物群系
            modifiedVanillaOverworldBuilder.replaceBiome(BiomeKeys.DESERT, ModBiomes.THE_SAME_AS_PLAIN);
            // 血腥洞穴替换繁花洞穴
            modifiedVanillaOverworldBuilder.replaceBiome(BiomeKeys.LUSH_CAVES, ModBiomes.BLOODY_CAVES);
        });
    }
}
