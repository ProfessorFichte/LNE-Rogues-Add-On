package com.lne_rogues.fabric;

import com.lne_rogues.LNE_Rogues_Mod;
import net.fabricmc.api.ModInitializer;



public final class FabricMod implements ModInitializer {
    @Override
    public void onInitialize() {
        LNE_Rogues_Mod.init();
        LNE_Rogues_Mod.registerItems();
        LNE_Rogues_Mod.registerEffects();
    }
}
