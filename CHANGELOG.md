# 1.2.1+1.20.1

> ### ⚠️ Read this before updating
>
> This release is a **major technical overhaul and is not backwards compatible.**
>
> - **Requires the matching Spell Engine and More RPG Library releases.** This version will not run on
>   Spell Engine **0.9.x**, and mods built against 0.9.x will not work alongside it.
> - **Update the whole set together.** Spell Engine, More RPG Library and every RPG Series mod must be on
>   matching versions. Mixing in an older add-on will break at startup or misbehave in play.
>
> **Back up your world before updating.**

- Ported to Minecraft 1.20.1 (Fabric + Forge 47). NeoForge is replaced by Forge on this line; the same
  Forge jar also loads on NeoForge 1.20.1.
- Requires the matching 1.20.1 releases of Spell Engine (1.10.5), More RPG Library (2.7.2) and
  Rogues & Warriors (3.1.1).
- Loot & Explore has no 1.20.1 Forge build, so on Forge this add-on contributes the Second Wind effect
  and the two spells only - the 16 weapons and their smithing recipes need Loot & Explore, same as the
  NeoForge build on 1.21.1.
- Every registry write goes through Forge's `RegisterEvent` window, so the mod also boots on Forge
  47.0-47.3 and on NeoForge 1.20.1, which never unlock the vanilla registries.

### Accepted 1.20.1 limitations

- 1.20.1 has no `generic.max_absorption` attribute, so Second Wind no longer carries its +2 attribute
  modifier. The absorption itself is unchanged - the effect grants it directly when applied.
- The Thieves' Guild structure's jigsaw depth is capped at 7 (1.20.1's limit) instead of 12. The
  structure's pool chain is only two elements deep, so nothing is lost.
- Datapacks overriding this mod's smithing recipes must use Forge's top-level `conditions` block
  instead of `neoforge:conditions`.

# 1.2.1 - 1.21.1
- Drop Forgified Fabric API (FFAPI) as a required dependency

# 1.2.0 - 1.21.1
- Adopt Spell Engine 1.10 - Thanks Daedelus for the PR!

# 1.1.2 - 1.21.1
- Add a proper Spell Tooltip for Second Wind
- Code Clean Up

# 1.1.1 - 1.21.1
- Adapt to Spell Engine 1.9.10+ API Changes
**Balancing & Internal Changes:**
- Second Wind now has a configurable max heal amount check in tweaks config

# 1.1.0 - 1.21.1
**Update to use Spell Engine 1.9.0**
- DISCLAIMER: All spell books and spell scrolls will be reset, due to major API changes.
- Second Wind & Dancing Dagger are now Tier 5 Spell's
- The Spells can now also be learned in the Spell Binding Table
- The additional Spells also got slightly buffed
- Small tweaks in the Loot Tables
- Added the missing smithing recipes for the additional Weapons

# 1.0.5 - 1.21.1
- move all the structures from loot_n_explore to lne_rogues, so it's clearer that these structures come from this add-on
- again nerf spacing and increase exclusion from minecraft:villages
- add stone double axe to warrior t1 loot table
- add config values for second wind effects - missing health min and max range heal

# 1.0.4 - 1.21.1
- Move to Architectury Enviroment for Multiloader
- NeoForge Beta!
- Update Weapon Spell Power
- Add new Structure - Thiefs Guild
- completely overhaul loot-tables
- Rogue Loot Tables have some small integration with a Deadeye Class Loot Table
- Warrior Loot Tables have some small integration with a War Archer Class Loot Table
- nerf separation and spacing for the structures from this mod, to make them rarer

# 1.0.3 - 1.21.1
- Dancing Dagger & Second Wind are now a T4 spell
- they can now also be looted outside the classes structure

# 1.0.2 - 1.21.1
- Spell Engine 1.7
- Dancing Dagger now also inflicts the grievous wounds effect

# 1.0.1 - 1.21.1
- Update License
- Update Mod Icon

# 1.0.0 - 1.21.1
## Official 1.21.1 Release!
### CHANGES
- Passive Spells For the Weapons are now handled with the new Spell Engine Passive API
- The Class related structures will now contain spell scrolls in their loot chests
- Made some small loot table tweaks
- Buff Second Wind and Dancing Dagger a bit