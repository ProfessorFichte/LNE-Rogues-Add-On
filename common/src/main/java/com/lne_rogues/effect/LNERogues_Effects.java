package com.lne_rogues.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.util.Identifier;
import net.spell_engine.rpg_series.config.AttributeModifier;
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
            Identifier.of(MOD_ID, "second_wind"),
            "Second Wind",
            "When applied, gain 20% of your max health as absorption. You can recover 10-35% of your missing health every 2 seconds.",
            new SecondWindStatusEffect(StatusEffectCategory.BENEFICIAL, 0x800000),
            new EffectConfig(
                    List.of(new AttributeModifier(
                            EntityAttributes.GENERIC_MAX_ABSORPTION.getIdAsString(),
                            2,
                            EntityAttributeModifier.Operation.ADD_VALUE
                    )
            )
    )));

    public static void register(ConfigFile.Effects config) {
        for (var entry : entries) {
            Synchronized.configure(entry.effect, true);
        }

        Effects.register(entries, config.effects);
    }
}
