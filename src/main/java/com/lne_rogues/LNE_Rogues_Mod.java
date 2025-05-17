package com.lne_rogues;

import com.lne_rogues.config.Default;
import com.lne_rogues.effect.Effects;
import com.lne_rogues.item.WeaponRegister;
import net.fabricmc.api.ModInitializer;
import com.lne_rogues.config.TweaksConfig;
import net.fabricmc.loader.api.FabricLoader;
import net.spell_engine.api.config.ConfigFile;
import net.tinyconfig.ConfigManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LNE_Rogues_Mod implements ModInitializer {
	public static final String MOD_ID = "lne_rogues";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static ConfigManager<ConfigFile.Equipment> itemConfig = new ConfigManager<>
			("items", Default.itemConfig)
			.builder()
			.setDirectory(MOD_ID)
			.sanitize(true)
			.build();
	public static ConfigManager<TweaksConfig> tweaksConfig = new ConfigManager<TweaksConfig>
			("tweaks", new TweaksConfig())
			.builder()
			.setDirectory(MOD_ID)
			.sanitize(true)
			.build();

	@Override
	public void onInitialize() {
		tweaksConfig.refresh();
		Effects.register();
		if(FabricLoader.getInstance().isModLoaded("loot_n_explore")) {
			itemConfig.refresh();
			WeaponRegister.register(itemConfig.value.weapons);
			itemConfig.save();
		}
	}
}