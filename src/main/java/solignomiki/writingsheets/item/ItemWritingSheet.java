package solignomiki.writingsheets.item;

import com.mojang.nbt.tags.CompoundTag;
import net.minecraft.client.entity.player.PlayerLocal;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;

public class ItemWritingSheet extends Item {
	public ItemWritingSheet(NamespaceID namespaceId, int id) {
		super(namespaceId, id);
	}

	@Override
	public ItemStack onUseItem(ItemStack itemstack, World world, Player entityplayer) {
		CompoundTag sheetData;

		int slot = -1;

		for(int i = 0; i < entityplayer.inventory.mainInventory.length; ++i) {
			if (entityplayer.inventory.mainInventory[i] == itemstack) {
				slot = i;
				break;
			}
		}

		sheetData = itemstack.getData().getCompoundOrDefault("SheetData", new CompoundTag());
		itemstack.getData().putCompound("SheetData", sheetData);

		((solignomiki.writingsheets.interfaces.Player)entityplayer).displayEditSheetScreen(itemstack, slot);
		return itemstack;
	}
}
