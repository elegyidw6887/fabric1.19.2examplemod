package org.example.examplefabricmod.world.biome;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class ModOverworldBiomes {

    // 自定义生物群系的创建
    public static Biome THE_SAME_AS_PLAIN() {

        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addPlainsMobs(spawnBuilder);// 添加平原生物
        DefaultBiomeFeatures.addFarmAnimals(spawnBuilder);// 添加农场动物
        DefaultBiomeFeatures.addMonsters(spawnBuilder, 100, 20, 100, false);//添加怪物
        spawnBuilder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.GOAT, 5, 1, 1));// 主动生成只会在高山群系生成的山羊

        GenerationSettings.Builder biomeBuilder = new GenerationSettings.Builder();
        globalOverworldGeneration(biomeBuilder);// 添加主世界通用生物群系特征
        // 添加更多特征
        DefaultBiomeFeatures.addDefaultOres(biomeBuilder); // 添加默认矿物
        DefaultBiomeFeatures.addDefaultDisks(biomeBuilder);// 添加默认圆盘（类似圆盘图案的方块聚合体地物）
        DefaultBiomeFeatures.addInfestedStone(biomeBuilder);// 添加蠹虫方块
        DefaultBiomeFeatures.addPlainsTallGrass(biomeBuilder);// 添加平原高草
        DefaultBiomeFeatures.addPlainsFeatures(biomeBuilder);// 添加平原特征
        DefaultBiomeFeatures.addDefaultMushrooms(biomeBuilder);// 添加默认蘑菇
        DefaultBiomeFeatures.addDefaultVegetation(biomeBuilder);// 添加默认植被

        return biome(Biome.Precipitation.RAIN, 2.0F, 0.0F, spawnBuilder, biomeBuilder);
    }

    // 计算天空颜色
    protected static int calculateSkyColor(float temperature) {
        float f = temperature;
        f /= 3.0f;
        f = MathHelper.clamp(f, -1.0f, 1.0f);
        return MathHelper.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1.0f);
    }

    // 生物群系创建方法#1
    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall,  SpawnSettings.Builder spawnBuilder, GenerationSettings.Builder biomeBuilder)
    {
        // 方法1的参数小于方法2，因为将“waterColor”、“waterFogColor”、“skyColor”三个参数设为了固定值
        return biome(precipitation, temperature, downfall, 4159204, 329011,7843327,  spawnBuilder, biomeBuilder);
    }

    // 生物群系创建方法#2
    private static Biome biome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, int skyColor,  SpawnSettings.Builder spawnBuilder, GenerationSettings.Builder biomeBuilder)
    {
        return (new Biome.Builder())
                .precipitation(precipitation)// 降水类型
                .temperature(temperature)// 温度
                .downfall(downfall)// 降水（0.0-1.0之间，与火的随机熄灭机制相关）
                .effects(// 影响
                        (new BiomeEffects.Builder())
                                .waterColor(waterColor)// 水颜色
                                .waterFogColor(waterFogColor)// 水雾颜色
                                .fogColor(12638463)// 雾颜色
                                .skyColor(ModOverworldBiomes.calculateSkyColor(temperature))// 天空颜色
                                .moodSound(BiomeMoodSound.CAVE)// 环境音效
                                .music(null)
                                .build())
                .spawnSettings(spawnBuilder.build())// 生物生成设定
                .generationSettings(biomeBuilder.build())// 生物群系生成设定
                .build();
    }

    // 主世界通用生物群系特征
    public static void globalOverworldGeneration(GenerationSettings.Builder builder) {
        DefaultBiomeFeatures.addAmethystGeodes(builder);// 添加紫水晶晶洞
        DefaultBiomeFeatures.addLandCarvers(builder);// 添加陆地洞穴
        DefaultBiomeFeatures.addDungeons(builder);// 添加地牢
        DefaultBiomeFeatures.addMineables(builder);// 添加可开采物
        DefaultBiomeFeatures.addSprings(builder);// 添加涌泉（随机生成单个液体方块的地物）
        DefaultBiomeFeatures.addFrozenTopLayer(builder);// 添加冰冻顶层（主世界中由雪和冰组成的一种地物）
    }
}
