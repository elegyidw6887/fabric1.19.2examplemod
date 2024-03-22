package org.example.examplefabricmod;

import net.fabricmc.api.ModInitializer;
import org.example.examplefabricmod.block.ModBlocks;
import org.example.examplefabricmod.effect.ModEffects;
import org.example.examplefabricmod.enchantment.ModEnchantments;
import org.example.examplefabricmod.entity.ModBlockEntities;
import org.example.examplefabricmod.event.ModEventsHandler;
import org.example.examplefabricmod.fluid.ModFluids;
import org.example.examplefabricmod.item.ModFuels;
import org.example.examplefabricmod.item.ModItems;
import org.example.examplefabricmod.item.ModPaintings;
import org.example.examplefabricmod.item.ModPotions;
import org.example.examplefabricmod.network.ModPacketHandler;
import org.example.examplefabricmod.recipe.ModRecipes;
import org.example.examplefabricmod.screen.ModScreenHandlers;
import org.example.examplefabricmod.util.ModCustomTrades;
import org.example.examplefabricmod.util.ModFlammableBlocks;
import org.example.examplefabricmod.util.ModStrippables;
import org.example.examplefabricmod.world.biome.ModBiomes;
import org.example.examplefabricmod.world.biome.ModMaterialRules;
import org.example.examplefabricmod.world.dimension.ModDimensions;
import org.example.examplefabricmod.world.feature.ModConfiguredFeatures;
import org.example.examplefabricmod.world.feature.ModPlacedFeatures;
import org.example.examplefabricmod.world.gen.ModWorldGeneration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import terrablender.api.SurfaceRuleManager;

public class ExampleFabricMod implements ModInitializer {

    public static final String MOD_ID = "examplefabricmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        // 模组物品注册
        ModItems.registerModItems();
        // 模组方块注册
        ModBlocks.registerModBlocks();
        // 模组燃料注册
        ModFuels.registerModFuels();
        // 模组画注册
        ModPaintings.registerModPaintings();
        // 模组自定义交易注册
        ModCustomTrades.registerCustomTrades();
        // 模组自定义效果注册
        ModEffects.registerModEffects();
        // 模组自定义附魔注册
        ModEnchantments.registerModEnchantments();
        // 模组药水注册
        ModPotions.registerModPotions();
        // 模组流体注册
        ModFluids.registerModFluids();
        // 模组可燃方块注册
        ModFlammableBlocks.registerModFlammableBlocks();
        // 模组去皮木材注册
        ModStrippables.registerModStrippables();
        // 模组配置特征注册
        ModConfiguredFeatures.registerModConfiguredFeatures();
        // 模组放置特征注册
        ModPlacedFeatures.registerModPlacedFeature();
        // 模组世界生成
        ModWorldGeneration.worldGeneration();
        // 模组生物群系注册
        ModBiomes.registerModBiomes();
        // 模组自定义生物群系表面规则
        ModMaterialRules.registerModMaterialRules();
        // 模组维度注册
        ModDimensions.registerModDimensions();
        // 模组方块实体注册
        ModBlockEntities.registerModBlockEntities();
        // 模组屏幕处理器注册
        ModScreenHandlers.registerModScreenHandlers();
        // 模组配方注册
        ModRecipes.registerModRecipes();
        // 模组服务端数据包注册
        ModPacketHandler.registerC2SPackets();
        // 模组服务端事件注册
        ModEventsHandler.registerModServerEventsHandler();

        LOGGER.info("Hello Fabric World!");
    }
}
