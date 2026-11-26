package com.lne_rogues.datagen;

import com.mrpg_lib.compat.spell_engine.loot.SpecificSpellScrollPoolLootFunction;
import com.mrpg_lib.util.loot.ConditionalItemEntry;
import com.mrpg_lib.util.loot.ConditionalItemLootFunction;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.KilledByPlayerLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.condition.SurvivesExplosionLootCondition;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static com.lne_rogues.LNE_Rogues_Mod.MOD_ID;

public abstract class ModLootTableProvider extends SimpleFabricLootTableProvider {
    private BiConsumer<RegistryKey<LootTable>, LootTable.Builder> exporter;

    protected ModLootTableProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup, LootContextType type) {
        super(output, registryLookup, type);
    }

    protected abstract void generate();

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> exporter) {
        this.exporter = exporter;
        generate();
    }

    protected void table(String path, LootPool.Builder... pools) {
        var table = LootTable.builder();
        for (var pool : pools) table.pool(pool);
        exporter.accept(RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(MOD_ID, path)), table);
    }

    protected static LootPool.Builder pool(LootNumberProvider rolls, LootPoolEntry.Builder<?>... entries) {
        var pool = LootPool.builder().rolls(rolls);
        for (var entry : entries) pool.with(entry);
        return pool;
    }

    protected static LootNumberProvider constant(float value) {
        return ConstantLootNumberProvider.create(value);
    }

    protected static LootNumberProvider uniform(float min, float max) {
        return UniformLootNumberProvider.create(min, max);
    }

    protected static LeafEntry.Builder<?> item(String id, int weight, LootFunction.Builder... functions) {
        Item item = Registries.ITEM.get(Identifier.of(id));
        if (item == Items.AIR) throw new IllegalStateException("Loot table item is not registered: " + id);
        var entry = ItemEntry.builder(item).weight(weight);
        for (var function : functions) entry.apply(function);
        return entry;
    }

    protected static LeafEntry.Builder<?> conditional(String id, int weight, LootFunction.Builder... functions) {
        var entry = ConditionalItemEntry.builder(Identifier.of(id), Optional.empty()).weight(weight);
        for (var function : functions) entry.apply(function);
        return entry;
    }

    protected static LeafEntry.Builder<?> empty(int weight) {
        return EmptyEntry.builder().weight(weight);
    }

    protected static LeafEntry.Builder<?> nested(String id, int weight) {
        return LootTableEntry.builder(RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(id))).weight(weight);
    }

    protected static ConditionalLootFunction.Builder<?> count(LootNumberProvider count) {
        return SetCountLootFunction.builder(count);
    }

    protected static EnchantWithLevelsLootFunction.Builder enchant(float min, float max) {
        return new EnchantWithLevelsLootFunction.Builder(uniform(min, max));
    }

    protected static LootFunction.Builder scroll(int tierMin, int tierMax, int count, String... pools) {
        return SpecificSpellScrollPoolLootFunction.builder(List.of(pools), constant(tierMin), constant(tierMax), constant(count), List.of());
    }

    protected static LootFunction.Builder conditionalItem(String id) {
        return ConditionalItemLootFunction.builder(Identifier.of(id));
    }

    protected static LootCondition.Builder chance(float chance) {
        return RandomChanceLootCondition.builder(chance);
    }

    protected static LootCondition.Builder killedByPlayer() {
        return KilledByPlayerLootCondition.builder();
    }

    protected static LootCondition.Builder survivesExplosion() {
        return SurvivesExplosionLootCondition.builder();
    }

    public static class Chests extends ModLootTableProvider {
        public Chests(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
            super(output, registryLookup, LootContextTypes.CHEST);
        }

        @Override
        protected void generate() {
            chestsGeneral0();
            chestsGeneral1();
            chestsRogueTier1();
            chestsRogueTier2();
            chestsRogueTier3();
            chestsRogueTier4();
            chestsRogueTier5();
            chestsWarriorTier1();
            chestsWarriorTier2();
            chestsWarriorTier3();
            chestsWarriorTier4();
            chestsWarriorTier5();
        }

        private void chestsGeneral0() {
            table("chests/general_0",
                pool(uniform(2, 4),
                        item("minecraft:flint", 1, count(uniform(1, 5))),
                        item("minecraft:paper", 1, count(uniform(1, 4))),
                        item("minecraft:white_wool", 1, count(uniform(1, 4))),
                        item("minecraft:leather", 1, count(uniform(1, 4))),
                        item("minecraft:string", 1, count(uniform(1, 4))),
                        item("minecraft:iron_nugget", 1, count(uniform(3, 8)))));
        }

        private void chestsGeneral1() {
            table("chests/general_1",
                pool(uniform(2, 4),
                        item("minecraft:bread", 1, count(uniform(1, 4))),
                        item("minecraft:wheat", 1, count(uniform(3, 7))),
                        item("minecraft:cooked_porkchop", 1, count(uniform(1, 2))),
                        item("minecraft:stick", 1, count(uniform(2, 5))),
                        item("minecraft:apple", 1, count(uniform(1, 3))),
                        item("minecraft:lapis_lazuli", 1, count(uniform(1, 2))),
                        item("minecraft:arrow", 1, count(uniform(1, 10)))));
        }

        private void chestsRogueTier1() {
            table("chests/rogue/tier1",
                pool(uniform(1, 2),
                        item("minecraft:stick", 5, count(uniform(2, 4))),
                        item("minecraft:leather", 5, count(uniform(4, 8))),
                        item("minecraft:white_wool", 5, count(uniform(2, 6))),
                        item("minecraft:flint", 4, count(uniform(1, 5))),
                        item("minecraft:gold_nugget", 4, count(uniform(2, 6))),
                        item("minecraft:lapis_lazuli", 3, count(uniform(1, 2))),
                        item("minecraft:iron_ingot", 2, count(uniform(1, 2))),
                        item("minecraft:gold_ingot", 2, count(uniform(1, 2))),
                        item("rogues:flint_dagger", 3),
                        item("minecraft:bow", 3),
                        item("rogues:iron_dagger", 1),
                        item("rogues:iron_sickle", 1)),
                pool(uniform(1, 1),
                        empty(7),
                        item("spell_engine:spell_scroll", 1, scroll(2, 2, 1, "#rogues:spell_scroll/rogue"))),
                pool(uniform(1, 1),
                        empty(15),
                        conditional("jewelry:diamond_ring", 1),
                        conditional("jewelry:diamond_necklace", 1),
                        conditional("jewelry:ruby_ring", 1),
                        conditional("jewelry:ruby_necklace", 1),
                        conditional("relics_rpgs:jewel_figurine_ruby", 1),
                        conditional("loot_n_explore:hot_chocolate", 1),
                        conditional("loot_n_explore:potato_soup", 1)),
                pool(uniform(1, 1),
                        empty(15),
                        item("spell_engine:spell_scroll", 1, scroll(2, 2, 1, "#archers_expansion:spell_scroll/deadeye"))));
        }

        private void chestsRogueTier2() {
            table("chests/rogue/tier2",
                pool(uniform(1, 3),
                        item("minecraft:leather", 3, count(uniform(4, 8))),
                        item("minecraft:iron_ingot", 3, count(uniform(2, 5))),
                        item("minecraft:gold_ingot", 3, count(uniform(2, 5))),
                        item("minecraft:ink_sac", 3, count(uniform(1, 2))),
                        item("minecraft:rabbit_hide", 3, count(uniform(1, 3))),
                        item("minecraft:lapis_lazuli", 3, count(uniform(4, 6))),
                        item("minecraft:arrow", 3, count(uniform(1, 10))),
                        item("minecraft:bow", 2, conditionalItem("archers:composite_longbow")),
                        item("rogues:iron_dagger", 2),
                        item("rogues:iron_sickle", 2),
                        item("rogues:rogue_armor_head", 1),
                        item("rogues:rogue_armor_chest", 1),
                        item("rogues:rogue_armor_legs", 1),
                        item("rogues:rogue_armor_feet", 1)),
                pool(uniform(1, 1),
                        empty(5),
                        item("spell_engine:spell_scroll", 1, scroll(2, 2, 1, "#rogues:spell_scroll/rogue"))),
                pool(uniform(1, 1),
                        empty(10),
                        conditional("jewelry:diamond_ring", 1),
                        conditional("jewelry:diamond_necklace", 1),
                        conditional("jewelry:ruby_ring", 1),
                        conditional("jewelry:ruby_necklace", 1),
                        conditional("relics_rpgs:jewel_figurine_ruby", 1),
                        conditional("loot_n_explore:hot_chocolate", 1),
                        conditional("loot_n_explore:potato_soup", 1)),
                pool(uniform(1, 1),
                        empty(10),
                        item("spell_engine:spell_scroll", 1, scroll(2, 2, 1, "#archers_expansion:spell_scroll/deadeye"))));
        }

        private void chestsRogueTier3() {
            table("chests/rogue/tier3",
                pool(uniform(2, 3),
                        item("minecraft:ink_sac", 4, count(uniform(1, 4))),
                        item("minecraft:arrow", 4, count(uniform(6, 16))),
                        item("minecraft:gold_ingot", 4, count(uniform(1, 4))),
                        item("minecraft:rabbit_hide", 4, count(uniform(2, 5))),
                        item("rogues:iron_dagger", 3, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:iron_sickle", 3, enchant(1, 13).conditionally(chance(0.35F))),
                        item("minecraft:iron_sword", 3, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:rogue_armor_head", 2, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:rogue_armor_chest", 2, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:rogue_armor_legs", 2, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:rogue_armor_feet", 2, enchant(1, 13).conditionally(chance(0.35F))),
                        item("minecraft:diamond_sword", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:diamond_dagger", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:diamond_sickle", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:assassin_armor_head", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:assassin_armor_chest", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:assassin_armor_legs", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:assassin_armor_feet", 1, enchant(1, 13).conditionally(chance(0.35F)))).bonusRolls(constant(0.25F)),
                pool(uniform(1, 1),
                        empty(3),
                        item("spell_engine:spell_scroll", 1, scroll(2, 3, 1, "#rogues:spell_scroll/rogue"))),
                pool(uniform(1, 1),
                        empty(8),
                        conditional("jewelry:diamond_ring", 1),
                        conditional("jewelry:diamond_necklace", 1),
                        conditional("jewelry:ruby_ring", 1),
                        conditional("jewelry:ruby_necklace", 1),
                        conditional("relics_rpgs:lesser_evasion", 1),
                        conditional("relics_rpgs:lesser_melee_crit_chance", 1),
                        conditional("relics_rpgs:lesser_roll", 1),
                        conditional("relics_rpgs:lesser_use_dex", 1),
                        conditional("relics_rpgs:medium_proc_attack_damage", 1),
                        conditional("loot_n_explore:malt_extract", 1)),
                pool(uniform(1, 2),
                        empty(8),
                        item("spell_engine:spell_scroll", 1, scroll(2, 3, 1, "#archers_expansion:spell_scroll/deadeye")),
                        conditional("archers:mechanic_shortbow", 2, enchant(1, 13).conditionally(chance(0.35F))),
                        conditional("archers_expansion:deadeye_head", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        conditional("archers_expansion:deadeye_chest", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        conditional("archers_expansion:deadeye_legs", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        conditional("archers_expansion:deadeye_feet", 1, enchant(1, 13).conditionally(chance(0.35F)))));
        }

        private void chestsRogueTier4() {
            table("chests/rogue/tier4",
                pool(uniform(2, 4),
                        item("minecraft:ink_sac", 3, count(uniform(3, 5))),
                        item("minecraft:gold_ingot", 3, count(uniform(3, 7))),
                        item("minecraft:rabbit_hide", 3, count(uniform(4, 6))),
                        item("minecraft:diamond", 2, count(uniform(1, 4))),
                        item("rogues:diamond_dagger", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        item("rogues:diamond_sickle", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        item("minecraft:diamond_sword", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        item("rogues:assassin_armor_head", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        item("rogues:assassin_armor_chest", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        item("rogues:assassin_armor_legs", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        item("rogues:assassin_armor_feet", 1, enchant(6, 18).conditionally(chance(0.5F)))).bonusRolls(constant(0.5F)),
                pool(uniform(1, 1),
                        empty(1),
                        item("spell_engine:spell_scroll", 1, scroll(3, 5, 1, "#rogues:spell_scroll/rogue"))),
                pool(uniform(1, 1),
                        empty(3),
                        conditional("jewelry:netherite_ruby_ring", 1),
                        conditional("jewelry:netherite_ruby_necklace", 1),
                        conditional("relics_rpgs:medium_proc_evasion", 1),
                        conditional("relics_rpgs:medium_proc_attack_speed", 1),
                        conditional("loot_n_explore:espresso", 1)),
                pool(uniform(1, 2),
                        empty(4),
                        item("spell_engine:spell_scroll", 1, scroll(3, 5, 1, "#archers_expansion:spell_scroll/deadeye")),
                        conditional("archers:mechanic_shortbow", 2, enchant(1, 13).conditionally(chance(0.5F))),
                        conditional("archers_expansion:deadeye_head", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        conditional("archers_expansion:deadeye_chest", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        conditional("archers_expansion:deadeye_legs", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        conditional("archers_expansion:deadeye_feet", 1, enchant(6, 18).conditionally(chance(0.5F)))));
        }

        private void chestsRogueTier5() {
            table("chests/rogue/tier5",
                pool(uniform(2, 4),
                        item("minecraft:gold_ingot", 3, count(uniform(3, 7))),
                        item("minecraft:diamond", 2, count(uniform(3, 6))),
                        item("minecraft:golden_apple", 1),
                        item("minecraft:diamond_sword", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        item("rogues:diamond_dagger", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        item("rogues:diamond_sickle", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        item("rogues:assassin_armor_head", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        item("rogues:assassin_armor_chest", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        item("rogues:assassin_armor_legs", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        item("rogues:assassin_armor_feet", 1, enchant(13, 24).conditionally(chance(0.75F)))).bonusRolls(constant(0.75F)),
                pool(uniform(1, 1),
                        item("spell_engine:spell_scroll", 1, scroll(4, 5, 1, "#rogues:spell_scroll/rogue"))),
                pool(uniform(1, 1),
                        conditional("jewelry:netherite_ruby_ring", 1),
                        conditional("jewelry:netherite_ruby_necklace", 1),
                        conditional("relics_rpgs:greater_perk_roll_damage", 1),
                        conditional("relics_rpgs:greater_perk_evasion_attack", 1),
                        conditional("loot_n_explore:espresso", 1)),
                pool(uniform(1, 2),
                        empty(1),
                        item("spell_engine:spell_scroll", 1, scroll(4, 5, 1, "#archers_expansion:spell_scroll/deadeye")),
                        conditional("archers:mechanic_shortbow", 2, enchant(13, 24).conditionally(chance(0.75F))),
                        conditional("archers_expansion:deadeye_head", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        conditional("archers_expansion:deadeye_chest", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        conditional("archers_expansion:deadeye_legs", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        conditional("archers_expansion:deadeye_feet", 1, enchant(13, 24).conditionally(chance(0.75F)))));
        }

        private void chestsWarriorTier1() {
            table("chests/warrior/tier1",
                pool(uniform(1, 2),
                        item("minecraft:stick", 5, count(uniform(2, 4))),
                        item("minecraft:leather", 5, count(uniform(4, 8))),
                        item("minecraft:string", 5, count(uniform(2, 6))),
                        item("minecraft:flint", 4, count(uniform(1, 5))),
                        item("minecraft:iron_nugget", 4, count(uniform(2, 6))),
                        item("minecraft:lapis_lazuli", 3, count(uniform(1, 2))),
                        item("minecraft:iron_ingot", 2, count(uniform(1, 3))),
                        item("rogues:stone_double_axe", 3),
                        item("minecraft:crossbow", 3),
                        item("rogues:iron_glaive", 1),
                        item("rogues:iron_double_axe", 1)),
                pool(uniform(1, 1),
                        empty(7),
                        item("spell_engine:spell_scroll", 1, scroll(2, 2, 1, "#rogues:spell_scroll/warrior"))),
                pool(uniform(1, 1),
                        empty(15),
                        conditional("jewelry:sapphire_ring", 1),
                        conditional("jewelry:sapphire_necklace", 1),
                        conditional("jewelry:ruby_ring", 1),
                        conditional("jewelry:ruby_necklace", 1),
                        conditional("relics_rpgs:jewel_figurine_ruby", 1),
                        conditional("relics_rpgs:jewel_figurine_sapphire", 1),
                        conditional("loot_n_explore:sweet_berry_punch", 1),
                        conditional("loot_n_explore:potato_soup", 1)),
                pool(uniform(1, 1),
                        empty(15),
                        item("spell_engine:spell_scroll", 1, scroll(2, 2, 1, "#archers_expansion:spell_scroll/war_archer"))));
        }

        private void chestsWarriorTier2() {
            table("chests/warrior/tier2",
                pool(uniform(1, 3),
                        item("minecraft:leather", 3, count(uniform(4, 8))),
                        item("minecraft:iron_ingot", 3, count(uniform(2, 5))),
                        item("minecraft:arrow", 3, count(uniform(1, 10))),
                        item("minecraft:chain", 3, count(uniform(2, 4))),
                        item("minecraft:goat_horn", 3, count(uniform(1, 2))),
                        item("minecraft:crossbow", 2, conditionalItem("archers:composite_longbow")),
                        item("rogues:iron_glaive", 2),
                        item("rogues:iron_double_axe", 2),
                        item("rogues:warrior_armor_head", 1),
                        item("rogues:warrior_armor_chest", 1),
                        item("rogues:warrior_armor_legs", 1),
                        item("rogues:warrior_armor_feet", 1)),
                pool(uniform(1, 1),
                        empty(5),
                        item("spell_engine:spell_scroll", 1, scroll(2, 2, 1, "#rogues:spell_scroll/warrior"))),
                pool(uniform(1, 1),
                        empty(10),
                        conditional("jewelry:sapphire_ring", 1),
                        conditional("jewelry:sapphire_necklace", 1),
                        conditional("jewelry:ruby_ring", 1),
                        conditional("jewelry:ruby_necklace", 1),
                        conditional("relics_rpgs:jewel_figurine_ruby", 1),
                        conditional("relics_rpgs:jewel_figurine_sapphire", 1),
                        conditional("loot_n_explore:sweet_berry_punch", 1),
                        conditional("loot_n_explore:potato_soup", 1)),
                pool(uniform(1, 1),
                        empty(10),
                        item("spell_engine:spell_scroll", 1, scroll(2, 2, 1, "#archers_expansion:spell_scroll/war_archer"))));
        }

        private void chestsWarriorTier3() {
            table("chests/warrior/tier3",
                pool(uniform(2, 3),
                        item("minecraft:chain", 4, count(uniform(1, 4))),
                        item("minecraft:iron_ingot", 4, count(uniform(1, 4))),
                        item("minecraft:arrow", 4, count(uniform(6, 16))),
                        item("minecraft:goat_horn", 4, count(uniform(1, 2))),
                        item("minecraft:netherite_scrap", 3, count(uniform(1, 1))),
                        item("rogues:iron_double_axe", 3, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:iron_glaive", 3, enchant(1, 13).conditionally(chance(0.35F))),
                        item("minecraft:iron_sword", 3, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:warrior_armor_head", 2, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:warrior_armor_chest", 2, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:warrior_armor_legs", 2, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:warrior_armor_feet", 2, enchant(1, 13).conditionally(chance(0.35F))),
                        item("minecraft:diamond_sword", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:diamond_double_axe", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:diamond_glaive", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:berserker_armor_head", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:berserker_armor_chest", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:berserker_armor_legs", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        item("rogues:berserker_armor_feet", 1, enchant(1, 13).conditionally(chance(0.35F)))).bonusRolls(constant(0.25F)),
                pool(uniform(1, 1),
                        empty(3),
                        item("spell_engine:spell_scroll", 1, scroll(2, 3, 1, "#rogues:spell_scroll/warrior"))),
                pool(uniform(1, 1),
                        empty(8),
                        conditional("jewelry:sapphire_ring", 1),
                        conditional("jewelry:sapphire_necklace", 1),
                        conditional("jewelry:ruby_ring", 1),
                        conditional("jewelry:ruby_necklace", 1),
                        conditional("relics_rpgs:lesser_melee_crit_damage", 1),
                        conditional("relics_rpgs:lesser_use_damage", 1),
                        conditional("loot_n_explore:malt_extract", 1),
                        conditional("loot_n_explore:beet_rootbeer", 1)),
                pool(uniform(1, 2),
                        empty(8),
                        item("spell_engine:spell_scroll", 1, scroll(2, 3, 1, "#archers_expansion:spell_scroll/war_archer")),
                        conditional("archers:composite_longbow", 3, enchant(1, 13).conditionally(chance(0.35F))),
                        conditional("archers:heavy_crossbow", 2, enchant(1, 13).conditionally(chance(0.35F))),
                        conditional("archers_expansion:war_archer_head", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        conditional("archers_expansion:war_archer_chest", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        conditional("archers_expansion:war_archer_legs", 1, enchant(1, 13).conditionally(chance(0.35F))),
                        conditional("archers_expansion:war_archer_feet", 1, enchant(1, 13).conditionally(chance(0.35F)))));
        }

        private void chestsWarriorTier4() {
            table("chests/warrior/tier4",
                pool(uniform(2, 4),
                        item("minecraft:chain", 3, count(uniform(3, 5))),
                        item("minecraft:iron_ingot", 3, count(uniform(3, 7))),
                        item("minecraft:goat_horn", 3, count(uniform(1, 2))),
                        item("minecraft:netherite_scrap", 2, count(uniform(1, 2))),
                        item("minecraft:diamond", 2, count(uniform(1, 4))),
                        item("minecraft:diamond_sword", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        item("rogues:diamond_glaive", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        item("rogues:diamond_double_axe", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        item("rogues:berserker_armor_head", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        item("rogues:berserker_armor_chest", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        item("rogues:berserker_armor_legs", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        item("rogues:berserker_armor_feet", 1, enchant(6, 18).conditionally(chance(0.5F)))).bonusRolls(constant(0.5F)),
                pool(uniform(1, 1),
                        empty(1),
                        item("spell_engine:spell_scroll", 1, scroll(3, 5, 1, "#rogues:spell_scroll/warrior"))),
                pool(uniform(1, 1),
                        empty(3),
                        item("spell_engine:spell_scroll", 1, scroll(3, 5, 1, "#archers_expansion:spell_scroll/war_archer")),
                        conditional("jewelry:netherite_sapphire_ring", 1),
                        conditional("jewelry:netherite_sapphire_necklace", 1),
                        conditional("jewelry:netherite_ruby_ring", 1),
                        conditional("jewelry:netherite_ruby_necklace", 1),
                        conditional("relics_rpgs:medium_proc_attack_damage", 1),
                        conditional("relics_rpgs:medium_proc_defense", 1),
                        conditional("loot_n_explore:vital_drink", 1)),
                pool(uniform(1, 2),
                        empty(4),
                        conditional("archers:heavy_crossbow", 2, enchant(1, 13).conditionally(chance(0.5F))),
                        conditional("archers_expansion:war_archer_head", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        conditional("archers_expansion:war_archer_chest", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        conditional("archers_expansion:war_archer_legs", 1, enchant(6, 18).conditionally(chance(0.5F))),
                        conditional("archers_expansion:war_archer_feet", 1, enchant(6, 18).conditionally(chance(0.5F)))));
        }

        private void chestsWarriorTier5() {
            table("chests/warrior/tier5",
                pool(uniform(2, 4),
                        item("minecraft:iron_ingot", 3, count(uniform(3, 7))),
                        item("minecraft:diamond", 2, count(uniform(3, 6))),
                        item("minecraft:netherite_scrap", 1, count(uniform(1, 2))),
                        item("minecraft:golden_apple", 1),
                        item("minecraft:diamond_sword", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        item("rogues:diamond_double_axe", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        item("rogues:diamond_glaive", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        item("rogues:berserker_armor_head", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        item("rogues:berserker_armor_chest", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        item("rogues:berserker_armor_legs", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        item("rogues:berserker_armor_feet", 1, enchant(13, 24).conditionally(chance(0.75F)))).bonusRolls(constant(0.75F)),
                pool(uniform(1, 1),
                        item("spell_engine:spell_scroll", 1, scroll(4, 5, 1, "#rogues:spell_scroll/warrior"))),
                pool(uniform(1, 1),
                        conditional("jewelry:netherite_sapphire_ring", 1),
                        conditional("jewelry:netherite_sapphire_necklace", 1),
                        conditional("jewelry:netherite_ruby_ring", 1),
                        conditional("jewelry:netherite_ruby_necklace", 1),
                        conditional("relics_rpgs:greater_perk_melee_stun", 1),
                        conditional("relics_rpgs:greater_proc_physical_trance", 1),
                        conditional("loot_n_explore:vital_drink", 1)),
                pool(uniform(1, 1),
                        conditional("jewelry:netherite_ruby_ring", 1),
                        conditional("jewelry:netherite_ruby_necklace", 1),
                        conditional("relics_rpgs:greater_perk_roll_damage", 1),
                        conditional("relics_rpgs:greater_perk_evasion_attack", 1),
                        conditional("loot_n_explore:espresso", 1)),
                pool(uniform(1, 2),
                        empty(1),
                        item("spell_engine:spell_scroll", 1, scroll(4, 5, 1, "#archers_expansion:spell_scroll/war_archer")),
                        conditional("archers:heavy_crossbow", 2, enchant(13, 24).conditionally(chance(0.75F))),
                        conditional("archers_expansion:war_archer_head", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        conditional("archers_expansion:war_archer_chest", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        conditional("archers_expansion:war_archer_legs", 1, enchant(13, 24).conditionally(chance(0.75F))),
                        conditional("archers_expansion:war_archer_feet", 1, enchant(13, 24).conditionally(chance(0.75F)))));
        }
    }
}
