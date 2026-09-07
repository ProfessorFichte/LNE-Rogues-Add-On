package com.lne_rogues.datagen;

import com.google.gson.JsonObject;
import com.lne_rogues.item.WeaponRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.spell_engine.Platform;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import static com.lne_rogues.LNE_Rogues_Mod.MOD_ID;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        if (!Platform.util().isModLoaded("spell_engine")) {
            return;
        }

        for (var entry : WeaponRegister.entries) {
            Item item = entry.item();
            if (item == null) continue;

            Identifier itemId = Registries.ITEM.getId(item);
            String name = itemId.getPath();
            Identifier modelId = Identifier.of(itemId.getNamespace(), "item/" + name);

            String parent = getCustomParent(name);

            JsonObject json = new JsonObject();
            json.addProperty("parent", parent);
            JsonObject textures = new JsonObject();
            textures.addProperty("layer0", MOD_ID + ":item/" + name);
            json.add("textures", textures);

            itemModelGenerator.writer.accept(modelId, () -> json);
        }
    }

    private String getCustomParent(String name) {
        if (name.contains("glaive")) {
            return "rogues:item/base/glaive_30";
        }
        if (name.contains("double_axe")) {
            return "rogues:item/base/double_axe_18";
        }
        // Default to standard handheld model
        return "item/handheld";
    }
}
