package com.lne_rogues.datagen;

import com.google.gson.JsonObject;
import com.lne_rogues.item.WeaponRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.registry.RegistryWrapper;
import net.spell_engine.api.item.weapon.Weapon;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WeaponAttributesGenerator implements DataProvider {
    private final DataOutput.PathResolver pathResolver;

    public WeaponAttributesGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        this.pathResolver = output.getResolver(DataOutput.OutputType.DATA_PACK, "weapon_attributes");
    }

    @Override
    public CompletableFuture<?> run(DataWriter writer) {
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (Weapon.Entry entry : WeaponRegister.entries) {
            if (entry.id().getPath().contains("dagger")) {
                JsonObject json = new JsonObject();
                json.addProperty("parent", "bettercombat" + ":dagger");
                Path path = pathResolver.resolveJson(entry.id());
                futures.add(DataProvider.writeToPath(writer, json, path));
            }
            if (entry.id().getPath().contains("double_axe")) {
                JsonObject json = new JsonObject();
                json.addProperty("parent", "bettercombat" + ":double_axe");
                Path path = pathResolver.resolveJson(entry.id());
                futures.add(DataProvider.writeToPath(writer, json, path));
            }
            if (entry.id().getPath().contains("glaive")) {
                JsonObject json = new JsonObject();
                json.addProperty("parent", "bettercombat" + ":glaive");
                Path path = pathResolver.resolveJson(entry.id());
                futures.add(DataProvider.writeToPath(writer, json, path));
            }
            if (entry.id().getPath().contains("sickle")) {
                JsonObject json = new JsonObject();
                json.addProperty("parent", "bettercombat" + ":sickle");
                Path path = pathResolver.resolveJson(entry.id());
                futures.add(DataProvider.writeToPath(writer, json, path));
            }
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public String getName() {
        return "LNE Rogues & Warriors Weapon Attributes";
    }
}