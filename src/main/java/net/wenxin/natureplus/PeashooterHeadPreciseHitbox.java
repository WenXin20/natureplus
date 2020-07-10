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
public class PeashooterHeadPreciseHitbox extends NatureplusModElements.ModElement {
	public static final Map<Direction, VoxelShape> PEASHOOTER = new HashMap<>();
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	// Precise selection box
	static {
		PEASHOOTER.put(Direction.NORTH,
				VoxelShapes.combineAndSimplify(Block.makeCuboidShape(6, 0, 7, 10, 1, 11), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(5, 1, 4, 11, 7, 13), Block.makeCuboidShape(6, 2, 0, 10, 6, 4), IBooleanFunction.OR),
						IBooleanFunction.OR));
		PEASHOOTER.put(Direction.SOUTH, VoxelShapes.combineAndSimplify(Block.makeCuboidShape(6, 0, 5, 10, 1, 9), VoxelShapes.combineAndSimplify(
				Block.makeCuboidShape(5, 1, 3, 11, 7, 12), Block.makeCuboidShape(6, 2, 12, 10, 6, 16), IBooleanFunction.OR), IBooleanFunction.OR));
		PEASHOOTER.put(Direction.EAST, VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5, 0, 6, 9, 1, 10), VoxelShapes.combineAndSimplify(
				Block.makeCuboidShape(3, 1, 5, 12, 7, 11), Block.makeCuboidShape(12, 2, 6, 16, 6, 10), IBooleanFunction.OR), IBooleanFunction.OR));
		PEASHOOTER.put(Direction.WEST,
				VoxelShapes.combineAndSimplify(Block.makeCuboidShape(7, 0, 6, 11, 1, 10), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(4, 1, 5, 13, 7, 11), Block.makeCuboidShape(0, 2, 6, 4, 6, 10), IBooleanFunction.OR),
						IBooleanFunction.OR));
	}
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return PEASHOOTER.get(state.get(FACING));
	}

	/**
	 * Do not remove this constructor
	 */
	public PeashooterHeadPreciseHitbox(NatureplusModElements instance) {
		super(instance, 461);
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
