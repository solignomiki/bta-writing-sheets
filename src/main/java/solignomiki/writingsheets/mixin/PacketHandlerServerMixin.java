package solignomiki.writingsheets.mixin;

import com.mojang.nbt.tags.CompoundTag;
import net.fabricmc.api.Environment;
import net.minecraft.core.net.handler.PacketHandler;
import net.minecraft.core.net.packet.PacketCustomPayload;
import net.minecraft.server.entity.player.PlayerServer;
import net.minecraft.server.net.handler.PacketHandlerServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import solignomiki.writingsheets.WritingSheets;
import solignomiki.writingsheets.item.ModItems;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

@Mixin(value = PacketHandlerServer.class)
public abstract class PacketHandlerServerMixin extends PacketHandler {
	@Shadow()
	private PlayerServer playerEntity;

	@Inject(
		method = "handleCustomPayload(Lnet/minecraft/core/net/packet/PacketCustomPayload;)V",
		at = @At("TAIL"),
		remap = false
	)
	public void handleCustomPayload(PacketCustomPayload packet, CallbackInfo ci) {
		if ("WritingSheets|Text".equals(packet.channel)) {
			if (
				this.playerEntity.getHeldItem() != null &&
				this.playerEntity.getHeldItem().getItem().equals(ModItems.writingSheetItem)
			) {
				ByteArrayInputStream byteInput = new ByteArrayInputStream(packet.data);
				DataInputStream dataInputStream = new DataInputStream(byteInput);

				String[] lines = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
				int index = 0;
				try {
					while (byteInput.available() > 0 && index < lines.length) {
						lines[index++] = dataInputStream.readUTF();
					}
				} catch (IOException e) {
					WritingSheets.LOGGER.error("IOException occurred in decoding!", e);
				}

				CompoundTag sheetData = new CompoundTag();
				for (int i = 0; i < lines.length; i++) {
					sheetData.putString("Text_" + i, lines[i]);
				}
				if (!this.playerEntity.getHeldItem().getData().getBoolean("IsWritten")) {
					int emptyRows = 0;
					for (int i = 0; i < 16; i++) {
						String line = lines[i];
						if (line == "") {
							emptyRows++;
						}
					}
					if (emptyRows < 16) {
						sheetData.putBoolean("IsWritten", true);
					}

				}
				this.playerEntity.getHeldItem().getData().putCompound("SheetData", sheetData);
			}
		}
	}


}
