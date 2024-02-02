package org.example.examplefabricmod.world.dimension;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.block.ModBlocks;
import org.example.examplefabricmod.item.ModItems;

public class ModDimensions {

    public static final RegistryKey<World> EXAMPLE_DIMENSION_KEY = RegistryKey.of(Registry.WORLD_KEY,
            new Identifier(ExampleFabricMod.MOD_ID, "example_dimension"));
    public static final RegistryKey<DimensionType> EXAMPLE_DIMENSION_TYPE = RegistryKey.of(Registry.DIMENSION_TYPE_KEY,
            new Identifier(ExampleFabricMod.MOD_ID, "example_dimension_type"));

    public static void registerModDimensions() {

        // API的调用
        CustomPortalBuilder.beginPortal()
                // 传送门框架方块
                .frameBlock(ModBlocks.AMETHYST_BLOCK)
                // 目标维度ID
                .destDimID(EXAMPLE_DIMENSION_KEY.getValue())
                // 传送门颜色
                .tintColor(45, 79, 135)
                // 传送门激活方式（此处选用物品激活）
                .lightWithItem(ModItems.AMETHYST)
                // 只可以在主世界生成传送门
                .onlyLightInOverworld()
                // 完成传送门的注册
                .registerPortal();

        ExampleFabricMod.LOGGER.info("Registering ModDimensions for " + ExampleFabricMod.MOD_ID);
    }
}
