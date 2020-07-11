
package net.wenxin.natureplus.item;

import org.omg.CORBA.ObjectHolder;

import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class MonarchButterflyIconItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:monarch_butterfly_icon")
	public static final Item block = null;
	public MonarchButterflyIconItem(NatureplusModElements instance) {
		super(instance, 665);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(null).maxStackSize(64));
			setRegistryName("monarch_butterfly_icon");
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
