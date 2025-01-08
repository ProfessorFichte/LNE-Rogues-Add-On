package com.lne_rogues.item;

import com.lne_rogues.item.weapons.DragonRogue;
import com.lne_rogues.item.weapons.ElderGuardianRogue;
import com.lne_rogues.item.weapons.GlacialRogue;
import com.lne_rogues.item.weapons.WitherRogue;
import more_rpg_loot.item.Group;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterials;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.more_rpg_classes.custom.MoreSpellSchools;
import net.spell_engine.api.item.ItemConfig;
import net.spell_engine.api.item.weapon.Weapon;
import net.spell_power.api.SpellSchools;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Supplier;

import static com.lne_rogues.LNE_Rogues_Mod.MOD_ID;
import static com.lne_rogues.LNE_Rogues_Mod.tweaksConfig;

public class WeaponRegister {
    public static final ArrayList<Weapon.Entry> entries = new ArrayList<>();

    private static Weapon.Entry entry(String name, Weapon.CustomMaterial material, Item item, ItemConfig.Weapon defaults) {
        return entry(null, name, material, item, defaults);
    }

    private static Weapon.Entry entry(String requiredMod, String name, Weapon.CustomMaterial material, Item item, ItemConfig.Weapon defaults) {
        var entry = new Weapon.Entry(MOD_ID, name, material, item, defaults, null);
        if (entry.isRequiredModInstalled()) {
            entries.add(entry);
        }
        return entry;
    }

    private static Supplier<Ingredient> ingredient(String idString, boolean requirement, Item fallback) {
        var id = new Identifier(idString);
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

    ///ATTACKSPEED_VALUES
    private static final float rogues_sickleAttackSpeed = -2.0F;
    private static final float rogues_daggerAttackSpeed = -1.6F;
    private static final float rogues_glaiveAttackSpeed = -2.6F;
    private static final float rogues_doubleAxeAttackSpeed = -2.8F;

    private static final float weaponSpellPower = 3.0F;
    //DAGGER
    private static final float daggerAttackDamage = 4.7F;
    private static Weapon.Entry daggerDragon(String name, Weapon.CustomMaterial material) {
        return daggerDragon(null, name, material);
    }
    private static Weapon.Entry daggerDragon(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new DragonRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(daggerAttackDamage, rogues_daggerAttackSpeed));
    }
    private static Weapon.Entry daggerElderGuardian(String name, Weapon.CustomMaterial material) {
        return daggerElderGuardian(null, name, material);
    }
    private static Weapon.Entry daggerElderGuardian(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new ElderGuardianRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(daggerAttackDamage, rogues_daggerAttackSpeed));
    }
    private static Weapon.Entry daggerWither(String name, Weapon.CustomMaterial material) {
        return daggerWither(null, name, material);
    }
    private static Weapon.Entry daggerWither(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new WitherRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(daggerAttackDamage, rogues_daggerAttackSpeed));
    }
    private static Weapon.Entry daggerGlacial(String name, Weapon.CustomMaterial material) {
        return daggerGlacial(null, name, material);
    }
    private static Weapon.Entry daggerGlacial(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new GlacialRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(daggerAttackDamage, rogues_daggerAttackSpeed));
    }

    //SICKLE
    private static final float sickleAttackDamage = 5.9F;
    private static Weapon.Entry sickleDragon(String name, Weapon.CustomMaterial material) {
        return sickleDragon(null, name, material);
    }
    private static Weapon.Entry sickleDragon(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new DragonRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(sickleAttackDamage, rogues_sickleAttackSpeed));
    }
    private static Weapon.Entry sickleElderGuardian(String name, Weapon.CustomMaterial material) {
        return sickleElderGuardian(null, name, material);
    }
    private static Weapon.Entry sickleElderGuardian(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new ElderGuardianRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(sickleAttackDamage, rogues_sickleAttackSpeed));
    }
    private static Weapon.Entry sickleWither(String name, Weapon.CustomMaterial material) {
        return sickleWither(null, name, material);
    }
    private static Weapon.Entry sickleWither(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new WitherRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(sickleAttackDamage, rogues_sickleAttackSpeed));
    }
    private static Weapon.Entry sickleGlacial(String name, Weapon.CustomMaterial material) {
        return sickleGlacial(null, name, material);
    }
    private static Weapon.Entry sickleGlacial(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new GlacialRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(sickleAttackDamage, rogues_sickleAttackSpeed));
    }

    //GLAIVE
    private static final float glaiveAttackDamage = 8.1F;
    private static Weapon.Entry glaiveDragon(String name, Weapon.CustomMaterial material) {
        return glaiveDragon(null, name, material);
    }
    private static Weapon.Entry glaiveDragon(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new DragonRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(glaiveAttackDamage, rogues_glaiveAttackSpeed));
    }
    private static Weapon.Entry glaiveElderGuardian(String name, Weapon.CustomMaterial material) {
        return glaiveElderGuardian(null, name, material);
    }
    private static Weapon.Entry glaiveElderGuardian(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new ElderGuardianRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(glaiveAttackDamage, rogues_glaiveAttackSpeed));
    }
    private static Weapon.Entry glaiveWither(String name, Weapon.CustomMaterial material) {
        return glaiveWither(null, name, material);
    }
    private static Weapon.Entry glaiveWither(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new WitherRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(glaiveAttackDamage, rogues_glaiveAttackSpeed));
    }
    private static Weapon.Entry glaiveGlacial(String name, Weapon.CustomMaterial material) {
        return glaiveGlacial(null, name, material);
    }
    private static Weapon.Entry glaiveGlacial(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new GlacialRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(glaiveAttackDamage, rogues_glaiveAttackSpeed));
    }

    //DOUBLE AXE
    private static final float doubleAxeAttackDamage = 9.6F;
    private static Weapon.Entry doubleAxeDragon(String name, Weapon.CustomMaterial material) {
        return doubleAxeDragon(null, name, material);
    }
    private static Weapon.Entry doubleAxeDragon(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new DragonRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(doubleAxeAttackDamage, rogues_doubleAxeAttackSpeed));
    }
    private static Weapon.Entry doubleAxeElderGuardian(String name, Weapon.CustomMaterial material) {
        return doubleAxeElderGuardian(null, name, material);
    }
    private static Weapon.Entry doubleAxeElderGuardian(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new ElderGuardianRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(doubleAxeAttackDamage, rogues_doubleAxeAttackSpeed));
    }
    private static Weapon.Entry doubleAxeWither(String name, Weapon.CustomMaterial material) {
        return doubleAxeWither(null, name, material);
    }
    private static Weapon.Entry doubleAxeWither(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new WitherRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(doubleAxeAttackDamage, rogues_doubleAxeAttackSpeed));
    }
    private static Weapon.Entry doubleAxeGlacial(String name, Weapon.CustomMaterial material) {
        return doubleAxeGlacial(null, name, material);
    }
    private static Weapon.Entry doubleAxeGlacial(String requiredMod, String name, Weapon.CustomMaterial material) {
        var settings = new Item.Settings();
        var item = new GlacialRogue(material, settings);
        return entry(requiredMod, name, material, item, new ItemConfig.Weapon(doubleAxeAttackDamage, rogues_doubleAxeAttackSpeed));
    }


    public static void register(Map<String, ItemConfig.Weapon> configs) {
        if (!tweaksConfig.value.disable_special_lne_weapons) {
            var dragonRepair = ingredient("loot_n_explore:ender_dragon_scales",
                    FabricLoader.getInstance().isModLoaded("loot_n_explore"), Items.NETHERITE_INGOT);
            var elderGuardianRepair = ingredient("loot_n_explore:elder_guardian_eye",
                    FabricLoader.getInstance().isModLoaded("loot_n_explore"), Items.NETHERITE_INGOT);
            var frostMonarchRepair = ingredient("loot_n_explore:frozen_soul",
                    FabricLoader.getInstance().isModLoaded("loot_n_explore"), Items.NETHERITE_INGOT);
            var witherRepair = ingredient("minecraft:nether_star",
                    FabricLoader.getInstance().isModLoaded("loot_n_explore"), Items.NETHERITE_INGOT);
            //DAGGER
            daggerDragon("ender_dragon_dagger",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, dragonRepair))
                    .attribute(ItemConfig.Attribute.bonus(SpellSchools.ARCANE.id, weaponSpellPower));
            daggerElderGuardian("elder_guardian_dagger",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, elderGuardianRepair))
                    .attribute(ItemConfig.Attribute.bonus(MoreSpellSchools.WATER.id, weaponSpellPower));
            daggerWither("wither_dagger",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, witherRepair))
                    .attribute(ItemConfig.Attribute.bonus(SpellSchools.SOUL.id, weaponSpellPower));
            daggerGlacial("glacial_dagger",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, frostMonarchRepair))
                    .attribute(ItemConfig.Attribute.bonus(SpellSchools.FROST.id, weaponSpellPower));

            //SICKLE
            sickleDragon("ender_dragon_sickle",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, dragonRepair))
                    .attribute(ItemConfig.Attribute.bonus(SpellSchools.ARCANE.id, weaponSpellPower));
            sickleElderGuardian("elder_guardian_sickle",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, elderGuardianRepair))
                    .attribute(ItemConfig.Attribute.bonus(MoreSpellSchools.WATER.id, weaponSpellPower));
            sickleWither("wither_sickle",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, witherRepair))
                    .attribute(ItemConfig.Attribute.bonus(SpellSchools.SOUL.id, weaponSpellPower));
            sickleGlacial("glacial_sickle",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, frostMonarchRepair))
                    .attribute(ItemConfig.Attribute.bonus(SpellSchools.FROST.id, weaponSpellPower));

            //GLAIVE
            glaiveDragon("ender_dragon_glaive",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, dragonRepair))
                    .attribute(ItemConfig.Attribute.bonus(SpellSchools.ARCANE.id, weaponSpellPower));
            glaiveElderGuardian("elder_guardian_glaive",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, elderGuardianRepair))
                    .attribute(ItemConfig.Attribute.bonus(MoreSpellSchools.WATER.id, weaponSpellPower));
            glaiveWither("wither_glaive",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, witherRepair))
                    .attribute(ItemConfig.Attribute.bonus(SpellSchools.SOUL.id, weaponSpellPower));
            glaiveGlacial("glacial_glaive",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, frostMonarchRepair))
                    .attribute(ItemConfig.Attribute.bonus(SpellSchools.FROST.id, weaponSpellPower));

            //DOUBLE AXE
            doubleAxeDragon("ender_dragon_double_axe",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, dragonRepair))
                    .attribute(ItemConfig.Attribute.bonus(SpellSchools.ARCANE.id, weaponSpellPower));
            doubleAxeElderGuardian("elder_guardian_double_axe",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, elderGuardianRepair))
                    .attribute(ItemConfig.Attribute.bonus(MoreSpellSchools.WATER.id, weaponSpellPower));
            doubleAxeWither("wither_double_axe",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, witherRepair))
                    .attribute(ItemConfig.Attribute.bonus(SpellSchools.SOUL.id, weaponSpellPower));
            doubleAxeGlacial("glacial_double_axe",
                    Weapon.CustomMaterial.matching(ToolMaterials.NETHERITE, frostMonarchRepair))
                    .attribute(ItemConfig.Attribute.bonus(SpellSchools.FROST.id, weaponSpellPower));


        }

        Weapon.register(configs, entries, Group.RPG_LOOT_KEY);
    }
}