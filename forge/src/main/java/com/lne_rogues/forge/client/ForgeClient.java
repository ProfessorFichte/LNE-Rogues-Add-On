package com.lne_rogues.forge.client;

import com.lne_rogues.client.LNE_Rogues_Client;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.spell_engine.client.gui.ConfigMenuScreen;

/// Client-only wiring for Forge 47; only touched from {@link com.lne_rogues.forge.ForgeMod} behind a
/// `Dist.CLIENT` check, and registered with explicit mod-bus listeners (no `@EventBusSubscriber`
/// scanning), so a dedicated server never classloads it.
///
/// 1.20.1 port of the NeoForge client entrypoint: `IConfigScreenFactory` becomes
/// `ConfigScreenHandler.ConfigScreenFactory`.
public final class ForgeClient {
    public static void register(IEventBus modBus) {
        modBus.addListener(EventPriority.NORMAL, false, FMLClientSetupEvent.class, ForgeClient::onClientSetup);
    }

    // ModLoadingContext.get() is flagged for removal by late 47.x builds; the replacement does
    // not exist on early 47.x, and get() works on all of [47,).
    @SuppressWarnings("removal")
    private static void onClientSetup(FMLClientSetupEvent event) {
        LNE_Rogues_Client.init();
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> new ConfigMenuScreen(parent)));
    }
}
