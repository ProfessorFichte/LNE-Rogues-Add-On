package com.lne_rogues.effect;

import com.lne_rogues.LNE_Rogues_Mod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.sound.SoundEvents;
import net.spell_engine.api.spell.fx.ParticleGroup;
import net.spell_engine.api.spell.fx.ParticleGroupBuilder;
import net.spell_engine.client.util.Color;
import net.spell_engine.fx.ParticleHelper;
import net.spell_engine.fx.SpellEngineParticles;

import java.util.List;
import java.util.Random;

public class SecondWindStatusEffect extends StatusEffect {
    private int healthPerStack;

    protected SecondWindStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
        this.healthPerStack = 2  ;
    }
    private static final ParticleGroup particles = ParticleGroupBuilder
            .of(SpellEngineParticles.smoke_medium)
            .color(Color.from(0x993333))
            .opacity(0.875F)
            .gravity(0.01F)
            .batch(b -> b.shape(ParticleGroup.Shape.PILLAR)
                    .anchor(ParticleGroup.Anchor.LAUNCH_POINT)
                    .count(3F).speed(0F, 0.01F).extent(0.5F));


    public boolean applyUpdateEffect(LivingEntity livingEntity, int amplifier) {
        float missing_health = livingEntity.getMaxHealth() - livingEntity.getHealth();
        float min_heal_range = LNE_Rogues_Mod.tweaksConfig.value.second_wind_missing_health_heal_min_range;
        float max_heal_range = LNE_Rogues_Mod.tweaksConfig.value.second_wind_missing_health_heal_max_range;
        float randomHeal = new Random().nextFloat() * (max_heal_range - min_heal_range) + 0.01F;
        float healAmount = missing_health * randomHeal;
        float maxHeal = LNE_Rogues_Mod.tweaksConfig.value.second_wind_max_heal_hearts * 2F;
        healAmount = Math.min(healAmount, maxHeal);
        livingEntity.heal(healAmount);
        livingEntity.playSound(SoundEvents.ENTITY_PLAYER_BREATH,1.0F,0);
        if(!livingEntity.getWorld().isClient()){
            ParticleHelper.sendBatches(livingEntity, List.of(particles));
        }
        return true;
    }

    public void onApplied(LivingEntity entity, int amplifier) {
        entity.setAbsorptionAmount(Math.max(entity.getAbsorptionAmount(), healthPerStack  * (1 + amplifier )));
        super.onApplied(entity, amplifier);
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return duration % 40 == 0;
    }
}
