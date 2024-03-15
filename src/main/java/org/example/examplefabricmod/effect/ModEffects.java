package org.example.examplefabricmod.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.example.examplefabricmod.ExampleFabricMod;
import org.example.examplefabricmod.effect.CustomEffect.BloodThirstEffect;
import org.example.examplefabricmod.effect.CustomEffect.FreezeEffect;

public class ModEffects {

    public static StatusEffect FREEZE = registerStatusEffects("freeze",
            new FreezeEffect(StatusEffectCategory.HARMFUL, 3124687));

    public static StatusEffect BLOOD_THIRST = registerStatusEffects("blood_thirst",
            new BloodThirstEffect(StatusEffectCategory.BENEFICIAL, 10824234, 1.0)
                    .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, "6fdfb34c-c9a5-4f48-b1da-9c89efb4ef4f", 0.0, EntityAttributeModifier.Operation.MULTIPLY_BASE));

    public static StatusEffect registerStatusEffects(String name, StatusEffect statusEffect) {
        return Registry.register(Registry.STATUS_EFFECT, new Identifier(ExampleFabricMod.MOD_ID, name), statusEffect);
    }

    public static void registerModEffects() {
        ExampleFabricMod.LOGGER.info("Registering ModEffects for " + ExampleFabricMod.MOD_ID);
    }
}
