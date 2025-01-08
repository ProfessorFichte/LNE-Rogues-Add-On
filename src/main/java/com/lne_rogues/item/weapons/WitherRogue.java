package com.lne_rogues.item.weapons;

import more_rpg_loot.item.weapons.WitherMeleeWeapon;
import net.minecraft.item.ToolMaterial;

public class WitherRogue extends WitherMeleeWeapon {
    public WitherRogue(ToolMaterial material, Settings settings) {
        this(material, 1, 2.4F, settings);
    }

    public WitherRogue(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
}
