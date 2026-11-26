package com.lne_rogues;

import com.lne_rogues.datagen.*;
import com.lne_rogues.item.WeaponRegister;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.spell_engine.rpg_series.datagen.RPGSeriesDataGen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Lne_roguesDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ItemTagGenerator::new);
		pack.addProvider(ModLanguageProvider::new);
		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(RoguesAbilityDatagen::new);
		pack.addProvider(WeaponAttributesGenerator::new);
		pack.addProvider(ModLootTableProvider.Chests::new);

	}

	public static class ItemTagGenerator extends RPGSeriesDataGen.ItemTagGenerator {
		public ItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
			super(output, registriesFuture);
		}

		private static final List<String> WEAPON_THEMES = List.of("ender_dragon", "elder_guardian", "wither", "glacial");

		@Override
		protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
			generateWeaponTags(WeaponRegister.entries);
			for (var entry : WeaponRegister.entries) {
				String name = entry.id().getPath();
				for (String theme : WEAPON_THEMES) {
					if (name.startsWith(theme + "_")) {
						getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, Identifier.of("loot_n_explore", "weapon_themes/" + theme)))
								.addOptional(entry.id());
						break;
					}
				}
			}
		}
	}
}
