package org.example.examplefabricmod.tag;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.example.examplefabricmod.block.ModBlocks;

public class ModFluidTags {

    public static final TagKey<Fluid> BLOOD = ModFluidTags.of(ModBlocks.BLOOD);

    private static TagKey<Fluid> of(Block fluid) {
        return TagKey.of(Registry.FLUID_KEY, new Identifier(fluid.getTranslationKey()));
    }
}
