package solignomiki.writingsheets;

import net.minecraft.client.render.EntityRenderDispatcher;
import net.minecraft.client.render.TileEntityRenderDispatcher;
import net.minecraft.client.render.block.color.BlockColorDispatcher;
import net.minecraft.client.render.block.model.BlockModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.core.item.Item;
import solignomiki.writingsheets.item.ModItems;
import solignomiki.writingsheets.item.model.ItemModelWritingSheet;
import turniplabs.halplibe.helper.ModelHelper;
import turniplabs.halplibe.util.ModelEntrypoint;

public class ModModels implements ModelEntrypoint {
	public void initBlockModels(BlockModelDispatcher dispatcher) {

	}
	public void initItemModels(ItemModelDispatcher dispatcher) {
		ModelHelper.setItemModel(ModItems.writingSheetItem, () -> new ItemModelWritingSheet(ModItems.writingSheetItem, WritingSheets.MOD_ID));
	}
	public void initEntityModels(EntityRenderDispatcher dispatcher) {

	}
	public void initTileEntityModels(TileEntityRenderDispatcher dispatcher) {

	}
	public void initBlockColors(BlockColorDispatcher dispatcher) {
	}
}
