package org.example.examplefabricmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.block.CustomBlock.AmethystLampBlock;
import org.example.examplefabricmod.block.CustomBlock.GrapeVineBlock;
import org.example.examplefabricmod.block.CustomBlock.InjectionBenchBlock;
import org.example.examplefabricmod.block.CustomBlock.JumpBlock;
import org.example.examplefabricmod.fluid.ModFluids;
import org.example.examplefabricmod.util.ModItemGroup;
import org.example.examplefabricmod.util.PublicConstructor.ModFluidBlock;
import org.example.examplefabricmod.world.feature.Tree.JacarandaSaplingGenerator;

import java.util.List;

public class ModBlocks {

    // 紫水晶块与矿石
    public static final Block AMETHYST_BLOCK = registerBlock("amethyst_block",
            new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0F).requiresTool()), ModItemGroup.LOSTsMOD);
    public static final Block AMETHYST_ORE = registerBlock("amethyst_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(3.0F).requiresTool(), UniformIntProvider.create(2, 6)), ModItemGroup.LOSTsMOD);
    public static final Block DEEPSLATE_AMETHYST_ORE = registerBlock("deepslate_amethyst_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(4.5F).requiresTool(), UniformIntProvider.create(2, 6)), ModItemGroup.LOSTsMOD);
    // 跳跃方块
    public static final Block JUMP_BLOCK = registerBlock("jump_block",
            new JumpBlock(FabricBlockSettings.of(Material.METAL).hardness(4.0F).requiresTool()), ModItemGroup.LOSTsMOD);
    // 紫水晶灯方块
    public static final Block AMETHYST_LAMP_BLOCK = registerBlock("amethyst_lamp_block",
            new AmethystLampBlock(FabricBlockSettings.of(Material.STONE).hardness(4.5F).requiresTool()
                    .luminance((state) -> (state.get(AmethystLampBlock.CLICKED) ? 15 : 0)) // 三目运算符
            ), ModItemGroup.LOSTsMOD);
    // 葡萄作物方块
    public static final Block GRAPE_VINE = registerBlockWithoutBlockItem("grape_vine",
            new GrapeVineBlock(FabricBlockSettings.copy(Blocks.WHEAT).nonOpaque()));
    // 蓝花楹原木、木头、去皮原木以及去皮木头方块
    public static final Block JACARANDA_LOG = registerBlock("jacaranda_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG)), ModItemGroup.LOSTsMOD);
    public static final Block JACARANDA_WOOD = registerBlock("jacaranda_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD)), ModItemGroup.LOSTsMOD);
    public static final Block STRIPPED_JACARANDA_LOG = registerBlock("stripped_jacaranda_log",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG)), ModItemGroup.LOSTsMOD);
    public static final Block STRIPPED_JACARANDA_WOOD = registerBlock("stripped_jacaranda_wood",
            new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD)), ModItemGroup.LOSTsMOD);
    // 蓝花楹木板方块
    public static final Block JACARANDA_PLANKS = registerBlock("jacaranda_planks",
            new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS)), ModItemGroup.LOSTsMOD);
    // 蓝花楹树叶方块
    public static final Block JACARANDA_LEAVES = registerBlock("jacaranda_leaves",
            new LeavesBlock(FabricBlockSettings.copy(Blocks.AZALEA_LEAVES)), ModItemGroup.LOSTsMOD);
    // 蓝花楹树苗方块
    public static final Block JACARANDA_SAPLING = registerBlock("jacaranda_sapling",
            new SaplingBlock(new JacarandaSaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ModItemGroup.LOSTsMOD);
    // 花方块与花盆
    public static final Block LILAC_FLOWER = registerBlock("lilac_flower",
            new FlowerBlock(StatusEffects.HASTE, 12,
                    FabricBlockSettings.copy(Blocks.DANDELION).strength(4.0F).nonOpaque()), ModItemGroup.LOSTsMOD);
    public static final Block POTTED_LILAC_FLOWER = registerBlockWithoutBlockItem("lilac_flower_potted",
            new FlowerPotBlock(ModBlocks.LILAC_FLOWER, FabricBlockSettings.copy(Blocks.POTTED_DANDELION)));

    //////////////////////////////////////////////////正式版MOD测试内容//////////////////////////////////////////////////
    // 血晶石
    public static final Block BLOODY_CRYSTAL_BLOCK = registerBlock("bloody_crystal_block",
            new Block(FabricBlockSettings.of(Material.STONE).requiresTool()), ModItemGroup.THE_LOST_LAND);
    // 血晶矿
    public static final Block BLOODY_CRYSTAL_ORE = registerBlock("bloody_crystal_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(3.0F).requiresTool(), UniformIntProvider.create(2, 6)), ModItemGroup.THE_LOST_LAND);
    // 深层血晶矿
    public static final Block DEEPSLATE_BLOODY_CRYSTAL_ORE = registerBlock("deepslate_bloody_crystal_ore",
            new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(4.5F).requiresTool(), UniformIntProvider.create(2, 6)), ModItemGroup.THE_LOST_LAND);
    //
    public static final Block BLOODY_DIRT_BLOCK = registerBlock("bloody_dirt_block",
            new GrassBlock(FabricBlockSettings.of(Material.SOLID_ORGANIC).ticksRandomly().strength(0.6f).sounds(BlockSoundGroup.GRASS)) , ModItemGroup.THE_LOST_LAND);
    // 血流体方块
    public static final Block BLOOD = registerBlockWithoutBlockItem("blood_block",
            new ModFluidBlock(ModFluids.STILL_BLOOD, FabricBlockSettings.of(Material.WATER).noCollision().nonOpaque().dropsNothing()));
    // 注入工作台方块
    public static final Block INJECTION_BENCH_BLOCK = registerBlock("injection_bench_block",
            new InjectionBenchBlock(FabricBlockSettings.of(Material.STONE).requiresTool()), ModItemGroup.THE_LOST_LAND);


    // 调用类中方块相关物品对象注册方法，同时做到方块与方块相关物品对象的注册
    private static Block registerBlock(String name, Block block, ItemGroup itemGroup) { // 注册方块的同时注册一个对应的物品
        registerBlockItem(name, block, itemGroup);
        return Registry.register(Registry.BLOCK, new Identifier(ExampleFabricMod.MOD_ID, name), block);
    }

    // 调用类中方块相关物品对象注册方法，进行方块与方块相关物品对象的注册，同时添加物品提示
    private static Block registerBlockWithTooltip(String name, Block block, ItemGroup itemGroup, String tooltips) { // 注册方块的同时注册一个有物品提示的对应物品
        registerBlockItemWithTooltip(name, block, itemGroup, tooltips);
        return Registry.register(Registry.BLOCK, new Identifier(ExampleFabricMod.MOD_ID, name), block);
    }

    // 调用类中方块相关物品对象注册方法，同时不进行方块相关物品对象的注册
    private static Block registerBlockWithoutBlockItem(String name, Block block) { // 注册方块的同时不进行物品的注册
        return Registry.register(Registry.BLOCK, new Identifier(ExampleFabricMod.MOD_ID, name), block);
    }

    // 方块物品注册方法
    private static void registerBlockItem(String name, Block block, ItemGroup itemGroup) { // 注册与方块对应的物品
        Registry.register(Registry.ITEM, new Identifier(ExampleFabricMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(itemGroup)));
    }

    // 方块物品与物品提示注册方法
    private static void registerBlockItemWithTooltip(String name, Block block, ItemGroup itemGroup, String tooltips) { // 注册与方块对应并且有物品提示的物品
        Registry.register(Registry.ITEM, new Identifier(ExampleFabricMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(itemGroup)) {
                    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
                        tooltip.add(Text.literal(tooltips));
                    }
                });
    }

    public static void registerModBlocks() {
        ExampleFabricMod.LOGGER.info("Registering ModBlocks for " + ExampleFabricMod.MOD_ID);
    }
}
