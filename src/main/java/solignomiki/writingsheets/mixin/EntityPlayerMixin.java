package solignomiki.writingsheets.mixin;

import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;
import net.minecraft.server.entity.player.EntityPlayerMP;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value = EntityPlayer.class)
abstract public class EntityPlayerMixin extends EntityLiving implements solignomiki.writingsheets.interfaces.EntityPlayer {
	public EntityPlayerMixin(World world) {
		super(world);
	}

	@Unique()
	public void displayGUIEditSheet(ItemStack itemstack, int slot) {
	}
}
