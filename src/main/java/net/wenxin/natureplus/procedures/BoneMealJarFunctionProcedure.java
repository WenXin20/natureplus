package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.block.EmptyJarBlock;
import net.wenxin.natureplus.block.BoneMealJarBlock;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Hand;
import net.minecraft.util.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.block.IGrowable;
import net.minecraft.block.DeadCoralWallFanBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import javax.annotation.Nullable;

import java.util.Random;

@NatureplusModElements.ModElement.Tag
public class BoneMealJarFunctionProcedure extends NatureplusModElements.ModElement {
	public BoneMealJarFunctionProcedure(NatureplusModElements instance) {
		super(instance, 931);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Deprecated // Forge: Use Player/Hand version
	public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos pos) {
		if (worldIn instanceof net.minecraft.world.server.ServerWorld)
			return applyBonemeal(stack, worldIn, pos,
					net.minecraftforge.common.util.FakePlayerFactory.getMinecraft((net.minecraft.world.server.ServerWorld) worldIn));
		return false;
	}

	public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos pos, net.minecraft.entity.player.PlayerEntity player) {
		BlockState blockstate = worldIn.getBlockState(pos);
		int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, worldIn, pos, blockstate, stack);
		if (hook != 0)
			return hook > 0;
		if (blockstate.getBlock() instanceof IGrowable && !(player.isSneaking())) {
			IGrowable igrowable = (IGrowable) blockstate.getBlock();
			if (igrowable.canGrow(worldIn, pos, blockstate, worldIn.isRemote)) {
				if (worldIn instanceof ServerWorld) {
					if (igrowable.canUseBonemeal(worldIn, worldIn.rand, pos, blockstate)) {
						igrowable.grow((ServerWorld) worldIn, worldIn.rand, pos, blockstate);
					}
					if (!(player.abilities.isCreativeMode)) {
						stack.damageItem(1, player, onBroken -> {
							player.setHeldItem(Hand.MAIN_HAND, new ItemStack(EmptyJarBlock.block, (int) (1)));
						});
					}
				}
				return true;
			}
		}
		return false;
	}
	public static final Random random = new Random();
	public static boolean growSeagrass(ItemStack stack, World worldIn, BlockPos pos, @Nullable Direction side,
			net.minecraft.entity.player.PlayerEntity player) {
		if (worldIn.getBlockState(pos).getBlock() == Blocks.WATER && worldIn.getFluidState(pos).getLevel() == 8) {
			if (!(worldIn instanceof ServerWorld)) {
				return true;
			} else {
				label80 : for (int i = 0; i < 128; ++i) {
					BlockPos blockpos = pos;
					Biome biome = worldIn.getBiome(pos);
					BlockState blockstate = Blocks.SEAGRASS.getDefaultState();
					for (int j = 0; j < i / 16; ++j) {
						blockpos = blockpos.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
						biome = worldIn.getBiome(blockpos);
						if (worldIn.getBlockState(blockpos).isCollisionShapeOpaque(worldIn, blockpos)) {
							continue label80;
						}
					}
					// FORGE: Use BiomeDictionary here to allow modded warm ocean biomes to spawn
					// coral from bonemeal
					if (net.minecraftforge.common.BiomeDictionary.hasType(biome, net.minecraftforge.common.BiomeDictionary.Type.OCEAN)
							&& net.minecraftforge.common.BiomeDictionary.hasType(biome, net.minecraftforge.common.BiomeDictionary.Type.HOT)) {
						if (i == 0 && side != null && side.getAxis().isHorizontal()) {
							blockstate = BlockTags.WALL_CORALS.getRandomElement(worldIn.rand).getDefaultState().with(DeadCoralWallFanBlock.FACING,
									side);
						} else if (random.nextInt(4) == 0) {
							blockstate = BlockTags.UNDERWATER_BONEMEALS.getRandomElement(random).getDefaultState();
						}
					}
					if (blockstate.getBlock().isIn(BlockTags.WALL_CORALS)) {
						for (int k = 0; !blockstate.isValidPosition(worldIn, blockpos) && k < 4; ++k) {
							blockstate = blockstate.with(DeadCoralWallFanBlock.FACING, Direction.Plane.HORIZONTAL.random(random));
						}
					}
					if (blockstate.isValidPosition(worldIn, blockpos)) {
						BlockState blockstate1 = worldIn.getBlockState(blockpos);
						if (blockstate1.getBlock() == Blocks.WATER && worldIn.getFluidState(blockpos).getLevel() == 8) {
							worldIn.setBlockState(blockpos, blockstate, 3);
						} else if (blockstate1.getBlock() == Blocks.SEAGRASS && random.nextInt(10) == 0) {
							((IGrowable) Blocks.SEAGRASS).grow((ServerWorld) worldIn, random, blockpos, blockstate1);
						}
					}
				}
				if (!(player.abilities.isCreativeMode)) {
					stack.damageItem(1, player, onBroken -> {
						player.setHeldItem(Hand.MAIN_HAND, new ItemStack(EmptyJarBlock.block, (int) (1)));
					});
				}
				return true;
			}
		} else {
			return false;
		}
	}

	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		PlayerEntity entity = event.getPlayer();
		World world = event.getWorld();
		BlockPos blockpos = event.getPos();
		BlockPos blockpos1 = blockpos.offset(event.getFace());
		ItemStack stack2 = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
		if (event.getHand() != entity.getActiveHand())
			return;
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(BoneMealJarBlock.block, (int) (1)).getItem())) {
			if (applyBonemeal(stack2, world, blockpos, event.getPlayer()) && !event.getPlayer().isSneaking()) {
				((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
				if (!world.isRemote) {
					world.playEvent(2005, blockpos, 0);
				}
			} else if (!event.getPlayer().isSneaking()) {
				BlockState blockstate = world.getBlockState(blockpos);
				boolean flag = blockstate.isSolidSide(world, blockpos, event.getFace());
				if (flag && growSeagrass(stack2, world, blockpos1, event.getFace(), event.getPlayer())) {
					((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
					if (!world.isRemote) {
						world.playEvent(2005, blockpos1, 0);
					}
				}
			}
		}
	}
}
