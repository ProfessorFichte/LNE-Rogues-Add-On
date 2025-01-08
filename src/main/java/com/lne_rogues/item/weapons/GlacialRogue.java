package com.lne_rogues.item.weapons;

import more_rpg_loot.item.weapons.GlacialMeleeWeapon;
import net.minecraft.item.ToolMaterial;

public class GlacialRogue extends GlacialMeleeWeapon {
    public GlacialRogue(ToolMaterial material, Settings settings) {
        this(material, 1, 2.4F, settings);
    }

    public GlacialRogue(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
}

