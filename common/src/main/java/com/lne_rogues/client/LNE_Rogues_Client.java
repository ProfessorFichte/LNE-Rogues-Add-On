package com.lne_rogues.client;

import com.lne_rogues.spells.RoguesSpells;
import net.spell_engine.client.gui.SpellTooltip;

public class LNE_Rogues_Client {

    public static void init() {
        for (var entry : RoguesSpells.entries) {
            if (entry.mutator() != null) {
                SpellTooltip.addDescriptionMutator(entry.id(), entry.mutator());
            }
        }
    }
}
