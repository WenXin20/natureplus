
package net.wenxin.natureplus.item;

import net.wenxin.natureplus.procedures.GoldenMexicanSunflowerSeedsEffectsProcedure;
import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.UseAction;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.LivingEntity;

import java.util.Map;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class GoldenMexicanSunflowerSeedsItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:golden_mexican_sunflower_seeds")
	public static final Item block = null;
	public GoldenMexicanSunflowerSeedsItem(NatureplusModElements instance) {
		super(instance, 108);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(NaturePlusTabItemGroup.tab).maxStackSize(64).rarity(Rarity.COMMON)
					.food((new Food.Builder()).hunger(6).saturation(0.3f).setAlwaysEdible().build()));
			setRegistryName("golden_mexican_sunflower_seeds");
		}

		@Override
		public int getUseDuration(ItemStack stack) {
			return 16;
		}

		@Override
		public UseAction getUseAction(ItemStack itemstack) {
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
				GoldenMexicanSunflowerSeedsEffectsProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
