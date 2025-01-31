package solignomiki.writingsheets.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.item.Item;
import net.minecraft.core.util.collection.NamespaceID;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import solignomiki.writingsheets.Utilities;
import solignomiki.writingsheets.WritingSheets;
import solignomiki.writingsheets.item.model.ItemModelWritingSheet;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.helper.ItemBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class ModItems {
	private static Integer stackSize = null;
	@Nullable
	private static String overrideKey = null;

	public static Item writingSheetItem;

//	public static Item writingSheetItem;
//	public static Item writingSheetItem = new ItemBuilder(WritingSheets.MOD_ID)
//		.setIcon(WritingSheets.MOD_ID + ":writingSheetItem/emptywritingsheet")
//		.setItemModel((writingSheetItem) -> new ItemModelWritingSheet(writingSheetItem, WritingSheets.MOD_ID))
//		.setStackSize(1)
//		.setKey("writingsheets.writingsheet")
//		.build(new ItemWritingSheet(new NamespaceID("writingsheets", "writingsheet"), Utilities.nextID()));

	static {

//		writingSheetItem = new ItemBuilder(WritingSheets.MOD_ID)
//			.setIcon(WritingSheets.MOD_ID + ":writingSheetItem/emptywritingsheet")
//			.setItemModel((writingSheetItem) -> new ItemModelWritingSheet(writingSheetItem, WritingSheets.MOD_ID))
//			.setStackSize(1)
//			.setKey("writingsheets.writingsheet")
//			.build(new ItemWritingSheet(new NamespaceID("writingsheets", "writingsheet"), Utilities.nextID()));
	}

	public static void init() {

		stackSize = 1;
		overrideKey = "writingsheets.writingsheet";

		writingSheetItem = new ItemWritingSheet(new NamespaceID("writingsheets", "writingsheet"), Utilities.nextID());

		List<String> tokens;

		if (overrideKey != null){
			tokens = Arrays.stream(overrideKey.split("\\.")).collect(Collectors.toList());
		} else {
			tokens = Arrays.stream(writingSheetItem.getKey().split("\\.")).collect(Collectors.toList());
		}


		if (stackSize != null){
			writingSheetItem.setMaxStackSize(stackSize);
		}

		List<String> newTokens = new ArrayList<>();
		newTokens.add(WritingSheets.MOD_ID);
		newTokens.addAll(tokens.subList(1, tokens.size()));

		writingSheetItem.setKey(StringUtils.join(newTokens, "."));
	}

}
