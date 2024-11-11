package solignomiki.writingsheets.interfaces;

import net.minecraft.core.item.ItemStack;
import solignomiki.writingsheets.gui.GuiEditSheet;

public interface EntityPlayer {
	void displayGUIEditSheet(ItemStack itemstack, int slot);
}
