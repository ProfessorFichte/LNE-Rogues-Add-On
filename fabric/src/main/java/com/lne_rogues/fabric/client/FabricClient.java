package com.lne_rogues.fabric.client;

import com.lne_rogues.client.LNE_Rogues_Client;
import net.fabricmc.api.ClientModInitializer;

public final class FabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        LNE_Rogues_Client.init();
    }
}
