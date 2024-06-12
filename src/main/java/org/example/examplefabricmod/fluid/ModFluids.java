package org.example.examplefabricmod.fluid;

import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.fluid.CustomFluid.BloodFluid;

public class ModFluids {

    public static FlowableFluid STILL_BLOOD = registerFlowableFluid("still_blood", new BloodFluid.Still());
    public static FlowableFluid FLOWING_BLOOD = registerFlowableFluid("flowing_blood", new BloodFluid.Flowing());

    private static FlowableFluid registerFlowableFluid(String name, FlowableFluid flowableFluid) {
        return Registry.register(Registry.FLUID, new Identifier(ExampleFabricMod.MOD_ID, name), flowableFluid);
    }

    public static void registerModFluids() {
        ExampleFabricMod.LOGGER.info("Registering ModFluids for " + ExampleFabricMod.MOD_ID);
    }
}
