package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.BoneMealJarItem;
import net.wenxin.natureplus.block.MexicanSunflowerCropStage2Block;
import net.wenxin.natureplus.block.MexicanSunflowerCropStage1Block;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Hand;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class GrowStage1to2BonemealProcedure extends NatureplusModElements.ModElement {
	public GrowStage1to2BonemealProcedure(NatureplusModElements instance) {
		super(instance, 206);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure GrowStage1to2Bonemeal!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure GrowStage1to2Bonemeal!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure GrowStage1to2Bonemeal!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure GrowStage1to2Bonemeal!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure GrowStage1to2Bonemeal!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((!(world.isRemote))) {
			if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MexicanSunflowerCropStage1Block.block.getDefaultState()
					.getBlock())
					&& ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getItem() == new ItemStack(Items.BONE_MEAL, (int) (1)).getItem())
							|| (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getItem() == new ItemStack(BoneMealJarItem.block, (int) (1)).getItem())))) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 0.5), (z + 0.5), (int) 25, 0.25, 0.25, 0.25, 5);
				}
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).swingArm(Hand.MAIN_HAND);
				}
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), MexicanSunflowerCropStage2Block.block.getDefaultState(), 3);
				if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
					if (entity instanceof PlayerEntity)
						((PlayerEntity) entity).inventory.clearMatchingItems(
								p -> ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
										.getItem() == p.getItem(),
								(int) 1);
				}
			} else if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == MexicanSunflowerCropStage1Block.block
					.getDefaultState().getBlock())
					&& ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
							.getItem() == new ItemStack(Items.BONE_MEAL, (int) (1)).getItem())
							|| (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
									.getItem() == new ItemStack(BoneMealJarItem.block, (int) (1)).getItem())))) {
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 0.5), (z + 0.5), (int) 25, 0.25, 0.25, 0.25, 5);
				}
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).swingArm(Hand.OFF_HAND);
				}
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
				world.setBlockState(new BlockPos((int) x, (int) y, (int) z), MexicanSunflowerCropStage2Block.block.getDefaultState(), 3);
				if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
					if (entity instanceof PlayerEntity)
						((PlayerEntity) entity).inventory.clearMatchingItems(
								p -> ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
										.getItem() == p.getItem(),
								(int) 1);
				}
			}
		}
	}
}
