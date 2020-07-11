
package net.wenxin.natureplus.item;

import org.omg.CORBA.ObjectHolder;

import net.wenxin.natureplus.procedures.GoldenPlumEffectsProcedure;
import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import java.util.Map;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class GoldenPlumItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:golden_plum")
	public static final Item block = null;
	public GoldenPlumItem(NatureplusModElements instance) {
		super(instance, 108);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(NaturePlusTabItemGroup.tab).maxStackSize(64)
					.food((new Food.Builder()).hunger(4).saturation(9.6f).setAlwaysEdible().build()));
			setRegistryName("golden_plum");
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}

		@Override
		public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
			ItemStack retval = super.onItemUseFinish(itemStack, world, entity);
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				GoldenPlumEffectsProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
