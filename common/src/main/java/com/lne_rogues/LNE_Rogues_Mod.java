package com.lne_rogues;

import com.lne_rogues.config.Default;
import com.lne_rogues.effect.LNERogues_Effects;
import com.lne_rogues.item.WeaponRegister;
import com.lne_rogues.config.TweaksConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.spell_engine.rpg_series.config.ConfigFile;
import net.tiny_config.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LNE_Rogues_Mod{
	public static final String MOD_ID = "lne_rogues";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ConfigManager<ConfigFile.Equipment> itemConfig = new ConfigManager<>
			("items_v1", Default.itemConfig)
			.builder()
			.setDirectory(MOD_ID)
			.sanitize(true)
			.build();
	public static ConfigManager<TweaksConfig> tweaksConfig = new ConfigManager<TweaksConfig>
			("tweaks_v2", new TweaksConfig())
			.builder()
			.setDirectory(MOD_ID)
			.sanitize(true)
			.build();
	public static ConfigManager<ConfigFile.Effects> effectConfig = new ConfigManager<>
			("effects", new ConfigFile.Effects())
			.builder()
			.setDirectory(MOD_ID)
			.sanitize(true)
			.build();

	public static void init() {
		tweaksConfig.refresh();

	}
	public static void registerItems(){
		if(FabricLoader.getInstance().isModLoaded("loot_n_explore")) {
			itemConfig.refresh();
			WeaponRegister.register(itemConfig.value.weapons);
			itemConfig.save();
		}
	}
	public static void registerEffects(){
		effectConfig.refresh();
		LNERogues_Effects.register(effectConfig.value);
		effectConfig.save();
	}
}