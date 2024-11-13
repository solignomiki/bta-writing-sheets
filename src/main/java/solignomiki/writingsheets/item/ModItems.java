package solignomiki.writingsheets.item;

import net.minecraft.core.item.Item;
import solignomiki.writingsheets.Utilities;
import solignomiki.writingsheets.WritingSheets;
import solignomiki.writingsheets.item.model.ItemModelWritingSheet;
import turniplabs.halplibe.helper.ItemBuilder;

public class ModItems {

	public static Item writingSheetItem = new ItemBuilder(WritingSheets.MOD_ID)
		.setIcon(WritingSheets.MOD_ID + ":item/emptywritingsheet")
		.setItemModel((item) -> new ItemModelWritingSheet(item, WritingSheets.MOD_ID))
		.setStackSize(1)
		.build(new ItemWritingSheet("writingsheet", Utilities.nextID() ));

	public static void init() {
	}

}
