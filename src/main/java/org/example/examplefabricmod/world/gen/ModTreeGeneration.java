package org.example.examplefabricmod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import org.example.examplefabricmod.world.biome.ModBiomes;
import org.example.examplefabricmod.world.feature.ModPlacedFeatures;

public class ModTreeGeneration {

    // 蓝花楹树配置#4
    public static void generationTree() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.BLOODY_PLAIN),
                GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.JACARANDA_PLACED.getKey().get());
    }
}
