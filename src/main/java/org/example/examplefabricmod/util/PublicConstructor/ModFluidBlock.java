package org.example.examplefabricmod.util.PublicConstructor;

import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;

public class ModFluidBlock extends FluidBlock {

    public ModFluidBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
    }
}
