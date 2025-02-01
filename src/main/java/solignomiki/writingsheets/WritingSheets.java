package solignomiki.writingsheets;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solignomiki.writingsheets.item.ModItems;
import turniplabs.halplibe.util.ConfigHandler;
import turniplabs.halplibe.util.GameStartEntrypoint;

import java.util.Properties;


public class WritingSheets implements ModInitializer, GameStartEntrypoint {
    public static final String MOD_ID = "writingsheets";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final ConfigHandler CONFIG;
    @Override
    public void onInitialize() {
		ModItems.init();

		LOGGER.info("WritingSheetsMod initialized.");
    }


	@Override
	public void beforeGameStart() {

	}

	@Override
	public void afterGameStart() {

	}


	static {
		Properties props = new Properties();
		props.setProperty("StartId", "21000");
		CONFIG = new ConfigHandler(MOD_ID, props);
	}
}
