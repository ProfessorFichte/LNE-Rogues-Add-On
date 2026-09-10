package com.lne_rogues.effect;

import java.util.Map;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.spell_engine.rpg_series.config.ConfigFile;
import net.spell_engine.rpg_series.config.EffectConfig;
import net.spell_engine.api.effect.*;

import java.util.ArrayList;
import java.util.List;

import static com.lne_rogues.LNE_Rogues_Mod.MOD_ID;

public class LNERogues_Effects {
    public static final List<Effects.Entry> entries = new ArrayList<>();

    private static Effects.Entry add(Effects.Entry entry) {
        entries.add(entry);
        return entry;
    }

    public static Effects.Entry SECOND_WIND = add(new Effects.Entry(
            new Identifier(MOD_ID, "second_wind"),
            "Second Wind",
            "When applied, gain 20% of your max health as absorption. You can recover 10-35% of your missing health every 2 seconds.",
            new SecondWindStatusEffect(StatusEffectCategory.BENEFICIAL, 0x800000),
            // 1.20.1 has no `minecraft:generic.max_absorption` attribute (added in 1.21), so the
            // `+2 max absorption` modifier the 1.21.1 branch carries is dropped. The absorption
            // itself is unaffected: SecondWindStatusEffect#onApplied sets it imperatively, and on
            // this game version `setAbsorptionAmount` has no attribute-driven cap to raise.
            EffectConfig.EMPTY
    ));

    /// Behaviour attachment, split out of `register` so the Forge path can run it before its own
    /// registration loop. Operates on the raw effect, so it does not need the registry.
    public static void configureBehaviours() {
        for (var entry : entries) {
            Synchronized.configure(entry.effect, true);
        }
    }

    /// Creation half for Forge: the same content `register` writes, keyed by registration id.
    /// Nothing in this mod reads `Effects.Entry#entry`, so there is no link step.
    public static Map<Identifier, StatusEffect> effectsToRegister(ConfigFile.Effects config) {
        configureBehaviours();
        return Effects.effectsToRegister(entries, config.effects);
    }

    public static void register(ConfigFile.Effects config) {
        configureBehaviours();

        Effects.register(entries, config.effects);
    }
}
