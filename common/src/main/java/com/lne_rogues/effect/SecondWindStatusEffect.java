package com.lne_rogues.effect;

import com.lne_rogues.LNE_Rogues_Mod;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.sound.SoundEvents;
import net.spell_engine.api.spell.fx.ParticleBatch;
import net.spell_engine.fx.ParticleHelper;

import java.util.Random;

public class SecondWindStatusEffect extends StatusEffect {
    private int healthPerStack;

    protected SecondWindStatusEffect(StatusEffectCategory category, int color) {
        super(category, color);
        this.healthPerStack = 2  ;
    }
    private static final ParticleBatch particles = new ParticleBatch(
            "spell_engine:weakness_smoke",
            ParticleBatch.Shape.PILLAR, ParticleBatch.Origin.LAUNCH_POINT, null,
            3, 0.0F, 0.01F, 0, 0.5F);


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
            ParticleHelper.sendBatches(livingEntity, new ParticleBatch[]{particles});
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
