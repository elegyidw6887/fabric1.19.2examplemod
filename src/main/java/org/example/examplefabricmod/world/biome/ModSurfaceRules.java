package org.example.examplefabricmod.world.biome;

import net.minecraft.block.Block;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import org.example.examplefabricmod.block.ModBlocks;

public class ModSurfaceRules {
    private static final MaterialRules.MaterialRule AMETHYST_BLOCK = makeStateRule(ModBlocks.AMETHYST_BLOCK);

    // 制作规则
    public static MaterialRules.MaterialRule makeRules() {
        /*
        TerraBlender提供了6种材料条件（MaterialCondition）：
        STONE_DEPTH_FLOOR：生物群系地表材料，替换原版的草方块
        STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH：生物群系地表材料与地表深度，效果为替换地表泥土层，替换包括草方块在内的所有泥土方块
         STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_6：生物群系地表材料，深度为6
        STONE_DEPTH_FLOOR_WITH_SURFACE_DEPTH_RANGE_30：生物群系地表材料，深度为30（实测深度可以达到繁花洞穴深度）
        STONE_DEPTH_CEILING：生物群系天花板
        STONE_DEPTH_CEILING_WITH_SURFACE_DEPTH：生物群系天花板与地表深度
         */
        return MaterialRules.sequence(MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, AMETHYST_BLOCK));
    }

    // 制作状态规则
    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
