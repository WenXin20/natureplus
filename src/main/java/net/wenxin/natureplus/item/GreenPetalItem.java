
package net.wenxin.natureplus.item;

import org.omg.CORBA.ObjectHolder;

import net.wenxin.natureplus.procedures.GrantPetalsAdvancementProcedure;
import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import java.util.Map;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class GreenPetalItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:green_petal")
	public static final Item block = null;
	public GreenPetalItem(NatureplusModElements instance) {
		super(instance, 98);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(NaturePlusTabItemGroup.tab).maxStackSize(64));
			setRegistryName("green_petal");
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

		@Override
		public void inventoryTick(ItemStack itemstack, World world, Entity entity, int slot, boolean selected) {
			super.inventoryTick(itemstack, world, entity, slot, selected);
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				GrantPetalsAdvancementProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
