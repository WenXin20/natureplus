
package net.wenxin.natureplus.item;

import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

@NatureplusModElements.ModElement.Tag
public class DuckFeatherItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:duck_feather")
	public static final Item block = null;
	public DuckFeatherItem(NatureplusModElements instance) {
		super(instance, 841);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(NaturePlusTabItemGroup.tab).maxStackSize(64));
			setRegistryName("duck_feather");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
