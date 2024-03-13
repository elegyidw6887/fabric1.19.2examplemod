package org.example.examplefabricmod.world.feature;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;

public class ModBiomeFeatures {

    public static void addAmethystOre(GenerationSettings.Builder biomeBuilder) {

        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.AMETHYST_ORE_PLACED);
    }

    public static void addAmethystGeodes(GenerationSettings.Builder biomeBuilder) {

        biomeBuilder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ModPlacedFeatures.CUSTOM_GEODE_PLACED);
    }
}
