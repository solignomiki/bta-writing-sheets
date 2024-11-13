package solignomiki.writingsheets.item.model;

import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ItemModelWritingSheet extends ItemModelStandard {

	public IconCoordinate sheetEmpty = TextureRegistry.getTexture("writingsheets:item/emptywritingsheet");
	public IconCoordinate sheetWritten = TextureRegistry.getTexture("writingsheets:item/writingsheet");

	public ItemModelWritingSheet(Item item, String namespace) {
		super(item, namespace);
	}

	@Override
	public @NotNull IconCoordinate getIcon(@Nullable Entity entity, ItemStack itemStack) {
		if(itemStack.getData().getCompound("SheetData").getBoolean("IsWritten")){
			return sheetWritten;
		}
		return sheetEmpty;
	}

}
