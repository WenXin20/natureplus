
package net.wenxin.natureplus.item;

import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

@NatureplusModElements.ModElement.Tag
public class RawDragonflyItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:raw_dragonfly")
	public static final Item block = null;
	public RawDragonflyItem(NatureplusModElements instance) {
		super(instance, 115);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(NaturePlusTabItemGroup.tab).maxStackSize(64)
					.food((new Food.Builder()).hunger(2).saturation(0.1f).meat().build()));
			setRegistryName("raw_dragonfly");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 16;
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}
	}
}
