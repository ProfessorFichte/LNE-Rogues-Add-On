package com.lne_rogues.item;

import more_rpg_loot.compat.spell_engine.LNE_Abilities;
import more_rpg_loot.item.Group;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.more_rpg_classes.custom.MoreSpellSchools;
import net.spell_engine.api.config.AttributeModifier;
import net.spell_engine.api.config.WeaponConfig;
import net.spell_engine.api.spell.container.SpellContainers;
import net.spell_engine.rpg_series.datagen.WeaponSkills;
import net.spell_engine.rpg_series.item.Equipment;
import net.spell_engine.rpg_series.item.Weapon;
import net.spell_power.api.SpellSchools;
import net.spell_engine.api.item.weapon.SpellSwordItem;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Supplier;

import static com.lne_rogues.LNE_Rogues_Mod.MOD_ID;
import static com.lne_rogues.LNE_Rogues_Mod.tweaksConfig;
import static more_rpg_loot.compat.spell_engine.LNE_Weapons.*;

public class WeaponRegister {
    public static final ArrayList<Weapon.Entry> entries = new ArrayList<>();

    private static Weapon.Entry entry(String name, Weapon.CustomMaterial material, Weapon.Factory factory, WeaponConfig defaults, Equipment.WeaponType type) {
        var entry = new Weapon.Entry(MOD_ID, name, material, factory, defaults, type);
        entry.spellContainer(SpellContainers.forMagicWeapon());
        entries.add(entry);
        return entry;
    }

    private static Supplier<Ingredient> ingredient(String idString, boolean requirement, Item fallback) {
        var id = Identifier.of(idString);
        if (requirement) {
            return () -> {
                return Ingredient.ofItems(fallback);
            };
        } else {
            return () -> {
                var item = Registries.ITEM.get(id);
                var ingredient = item != null ? item : fallback;
                return Ingredient.ofItems(ingredient);
            };
        }
    }

    private static Weapon.Entry dagger(String name, Weapon.CustomMaterial material, float damage) {
        return entry(name, material, SpellSwordItem::new, new WeaponConfig(damage, rogues_daggerAttackSpeed), Equipment.WeaponType.DAGGER)
                .spellContainer(SpellContainers.forMeleeWeapon().withSpellId(WeaponSkills.FAN_OF_KNIVES.id()));
    }
    private static Weapon.Entry axe(String name, Weapon.CustomMaterial material, float damage) {
        return entry(name, material, SpellSwordItem::new, new WeaponConfig(damage, rogues_doubleAxeAttackSpeed), Equipment.WeaponType.DOUBLE_AXE)
                .spellContainer(SpellContainers.forMeleeWeapon().withSpellId(WeaponSkills.WHIRLWIND.id()));
    }
    private static Weapon.Entry glaive(String name, Weapon.CustomMaterial material, float damage) {
        return entry(name, material, SpellSwordItem::new, new WeaponConfig(damage, rogues_glaiveAttackSpeed), Equipment.WeaponType.GLAIVE)
                .spellContainer(SpellContainers.forMeleeWeapon().withSpellId(WeaponSkills.THRUST.id()));
    }
    private static Weapon.Entry sickle(String name, Weapon.CustomMaterial material, float damage) {
        return entry(name, material, SpellSwordItem::new, new WeaponConfig(damage, rogues_sickleAttackSpeed), Equipment.WeaponType.SICKLE)
                .spellContainer(SpellContainers.forMeleeWeapon().withSpellId(WeaponSkills.SWIPE.id()));
    }

    private static final float rogues_sickleAttackSpeed = -2.0F;
    private static final float rogues_daggerAttackSpeed = -1.6F;
    private static final float rogues_glaiveAttackSpeed = -2.6F;
    private static final float rogues_doubleAxeAttackSpeed = -2.8F;
    private static final float weaponSpellPower = 4.0F;
    private static final float daggerAttackDamage = 5.5F;
    private static final float sickleAttackDamage = 6.8F;
    private static final float glaiveAttackDamage = 9.3F;
    private static final float doubleAxeAttackDamage = 11.0F;

    public static void register(Map<String, WeaponConfig> configs) {
        if (!tweaksConfig.value.disable_special_lne_weapons) {
            dagger("ender_dragon_dagger",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.AMETHYST_SHARD)), daggerAttackDamage)
                    .translatedName("Void Sting")
                    .withAdditionalSpell(LNE_Abilities.dragonclaw.id().toString())
                    .attribute(AttributeModifier.bonus(SpellSchools.ARCANE.id, weaponSpellPower));
            sickle("ender_dragon_sickle",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.AMETHYST_SHARD)), sickleAttackDamage)
                    .translatedName("Dragon Sickle")
                    .withAdditionalSpell(LNE_Abilities.dragonclaw.id().toString())
                    .attribute(AttributeModifier.bonus(SpellSchools.ARCANE.id, weaponSpellPower));
            glaive("ender_dragon_glaive",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.AMETHYST_SHARD)), glaiveAttackDamage)
                    .translatedName("Ender Fang")
                    .withAdditionalSpell(LNE_Abilities.dragonclaw.id().toString())
                    .attribute(AttributeModifier.bonus(SpellSchools.ARCANE.id, weaponSpellPower));
            axe("ender_dragon_double_axe",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.AMETHYST_SHARD)), doubleAxeAttackDamage)
                    .translatedName("Dragonclaw")
                    .withAdditionalSpell(LNE_Abilities.dragonclaw.id().toString())
                    .attribute(AttributeModifier.bonus(SpellSchools.ARCANE.id, weaponSpellPower));
            dagger("elder_guardian_dagger",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.PRISMARINE_SHARD)), daggerAttackDamage)
                    .translatedName("Reef Dagger")
                    .withAdditionalSpell(LNE_Abilities.waterbomb.id().toString())
                    .attribute(AttributeModifier.bonus(MoreSpellSchools.WATER.id, weaponSpellPower));
            sickle("elder_guardian_sickle",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.PRISMARINE_SHARD)), sickleAttackDamage)
                    .translatedName("Abyssal Reaper")
                    .withAdditionalSpell(LNE_Abilities.waterbomb.id().toString())
                    .attribute(AttributeModifier.bonus(MoreSpellSchools.WATER.id, weaponSpellPower));
            glaive("elder_guardian_glaive",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.PRISMARINE_SHARD)), glaiveAttackDamage)
                    .translatedName("Sea Serpent's Fang")
                    .withAdditionalSpell(LNE_Abilities.waterbomb.id().toString())
                    .attribute(AttributeModifier.bonus(MoreSpellSchools.WATER.id, weaponSpellPower));
            axe("elder_guardian_double_axe",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.PRISMARINE_SHARD)), doubleAxeAttackDamage)
                    .translatedName("Tidal Cleaver")
                    .withAdditionalSpell(LNE_Abilities.waterbomb.id().toString())
                    .attribute(AttributeModifier.bonus(MoreSpellSchools.WATER.id, weaponSpellPower));
            dagger("wither_dagger",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.BONE)), daggerAttackDamage)
                    .translatedName("Soulrender")
                    .withAdditionalSpell(LNE_Abilities.wither_pulse.id().toString())
                    .attribute(AttributeModifier.bonus(SpellSchools.SOUL.id, weaponSpellPower));
            sickle("wither_sickle",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.BONE)), sickleAttackDamage)
                    .translatedName("Wither Reaper")
                    .withAdditionalSpell(LNE_Abilities.wither_pulse.id().toString())
                    .attribute(AttributeModifier.bonus(SpellSchools.SOUL.id, weaponSpellPower));
            glaive("wither_glaive",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.BONE)), glaiveAttackDamage)
                    .translatedName("Withered Glaive")
                    .withAdditionalSpell(LNE_Abilities.wither_pulse.id().toString())
                    .attribute(AttributeModifier.bonus(SpellSchools.SOUL.id, weaponSpellPower));
            axe("wither_double_axe",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.BONE)), doubleAxeAttackDamage)
                    .translatedName("Wither Double Axe")
                    .withAdditionalSpell(LNE_Abilities.wither_pulse.id().toString())
                    .attribute(AttributeModifier.bonus(SpellSchools.SOUL.id, weaponSpellPower));
            dagger("glacial_dagger",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.ICE)), daggerAttackDamage)
                    .translatedName("Frosted Shard")
                    .withAdditionalSpell(LNE_Abilities.avalanche.id().toString())
                    .attribute(AttributeModifier.bonus(SpellSchools.FROST.id, weaponSpellPower));
            sickle("glacial_sickle",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.ICE)), sickleAttackDamage)
                    .translatedName("Frost Reaper")
                    .withAdditionalSpell(LNE_Abilities.avalanche.id().toString())
                    .attribute(AttributeModifier.bonus(SpellSchools.FROST.id, weaponSpellPower));
            glaive("glacial_glaive",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.ICE)), glaiveAttackDamage)
                    .translatedName("Frozen Fang")
                    .withAdditionalSpell(LNE_Abilities.avalanche.id().toString())
                    .attribute(AttributeModifier.bonus(SpellSchools.FROST.id, weaponSpellPower));
            axe("glacial_double_axe",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, () -> Ingredient.ofItems(Items.ICE)), doubleAxeAttackDamage)
                    .translatedName("Glacial Cleaver")
                    .withAdditionalSpell(LNE_Abilities.avalanche.id().toString())
                    .attribute(AttributeModifier.bonus(SpellSchools.FROST.id, weaponSpellPower));
        }
        entries.forEach(entry -> entry.rarity = Rarity.RARE);
        Weapon.register(configs, entries, Group.RPG_LOOT_KEY);
    }
}