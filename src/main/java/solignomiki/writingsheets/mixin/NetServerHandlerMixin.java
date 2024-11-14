package solignomiki.writingsheets.mixin;

import com.mojang.nbt.CompoundTag;
import net.minecraft.core.enums.PlacementMode;
import net.minecraft.core.net.handler.NetHandler;
import net.minecraft.core.net.packet.Packet250CustomPayload;
import net.minecraft.core.net.packet.Packet72UpdatePlayerProfile;
import net.minecraft.core.player.inventory.ContainerFlag;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.server.entity.player.EntityPlayerMP;
import net.minecraft.server.net.handler.NetServerHandler;
import org.lwjgl.Sys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import solignomiki.writingsheets.WritingSheets;
import solignomiki.writingsheets.item.ItemWritingSheet;
import solignomiki.writingsheets.item.ModItems;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

@Mixin(value = NetServerHandler.class)
public abstract class NetServerHandlerMixin extends NetHandler {
	@Shadow()
	private EntityPlayerMP playerEntity;

	@Inject(
		method = "handleCustomPayload(Lnet/minecraft/core/net/packet/Packet250CustomPayload;)V",
		at = @At("TAIL"),
		remap = false
	)
	public void handleCustomPayload(Packet250CustomPayload packet, CallbackInfo ci) {
		if ("writingsheets|text".equals(packet.channel)) {
			if (this.playerEntity.getHeldItem().getItem() == ModItems.writingSheetItem) {
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
