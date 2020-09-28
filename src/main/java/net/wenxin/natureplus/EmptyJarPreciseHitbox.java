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
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

@NatureplusModElements.ModElement.Tag
public class EmptyJarPreciseHitbox extends NatureplusModElements.ModElement {
	/**
	 * Do not remove this constructor
	 */
	public EmptyJarPreciseHitbox(NatureplusModElements instance) {
		super(instance, 914);
	}
	// Precise selection box
	public static final VoxelShape EMPTY_JAR = VoxelShapes.or(
		Block.makeCuboidShape(3.5, 0, 3.5, 12.5, 11, 12.5), 
		Block.makeCuboidShape(4.5, 11, 4.5, 11.5, 12, 11.5)).simplify();

	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return EMPTY_JAR;
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

	@OnlyIn(Dist.CLIENT)
	@Override
	public void clientLoad(FMLClientSetupEvent event) {
	}
}
