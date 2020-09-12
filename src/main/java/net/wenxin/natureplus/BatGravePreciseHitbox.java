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
public class BatGravePreciseHitbox extends NatureplusModElements.ModElement {
	public static final Map<Direction, VoxelShape> BAT_GRAVE = new HashMap<>();
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	// Precise selection box
	static {
		BAT_GRAVE.put(Direction.NORTH,
				VoxelShapes.combineAndSimplify(Block.makeCuboidShape(1, 0, 10, 15, 11, 15),
						VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5, 10, 9.5, 11, 16, 15.5),
								Block.makeCuboidShape(0, 11, 9.5, 16, 15, 15.5), IBooleanFunction.OR),
						IBooleanFunction.OR));
		BAT_GRAVE.put(Direction.SOUTH,
				VoxelShapes.combineAndSimplify(Block.makeCuboidShape(1, 0, 1, 15, 11, 6),
						VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5, 10, 0.5, 11, 16, 6.5), Block.makeCuboidShape(0, 11, 0.5, 16, 15, 6.5),
								IBooleanFunction.OR),
						IBooleanFunction.OR));
		BAT_GRAVE.put(Direction.EAST,
				VoxelShapes.combineAndSimplify(Block.makeCuboidShape(1, 0, 1, 6, 11, 15),
						VoxelShapes.combineAndSimplify(Block.makeCuboidShape(0.5, 10, 5, 6.5, 16, 11), Block.makeCuboidShape(0.5, 11, 0, 6.5, 15, 16),
								IBooleanFunction.OR),
						IBooleanFunction.OR));
		BAT_GRAVE.put(Direction.WEST,
				VoxelShapes.combineAndSimplify(Block.makeCuboidShape(10, 0, 1, 15, 11, 15),
						VoxelShapes.combineAndSimplify(Block.makeCuboidShape(9.5, 10, 5, 15.5, 16, 11),
								Block.makeCuboidShape(9.5, 11, 0, 15.5, 15, 16), IBooleanFunction.OR),
						IBooleanFunction.OR));
	}
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return BAT_GRAVE.get(state.get(FACING));
	}

	/**
	 * Do not remove this constructor
	 */
	public BatGravePreciseHitbox(NatureplusModElements instance) {
		super(instance, 886);
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
