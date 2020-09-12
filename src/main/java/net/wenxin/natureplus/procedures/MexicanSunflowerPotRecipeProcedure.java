package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.block.MexicanSunflowerFlowerPotBlock;
import net.wenxin.natureplus.block.MexicanSunflowerBlock;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class MexicanSunflowerPotRecipeProcedure extends NatureplusModElements.ModElement {
	public MexicanSunflowerPotRecipeProcedure(NatureplusModElements instance) {
		super(instance, 255);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure MexicanSunflowerPotRecipe!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure MexicanSunflowerPotRecipe!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure MexicanSunflowerPotRecipe!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure MexicanSunflowerPotRecipe!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure MexicanSunflowerPotRecipe!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(MexicanSunflowerBlock.block, (int) (1)).getItem())
				&& ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.FLOWER_POT.getDefaultState().getBlock()))) {
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).inventory.clearMatchingItems(
							p -> ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem() == p
									.getItem(),
							(int) 1);
			}
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
			}
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), MexicanSunflowerFlowerPotBlock.block.getDefaultState(), 3);
		}
		if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(MexicanSunflowerBlock.block, (int) (1)).getItem())
				&& ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.FLOWER_POT.getDefaultState().getBlock()))) {
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).inventory.clearMatchingItems(
							p -> ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getItem() == p
									.getItem(),
							(int) 1);
			}
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), Blocks.AIR.getDefaultState(), 3);
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).swing(Hand.OFF_HAND, true);
			}
			world.setBlockState(new BlockPos((int) x, (int) y, (int) z), MexicanSunflowerFlowerPotBlock.block.getDefaultState(), 3);
		}
	}

	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		PlayerEntity entity = event.getPlayer();
		if (event.getHand() != entity.getActiveHand())
			return;
		int i = event.getPos().getX();
		int j = event.getPos().getY();
		int k = event.getPos().getZ();
		World world = event.getWorld();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
