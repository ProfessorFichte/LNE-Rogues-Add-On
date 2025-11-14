package com.lne_rogues.neoforge;

import com.lne_rogues.LNE_Rogues_Mod;
import net.minecraft.registry.RegistryKeys;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod(LNE_Rogues_Mod.MOD_ID)
public final class NeoForgeMod {
    public NeoForgeMod(IEventBus modBus) {
        LNE_Rogues_Mod.init();
        modBus.addListener(RegisterEvent.class, NeoForgeMod::register);
    }
    public static void register(RegisterEvent event) {
        event.register(RegistryKeys.ITEM, reg -> {
            LNE_Rogues_Mod.registerItems();
        });
        event.register(RegistryKeys.STATUS_EFFECT, reg -> {
            LNE_Rogues_Mod.registerEffects();
        });
    }
}
