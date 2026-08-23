package com.lne_rogues.spells;

import com.lne_rogues.LNE_Rogues_Mod;
import com.lne_rogues.effect.LNERogues_Effects;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.spell_engine.api.datagen.SpellBuilder;
import net.spell_engine.api.spell.ExternalSpellSchools;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.fx.Fx;
import net.spell_engine.api.spell.fx.ParticleGroup;
import net.spell_engine.api.spell.fx.ParticleGroupBuilder;
import net.spell_engine.api.spell.fx.PlayerAnimation;
import net.spell_engine.api.spell.fx.Sound;
import net.spell_engine.api.spell.tooltip.TooltipTokens;
import net.spell_engine.client.util.Color;
import net.spell_engine.fx.SpellEngineParticles;
import java.util.ArrayList;
import java.util.List;

import static com.lne_rogues.LNE_Rogues_Mod.MOD_ID;

public class RoguesSpells {
    public record Entry(Identifier id, Spell spell, String title, String description) {
    }

    public static final List<Entry> entries = new ArrayList<>();

    private static Entry add(Entry entry) {
        entries.add(entry);
        return entry;
    }

    // ===== SPELL DEFINITIONS =====
    public static Entry second_wind = add(second_wind());
    private static Entry second_wind() {
        var id = Identifier.of(MOD_ID, "second_wind");
        var title = "Second Wind";
        var description = "Recover your breath, healing {heal_range} of your missing health every 2 seconds, " +
                "and gain {absorption_percent} of your max health as absorption, for {effect_duration} seconds.";

        var spell = SpellBuilder.createSpellActive();
        spell.school = ExternalSpellSchools.PHYSICAL_MELEE;
        spell.range = 0.0F;
        spell.tier = 5;

        spell.active.cast = new Spell.Active.Cast();
        spell.active.cast.duration = 0.0F;

        spell.release.animation = PlayerAnimation.of("spell_engine:one_handed_area_release");
        spell.release.sound = Sound.withVolume(Identifier.of("entity.player.breath"), 1.5F);
        var risingStripes = ParticleGroupBuilder
                .magic(SpellEngineParticles.magic_stripe, ParticleGroup.Motion.FLOAT, Color.RAGE)
                .batch(b -> b.shape(ParticleGroup.Shape.PILLAR)
                        .anchor(ParticleGroup.Anchor.LAUNCH_POINT)
                        .count(20F).speed(0.01F, 0.2F)
                        .extent(1.0F));
        var groundSmoke = ParticleGroupBuilder
                .of(SpellEngineParticles.smoke_medium)
                .batch(b -> b.shape(ParticleGroup.Shape.CIRCLE)
                        .verticalOrigin(ParticleGroupBuilder.Batches.FEET)
                        .count(10F).speed(0.15F, 0.15F)
                        .preTravel(1F));
        spell.release.visuals = Fx.Visuals.of(risingStripes, groundSmoke);

        spell.target.type = Spell.Target.Type.CASTER;

        var effect = SpellBuilder.Impacts.effectSet(LNERogues_Effects.SECOND_WIND.id.toString(), 12.5F, 0);
        effect.attribute = EntityAttributes.GENERIC_MAX_HEALTH.getIdAsString();
        effect.action.status_effect.amplifier_power_multiplier = 0.25F;
        effect.action.status_effect.show_particles = false;

        spell.impacts = List.of(effect);

        SpellBuilder.Cost.cooldown(spell, 30.0F);
        spell.cost.exhaust = 0.5F;

        return new Entry(id, spell, title, description);
    }

    public static Entry dancing_dagger = add(dancing_dagger());
    private static Entry dancing_dagger() {
        var id = Identifier.of(MOD_ID, "dancing_dagger");
        var title = "Dancing Dagger";
        var description = "Throw a dagger that ricochets between enemies, dealing {damage} damage and applying grievous wounds for {effect_duration} seconds.";

        var spell = SpellBuilder.createSpellActive();
        spell.school = ExternalSpellSchools.PHYSICAL_MELEE;
        spell.range = 26.0F;
        spell.tier = 5;

        spell.active.cast = new Spell.Active.Cast();
        spell.active.cast.duration = 0.5F;
        spell.active.cast.animation = PlayerAnimation.of("more_rpg_classes:dagger_throw_charge");
        spell.active.cast.particles = List.of();

        spell.release.animation = PlayerAnimation.of("more_rpg_classes:dagger_throw_release");
        spell.release.sound = new Sound(Identifier.of("rogues:throw"));

        spell.deliver = new Spell.Delivery();
        spell.deliver.type = Spell.Delivery.Type.PROJECTILE;

        spell.deliver.projectile = new Spell.Delivery.ShootProjectile();
        spell.deliver.projectile.launch_properties.velocity = 1.3F;

        spell.deliver.projectile.projectile = new Spell.ProjectileData();

        spell.deliver.projectile.projectile.perks = new Spell.ProjectileData.Perks();
        spell.deliver.projectile.projectile.perks.bounce = 0;
        spell.deliver.projectile.projectile.perks.ricochet = 7;
        spell.deliver.projectile.projectile.perks.ricochet_range = 16.0F;

        spell.deliver.projectile.projectile.travel_sound_interval = 8;
        spell.deliver.projectile.projectile.travel_sound = new Sound(Identifier.of("rogues:throw"));

        spell.deliver.projectile.projectile.client_data = new Spell.ProjectileData.Client();
        spell.deliver.projectile.projectile.client_data.travel_particles = List.of();
        var daggerModel = SpellBuilder.ProjectileModels.model("lne_rogues:spell_projectile/dancing_dagger", 1.0F);
        daggerModel.fx.light_emission = null;
        spell.deliver.projectile.projectile.client_data.composite_model = SpellBuilder.ProjectileModels.composite(daggerModel);

        var damage = SpellBuilder.Impacts.damage(1.2F);
        damage.sound = new Sound(Identifier.of("rogues:throw_impact"));

        var grievousWounds = SpellBuilder.Impacts.effectSet("more_rpg_classes:grievous_wounds", 5.0F, 0);
        grievousWounds.action.status_effect.amplifier_power_multiplier = 0.1F;
        grievousWounds.action.status_effect.show_particles = false;
        grievousWounds.visuals = Fx.Visuals.of(
                ParticleGroupBuilder
                        .of(SpellEngineParticles.smoke_medium)
                        .color(Color.RAGE)
                        .batch(b -> b.shape(ParticleGroup.Shape.SPHERE)
                                .count(5F).speed(0.2F, 0.2F)));

        spell.impacts = List.of(damage, grievousWounds);

        SpellBuilder.Cost.cooldown(spell, 15.0F);
        spell.cost.exhaust = 0.5F;

        return new Entry(id, spell, title, description);
    }

    public static void registerTooltipTokens() {
        TooltipTokens.registerCustom(second_wind.id(), args -> {
            var config = LNE_Rogues_Mod.tweaksConfig.value;
            var healRange = formattedRange(
                    config.second_wind_missing_health_heal_min_range * 100,
                    config.second_wind_missing_health_heal_max_range * 100) + "%";
            var description = args.description().replace("{heal_range}", healRange);
            for (var impact : args.spellEntry().value().impacts) {
                if (impact.action != null && impact.action.status_effect != null) {
                    description = description.replace("{absorption_percent}",
                            TooltipTokens.percent(impact.action.status_effect.amplifier_power_multiplier));
                    break;
                }
            }
            return description;
        });
    }

    private static String formattedRange(float min, float max) {
        if (min == max) {
            return TooltipTokens.formattedNumber(min);
        }
        return TooltipTokens.formattedNumber(min) + " - " + TooltipTokens.formattedNumber(max);
    }
}
