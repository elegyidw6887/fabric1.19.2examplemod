package org.example.examplefabricmod.world.feature.Tree;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.example.examplefabricmod.world.feature.ModConfiguredFeatures;
import org.jetbrains.annotations.Nullable;

public class JacarandaSaplingGenerator extends SaplingGenerator {

    @Nullable
    @Override
    protected RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> getTreeFeature(Random random, boolean bees) {
        return ModConfiguredFeatures.JACARANDA_TREE;
    }
}
