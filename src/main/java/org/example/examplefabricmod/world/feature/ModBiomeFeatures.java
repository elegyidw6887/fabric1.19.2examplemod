package org.example.examplefabricmod.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.NoiseThresholdCountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import org.example.examplefabricmod.block.ModBlocks;

public class ModBiomeFeatures {

    public static void addAmethystOre(GenerationSettings.Builder biomeBuilder) {

        biomeBuilder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.AMETHYST_ORE_PLACED);
    }

    public static void addAmethystGeodes(GenerationSettings.Builder biomeBuilder) {

        biomeBuilder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, ModPlacedFeatures.CUSTOM_GEODE_PLACED);
    }
}
