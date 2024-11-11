package solignomiki.writingsheets.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.client.gui.GuiEditLabel;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.gamemode.Gamemode;
import net.minecraft.core.player.inventory.ContainerPlayer;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.util.collection.NamespaceID;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.ChunkCoordinates;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import solignomiki.writingsheets.gui.GuiEditSheet;

import java.util.ArrayList;

@Mixin(value = EntityPlayerSP.class)
abstract public class EntityPlayerMixin extends EntityPlayer implements solignomiki.writingsheets.interfaces.EntityPlayer {
	@Shadow()
	protected Minecraft mc;

	public EntityPlayerMixin(World world) {
		super(world);
	}

	@Unique()
	public void displayGUIEditSheet(ItemStack itemstack, int slot) {
		this.mc.displayGuiScreen(new GuiEditSheet(itemstack, slot));
	}
}
