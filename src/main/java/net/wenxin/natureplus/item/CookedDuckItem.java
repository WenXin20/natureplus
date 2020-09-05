
package net.wenxin.natureplus.item;

import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

@NatureplusModElements.ModElement.Tag
public class CookedDuckItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:cooked_duck")
	public static final Item block = null;
	public CookedDuckItem(NatureplusModElements instance) {
		super(instance, 114);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(NaturePlusTabItemGroup.tab).maxStackSize(64)
					.food((new Food.Builder()).hunger(8).saturation(1f).meat().build()));
			setRegistryName("cooked_duck");
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}
	}
}
