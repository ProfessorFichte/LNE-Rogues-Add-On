package com.lne_rogues.forge.client;

import com.lne_rogues.client.LNE_Rogues_Client;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.spell_engine.client.gui.ConfigMenuScreen;

public final class ForgeClient {
    public static void register(IEventBus modBus) {
        modBus.addListener(EventPriority.NORMAL, false, FMLClientSetupEvent.class, ForgeClient::onClientSetup);
    }

    @SuppressWarnings("removal")
    private static void onClientSetup(FMLClientSetupEvent event) {
        LNE_Rogues_Client.init();
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> new ConfigMenuScreen(parent)));
    }
}
