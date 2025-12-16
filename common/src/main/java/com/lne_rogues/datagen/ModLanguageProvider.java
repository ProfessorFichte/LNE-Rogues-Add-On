package com.lne_rogues.datagen;

import com.lne_rogues.effect.LNERogues_Effects;
import com.lne_rogues.item.WeaponRegister;
import com.lne_rogues.spells.RoguesSpells;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModLanguageProvider extends FabricLanguageProvider {

    public ModLanguageProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, "en_us", registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder builder) {

        LNERogues_Effects.entries.forEach(entry -> {
            builder.add(entry.effect.getTranslationKey(), entry.title);
            builder.add(entry.effect.getTranslationKey() + ".description", entry.description);
        });

        for (var entry : RoguesSpells.entries) {
            var id = entry.id();
            builder.add("spell." + id.getNamespace() + "." + id.getPath() + ".name", entry.title());
            builder.add("spell." + id.getNamespace() + "." + id.getPath() + ".description", entry.description());
        }

        for (var entry : WeaponRegister.entries) {
            builder.add(entry.item().getTranslationKey(), entry.translatedName());
        }
    }
}
