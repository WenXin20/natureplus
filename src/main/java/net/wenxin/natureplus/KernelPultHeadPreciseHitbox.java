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
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.state.DirectionProperty;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.Map;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class KernelPultHeadPreciseHitbox extends NatureplusModElements.ModElement {
	public static final Map<Direction, VoxelShape> KERNEL_PULT = new HashMap<>();
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	// Precise selection box
	static {
		KERNEL_PULT.put(Direction.NORTH,
				VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5, 0, 5, 11, 5, 11), 
					Block.makeCuboidShape(6, 5, 6, 10, 7, 10),
					IBooleanFunction.OR));
		KERNEL_PULT.put(Direction.SOUTH, 
				VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5, 0, 5, 11, 5, 11), 
					Block.makeCuboidShape(6, 5, 6, 10, 7, 10),
					IBooleanFunction.OR));
		KERNEL_PULT.put(Direction.EAST, 
				VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5, 0, 5, 11, 5, 11), 
					Block.makeCuboidShape(6, 5, 6, 10, 7, 10),
					IBooleanFunction.OR));
		KERNEL_PULT.put(Direction.WEST,
				VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5, 0, 5, 11, 5, 11), 
					Block.makeCuboidShape(6, 5, 6, 10, 7, 10),
					IBooleanFunction.OR));
	}
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return KERNEL_PULT.get(state.get(FACING));
	}

	/**
	 * Do not remove this constructor
	 */
	public KernelPultHeadPreciseHitbox(NatureplusModElements instance) {
		super(instance, 629);
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
