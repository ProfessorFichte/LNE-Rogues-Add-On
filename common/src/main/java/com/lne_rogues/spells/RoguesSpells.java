package com.lne_rogues.spells;

import com.lne_rogues.effect.LNERogues_Effects;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.Identifier;
import net.spell_engine.api.datagen.SpellBuilder;
import net.spell_engine.api.spell.ExternalSpellSchools;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.api.spell.fx.ParticleBatch;
import net.spell_engine.api.spell.fx.PlayerAnimation;
import net.spell_engine.api.spell.fx.Sound;
import net.spell_engine.client.util.Color;
import net.spell_engine.fx.SpellEngineParticles;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.lne_rogues.LNE_Rogues_Mod.MOD_ID;

public class RoguesSpells {
    public record Entry(Identifier id, Spell spell, String title, String description,
                        @Nullable net.spell_engine.client.gui.SpellTooltip.DescriptionMutator mutator) {
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
        var description = "Recover your breath and gain a temporary boost based on your max health for {effect_duration} seconds.";

        var spell = SpellBuilder.createSpellActive();
        spell.school = ExternalSpellSchools.PHYSICAL_MELEE;
        spell.range = 0.0F;
        spell.tier = 5;

        spell.active.cast = new Spell.Active.Cast();
        spell.active.cast.duration = 0.0F;

        spell.release.animation = PlayerAnimation.of("spell_engine:one_handed_area_release");
        spell.release.sound = Sound.withVolume(Identifier.of("entity.player.breath"), 1.5F);
        var releaseParticle1 = new ParticleBatch(
                SpellEngineParticles.MagicParticles.get(
                        SpellEngineParticles.MagicParticles.Shape.STRIPE,
                        SpellEngineParticles.MagicParticles.Motion.FLOAT).id().toString(),
            ParticleBatch.Shape.PILLAR, ParticleBatch.Origin.LAUNCH_POINT,
            20.0F, 0.01F, 0.2F
        ).color(Color.RAGE.toRGBA());
        releaseParticle1.extent = 1.0F;
        spell.release.particles = new ParticleBatch[]{
            releaseParticle1,
            new ParticleBatch(
                    SpellEngineParticles.smoke_medium.id().toString(),
                ParticleBatch.Shape.CIRCLE, ParticleBatch.Origin.FEET,
                10.0F, 0.15F, 0.15F
            ).preSpawnTravel(1)
        };

        spell.target.type = Spell.Target.Type.CASTER;

        var effect = SpellBuilder.Impacts.effectSet(LNERogues_Effects.SECOND_WIND.id.toString(), 12.5F, 0);
        effect.attribute = EntityAttributes.GENERIC_MAX_HEALTH.getIdAsString();
        effect.action.status_effect.amplifier_power_multiplier = 0.25F;
        effect.action.status_effect.show_particles = false;

        spell.impacts = List.of(effect);

        SpellBuilder.Cost.cooldown(spell, 30.0F);
        spell.cost.exhaust = 0.5F;

        return new Entry(id, spell, title, description, null);
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
        spell.active.cast.particles = new ParticleBatch[]{};

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
        spell.deliver.projectile.projectile.client_data.travel_particles = new ParticleBatch[]{};
        var daggerModel = SpellBuilder.ProjectileModels.model("lne_rogues:spell_projectile/dancing_dagger", 1.0F);
        daggerModel.fx.light_emission = null;
        spell.deliver.projectile.projectile.client_data.composite_model = SpellBuilder.ProjectileModels.composite(daggerModel);

        var damage = SpellBuilder.Impacts.damage(1.2F);
        damage.sound = new Sound(Identifier.of("rogues:throw_impact"));

        var grievousWounds = SpellBuilder.Impacts.effectSet("more_rpg_classes:grievous_wounds", 5.0F, 0);
        grievousWounds.action.status_effect.amplifier_power_multiplier = 0.1F;
        grievousWounds.action.status_effect.show_particles = false;
        grievousWounds.particles = new ParticleBatch[]{
            new ParticleBatch(
                    SpellEngineParticles.smoke_medium.id().toString(),
                ParticleBatch.Shape.SPHERE, ParticleBatch.Origin.CENTER,
                5.0F, 0.2F, 0.2F
            ).color(Color.RAGE.toRGBA())
        };

        spell.impacts = List.of(damage, grievousWounds);

        SpellBuilder.Cost.cooldown(spell, 15.0F);
        spell.cost.exhaust = 0.5F;

        return new Entry(id, spell, title, description, null);
    }
}
