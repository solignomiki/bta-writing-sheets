package solignomiki.writingsheets.item;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class ItemWritingSheet extends Item {
	public ItemWritingSheet(String name, int id) {
		super(name, id);
	}

	@Override
	public ItemStack onUseItem(ItemStack stack, World world, EntityPlayer player) {
		CompoundTag sheetData;

		int slot = -1;

		for(int i = 0; i < player.inventory.mainInventory.length; ++i) {
			if (player.inventory.mainInventory[i] == stack) {
				slot = i;
				break;
			}
		}

		sheetData = stack.getData().getCompoundOrDefault("SheetData", new CompoundTag());
		stack.getData().putCompound("SheetData", sheetData);
		//ItemLabel
		//ItemModelLabel

		((solignomiki.writingsheets.interfaces.EntityPlayer)player).displayGUIEditSheet(stack, slot);
		return stack;
	}
}
