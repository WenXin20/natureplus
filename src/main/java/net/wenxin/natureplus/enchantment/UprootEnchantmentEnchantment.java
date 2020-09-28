
package net.wenxin.natureplus.enchantment;

import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.enchantment.Enchantment;

@NatureplusModElements.ModElement.Tag
public class UprootEnchantmentEnchantment extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:uproot_enchantment")
	public static final Enchantment enchantment = null;
	public UprootEnchantmentEnchantment(NatureplusModElements instance) {
		super(instance, 677);
	}

	@Override
	public void initElements() {
		elements.enchantments.add(() -> new CustomEnchantment(EquipmentSlotType.MAINHAND).setRegistryName("uproot_enchantment"));
	}
	public static class CustomEnchantment extends Enchantment {
		public CustomEnchantment(EquipmentSlotType... slots) {
			super(Enchantment.Rarity.UNCOMMON, EnchantmentType.DIGGER, slots);
		}

		@Override
		public int getMinLevel() {
			return 1;
		}

		@Override
		public int getMaxLevel() {
			return 1;
		}

		@Override
		public boolean isTreasureEnchantment() {
			return false;
		}

		@Override
		public boolean isCurse() {
			return false;
		}

		@Override
		public boolean isAllowedOnBooks() {
			return true;
		}
	}
}
