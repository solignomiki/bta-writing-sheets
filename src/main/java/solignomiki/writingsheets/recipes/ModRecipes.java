package solignomiki.writingsheets.recipes;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;
import solignomiki.writingsheets.WritingSheets;
import solignomiki.writingsheets.item.ModItems;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class ModRecipes implements RecipeEntrypoint {

	@Override
	public void onRecipesReady() {
		RecipeBuilder.Shapeless(WritingSheets.MOD_ID)
			.addInput(Items.PAPER)
			.addInput(new ItemStack(Items.DYE, 1, 0))
			.create("writingsheet", ModItems.writingSheetItem.getDefaultStack());
	}
	@Override
	public void initNamespaces() {
		RecipeBuilder.initNameSpace(WritingSheets.MOD_ID);
	}

}
