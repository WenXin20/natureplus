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

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

@NatureplusModElements.ModElement.Tag
public class VasePreciseHitbox extends NatureplusModElements.ModElement {
	// Precise selection box
	public static final VoxelShape VASE = VoxelShapes.or(
		Block.makeCuboidShape(4, 0, 4, 12, 1, 12), 
		Block.makeCuboidShape(5, 1, 5, 11, 3, 11), 
		Block.makeCuboidShape(4, 3, 4, 12, 6, 12), 
		Block.makeCuboidShape(3, 6, 3, 13, 13, 13), 
		Block.makeCuboidShape(4, 13, 4, 12, 14, 12), 
		Block.makeCuboidShape(6, 14, 6, 10, 15, 10), 
		Block.makeCuboidShape(5, 15, 5, 11, 16, 11)).simplify();

	/**
	 * Do not remove this constructor
	 */
	public VasePreciseHitbox(NatureplusModElements instance) {
		super(instance, 609);
	}

	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return VASE;
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
