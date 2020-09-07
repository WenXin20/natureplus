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

import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BannerPatternItem;
import net.minecraft.item.ItemGroup;

@NatureplusModElements.ModElement.Tag
public class BannerPatternRegistry extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:brain_banner_pattern")
	public static final Item block = null;
	/**
	 * Do not remove this constructor
	 */
	public BannerPatternRegistry(NatureplusModElements instance) {
		super(instance, 859);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> BRAIN);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}

	@Override
	public void serverLoad(FMLServerStartingEvent event) {
	}
	public static final Item BRAIN = register("natureplus:brain_banner_pattern", new BannerPatternItem(BannerPatternNew.BRAIN,
			(new Item.Properties()).maxStackSize(1).group(ItemGroup.MISC).rarity(Rarity.UNCOMMON)));

	private static Item register(String key, Item itemIn) {
		return register(new ResourceLocation(key), itemIn);
	}

	private static Item register(ResourceLocation key, Item itemIn) {
		if (itemIn instanceof BlockItem) {
			((BlockItem) itemIn).addToBlockToItemMap(Item.BLOCK_TO_ITEM, itemIn);
		}
		return Registry.register(Registry.ITEM, key, itemIn);
	}
}
