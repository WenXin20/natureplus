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
public class SnowPeaHeadPreciseHitbox extends NatureplusModElements.ModElement {
	public static final Map<Direction, VoxelShape> SNOWPEA = new HashMap<>();
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	// Precise selection box
	static {
		SNOWPEA.put(Direction.NORTH,
				VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5, 1, 4, 11, 7, 13), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(6, 2, 0, 10, 6, 4), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(5.5, 4.5, 13, 7.5, 6.5, 15), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(8.5, 4.5, 13, 10.5, 6.5, 15), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(5.5, 1.5, 13, 7.5, 3.5, 15), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(8.5, 1.5, 13, 10.5, 3.5, 15), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(7, 4.9, 12.25, 9, 6.9, 15.25), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(7, 1.1, 12.75, 9, 3.1, 14.75), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(5.25, 3, 12.75, 7.25, 5, 14.75), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(8.75, 3, 12.75, 10.75, 5, 14.75), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(7, 3, 12.5, 9, 5, 15.5), 
						Block.makeCuboidShape(6, 0, 7, 10, 1, 11), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR),
						IBooleanFunction.OR));
						
		SNOWPEA.put(Direction.SOUTH,
				VoxelShapes.combineAndSimplify(Block.makeCuboidShape(5, 1, 3, 11, 7, 12), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(6, 2, 12, 10, 6, 16), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(8.5, 4.5, 1, 10.5, 6.5, 3), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(5.5, 4.5, 1, 7.5, 6.5, 3), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(8.5, 1.5, 1, 10.5, 3.5, 3), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(5.5, 1.5, 1, 7.5, 3.5, 3), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(7, 4.9, 0.75, 9, 6.9, 3.75), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(7, 1.1, 1.25, 9, 3.1, 3.25), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(8.75, 3, 1.25, 10.75, 5, 3.25), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(5.25, 3, 1.25, 7.25, 5, 3.25), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(7, 3, 0.5, 9, 5, 3.5), 
						Block.makeCuboidShape(6, 0, 5, 10, 1, 9), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR),
						IBooleanFunction.OR));
		SNOWPEA.put(Direction.EAST,
				VoxelShapes.combineAndSimplify(Block.makeCuboidShape(3, 1, 5, 12, 7, 11), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(12, 2, 6, 16, 6, 10), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(1, 4.5, 5.5, 3, 6.5, 7.5), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(1, 4.5, 8.5, 3, 6.5, 10.5), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(1, 1.5, 5.5, 3, 3.5, 7.5), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(1, 1.5, 8.5, 3, 3.5, 10.5), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(0.75, 4.9, 7, 3.75, 6.9, 9), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(1.25, 1.1, 7, 3.25, 3.1, 9), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(1.25, 3, 5.25, 3.25, 5, 7.25), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(1.25, 3, 8.75, 3.25, 5, 10.75), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(0.5, 3, 7, 3.5, 5, 9), 
						Block.makeCuboidShape(5, 0, 6, 9, 1, 10), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR),
						IBooleanFunction.OR));
		SNOWPEA.put(Direction.WEST,
				VoxelShapes.combineAndSimplify(Block.makeCuboidShape(4, 1, 5, 13, 7, 11), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(0, 2, 6, 4, 6, 10), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(13, 4.5, 8.5, 15, 6.5, 10.5), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(13, 4.5, 5.5, 15, 6.5, 7.5), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(13, 1.5, 8.5, 15, 3.5, 10.5), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(13, 1.5, 5.5, 15, 3.5, 7.5), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(12.25, 4.9, 7, 15.25, 6.9, 9), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(12.75, 1.1, 7, 14.75, 3.1, 9), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(12.75, 3, 8.75, 14.75, 5, 10.75), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(12.75, 3, 5.25, 14.75, 5, 7.25), VoxelShapes
						.combineAndSimplify(Block.makeCuboidShape(12.5, 3, 7, 15.5, 5, 9), 
						Block.makeCuboidShape(7, 0, 6, 11, 1, 10), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR), IBooleanFunction.OR),
						IBooleanFunction.OR));
	}
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
		return SNOWPEA.get(state.get(FACING));
	}

	/**
	 * Do not remove this constructor
	 */
	public SnowPeaHeadPreciseHitbox(NatureplusModElements instance) {
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
