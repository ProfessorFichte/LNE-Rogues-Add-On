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
        // Templates and additions from loot_n_explore mod
        var dragonTemplate = Identifier.of("loot_n_explore", "dragon_upgrade_smithing_template");
        var guardianTemplate = Identifier.of("loot_n_explore", "guardian_upgrade_smithing_template");
        var witherTemplate = Identifier.of("loot_n_explore", "wither_upgrade_smithing_template");
        var frostMonarchTemplate = Identifier.of("loot_n_explore", "frostmonarch_upgrade_smithing_template");

        var dragonScales = Identifier.of("loot_n_explore", "ender_dragon_scales");
        var guardianEye = Identifier.of("loot_n_explore", "elder_guardian_eye");
        var witherSpine = Identifier.of("loot_n_explore", "wither_spine");
        var frozenSoul = Identifier.of("loot_n_explore", "frozen_soul");

        // Base items from rogues mod
        var netheriteDagger = Identifier.of("rogues", "netherite_dagger");
        var netheriteSickle = Identifier.of("rogues", "netherite_sickle");
        var netheriteDoubleAxe = Identifier.of("rogues", "netherite_double_axe");
        var netheriteGlaive = Identifier.of("rogues", "netherite_glaive");

        // Get result items from registry
        var entries = WeaponRegister.entries;

        // DAGGERS
        createSmithingTransformRecipe(
                "ender_dragon_dagger_smithing",
                Registries.ITEM.get(netheriteDagger),
                dragonTemplate,
                dragonScales,
                Registries.ITEM.get(Identifier.of("lne_rogues", "ender_dragon_dagger")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "elder_guardian_dagger_smithing",
                Registries.ITEM.get(netheriteDagger),
                guardianTemplate,
                guardianEye,
                Registries.ITEM.get(Identifier.of("lne_rogues", "elder_guardian_dagger")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "wither_dagger_smithing",
                Registries.ITEM.get(netheriteDagger),
                witherTemplate,
                witherSpine,
                Registries.ITEM.get(Identifier.of("lne_rogues", "wither_dagger")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "glacial_dagger_smithing",
                Registries.ITEM.get(netheriteDagger),
                frostMonarchTemplate,
                frozenSoul,
                Registries.ITEM.get(Identifier.of("lne_rogues", "glacial_dagger")),
                "loot_n_explore"
        );
        /// SICKLES
        createSmithingTransformRecipe(
                "ender_dragon_sickle_smithing",
                Registries.ITEM.get(netheriteSickle),
                dragonTemplate,
                dragonScales,
                Registries.ITEM.get(Identifier.of("lne_rogues", "ender_dragon_sickle")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "elder_guardian_sickle_smithing",
                Registries.ITEM.get(netheriteSickle),
                guardianTemplate,
                guardianEye,
                Registries.ITEM.get(Identifier.of("lne_rogues", "elder_guardian_sickle")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "wither_sickle_smithing",
                Registries.ITEM.get(netheriteSickle),
                witherTemplate,
                witherSpine,
                Registries.ITEM.get(Identifier.of("lne_rogues", "wither_sickle")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "glacial_sickle_smithing",
                Registries.ITEM.get(netheriteSickle),
                frostMonarchTemplate,
                frozenSoul,
                Registries.ITEM.get(Identifier.of("lne_rogues", "glacial_sickle")),
                "loot_n_explore"
        );
        /// DOUBLE AXE
        createSmithingTransformRecipe(
                "ender_dragon_double_axe_smithing",
                Registries.ITEM.get(netheriteDoubleAxe),
                dragonTemplate,
                dragonScales,
                Registries.ITEM.get(Identifier.of("lne_rogues", "ender_dragon_double_axe")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "elder_guardian_double_axe_smithing",
                Registries.ITEM.get(netheriteDoubleAxe),
                guardianTemplate,
                guardianEye,
                Registries.ITEM.get(Identifier.of("lne_rogues", "elder_guardian_double_axe")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "wither_double_axe_smithing",
                Registries.ITEM.get(netheriteDoubleAxe),
                witherTemplate,
                witherSpine,
                Registries.ITEM.get(Identifier.of("lne_rogues", "wither_double_axe")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "glacial_double_axe_smithing",
                Registries.ITEM.get(netheriteDoubleAxe),
                frostMonarchTemplate,
                frozenSoul,
                Registries.ITEM.get(Identifier.of("lne_rogues", "glacial_double_axe")),
                "loot_n_explore"
        );
        /// GLAIVE
        createSmithingTransformRecipe(
                "ender_dragon_glaive_smithing",
                Registries.ITEM.get(netheriteGlaive),
                dragonTemplate,
                dragonScales,
                Registries.ITEM.get(Identifier.of("lne_rogues", "ender_dragon_glaive")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "elder_guardian_glaive_smithing",
                Registries.ITEM.get(netheriteGlaive),
                guardianTemplate,
                guardianEye,
                Registries.ITEM.get(Identifier.of("lne_rogues", "elder_guardian_glaive")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "wither_glaive_smithing",
                Registries.ITEM.get(netheriteGlaive),
                witherTemplate,
                witherSpine,
                Registries.ITEM.get(Identifier.of("lne_rogues", "wither_glaive")),
                "loot_n_explore"
        );
        createSmithingTransformRecipe(
                "glacial_glaive_smithing",
                Registries.ITEM.get(netheriteGlaive),
                frostMonarchTemplate,
                frozenSoul,
                Registries.ITEM.get(Identifier.of("lne_rogues", "glacial_glaive")),
                "loot_n_explore"
        );
    }
}
