package solignomiki.writingsheets.mixin;



import net.minecraft.client.Minecraft;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import solignomiki.writingsheets.gui.ScreenSheetEditor;

@Mixin(value = Player.class)
abstract public class PlayerMixin extends Mob implements solignomiki.writingsheets.interfaces.Player {

	public PlayerMixin(World world) {
		super(world);
	}

	@Unique()
	public void displayEditSheetScreen(ItemStack itemstack, int slot) {
		System.out.println("showw_");
	}
}
