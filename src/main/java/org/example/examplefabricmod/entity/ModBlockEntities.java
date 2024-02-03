package org.example.examplefabricmod.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.block.ModBlocks;
import org.example.examplefabricmod.entity.BlockEntity.InjectionBenchBlockEntity;

public class ModBlockEntities {

    public static BlockEntityType<InjectionBenchBlockEntity> INJECTION_BENCH_BLOCK_ENTITY;

    public static void registerModBlockEntities() {

        INJECTION_BENCH_BLOCK_ENTITY = Registry.register(
                Registry.BLOCK_ENTITY_TYPE,
                new Identifier(ExampleFabricMod.MOD_ID, "injection_bench_block_entity"),
                FabricBlockEntityTypeBuilder.create(
                        InjectionBenchBlockEntity::new,
                        ModBlocks.INJECTION_BENCH_BLOCK).build(null)
        );

        ExampleFabricMod.LOGGER.info("Registering ModBlockEntities for " + ExampleFabricMod.MOD_ID);
    }
}
