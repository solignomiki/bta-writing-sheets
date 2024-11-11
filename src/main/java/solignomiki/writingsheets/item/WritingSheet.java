package solignomiki.writingsheets.item;

import com.mojang.nbt.CompoundTag;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemLabel;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import solignomiki.writingsheets.mixin.EntityPlayerMixin;

public class WritingSheet extends Item {
	public WritingSheet(String name, int id) {
		super(name, id);
	}

	@Override
	public ItemStack onUseItem(ItemStack stack, World world, EntityPlayer player) {
		CompoundTag sheetData;
		player.swingItem();
		//world.playSoundAtEntity(entityplayer, entityplayer, "chaotic_neutral.squeak", 1f,0.75f + (new Random().nextFloat() * 0.5F));

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
		((solignomiki.writingsheets.interfaces.EntityPlayer)player).displayGUIEditSheet(stack, slot);
		return stack;
	}
}
