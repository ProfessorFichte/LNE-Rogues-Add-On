package com.lne_rogues.client;

import com.lne_rogues.spells.RoguesSpells;

public class LNE_Rogues_Client {

    public static void init() {
        // Was `SpellTooltip.addDescriptionMutator` per entry; the mutator moved to the
        // server-safe `TooltipTokens.registerCustom`, registered by the spell definitions
        // themselves, so there is one call instead of a loop over the entries.
        RoguesSpells.registerTooltipTokens();
    }
}
