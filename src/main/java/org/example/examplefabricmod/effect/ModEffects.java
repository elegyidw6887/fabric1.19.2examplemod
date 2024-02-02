package org.example.examplefabricmod.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.effect.CustomEffect.FreezeEffect;

public class ModEffects {


    public static StatusEffect FREEZE = registerStatusEffects("freeze",
            new FreezeEffect(StatusEffectCategory.HARMFUL, 3124687));

    public static StatusEffect registerStatusEffects(String name, StatusEffect statusEffect) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(ExampleFabricMod.MOD_ID, name), statusEffect);
    }

    public static void registerModEffects() {
        ExampleFabricMod.LOGGER.info("Register ModEffects for " + ExampleFabricMod.MOD_ID);
    }
}
