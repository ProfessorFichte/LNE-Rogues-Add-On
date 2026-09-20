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

- Thanks to Daedelus for the PR!
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
