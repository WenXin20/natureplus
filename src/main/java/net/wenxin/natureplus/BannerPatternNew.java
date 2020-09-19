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

import net.wenxin.natureplus.item.RottenBrainItem;

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraft.tileentity.BannerPattern;
import net.minecraft.item.ItemStack;

import java.util.List;
import java.util.ArrayList;

@NatureplusModElements.ModElement.Tag
public class BannerPatternNew extends NatureplusModElements.ModElement {
	/**
	 * Do not remove this constructor
	 */
	public BannerPatternNew(NatureplusModElements instance) {
		super(instance, 857);
	}

	@Override
	public void initElements() {
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}

	@Override
	public void serverLoad(FMLServerStartingEvent event) {
	}
	public static final BannerPattern BRAIN = addBanner("brain", new ItemStack(RottenBrainItem.block));
	public static BannerPattern addBanner(String name, ItemStack item) {
		return BannerPattern.create(name.toUpperCase(), name, "natureplus." + name, item);
	}
}
