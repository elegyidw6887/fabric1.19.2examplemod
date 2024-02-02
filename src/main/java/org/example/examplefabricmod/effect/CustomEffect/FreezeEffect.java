package org.example.examplefabricmod.effect.CustomEffect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FreezeEffect extends StatusEffect {

    public FreezeEffect(StatusEffectCategory statusEffectCategory, int color) {
        super(statusEffectCategory, color);
    }

    // 应用效果
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // 判断实体是否为服务端
        if (!entity.world.isClient()) {
            // 获取实体的三维坐标
            double x = entity.getX();
            double y = entity.getY();
            double z = entity.getZ();
            // 将实体传送到获取的坐标
            entity.teleport(x, y, z);
            // 将实体的速度设置为0
            entity.setVelocity(0, 0, 0);
        }
        super.applyUpdateEffect(entity, amplifier);
    }

    // 是否设置效果
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
