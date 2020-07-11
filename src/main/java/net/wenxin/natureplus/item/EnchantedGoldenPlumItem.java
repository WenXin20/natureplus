
package net.wenxin.natureplus.item;

import org.omg.CORBA.ObjectHolder;

import net.wenxin.natureplus.procedures.EnchantedGoldenPlumEffectsProcedure;
import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import java.util.Map;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class EnchantedGoldenPlumItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:enchanted_golden_plum")
	public static final Item block = null;
	public EnchantedGoldenPlumItem(NatureplusModElements instance) {
		super(instance, 109);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(NaturePlusTabItemGroup.tab).maxStackSize(64)
					.food((new Food.Builder()).hunger(4).saturation(9.6f).setAlwaysEdible().build()));
			setRegistryName("enchanted_golden_plum");
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public boolean hasEffect(ItemStack itemstack) {
			return true;
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
				EnchantedGoldenPlumEffectsProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
