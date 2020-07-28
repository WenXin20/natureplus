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

import net.wenxin.natureplus.tileentity.NatureplusSkullTileEntity;

import net.minecraftforge.common.property.Properties;

import net.minecraft.world.IBlockReader;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.block.SkullBlock;

import javax.annotation.Nonnull;

@NatureplusModElements.ModElement.Tag
public class NatureplusWallHeadBlock extends SkullBlock {
	/**
	 * Do not remove this constructor
	 */
	// public NatureplusWallHeadBlock(NatureplusModElements instance) {
	// super(instance, 790);
	// }
	public NatureplusWallHeadBlock(Properties properties) {
		super(NatureplusHeadBlock.SNOW_PEA, properties);
	}

	@Nonnull
	@Override
	public TileEntity createNewTileEntity(IBlockReader world) {
		return new NatureplusSkullTileEntity();
	}
	// @Override
	// public void initElements() {
	// }
	//
	// @Override
	// public void init(FMLCommonSetupEvent event) {
	// }
	//
	// @Override
	// public void serverLoad(FMLServerStartingEvent event) {
	// }
}
