package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.block.ThornsBlock;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.state.DirectionProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.block.BlockState;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class ThornsBreakNoSupportProcedure extends NatureplusModElements.ModElement {
	public ThornsBreakNoSupportProcedure(NatureplusModElements instance) {
		super(instance, 588);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure ThornsBreakNoSupport!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure ThornsBreakNoSupport!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure ThornsBreakNoSupport!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure ThornsBreakNoSupport!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ThornsBlock.block.getDefaultState().getBlock())
				&& (((new Object() {
					public Direction getDirection(BlockPos pos) {
						try {
							BlockState _bs = world.getBlockState(pos);
							DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
							return _bs.get(property);
						} catch (Exception e) {
							return Direction.NORTH;
						}
					}
				}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.UP)
						&& (!(world.getBlockState(new BlockPos((int) x, (int) (y - 1), (int) z)).isSolid()))))) {
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			if (!world.isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(ThornsBlock.block, (int) (1)));
				entityToSpawn.setPickupDelay(10);
				world.addEntity(entityToSpawn);
			}
		} else if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ThornsBlock.block.getDefaultState().getBlock())
				&& (((new Object() {
					public Direction getDirection(BlockPos pos) {
						try {
							BlockState _bs = world.getBlockState(pos);
							DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
							return _bs.get(property);
						} catch (Exception e) {
							return Direction.NORTH;
						}
					}
				}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.DOWN)
						&& (!(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).isSolid()))))) {
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			if (!world.isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(ThornsBlock.block, (int) (1)));
				entityToSpawn.setPickupDelay(10);
				world.addEntity(entityToSpawn);
			}
		} else if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ThornsBlock.block.getDefaultState().getBlock())
				&& (((new Object() {
					public Direction getDirection(BlockPos pos) {
						try {
							BlockState _bs = world.getBlockState(pos);
							DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
							return _bs.get(property);
						} catch (Exception e) {
							return Direction.NORTH;
						}
					}
				}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.NORTH)
						&& (!(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z + 1))).isSolid()))))) {
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			if (!world.isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(ThornsBlock.block, (int) (1)));
				entityToSpawn.setPickupDelay(10);
				world.addEntity(entityToSpawn);
			}
		} else if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ThornsBlock.block.getDefaultState().getBlock())
				&& (((new Object() {
					public Direction getDirection(BlockPos pos) {
						try {
							BlockState _bs = world.getBlockState(pos);
							DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
							return _bs.get(property);
						} catch (Exception e) {
							return Direction.NORTH;
						}
					}
				}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.SOUTH)
						&& (!(world.getBlockState(new BlockPos((int) x, (int) y, (int) (z - 1))).isSolid()))))) {
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			if (!world.isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(ThornsBlock.block, (int) (1)));
				entityToSpawn.setPickupDelay(10);
				world.addEntity(entityToSpawn);
			}
		} else if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ThornsBlock.block.getDefaultState().getBlock())
				&& (((new Object() {
					public Direction getDirection(BlockPos pos) {
						try {
							BlockState _bs = world.getBlockState(pos);
							DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
							return _bs.get(property);
						} catch (Exception e) {
							return Direction.NORTH;
						}
					}
				}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.EAST)
						&& (!(world.getBlockState(new BlockPos((int) (x - 1), (int) y, (int) z)).isSolid()))))) {
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			if (!world.isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(ThornsBlock.block, (int) (1)));
				entityToSpawn.setPickupDelay(10);
				world.addEntity(entityToSpawn);
			}
		} else if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == ThornsBlock.block.getDefaultState().getBlock())
				&& (((new Object() {
					public Direction getDirection(BlockPos pos) {
						try {
							BlockState _bs = world.getBlockState(pos);
							DirectionProperty property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
							return _bs.get(property);
						} catch (Exception e) {
							return Direction.NORTH;
						}
					}
				}.getDirection(new BlockPos((int) x, (int) y, (int) z))) == Direction.WEST)
						&& (!(world.getBlockState(new BlockPos((int) (x + 1), (int) y, (int) z)).isSolid()))))) {
			world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			if (!world.isRemote) {
				ItemEntity entityToSpawn = new ItemEntity(world, x, y, z, new ItemStack(ThornsBlock.block, (int) (1)));
				entityToSpawn.setPickupDelay(10);
				world.addEntity(entityToSpawn);
			}
		}
	}
}
