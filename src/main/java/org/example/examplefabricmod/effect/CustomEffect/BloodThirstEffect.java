package org.example.examplefabricmod.effect.CustomEffect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class BloodThirstEffect extends StatusEffect {

    private final double modifier;

    public BloodThirstEffect(StatusEffectCategory statusEffectCategory, int color, double initModifier) {
        super(statusEffectCategory, color);
        this.modifier = initModifier;
    }

    @Override
    public double adjustModifierAmount(int amplifier2, EntityAttributeModifier modifier) {
        return this.modifier + (amplifier2 * 0.1);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
