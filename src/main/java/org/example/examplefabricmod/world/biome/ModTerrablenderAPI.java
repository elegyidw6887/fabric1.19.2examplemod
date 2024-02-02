package org.example.examplefabricmod.world.biome;

import net.minecraft.util.Identifier;
import org.example.examplefabricmod.ExampleFabricMod;
import terrablender.api.RegionType;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerrablenderAPI implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized() {

        // 注册对象实现自定义生物群系的生成
        Regions.register(new ModOverworldRegions(new Identifier(ExampleFabricMod.MOD_ID, "add_overworld_biome"), RegionType.OVERWORLD, 2));
        // 调用方法实现规则的添加
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, ExampleFabricMod.MOD_ID, ModSurfaceRules.makeRules());
    }
}
