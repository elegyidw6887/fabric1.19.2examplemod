package org.example.examplefabricmod.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {

    // 蓝花楹树的配置（对象的具体实现可以参考原版橡木的相关内容，简单树木的实现方式类似）
    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> JACARANDA_TREE = ConfiguredFeatures.register(

            /*参数1：id
            参数2：Feature对象，我们选择原版的Feature.TREE
            参数3：TreeFeatureConfig对象，我们new TreeFeatureConfig.Builder()来创建一个*/
            "jacaranda_tree",
            Feature.TREE,
            new TreeFeatureConfig.Builder(
                    /*参数1：方块状态提供器，此处提供的是树干对应的方块
                    参数2：直树干放置器，三个参数分别为“baseHeight”、“firstRandomHeight”与“secondRandomHeight”
                    参数3：方块状态提供器，此处提供的是树叶对应的方块
                    参数4：Blob树叶放置器，三个参数分别为“radius”、“offset”与树叶的高度
                    参数5：两层特征大小，三个参数分别为“limit”、“lowerSize”与“upperSize” */
                    BlockStateProvider.of(ModBlocks.JACARANDA_LOG),
                    new StraightTrunkPlacer(5, 6, 3),
                    BlockStateProvider.of(ModBlocks.JACARANDA_LEAVES),
                    new BlobFoliagePlacer(
                            ConstantIntProvider.create(2),
                            ConstantIntProvider.create(0),
                            4),
                    new TwoLayersFeatureSize(1, 0, 2))
                    .build());

    // 蓝花楹树配置#1
    public static final RegistryEntry<PlacedFeature> JACARANDA_CHECKED = PlacedFeatures.register(
            "jacaranda_checked",
            ModConfiguredFeatures.JACARANDA_TREE,
            List.of(PlacedFeatures.wouldSurvive(ModBlocks.JACARANDA_SAPLING)));

    // 蓝花楹树配置#2
    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> JACARANDA_SPAWN = ConfiguredFeatures.register(
            "jacaranda_spawn",
            Feature.RANDOM_SELECTOR,
            new RandomFeatureConfig(List.of(new RandomFeatureEntry(JACARANDA_CHECKED, 0.5F)), JACARANDA_CHECKED));

    // 紫丁香配置#1
    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> LILAC_SPAWN = ConfiguredFeatures.register(
            /*参数1：id翻译键
            参数2：Feature对象，此处选择的是原版的花对象，也就是Feature.FLOWER
            参数3：ConfiguredFeatures对象，此处调用.createRandomPatchFeatureConfig()方法来创建随机补丁特征配置，其需要两个参数*/
            "lilac_spawn",
            Feature.FLOWER,
            ConfiguredFeatures.createRandomPatchFeatureConfig(
                    /*参数1：int类型参数，涉及到生成概率
                    参数2：PlacedFeature对象，此处调用.createEntry()方法来创建输入，其需要两个参数*/
                    64,
                    PlacedFeatures.createEntry(
                            /*参数1：Feature对象，此处涉及到花能够生成的方块，我们选用Feature.SIMPLE_BLOCK
                            参数2：SimpleBlockFeatureConfig对象，创建新对象并将花方块对象当作参数传入*/
                            Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(ModBlocks.LILAC_FLOWER)))));

    // 紫水晶矿石配置#1
    public static final List<OreFeatureConfig.Target> OVERWORLD_AMETHYST_ORES = List.of(
            OreFeatureConfig.createTarget(
                    /*
                    参数1：调用原版“OreConfiguredFeatures.STONE_ORE_REPLACEABLES”，替换原版石头方块
                    参数2：矿石方块状态，调用矿石方块.getDefaultState()来获取*/
                    OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
                    ModBlocks.AMETHYST_ORE.getDefaultState()),
            // 需要添加普通矿石与深层矿石
            OreFeatureConfig.createTarget(
                    OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
                    ModBlocks.DEEPSLATE_AMETHYST_ORE.getDefaultState()));

    // 紫水晶矿石配置#2
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> AMETHYST_ORE =
            ConfiguredFeatures.register(
                       /*
                    参数1：id
                    参数2：Feature对象，使用原版的Feature.ORE
                    参数3：OreFeatureConfig对象，第一个参数为List对象，第二个参数为大小*/
                    "amethyst_ore",
                    Feature.ORE,
                    new OreFeatureConfig(OVERWORLD_AMETHYST_ORES, 9));

    // 下界矿石List对象#1
    public static final List<OreFeatureConfig.Target> NETHER_AMETHYST_ORES = List.of(
            // “OreConfiguredFeatures”修改为末地对应的“BASE_STONE_NETHER”
            OreFeatureConfig.createTarget(
                    OreConfiguredFeatures.BASE_STONE_NETHER,
                    ModBlocks.AMETHYST_ORE.getDefaultState()));

    // 下界矿石配置特征添加#2
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> NETHER_AMETHYST_ORE =
            ConfiguredFeatures.register(
                    "nether_amethyst_ore",
                    Feature.ORE,
                    // “OreFeatureConfig”中要修改为对应的List对象
                    new OreFeatureConfig(NETHER_AMETHYST_ORES, 9));

    // 末地矿石List对象#1
    public static final List<OreFeatureConfig.Target> END_AMETHYST_ORES = List.of(
            /*
            “OreConfiguredFeatures”直接修改为新对象的创建：
            new BlockMatchRuleTest(Blocks.END_STONE)*/
            OreFeatureConfig.createTarget(
                    new BlockMatchRuleTest(Blocks.END_STONE),
                    ModBlocks.AMETHYST_ORE.getDefaultState()));

    // 末地矿石配置特征添加#2
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig, ?>> END_AMETHYST_ORE =
            ConfiguredFeatures.register(
                    "end_amethyst_ore",
                    Feature.ORE,
                    // “OreFeatureConfig”中要修改为对应的List对象
                    new OreFeatureConfig(END_AMETHYST_ORES, 9));

    // 紫水晶晶洞配置#1
    public static final RegistryEntry<ConfiguredFeature<GeodeFeatureConfig, ?>> CUSTOM_GEODE =
            ConfiguredFeatures.register(
                    "custom_geode",
                    Feature.GEODE,
                    new GeodeFeatureConfig(
                            // 晶洞层配置
                            new GeodeLayerConfig(
                                    BlockStateProvider.of(Blocks.AIR), // 参数1：fillingProvider，晶洞的填充，默认提供空气方块
                                    BlockStateProvider.of(ModBlocks.AMETHYST_LAMP_BLOCK), // 参数2：innerLayerProvider，内部层，默认提供紫水晶块（原版）
                                    BlockStateProvider.of(ModBlocks.AMETHYST_BLOCK),// 参数3：alternateInnerLayerProvider，互生内部层，默认提供紫水晶芽
                                    BlockStateProvider.of(Blocks.STONE),// 参数4：middleLayerProvider，中间层，默认提供方解石
                                    BlockStateProvider.of(Blocks.EMERALD_BLOCK),// 参数5：outerLayerProvider，外部层方块，默认提供平滑玄武岩
                                    List.of(ModBlocks.AMETHYST_BLOCK.getDefaultState()),// 参数6：innerBlocks，内部方块，需要一个List参数，原版调用了不同阶段的紫水晶芽与紫水晶簇
                                    BlockTags.FEATURES_CANNOT_REPLACE,// 参数7：cannotReplace
                                    BlockTags.GEODE_INVALID_BLOCKS),// 参数8：invalidBlocks
                            // 晶洞层厚度配置，原版为：1.7， 2.2，3.2，4.2
                            new GeodeLayerThicknessConfig(1.7, 2.2, 1.2, 4.2),
                            // 晶洞裂纹配置，原版为：0.95，2.0，2
                            new GeodeCrackConfig(0.95, 2.0, 2),
                            0.35,
                            0.1,
                            true,
                            // outerWallDistance，外壁距离，最大为8最小为3
                            UniformIntProvider.create(4, 6),
                            // distributionPoints，分发点，最大为6最小为2
                            UniformIntProvider.create(3, 4),
                            // pointOffset，点偏移，最大为2最小为1
                            UniformIntProvider.create(1, 2),
                            -18,
                            18,
                            0.075,
                            1)
            );

    public static void registerModConfiguredFeatures() {
        ExampleFabricMod.LOGGER.info("Registering ModConfiguredFeatures for " + ExampleFabricMod.MOD_ID);
    }
}
