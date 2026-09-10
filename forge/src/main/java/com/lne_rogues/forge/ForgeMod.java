package com.lne_rogues.forge;

import com.lne_rogues.LNE_Rogues_Mod;
import com.lne_rogues.effect.LNERogues_Effects;
import com.lne_rogues.item.WeaponRegister;
import net.spell_engine.Platform;
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
/// Registration goes through the `RegisterHelper` that `RegisterEvent` hands out, NOT through
/// `Registry.register`. Forge only clears the vanilla `NamespacedWrapper`'s lock from 47.4.0 onward;
/// on 47.0-47.3 and NeoForge 1.20.1 it stays locked even inside the correct window, so a plain
/// `Registry.register` there throws `Can not register to a locked registry`. `mods.toml` declares
/// `[47,)`, so those are supported configurations.
///
/// The loops below duplicate what `common` runs on Fabric, on purpose - the whole workaround stays
/// inside `forge/` and the Fabric path is untouched. Measured window order on 47.4.22:
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
        event.register(RegistryKeys.STATUS_EFFECT, helper -> {
            LNE_Rogues_Mod.effectConfig.refresh();
            LNERogues_Effects.effectsToRegister(LNE_Rogues_Mod.effectConfig.value)
                    .forEach(helper::register);
            LNE_Rogues_Mod.effectConfig.save();
        });

        // Loot & Explore is Fabric-only, so this block registers nothing on Forge today - it mirrors
        // `LNE_Rogues_Mod.registerItems()` exactly so it stays correct if that ever changes.
        event.register(RegistryKeys.ITEM, helper -> {
            if (Platform.util().isModLoaded("loot_n_explore")) {
                LNE_Rogues_Mod.itemConfig.refresh();
                WeaponRegister.itemsToRegister(LNE_Rogues_Mod.itemConfig.value.weapons)
                        .forEach(helper::register);
                LNE_Rogues_Mod.itemConfig.save();
            }
        });
    }
}
