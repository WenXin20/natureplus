
package net.wenxin.natureplus.item;

import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

@NatureplusModElements.ModElement.Tag
public class MonarchCaterpillarIconItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:monarch_caterpillar_icon")
	public static final Item block = null;
	public MonarchCaterpillarIconItem(NatureplusModElements instance) {
		super(instance, 701);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(null).maxStackSize(64).rarity(Rarity.COMMON));
			setRegistryName("monarch_caterpillar_icon");
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
