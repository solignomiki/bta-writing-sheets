package solignomiki.writingsheets.gui;

import com.mojang.nbt.CompoundTag;
import net.minecraft.client.gui.*;

import net.minecraft.core.block.BlockSign;
import net.minecraft.core.item.ItemFlag;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.net.command.TextFormatting;
import net.minecraft.core.net.packet.Packet137SetItemName;
import net.minecraft.core.util.helper.ChatAllowedCharacters;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiEditSheet extends GuiScreen {
	private static final String allowedCharacters;
	protected ItemStack item;
	protected byte color;
	protected int xSize;
	protected int ySize;
	protected int slot;
	private int editLine = 0;
	private String[] text = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};

	public GuiEditSheet(ItemStack item, int slot) {
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

	public void drawScreen(int mouseX, int mouseY, float f) {
		this.drawDefaultBackground();
		int bgTex = this.mc.renderEngine.getTexture("/assets/writingsheets/textures/gui/sheet.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(bgTex);
		int widthBG = this.width / 2 - 80;
		this.drawTexturedModalRect(widthBG, 30, 0, 0, 175, 221);
		String name = "";
		if (this.item.hasCustomName()) name = this.item.getCustomName();
		else name = this.item.getDisplayName();
		this.drawStringCentered(this.fontRenderer, name, this.width / 2, 15, -1);

		for (int i = 0; i < 16; i++) {

			this.drawStringNoShadow(this.fontRenderer, TextFormatting.get(this.color) + this.text[i], this.width / 2 - 57, 45 + (i * 12), 16777215);
			if (i == editLine) {
				//this.drawStringNoShadow(this.fontRenderer, TextFormatting.get(this.color) + "> " + this.text[i] + " <", this.width / 2 - 57, 55 + (i * 10), 16777215);
				int textWidth = this.fontRenderer.getStringWidth(this.text[i]);
				this.drawStringNoShadow(this.fontRenderer, TextFormatting.get(this.color) + "> ", this.width / 2 - 57 - 10, 45 + (i * 12), 16777215);
				this.drawStringNoShadow(this.fontRenderer, TextFormatting.get(this.color) + " <", this.width / 2 - 57 + textWidth + 3, 45 + (i * 12), 16777215);
				//continue;
			}
		}
		super.drawScreen(mouseX, mouseY, f);
	}

	public void init() {
		this.controlList.clear();
		Keyboard.enableRepeatEvents(true);
		this.controlList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 190, I18n.getInstance().translateKey("gui.edit_label.button.done")));
	}

	public void keyTyped(char c, int i, int mouseX, int mouseY) {

		if (i == 1) {
			this.save();
			this.mc.displayGuiScreen((GuiScreen)null);
		}

		if (i == 200) {
			this.editLine = (this.editLine - 1 + 16) % 16;
		}

		if (i == 208 || i == 28 || i == 156) {
			this.editLine = (this.editLine + 1) % 16;
		}

		if (i == 14 && this.text[this.editLine].length() > 0) {
			this.text[this.editLine] = this.text[this.editLine].substring(0, this.text[this.editLine].length() - 1);
		}

		if ((allowedCharacters.indexOf(c) >= 0 || Character.isLetterOrDigit(c)) && this.fontRenderer.getStringWidth(this.text[this.editLine]) < 125) {
			StringBuilder stringBuilder = new StringBuilder();
			String[] textArray = this.text;
			int editLine = this.editLine;
			textArray[editLine] = stringBuilder.append(textArray[editLine]).append(c).toString();
		}

	}

	protected void buttonPressed(GuiButton button) {
		this.save();
		this.mc.displayGuiScreen((GuiScreen)null);
	}

	public void onClosed() {
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
	}

	static {
		allowedCharacters = ChatAllowedCharacters.ALLOWED_CHARACTERS;
	}
}
