package solignomiki.writingsheets.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
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
	public void displayEditSheetScreen(ItemStack itemstack, int slot) {
		System.out.println("showw_local");
		this.mc.displayScreen(new ScreenSheetEditor(itemstack, slot));
	}
}
