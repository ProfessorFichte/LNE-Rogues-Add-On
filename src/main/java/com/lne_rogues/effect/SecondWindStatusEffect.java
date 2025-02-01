package com.lne_rogues.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class SecondWindStatusEffect extends StatusEffect {
    protected SecondWindStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void onApplied(LivingEntity livingEntity, AttributeContainer attributes, int amplifier) {
        if(livingEntity instanceof PlayerEntity player){
            float max_health = player.defaultMaxHealth * 0.2F;
            player.setAbsorptionAmount(player.getAbsorptionAmount()+max_health);
        }
    }

}
