package solignomiki.writingsheets.item;

import net.minecraft.core.item.Item;
import solignomiki.writingsheets.Utilities;
import solignomiki.writingsheets.WritingSheets;
import turniplabs.halplibe.helper.ItemBuilder;

public class ModItems {

	public static Item writingSheetItem = new ItemBuilder(WritingSheets.MOD_ID)
		.setIcon(WritingSheets.MOD_ID + ":item/writingsheet")
		.setStackSize(1)
		.build(new WritingSheet("writingsheet", Utilities.nextID() ));

	public static void init() {
	}

}
