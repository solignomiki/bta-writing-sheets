package solignomiki.writingsheets.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.client.render.item.model.ItemModelDispatcher;
import net.minecraft.client.render.item.model.ItemModelStandard;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import solignomiki.writingsheets.WritingSheets;
import solignomiki.writingsheets.item.model.ItemModelWritingSheet;
import turniplabs.halplibe.HalpLibe;
import turniplabs.halplibe.helper.ItemBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class ModItemsClient {
	@NotNull
	private static Function<Item, ItemModel> customItemModelSupplier;
	@Nullable
	private static String textureKey = null;

	public static void init() {
		textureKey = WritingSheets.MOD_ID + ":item/emptywritingsheet";
		customItemModelSupplier = (item) -> new ItemModelWritingSheet(item, WritingSheets.MOD_ID);
		ItemBuilder.Assignment.queueItemModel(ModItems.writingSheetItem.id, customItemModelSupplier, textureKey);
	}

	@Environment(EnvType.CLIENT)
	public static class Assignment{
		public static boolean itemDispatcherInitialized = false;
		public static final List<ItemBuilder.Assignment.ItemAssignmentEntry<?>> queuedItemModels = new ArrayList<>();
		/**
		 *  Queues a ItemModel assignment until the game is ready to do so
		 */
		public static <T extends Item> void queueItemModel(int id, @NotNull Function<T, ItemModel> itemModelSupplier, @Nullable String iconTexture){
			if (!HalpLibe.isClient) return;
			if (itemDispatcherInitialized){
				ItemModelDispatcher.getInstance().addDispatch(new ItemBuilder.Assignment.ItemAssignmentEntry<>(id, itemModelSupplier, iconTexture).getModel());
				return;
			}
			queuedItemModels.add(new ItemBuilder.Assignment.ItemAssignmentEntry<>(id, itemModelSupplier, iconTexture));
		}
		public static class ItemAssignmentEntry<T extends Item>{
			public final int itemId;
			public final Function<T, ItemModel> modelFunction;
			public final String iconKey;

			public ItemAssignmentEntry(int id, Function<T, ItemModel> modelFunction, String iconKey){
				this.itemId = id;
				this.modelFunction = modelFunction;
				this.iconKey = iconKey;
			}
			public ItemModel getModel(){
				T item = (T) Item.itemsList[itemId];
				ItemModel model = modelFunction.apply(item);

				if (model instanceof ItemModelStandard && iconKey != null){
					((ItemModelStandard) model).icon = TextureRegistry.getTexture(iconKey);
					return model;
				}
				if (model instanceof ItemModelStandard && ((ItemModelStandard) model).icon == ItemModelStandard.ITEM_TEXTURE_UNASSIGNED){
					String namespace = item.getKey().split("\\.")[1];
					// Unholy string fuckery
					((ItemModelStandard) model).icon = TextureRegistry.getTexture(String.format("%s:item/%s", namespace,
						item.getKey().replaceFirst("item." + namespace + ".", "").replace(".", "_")));
					return model;
				}
				return model;
			}
		}
	}
}
