package com.lne_rogues.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.spell_engine.api.effect.Synchronized;

import static com.lne_rogues.LNE_Rogues_Mod.MOD_ID;

public class Effects {
    public static StatusEffect SECOND_WIND = new SecondWindStatusEffect(StatusEffectCategory.BENEFICIAL, 0x800000);

    public static void register() {
        Synchronized.configure(SECOND_WIND, true);

        int ID = 20300;
        Registry.register(Registries.STATUS_EFFECT, ID++, new Identifier(MOD_ID, "second_wind").toString(), SECOND_WIND);
    }
}
