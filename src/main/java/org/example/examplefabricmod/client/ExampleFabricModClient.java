package org.example.examplefabricmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;
import org.example.examplefabricmod.block.ModBlocks;
import org.example.examplefabricmod.event.ModKeyInputHandler;
import org.example.examplefabricmod.fluid.ModFluids;
import org.example.examplefabricmod.screen.InjectionBenchBlock.InjectionBenchBlockScreen;
import org.example.examplefabricmod.screen.ModScreenHandlers;
import org.example.examplefabricmod.util.ModModelPredicateProvider;

public class ExampleFabricModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        // 模组按键事件注册
        ModKeyInputHandler.register();

        // 葡萄作物方块渲染
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GRAPE_VINE, RenderLayer.getCutout());
        // 弓的渲染
        ModModelPredicateProvider.registerModModels();
        // 血流体的渲染
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.STILL_BLOOD,
                new SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        SimpleFluidRenderHandler.WATER_OVERLAY,
                        0xDC143C));
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.FLOWING_BLOOD,
                new SimpleFluidRenderHandler(SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        SimpleFluidRenderHandler.WATER_OVERLAY,
                        0xDC143C));
        // 蓝花楹树叶与树苗渲染
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JACARANDA_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.JACARANDA_SAPLING, RenderLayer.getCutout());
        // 花方块与盆花渲染
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LILAC_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_LILAC_FLOWER, RenderLayer.getCutout());
        // 注入工作台方块渲染
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INJECTION_BENCH_BLOCK, RenderLayer.getCutout());
        // 注入工作台GUI渲染
        ScreenRegistry.register(ModScreenHandlers.INJECTION_BENCH_BLOCK_SCREEN_HANDLER, InjectionBenchBlockScreen::new);
    }
}
