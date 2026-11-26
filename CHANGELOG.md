# Changelog

All notable changes to LNE Rogues & Warriors Add-On are documented in this file.
The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).

## [1.3.1+1.21.1]

### Changed

- Works with Loot & Explore 1.1.0 (the 1.21.1 rewrite) on Fabric and NeoForge
- Needs More RPG Library 2.8.4 or newer
- Loot tables are now made by datagen instead of hand-written files, the loot stays the same

### Fixed

- The empty chests and barrels in the Thieves Guild underground now roll loot
- Rogue tier 3 chests can drop a Relics RPG relic that exists (Medium Attack Damage relic), the old id was wrong

## [1.3.0+1.21.1]

### Changed

- Adopt Spell Engine 1.10.9 & More RPG Library 2.8.0
- **BREAKING for modpack/datapack developers:** More RPG Library's namespace was renamed `more_rpg_classes` -> `mrpg_lib` (and its advancement root moved to `mrpg_lib:root`). If you have any data files, loot tables, tags or advancements that reference the old `more_rpg_classes:`/`more_rpg_content:` ids from this mod or MRPGL, update them to `mrpg_lib:`
- Updated internal advancement parents, mod dependency declarations (fabric.mod.json, neoforge.mods.toml)

## [1.2.1+1.21.1]

### Changed

- Drop Forgified Fabric API (FFAPI) as a required dependency

## [1.2.0+1.21.1]

### Changed

- Adopt Spell Engine 1.10 - Thanks Daedelus for the PR!

## [1.1.2+1.21.1]

### Added

- Add a proper Spell Tooltip for Second Wind

### Changed

- Code Clean Up

## [1.1.1+1.21.1]

**Balancing & Internal Changes:**

### Changed

- Adapt to Spell Engine 1.9.10+ API Changes
- Second Wind now has a configurable max heal amount check in tweaks config

## [1.1.0+1.21.1]

**Update to use Spell Engine 1.9.0**

### Added

- Added the missing smithing recipes for the additional Weapons

### Changed

- DISCLAIMER: All spell books and spell scrolls will be reset, due to major API changes.
- Second Wind & Dancing Dagger are now Tier 5 Spell's
- The Spells can now also be learned in the Spell Binding Table
- The additional Spells also got slightly buffed
- Small tweaks in the Loot Tables

## [1.0.5+1.21.1]

### Added

- add stone double axe to warrior t1 loot table
- add config values for second wind effects - missing health min and max range heal

### Changed

- move all the structures from loot_n_explore to lne_rogues, so it's clearer that these structures come from this add-on
- again nerf spacing and increase exclusion from minecraft:villages

## [1.0.4+1.21.1]

### Added

- Add new Structure - Thiefs Guild

### Changed

- Move to Architectury Enviroment for Multiloader
- NeoForge Beta!
- Update Weapon Spell Power
- completely overhaul loot-tables
- Rogue Loot Tables have some small integration with a Deadeye Class Loot Table
- Warrior Loot Tables have some small integration with a War Archer Class Loot Table
- nerf separation and spacing for the structures from this mod, to make them rarer

## [1.0.3+1.21.1]

### Changed

- Dancing Dagger & Second Wind are now a T4 spell
- they can now also be looted outside the classes structure

## [1.0.2+1.21.1]

### Changed

- Spell Engine 1.7
- Dancing Dagger now also inflicts the grievous wounds effect

## [1.0.1+1.21.1]

### Changed

- Update License
- Update Mod Icon

## [1.0.0+1.21.1]

### Changed

**Official 1.21.1 Release!**

- Passive Spells For the Weapons are now handled with the new Spell Engine Passive API
- The Class related structures will now contain spell scrolls in their loot chests
- Made some small loot table tweaks
- Buff Second Wind and Dancing Dagger a bit
