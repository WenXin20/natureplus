
package net.wenxin.natureplus.item;

import org.omg.CORBA.ObjectHolder;

import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class PlumItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:plum")
	public static final Item block = null;
	public PlumItem(NatureplusModElements instance) {
		super(instance, 107);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(NaturePlusTabItemGroup.tab).maxStackSize(64)
					.food((new Food.Builder()).hunger(4).saturation(2.4f).build()));
			setRegistryName("plum");
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}
	}
}
