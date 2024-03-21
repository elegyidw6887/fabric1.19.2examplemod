package org.example.examplefabricmod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import org.example.examplefabricmod.world.biome.ModBiomes;
import org.example.examplefabricmod.world.feature.ModPlacedFeatures;

public class ModFlowerGeneration {

    public static void generateFlower() {

        // 紫丁香配置#2
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.BLOODY_PLAIN),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.LILAC_PLACED.getKey().get());
    }
}
