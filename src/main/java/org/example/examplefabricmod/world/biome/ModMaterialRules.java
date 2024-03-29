package org.example.examplefabricmod.world.biome;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.block.ModBlocks;
import terrablender.api.SurfaceRuleManager;

public class ModMaterialRules {
    private static final MaterialRules.MaterialRule DIRT_BLOCK = makeStateRule(ModBlocks.BLOODY_DIRT_BLOCK);
     private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule RED_TERRACOTTA = makeStateRule(Blocks.RED_TERRACOTTA);
    private static final MaterialRules.MaterialRule BLUE_TERRACOTTA = makeStateRule(Blocks.BLUE_TERRACOTTA);

    private static final MaterialRules.MaterialRule BLOODY_BLOCK = makeStateRule(ModBlocks.BLOODY_CRYSTAL_BLOCK);

    // 制作规则
    public static MaterialRules.MaterialRule makeRules() {
        /*
        原版MC提供了6种材料条件（MaterialCondition）：
        STONE_DEPTH_FLOOR：生物群系地表材料，替换原版的草方块
        STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH：生物群系地表材料与地表深度，效果为替换地表泥土层，替换包括草方块在内的所有泥土方块
         STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6：生物群系地表材料，深度为6
        STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_30：生物群系地表材料，深度为30（实测深度可以达到繁花洞穴深度）
        STONE_DEPTH_CEILING：生物群系天花板，地下开放空间天花板层
        STONE_DEPTH_CEILING_WITH_SURFACE_DEPTH：生物群系天花板与地表深度
         */
        return MaterialRules.sequence(
                // 如果想要将生物群系的地表替换为两种方块
                // 需要先条用“STONE_DEPTH_FLOOR”替换地表
                // 再用“STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH”等其他深层替换，否则地表的材料会同时被替换掉
                MaterialRules.condition(
                        MaterialRules.biome(ModBiomes.BLOODY_PLAIN),
                        MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, GRASS_BLOCK)),
                MaterialRules.condition(
                        MaterialRules.biome(ModBiomes.BLOODY_PLAIN),
                        MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH, DIRT_BLOCK)),
                // 血腥晶洞自定义
                MaterialRules.condition(
                        MaterialRules.biome(ModBiomes.BLOODY_CAVES),
                        MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6, BLOODY_BLOCK)),
                MaterialRules.condition(
                        MaterialRules.biome(ModBiomes.BLOODY_CAVES),
                        MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING_WITH_SURFACE_DEPTH, BLUE_TERRACOTTA)),
                //  全部自定义生物群系通用规则
                MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, RED_TERRACOTTA)
        );
    }

    // 制作状态规则
    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }

    public static void registerModMaterialRules() {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, ExampleFabricMod.MOD_ID, ModMaterialRules.makeRules());
        ExampleFabricMod.LOGGER.info("Registering ModMaterialRules for " + ExampleFabricMod.MOD_ID);
    }
}
