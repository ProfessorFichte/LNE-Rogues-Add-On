package com.lne_rogues.effect;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.spell_engine.api.effect.Synchronized;

import java.util.ArrayList;

import static com.lne_rogues.LNE_Rogues_Mod.MOD_ID;

public class Effects {
    private static final ArrayList<Entry> entries = new ArrayList<Entry>();
    public static class Entry {
        public final Identifier id;
        public final StatusEffect effect;
        public RegistryEntry<StatusEffect> registryEntry;

        public Entry(String name, StatusEffect effect) {
            this.id = Identifier.of(MOD_ID, name);
            this.effect = effect;
            entries.add(this);
        }

        public void register() {
            registryEntry = Registry.registerReference(Registries.STATUS_EFFECT, id, effect);
        }

        public Identifier modifierId() {
            return Identifier.of(MOD_ID, "effect." + id.getPath());
        }
    }
    public static final Entry SECOND_WIND =  new Entry("second_wind",
            new SecondWindStatusEffect(StatusEffectCategory.BENEFICIAL, 0x800000));


    public static void register() {
        SECOND_WIND.effect
                .addAttributeModifier(EntityAttributes.GENERIC_MAX_ABSORPTION,
                        SECOND_WIND.modifierId(), 2, EntityAttributeModifier.Operation.ADD_VALUE);

        Synchronized.configure(SECOND_WIND.effect, true);

        for (Entry entry: entries) {
            entry.register();
        }
    }
}
