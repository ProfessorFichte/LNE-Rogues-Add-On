package com.lne_rogues.config;

import com.lne_rogues.item.WeaponRegister;
import net.spell_engine.api.item.ItemConfig;

public class Default {
    public final static ItemConfig itemConfig;
    static {
        itemConfig = new ItemConfig();
        for (var weapon: WeaponRegister.entries) {
            itemConfig.weapons.put(weapon.name(), weapon.defaults());
        }

    }
}
