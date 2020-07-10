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
 * If you want to make a plain independent class, create it in
 * "Workspace" -> "Source" menu.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.wenxin.natureplus;

import net.wenxin.natureplus.block.StrippedAzureWoodBlock;
import net.wenxin.natureplus.block.StrippedAzureLogBlock;
import net.wenxin.natureplus.block.AzureWoodBlock;
import net.wenxin.natureplus.block.AzureLogBlock;

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraft.block.Block;

@NatureplusModElements.ModElement.Tag
public class AddStrippable extends NatureplusModElements.ModElement {
	public void strippableBlocks(FMLCommonSetupEvent e) {
		addStrippable(AzureLogBlock.block, StrippedAzureLogBlock.block);
		addStrippable(AzureWoodBlock.block, StrippedAzureWoodBlock.block);
	}

	public static void addStrippable(Block block, Block strippedBlock) {
		// AxeItem.BLOCK_STRIPPING_MAP = Maps.newHashMap(AxeItem.BLOCK_STRIPPING_MAP);
		// AxeItem.BLOCK_STRIPPING_MAP.put(block, strippedBlock);
	}

	/**
	 * Do not remove this constructor
	 */
	public AddStrippable(NatureplusModElements instance) {
		super(instance, 151);
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
}
