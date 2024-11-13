package solignomiki.writingsheets.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.EntityPlayerSP;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import solignomiki.writingsheets.gui.GuiEditSheet;

@Mixin(value = EntityPlayerSP.class)
abstract public class EntityPlayerSPMixin extends EntityPlayer implements solignomiki.writingsheets.interfaces.EntityPlayer {
	@Shadow()
	protected Minecraft mc;

	public EntityPlayerSPMixin(World world) {
		super(world);
	}

	@Unique()
	public void displayGUIEditSheet(ItemStack itemstack, int slot) {
		this.mc.displayGuiScreen(new GuiEditSheet(itemstack, slot));
	}
}
