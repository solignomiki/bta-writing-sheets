package solignomiki.writingsheets.recipes;

import net.minecraft.core.block.Block;
import net.minecraft.core.crafting.legacy.type.RecipeLabel;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeRegistry;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryLabel;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import org.lwjgl.Sys;
import solignomiki.writingsheets.Utilities;
import solignomiki.writingsheets.WritingSheets;
import solignomiki.writingsheets.item.ModItems;
import solignomiki.writingsheets.item.model.ItemModelWritingSheet;
import turniplabs.halplibe.helper.ItemBuilder;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class ModRecipes implements RecipeEntrypoint {
	public static RecipeNamespaceWritingSheets WRITING_SHEETS = new RecipeNamespaceWritingSheets();
	public static RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH;


	@Override
	public void onRecipesReady() {
		resetGroups();
		registerNamespaces();
		load();
	}
	@Override
	public void initNamespaces() {
		resetGroups();
		registerNamespaces();
	}

	public void load() {
		RecipeBuilder.Shapeless(WritingSheets.MOD_ID)
			.addInput(Item.paper)
			.addInput(new ItemStack(Item.dye, 1, 0))
			.create("writingsheet", ModItems.writingSheetItem.getDefaultStack());
		;
	}

	public void resetGroups(){
		WRITING_SHEETS = new RecipeNamespaceWritingSheets();
		WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));
	}


	public void registerNamespaces(){
		WRITING_SHEETS.register("workbench",WORKBENCH);
		Registries.RECIPES.register("writingsheets",WRITING_SHEETS);
	}


}
