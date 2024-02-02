package org.example.examplefabricmod.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;
import org.example.examplefabricmod.ExampleFabricMod;

public class ModPlacedFeatures {

    // 蓝花楹树配置#3
    public static final RegistryEntry<PlacedFeature> JACARANDA_PLACED = PlacedFeatures.register(
            "jacaranda_placed",
            ModConfiguredFeatures.JACARANDA_SPAWN,
            /*
              PlacedFeatures.createCountExtraModifier()参数解释：
              在1（参数1）棵树的周围有1（参数1）/0.1F（参数2）的概率再额外放置2（参数3）棵树
              其中计算式必须为整数
             */
            VegetationPlacedFeatures.modifiers(PlacedFeatures.createCountExtraModifier(1, 0.1F, 2)));

    // 紫丁香配置#2
    public static final RegistryEntry<PlacedFeature> LILAC_PLACED = PlacedFeatures.register(
            /*
            参数1：id
            参数2：ModConfiguredFeatures对象，选用创建的紫丁香生成对象
            参数3：稀有过滤器放置修改器，关系到生成概率
            参数4：方块放置修改器，不需要参数
            参数5：PF参数，我们选用原版的PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP
            参数6：生物群落放置修改器，不需要参数*/
            "lilac_placed",
            ModConfiguredFeatures.LILAC_SPAWN,
            RarityFilterPlacementModifier.of(4),
            SquarePlacementModifier.of(),
            PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP,
            BiomePlacementModifier.of());

    // 紫水晶矿石配置#3
    public static final RegistryEntry<PlacedFeature> AMETHYST_ORE_PLACED =
            PlacedFeatures.register(
                    /*
                    参数1：id
                    参数2：配置特征对象
                    参数3：调用.modifiersWithCount()方法，并传入两个参数 */
                    "amethyst_ore_placed",
                    ModConfiguredFeatures.AMETHYST_ORE,
                    ModOreFeatures.modifiersWithCount(
                            /*
                            参数1：数量
                            参数2：高度范围放置修改器，调用.trapezoid()方法并传入两个YOffset.aboveBottom()参数，第一个为Y轴生成下限，第二个为Y轴生成上限*/
                            7,
                            HeightRangePlacementModifier.trapezoid(YOffset.aboveBottom(-88), YOffset.aboveBottom(80))));

    // 下界矿石放置特征添加#3
    public static final RegistryEntry<PlacedFeature> NETHER_AMETHYST_ORE_PLACED =
            PlacedFeatures.register(
                    "nether_amethyst_ore_placed",
                    // “ModConfiguredFeatures”需要修改为对应注册表项
                    ModConfiguredFeatures.NETHER_AMETHYST_ORE,
                    ModOreFeatures.modifiersWithCount(
                            7,
                            // “.trapezoid”调用要修改为“.uniform”调用
                            HeightRangePlacementModifier.uniform(YOffset.aboveBottom(-88), YOffset.aboveBottom(80))));

    // 末地矿石放置特征添加#3
    public static final RegistryEntry<PlacedFeature> END_AMETHYST_ORE_PLACED =
            PlacedFeatures.register(
                    "end_amethyst_ore_placed",
                    // “ModConfiguredFeatures”需要修改为对应注册表项
                    ModConfiguredFeatures.END_AMETHYST_ORE,
                    ModOreFeatures.modifiersWithCount(
                            7,
                            // “.trapezoid”调用要修改为“.uniform”调用
                            HeightRangePlacementModifier.uniform(YOffset.aboveBottom(-88), YOffset.aboveBottom(80))));

    // 紫水晶晶洞配置#2
    public static final RegistryEntry<PlacedFeature> CUSTOM_GEODE_PLACED =
            PlacedFeatures.register(
                    "custom_geode_placed",
                    ModConfiguredFeatures.CUSTOM_GEODE,
                    // 稀有度滤镜放置修改器，生成概率为1/42
                    RarityFilterPlacementModifier.of(42),
                    // 正方形放置修改器
                    SquarePlacementModifier.of(),
                    // 高度范围放置修改器，将在Y轴6到50之间进行生成
                    HeightRangePlacementModifier.uniform(YOffset.aboveBottom(6), YOffset.aboveBottom(50)),
                    // 生物群落放置编辑
                    BiomePlacementModifier.of());

    public static void registerModPlacedFeature() {
        ExampleFabricMod.LOGGER.info("Registering ModPlacedFeature for " + ExampleFabricMod.MOD_ID);
    }
}
