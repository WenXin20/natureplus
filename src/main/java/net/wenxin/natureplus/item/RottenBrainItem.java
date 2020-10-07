
package net.wenxin.natureplus.item;

import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;

@NatureplusModElements.ModElement.Tag
public class RottenBrainItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:rotten_brain")
	public static final Item block = null;
	public RottenBrainItem(NatureplusModElements instance) {
		super(instance, 220);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(PlantsVsZombiesTabItemGroup.tab).maxStackSize(64)
					.food((new Food.Builder()).hunger(4).saturation(0.2f).effect(new EffectInstance(Effects.HUNGER, 700, 0), 0.8F).meat().build()));
			setRegistryName("rotten_brain");
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.EAT;
		}
	}
}
