/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class NatureplusModElements.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser - New... and make sure to make the class
 * outside net.wenxin.natureplus as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.wenxin.natureplus;

import net.minecraftforge.common.property.Properties;

import net.minecraft.item.WallOrFloorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.block.Block;

@NatureplusModElements.ModElement.Tag
public class NatureplusHeadItem extends WallOrFloorItem {
	/**
	 * Do not remove this constructor
	 */
	public NatureplusHeadItem(Block floor, Block wall, Properties props) {
		super(floor, wall, props);
	}

	@Override
	public EquipmentSlotType getEquipmentSlot(ItemStack stack) {
		return EquipmentSlotType.HEAD;
	}
}
