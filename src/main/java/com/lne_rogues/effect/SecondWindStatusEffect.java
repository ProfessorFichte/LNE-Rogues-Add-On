package com.lne_rogues.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.spell_engine.api.spell.ParticleBatch;
import net.spell_engine.particle.ParticleHelper;

import java.util.Random;

public class SecondWindStatusEffect extends StatusEffect {
    protected SecondWindStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
    private static final ParticleBatch particles = new ParticleBatch(
            "spell_engine:weakness_smoke",
            ParticleBatch.Shape.PILLAR, ParticleBatch.Origin.LAUNCH_POINT, null,
            3, 0.0F, 0.01F, 0, 0.5F);

    @Override
    public void onApplied(LivingEntity livingEntity, AttributeContainer attributes, int amplifier) {
        if(livingEntity instanceof PlayerEntity player){
            float max_health = player.defaultMaxHealth * 0.2F;
            player.setAbsorptionAmount(player.getAbsorptionAmount()+max_health);
        }
    }
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        float actual_absorption = entity.getAbsorptionAmount();
        if(actual_absorption != 0){
            float max_health = entity.defaultMaxHealth * 0.2F;
            entity.setAbsorptionAmount(actual_absorption- max_health);
        }
    }
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        float missing_health = entity.getMaxHealth() - entity.getHealth();
        float randomHeal = new Random().nextFloat() * (0.35F - 0.1F) + 0.01F;
        float healAmount = missing_health * randomHeal;
        entity.heal(healAmount);
        entity.playSound(SoundEvents.ENTITY_PLAYER_BREATH,1.0F,0);
        if(!entity.getWorld().isClient()){

            ParticleHelper.sendBatches(entity, new ParticleBatch[]{particles});
        }
    }
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i;
        if (this == Effects.SECOND_WIND) {
            i = 40 >> amplifier;
            if (i > 0) {
                return duration % i == 0;
            } else {
                return true;
            }
        }
        return false;
    }
}
