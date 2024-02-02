package org.example.examplefabricmod.enchantment.CustomEnchantmen;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.example.examplefabricmod.effect.ModEffects;

public class FreezeEnchantment extends Enchantment {

    // 构造方法
    public FreezeEnchantment() {
        /*
        参数1：附魔稀有度
        参数2：附魔对象
        参数3：装备槽
         */
        super(Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    // 附魔权重，大多数的附魔类的这项返回值会像是 10 * level，根据附魔的最高等级和稀有度有不同的等级
    // 对于附魔等级最高的力量V来说，其出现的权重为 5 + level，因此出现的概率很低
    public int getMinPower(int level) {
        return 10 * level;
    }

    @Override
    // 最高附魔等级，如：锋利最高为6
    public int getMaxLevel() {
        return 3;
    }

    // 造成伤害时调用
    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        // 实现该附魔效果的方法
        // LivingEntity是可以有状态效果的， 不只是Entity
        if (target instanceof LivingEntity) {
            ((LivingEntity) target).addStatusEffect(
                    new StatusEffectInstance(ModEffects.FREEZE, 20 * level, level - 1)); // 施加自定义效果
        }
        /*
        StatusEffectInstance对象共有三个参数
            1.StatusEffects：状态效果
            2.duration：持续时间
            3.amplifier：放大器
         */
        super.onTargetDamaged(user, target, level);
    }
}
