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

@Mod(LNE_Rogues_Mod.MOD_ID)
public final class ForgeMod {
    @SuppressWarnings("removal")
    public ForgeMod() {
        LNE_Rogues_Mod.init();

        var modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(EventPriority.NORMAL, false, RegisterEvent.class, ForgeMod::register);

        if (FMLEnvironment.dist == Dist.CLIENT) {
            ForgeClient.register(modBus);
        }
    }

    // Goes through the helper on purpose, on Forge 47.0-47.3 a plain Registry.register throws "Can not register to a locked registry".
    public static void register(RegisterEvent event) {
        event.register(RegistryKeys.STATUS_EFFECT, helper -> {
            LNE_Rogues_Mod.effectConfig.refresh();
            LNERogues_Effects.effectsToRegister(LNE_Rogues_Mod.effectConfig.value)
                    .forEach(helper::register);
            LNE_Rogues_Mod.effectConfig.save();
        });

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
