package solignomiki.writingsheets;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.block.BlockFlag;
import net.minecraft.core.block.BlockSign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import solignomiki.writingsheets.item.ModItems;
import turniplabs.halplibe.util.GameStartEntrypoint;
import turniplabs.halplibe.util.RecipeEntrypoint;


public class WritingSheets implements ModInitializer, GameStartEntrypoint, RecipeEntrypoint {
    public static final String MOD_ID = "writingsheets";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
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

	@Override
	public void onRecipesReady() {

	}

	@Override
	public void initNamespaces() {

	}
}
