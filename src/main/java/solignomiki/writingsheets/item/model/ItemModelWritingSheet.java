package solignomiki.writingsheets.item.model;

import com.mojang.nbt.CompoundTag;
import net.minecraft.client.render.item.model.ItemModelBow;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.Sys;

import java.util.Objects;

public class ItemModelWritingSheet extends ItemModelStandard {

	public IconCoordinate sheetEmpty = TextureRegistry.getTexture("writingsheets:item/emptywritingsheet");
	public IconCoordinate sheetWritten = TextureRegistry.getTexture("writingsheets:item/writingsheet");

	public ItemModelWritingSheet(Item item, String namespace) {
		super(item, namespace);
	}

	@Override
	public @NotNull IconCoordinate getIcon(@Nullable Entity entity, ItemStack itemStack) {
		if(isWritten(itemStack)){
			return sheetWritten;
		}
		return sheetEmpty;
	}

	private boolean isWritten(ItemStack itemStack) {
		CompoundTag nbt = itemStack.getData();
		CompoundTag sheetData = nbt.getCompound("SheetData");
		int emptyRows = 0;
		for (int i = 0; i < 16; i++) {
			String textLine = sheetData.getString("Text_" + i);
			if (textLine.isEmpty()) {
				emptyRows++;
			}
		}
		return emptyRows < 16;
	}

}
