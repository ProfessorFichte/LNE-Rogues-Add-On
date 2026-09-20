package com.lne_rogues.datagen;

import com.lne_rogues.item.WeaponRegister;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.more_rpg_classes.datagen.SmithingRecipeGenerator;

public class ModRecipeProvider extends SmithingRecipeGenerator {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output, "lne_rogues");
    }

    @Override
    public void generate() {
        var dragonTemplate = new Identifier("loot_n_explore", "dragon_upgrade_smithing_template");
        var guardianTemplate = new Identifier("loot_n_explore", "guardian_upgrade_smithing_template");
        var witherTemplate = new Identifier("loot_n_explore", "wither_upgrade_smithing_template");
        var frostMonarchTemplate = new Identifier("loot_n_explore", "frostmonarch_upgrade_smithing_template");

        var dragonScales = new Identifier("loot_n_explore", "ender_dragon_scales");
        var guardianEye = new Identifier("loot_n_explore", "elder_guardian_eye");
        var witherSpine = new Identifier("loot_n_explore", "wither_spine");
        var frozenSoul = new Identifier("loot_n_explore", "frozen_soul");

        var netheriteDagger = new Identifier("rogues", "netherite_dagger");
        var netheriteSickle = new Identifier("rogues", "netherite_sickle");
        var netheriteDoubleAxe = new Identifier("rogues", "netherite_double_axe");
        var netheriteGlaive = new Identifier("rogues", "netherite_glaive");

        var entries = WeaponRegister.entries;

        createSmithingTransformRecipe(
                "ender_dragon_dagger_smithing",
                Registries.ITEM.get(netheriteDagger),
                dragonTemplate,
                dragonScales,
                Registries.ITEM.get(new Identifier("lne_rogues", "ender_dragon_dagger")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "elder_guardian_dagger_smithing",
                Registries.ITEM.get(netheriteDagger),
                guardianTemplate,
                guardianEye,
                Registries.ITEM.get(new Identifier("lne_rogues", "elder_guardian_dagger")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "wither_dagger_smithing",
                Registries.ITEM.get(netheriteDagger),
                witherTemplate,
                witherSpine,
                Registries.ITEM.get(new Identifier("lne_rogues", "wither_dagger")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "glacial_dagger_smithing",
                Registries.ITEM.get(netheriteDagger),
                frostMonarchTemplate,
                frozenSoul,
                Registries.ITEM.get(new Identifier("lne_rogues", "glacial_dagger")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "ender_dragon_sickle_smithing",
                Registries.ITEM.get(netheriteSickle),
                dragonTemplate,
                dragonScales,
                Registries.ITEM.get(new Identifier("lne_rogues", "ender_dragon_sickle")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "elder_guardian_sickle_smithing",
                Registries.ITEM.get(netheriteSickle),
                guardianTemplate,
                guardianEye,
                Registries.ITEM.get(new Identifier("lne_rogues", "elder_guardian_sickle")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "wither_sickle_smithing",
                Registries.ITEM.get(netheriteSickle),
                witherTemplate,
                witherSpine,
                Registries.ITEM.get(new Identifier("lne_rogues", "wither_sickle")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "glacial_sickle_smithing",
                Registries.ITEM.get(netheriteSickle),
                frostMonarchTemplate,
                frozenSoul,
                Registries.ITEM.get(new Identifier("lne_rogues", "glacial_sickle")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "ender_dragon_double_axe_smithing",
                Registries.ITEM.get(netheriteDoubleAxe),
                dragonTemplate,
                dragonScales,
                Registries.ITEM.get(new Identifier("lne_rogues", "ender_dragon_double_axe")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "elder_guardian_double_axe_smithing",
                Registries.ITEM.get(netheriteDoubleAxe),
                guardianTemplate,
                guardianEye,
                Registries.ITEM.get(new Identifier("lne_rogues", "elder_guardian_double_axe")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "wither_double_axe_smithing",
                Registries.ITEM.get(netheriteDoubleAxe),
                witherTemplate,
                witherSpine,
                Registries.ITEM.get(new Identifier("lne_rogues", "wither_double_axe")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "glacial_double_axe_smithing",
                Registries.ITEM.get(netheriteDoubleAxe),
                frostMonarchTemplate,
                frozenSoul,
                Registries.ITEM.get(new Identifier("lne_rogues", "glacial_double_axe")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "ender_dragon_glaive_smithing",
                Registries.ITEM.get(netheriteGlaive),
                dragonTemplate,
                dragonScales,
                Registries.ITEM.get(new Identifier("lne_rogues", "ender_dragon_glaive")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "elder_guardian_glaive_smithing",
                Registries.ITEM.get(netheriteGlaive),
                guardianTemplate,
                guardianEye,
                Registries.ITEM.get(new Identifier("lne_rogues", "elder_guardian_glaive")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "wither_glaive_smithing",
                Registries.ITEM.get(netheriteGlaive),
                witherTemplate,
                witherSpine,
                Registries.ITEM.get(new Identifier("lne_rogues", "wither_glaive")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "glacial_glaive_smithing",
                Registries.ITEM.get(netheriteGlaive),
                frostMonarchTemplate,
                frozenSoul,
                Registries.ITEM.get(new Identifier("lne_rogues", "glacial_glaive")),
                "loot_n_explore"
        );
    }
}
