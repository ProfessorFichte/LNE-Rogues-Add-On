package com.lne_rogues.item.weapons;

import more_rpg_loot.item.weapons.ElderGuardianMeleeWeapon;
import net.minecraft.item.ToolMaterial;

public class ElderGuardianRogue extends ElderGuardianMeleeWeapon {
    public ElderGuardianRogue(ToolMaterial material, Settings settings) {
        this(material, 1, 2.4F, settings);
    }

    public ElderGuardianRogue(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
}
