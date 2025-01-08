package com.lne_rogues.item.weapons;

import more_rpg_loot.item.weapons.DragonMeeleeWeapon;
import net.minecraft.item.ToolMaterial;

public class DragonRogue extends DragonMeeleeWeapon {
    public DragonRogue(ToolMaterial material, Settings settings) {
        this(material, 1, 2.4F, settings);
    }

    public DragonRogue(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
}
