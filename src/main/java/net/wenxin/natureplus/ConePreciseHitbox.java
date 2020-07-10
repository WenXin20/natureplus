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
public class ConePreciseHitbox extends NatureplusModElements.ModElement {
	// Precise selection box
	public static final VoxelShape CONE = VoxelShapes.or(Block.makeCuboidShape(2, 0, 2, 4, 1, 14), Block.makeCuboidShape(4, 0, 12, 12, 1, 14),
			Block.makeCuboidShape(12, 0, 2, 14, 1, 14), Block.makeCuboidShape(4, 0, 2, 12, 1, 4), Block.makeCuboidShape(4, 1, 4, 5, 6, 12),
			Block.makeCuboidShape(5, 1, 4, 11, 6, 5), Block.makeCuboidShape(5, 1, 11, 11, 6, 12), Block.makeCuboidShape(11, 1, 4, 12, 6, 12),
			Block.makeCuboidShape(5, 6, 5, 6, 11, 11), Block.makeCuboidShape(6, 6, 5, 10, 11, 6), Block.makeCuboidShape(6, 6, 10, 10, 11, 11),
			Block.makeCuboidShape(10, 6, 5, 11, 11, 11), Block.makeCuboidShape(7, 11, 6, 9, 16, 7), Block.makeCuboidShape(6, 11, 6, 7, 16, 10),
			Block.makeCuboidShape(7, 11, 9, 9, 16, 10), Block.makeCuboidShape(9, 11, 6, 10, 16, 10)).simplify();
	public static final VoxelShape CONE_COLLISION = VoxelShapes
			.or(Block.makeCuboidShape(2, 0, 2, 4, 1, 14), Block.makeCuboidShape(4, 0, 12, 12, 1, 14), Block.makeCuboidShape(12, 0, 2, 14, 1, 14),
					Block.makeCuboidShape(4, 0, 2, 12, 1, 4), Block.makeCuboidShape(4, 1, 4, 5, 25, 12), Block.makeCuboidShape(5, 1, 4, 11, 25, 5),
					Block.makeCuboidShape(5, 1, 11, 11, 25, 12), Block.makeCuboidShape(11, 1, 4, 12, 25, 12))
			.simplify();
	/**
	 * Do not remove this constructor
	 */
	public ConePreciseHitbox(NatureplusModElements instance) {
		super(instance, 457);
	}

	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return CONE;
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
