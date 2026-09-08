package com.lne_rogues.forge;

import com.lne_rogues.LNE_Rogues_Mod;
import com.lne_rogues.forge.client.ForgeClient;
import net.minecraft.registry.RegistryKeys;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.RegisterEvent;

/// Forge 47 entrypoint (1.20.1 port of the NeoForge entrypoint).
///
/// Forge locks every vanilla registry outside its own `RegisterEvent` window, so each `registerX()`
/// call sits inside the window of the registry it writes to. Measured window order on 47.4.22:
/// `sound_event -> block -> attribute -> mob_effect -> ... -> item -> ...`, so STATUS_EFFECT is
/// served before ITEM.
///
/// Loot & Explore is Fabric-only, so it can never be present here. `LNE_Rogues_Mod.registerItems()`
/// is a no-op on this loader (see `com.lne_rogues.compat.LootNExploreCompat`); the status effect and
/// the datapack spells are the whole Forge-side content.
@Mod(LNE_Rogues_Mod.MOD_ID)
public final class ForgeMod {
    // FMLJavaModLoadingContext.get() is flagged for removal by late 47.x builds, but the
    // constructor-injected replacement doesn't exist on early 47.x; get() works on all of [47,).
    @SuppressWarnings("removal")
    public ForgeMod() {
        LNE_Rogues_Mod.init();

        var modBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Explicit event classes: Forge 47's plain addListener(Consumer) infers the event type from the
        // lambda via TypeTools, which is fragile; the 4-arg overload takes it directly.
        modBus.addListener(EventPriority.NORMAL, false, RegisterEvent.class, ForgeMod::register);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            ForgeClient.register(modBus);
        }
    }

    public static void register(RegisterEvent event) {
        event.register(RegistryKeys.STATUS_EFFECT, reg -> LNE_Rogues_Mod.registerEffects());
        event.register(RegistryKeys.ITEM, reg -> LNE_Rogues_Mod.registerItems());
    }
}
