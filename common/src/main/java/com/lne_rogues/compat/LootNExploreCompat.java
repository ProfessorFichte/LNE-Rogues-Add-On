package com.lne_rogues.compat;

import more_rpg_loot.item.Group;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;

/// The **only** place this mod touches a Loot & Explore class.
///
/// Loot & Explore is Fabric-only on the 1.20.1 line (and on 1.21.1) - there is no Forge build - so
/// `more_rpg_loot.item.Group` is absent from the Forge runtime entirely. Keeping the reference in a
/// dedicated holder class means the JVM only resolves it when this class is first touched, which
/// happens exclusively inside the `Platform.util().isModLoaded("loot_n_explore")` branch of
/// {@link com.lne_rogues.LNE_Rogues_Mod#registerItems()}. On Forge the class is never loaded and no
/// `NoClassDefFoundError` can occur. Same shape as Spell Engine's `ExternalSpellSchools` holder.
///
/// **Do not reference this class from anywhere that is not already behind that check**, and do not
/// add an `isModLoaded` helper here - that would make the guard itself load the holder.
public final class LootNExploreCompat {
    public static final String MOD_ID = "loot_n_explore";

    private LootNExploreCompat() { }

    /// The creative tab Loot & Explore owns; this mod's weapons are placed into it.
    public static RegistryKey<ItemGroup> itemGroupKey() {
        return Group.RPG_LOOT_KEY;
    }
}
