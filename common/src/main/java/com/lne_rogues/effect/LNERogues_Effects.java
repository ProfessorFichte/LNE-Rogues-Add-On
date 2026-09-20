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
            EffectConfig.EMPTY
    ));

    public static void configureBehaviours() {
        for (var entry : entries) {
            Synchronized.configure(entry.effect, true);
        }
    }

    public static Map<Identifier, StatusEffect> effectsToRegister(ConfigFile.Effects config) {
        configureBehaviours();
        return Effects.effectsToRegister(entries, config.effects);
    }

    public static void register(ConfigFile.Effects config) {
        configureBehaviours();

        Effects.register(entries, config.effects);
    }
}
