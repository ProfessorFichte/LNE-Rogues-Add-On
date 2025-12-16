package com.lne_rogues.datagen;

import com.lne_rogues.spells.RoguesSpells;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryWrapper;
import net.spell_engine.api.datagen.SpellGenerator;

import java.util.concurrent.CompletableFuture;

public class RoguesAbilityDatagen extends SpellGenerator {
    public RoguesAbilityDatagen(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateSpells(Builder builder) {
        // Register all spells from the RoguesSpells entries list
        for (var entry : RoguesSpells.entries) {
            builder.add(entry.id(), entry.spell());
        }
    }
}
