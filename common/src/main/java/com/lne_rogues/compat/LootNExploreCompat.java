package com.lne_rogues.compat;

import more_rpg_loot.item.Group;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;

// Loot & Explore has no Forge build, so this is the only class that touches more_rpg_loot and it has to stay behind an isModLoaded check.
public final class LootNExploreCompat {
    public static final String MOD_ID = "loot_n_explore";

    private LootNExploreCompat() { }

    public static RegistryKey<ItemGroup> itemGroupKey() {
        return Group.RPG_LOOT_KEY;
    }
}
