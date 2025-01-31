package solignomiki.writingsheets.gui;

import com.mojang.nbt.tags.CompoundTag;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.*;

import net.minecraft.core.item.ItemStack;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.net.command.TextFormatting;
import net.minecraft.core.net.packet.PacketCustomPayload;
import net.minecraft.core.util.helper.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import solignomiki.writingsheets.WritingSheets;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ScreenSheetEditor extends Screen {
	private static final String allowedCharacters;
	protected ItemStack item;
	protected byte color;
	protected int xSize;
	protected int ySize;
	protected int slot;
	private int editLine = 0;
	private String title = "";
	private String[] text = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

	public ScreenSheetEditor(ItemStack item, int slot) {
		this.item = item;

		this.color = 15;
		this.xSize = 176;
		this.ySize = 166;
		this.slot = slot;

		CompoundTag nbt = item.getData();
		CompoundTag sheetData = nbt.getCompound("SheetData");
		for (int i = 0; i < 16; i++) {
			String textLine = sheetData.getString("Text_" + i);
			text[i] = textLine;
		}
	}

	public void render(int mouseX, int mouseY, float f) {
		this.renderBackground();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.textureManager.loadTexture("/assets/writingsheets/textures/gui/sheet.png").bind();
		int widthBG = this.width / 2 - 80;
		this.drawTexturedModalRect(widthBG, 10, 0, 0, 175, 221);

		if (this.item.hasCustomName()) this.title = this.item.getCustomName();
		else this.title = this.item.getDisplayName();

		this.drawString(this.font, this.title, 10, 10, -1);

		for (int i = 0; i < 16; i++) {

			this.drawStringNoShadow(this.font, TextFormatting.get(this.color) + this.text[i], this.width / 2 - 57, 25 + (i * 12), 16777215);
			if (i == editLine) {
				int textWidth = this.font.getStringWidth(this.text[i]);
				this.drawStringNoShadow(this.font, TextFormatting.get(this.color) + "> ", this.width / 2 - 57 - 10, 25 + (i * 12), 16777215);
				this.drawStringNoShadow(this.font, TextFormatting.get(this.color) + " <", this.width / 2 - 57 + textWidth + 3, 25 + (i * 12), 16777215);
			}
		}
		super.render(mouseX, mouseY, f);
	}

	public void init() {
		this.buttons.clear();
		Keyboard.enableRepeatEvents(true);
		this.buttons.add(new ButtonElement(0, this.width / 2 - 100, this.height / 4 + 170, I18n.getInstance().translateKey("gui.edit_label.button.done")));
	}

	public void keyPressed(char eventCharacter, int eventKey, int mx, int my) {

		if (eventKey == Keyboard.KEY_ESCAPE) {
			this.save();
			this.mc.displayScreen((Screen)null);
		}

		if (eventKey == Keyboard.KEY_UP) {
			this.editLine = (this.editLine - 1 + 16) % 16;
		}

		if (
			eventKey == Keyboard.KEY_DOWN ||
			eventKey == Keyboard.KEY_RETURN ||
			eventKey == Keyboard.KEY_NUMPADENTER
		) {
			this.editLine = (this.editLine + 1) % 16;
		}

		if (eventKey == Keyboard.KEY_BACK && !this.text[this.editLine].isEmpty()) {
			this.text[this.editLine] = this.text[this.editLine].substring(0, this.text[this.editLine].length() - 1);
		}

		if ((allowedCharacters.indexOf(eventCharacter) >= 0 || Character.isLetterOrDigit(eventCharacter)) && this.font.getStringWidth(this.text[this.editLine]) < 125) {
			StringBuilder stringBuilder = new StringBuilder();
			String[] textArray = this.text;
			int editLine = this.editLine;
			textArray[editLine] = stringBuilder.append(textArray[editLine]).append(eventCharacter).toString();
		}

	}

	protected void buttonClicked(ButtonElement button) {
		this.save();
		this.mc.displayScreen((Screen)null);
	}

	public void removed() {
		Keyboard.enableRepeatEvents(false);
	}


	private void save() {
		CompoundTag nbt = item.getData();
		CompoundTag sheetData = nbt.getCompound("SheetData");
		for (int i = 0; i < 16; i++) {
			sheetData.putString("Text_" + i, text[i]);
		}

		nbt.putCompound("SheetData", sheetData);
		item.setData(nbt);
		if (this.mc.currentWorld.isClientSide) {
			ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(byteOutput);
			try {
				for (String str : text) {
					dataOutputStream.writeUTF(str);
				}
			} catch (IOException e) {
				WritingSheets.LOGGER.error("IOException occurred in encoding!", e);
			}

			byte[] data = byteOutput.toByteArray();
			if (data.length != 0) {
				this.mc.getSendQueue().addToSendQueue(new PacketCustomPayload("WritingSheets|Text", data));
			}
		}
		if (!sheetData.getBoolean("IsWritten")) {
			int emptyRows = 0;
			for (int i = 0; i < 16; i++) {
				String textData = sheetData.getString("Text_" + i);

				if (textData == "") {
					emptyRows++;
				}
			}
			if (emptyRows < 16) {
				sheetData.putBoolean("IsWritten", true);
			}

		}
	}

	static {
		allowedCharacters = ChatAllowedCharacters.ALLOWED_CHARACTERS;
	}
}
