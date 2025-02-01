package solignomiki.writingsheets.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.PlayerLocal;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import solignomiki.writingsheets.gui.ScreenSheetEditor;

@Mixin(value = PlayerLocal.class)
abstract public class PlayerLocalMixin extends Player implements solignomiki.writingsheets.interfaces.Player {
	@Shadow()
	protected Minecraft mc;

	public PlayerLocalMixin(World world) {
		super(world);
	}

	@Unique()
	public void displayEditSheetScreen(ItemStack itemstack) {
		ScreenSheetEditor screenEditor = new ScreenSheetEditor(itemstack);
		this.mc.displayScreen(screenEditor);
		screenEditor.playPageSound();
	}
}
