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
	public static RecipeNamespace RN;
	public static RecipeNamespaceWritingSheets WRITING_SHEETS = new RecipeNamespaceWritingSheets();
	public static RecipeGroup<RecipeEntryCrafting<?, ?>> WORKBENCH;


	public static void init() {
		RecipeBuilder.Shapeless(WritingSheets.MOD_ID)
			.addInput(Item.paper)
			.addInput(new ItemStack(Item.dye, 1, 0))
			.create("writingsheet", ModItems.writingSheetItem.getDefaultStack());
		;
	}

	@Override
	public void onRecipesReady() {
		System.out.println("onRecipesReady");
		init();
	}
	@Override
	public void initNamespaces() {
		System.out.println("initNamespaces");
		registerNamespaces();
	}

	public void registerNamespaces(){
		RN = new RecipeNamespace();
		Registries.RECIPES.register(WritingSheets.MOD_ID, RN);
		WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));
		RN.register("workbench", WORKBENCH);
		WRITING_SHEETS.register("writingsheets",WORKBENCH);
		Registries.RECIPES.register("writingsheets",WRITING_SHEETS);
	}


}
