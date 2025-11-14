package com.lne_rogues.client;

import net.minecraft.util.Identifier;
import net.spell_engine.api.render.CustomModels;

import java.util.List;

import static com.lne_rogues.LNE_Rogues_Mod.MOD_ID;

public class LNE_Rogues_Client {

    public static void init() {
        CustomModels.registerModelIds(List.of(
                Identifier.of(MOD_ID, "projectile/dancing_dagger")
        ));
    }
}
