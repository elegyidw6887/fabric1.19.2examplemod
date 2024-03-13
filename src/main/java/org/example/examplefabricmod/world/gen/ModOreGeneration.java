package org.example.examplefabricmod.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import org.example.examplefabricmod.world.feature.ModPlacedFeatures;

public class ModOreGeneration {

    public static void generateOres() {
//        // 紫水晶矿石配置#4
//        BiomeModifications.addFeature(
//                BiomeSelectors.foundInOverworld(),
//                GenerationStep.Feature.UNDERGROUND_ORES,
//                ModPlacedFeatures.AMETHYST_ORE_PLACED.getKey().get());

        // 下界矿石放置#4
        // “BiomeSelectors”修改为对应的世界
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheNether(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                // “ModPlacedFeatures”修改为对应的矿石放置特征
                ModPlacedFeatures.NETHER_AMETHYST_ORE_PLACED.getKey().get());

        // 末地矿石放置#4
        // “BiomeSelectors”修改为对应的世界
        BiomeModifications.addFeature(
                BiomeSelectors.foundInTheEnd(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                // “ModPlacedFeatures”修改为对应的矿石放置特征
                ModPlacedFeatures.END_AMETHYST_ORE_PLACED.getKey().get());

//        // 紫水晶晶洞配置#3
//        BiomeModifications.addFeature(
//                BiomeSelectors.foundInOverworld(),
//                GenerationStep.Feature.UNDERGROUND_ORES,
//                ModPlacedFeatures.CUSTOM_GEODE_PLACED.getKey().get());
    }
}
