package com.lne_rogues.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import net.spell_engine.api.render.CustomModels;

import java.util.List;

import static com.lne_rogues.LNE_Rogues_Mod.MOD_ID;

@Environment(EnvType.CLIENT)
public class LNE_Rogues_Client implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        CustomModels.registerModelIds(List.of(
                Identifier.of(MOD_ID, "projectile/dancing_dagger")
        ));
    }
}
