package com.lne_rogues.mixin;

import com.lne_rogues.LNE_Rogues_Mod;
import com.lne_rogues.effect.Effects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Unique
    LivingEntity livingEntity = (LivingEntity) (Object) (this);
    @Inject(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"))
    private void applyQuenHealBeforeDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (livingEntity instanceof PlayerEntity player && player.hasStatusEffect(Effects.SECOND_WIND) && !source.isIn(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            float randomHeal = new Random().nextFloat() * (0.75F - 0.1F) + 0.1F;
            float healAmount = amount * randomHeal;
            player.heal(healAmount);
            LNE_Rogues_Mod.LOGGER.info(String.valueOf(randomHeal));
            LNE_Rogues_Mod.LOGGER.info(String.valueOf(healAmount));
        }

    }
}
