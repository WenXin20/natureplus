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
public class SmallCrossGravePreciseHitbox extends NatureplusModElements.ModElement {
	public static final Map<Direction, VoxelShape> SMALL_CROSS_GRAVE = new HashMap<>();
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	// Precise selection box
	static {
		SMALL_CROSS_GRAVE
				.put(Direction.NORTH,
						VoxelShapes.combineAndSimplify(Block.makeCuboidShape(2, 2, 10, 14, 11, 15),
								VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7, 11, 10, 9, 16, 15),
										VoxelShapes.combineAndSimplify(Block.makeCuboidShape(6, 13, 10, 10, 15, 15),
												Block.makeCuboidShape(1, 0, 9, 15, 2, 16), IBooleanFunction.OR),
										IBooleanFunction.OR),
								IBooleanFunction.OR));
		SMALL_CROSS_GRAVE
				.put(Direction.SOUTH,
						VoxelShapes.combineAndSimplify(Block.makeCuboidShape(2, 2, 1, 14, 11, 6),
								VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7, 11, 1, 9, 16, 6),
										VoxelShapes.combineAndSimplify(Block.makeCuboidShape(6, 13, 1, 10, 15, 6),
												Block.makeCuboidShape(1, 0, 0, 15, 2, 7), IBooleanFunction.OR),
										IBooleanFunction.OR),
								IBooleanFunction.OR));
		SMALL_CROSS_GRAVE
				.put(Direction.EAST,
						VoxelShapes.combineAndSimplify(Block.makeCuboidShape(1, 2, 2, 6, 11, 14),
								VoxelShapes.combineAndSimplify(Block.makeCuboidShape(1, 11, 7, 6, 16, 9),
										VoxelShapes.combineAndSimplify(Block.makeCuboidShape(1, 13, 6, 6, 15, 10),
												Block.makeCuboidShape(0, 0, 1, 7, 2, 15), IBooleanFunction.OR),
										IBooleanFunction.OR),
								IBooleanFunction.OR));
		SMALL_CROSS_GRAVE
				.put(Direction.WEST,
						VoxelShapes.combineAndSimplify(Block.makeCuboidShape(10, 2, 2, 15, 11, 14),
								VoxelShapes.combineAndSimplify(Block.makeCuboidShape(10, 11, 7, 15, 16, 9),
										VoxelShapes.combineAndSimplify(Block.makeCuboidShape(10, 13, 6, 15, 15, 10),
												Block.makeCuboidShape(9, 0, 1, 16, 2, 15), IBooleanFunction.OR),
										IBooleanFunction.OR),
								IBooleanFunction.OR));
	}
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return SMALL_CROSS_GRAVE.get(state.get(FACING));
	}

	/**
	 * Do not remove this constructor
	 */
	public SmallCrossGravePreciseHitbox(NatureplusModElements instance) {
		super(instance, 894);
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
