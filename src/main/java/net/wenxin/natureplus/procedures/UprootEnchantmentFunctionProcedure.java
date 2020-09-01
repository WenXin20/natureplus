package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.enchantment.UprootEnchantmentEnchantment;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.tags.BlockTags;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.block.Block;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class UprootEnchantmentFunctionProcedure extends NatureplusModElements.ModElement {
	public UprootEnchantmentFunctionProcedure(NatureplusModElements instance) {
		super(instance, 687);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure UprootEnchantmentFunction!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure UprootEnchantmentFunction!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure UprootEnchantmentFunction!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure UprootEnchantmentFunction!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure UprootEnchantmentFunction!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((EnchantmentHelper.getEnchantmentLevel(UprootEnchantmentEnchantment.enchantment,
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY))) >= 1)
				&& ((!(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).isSolid())) && (((world
						.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == net.minecraft.block.material.Material.PLANTS)
						|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
								.getMaterial() == net.minecraft.block.material.Material.TALL_PLANTS)
								|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
										.getMaterial() == net.minecraft.block.material.Material.CACTUS)
										|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
												.getMaterial() == net.minecraft.block.material.Material.OCEAN_PLANT)
												|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
														.getMaterial() == net.minecraft.block.material.Material.SEA_GRASS)
														|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																.getMaterial() == net.minecraft.block.material.Material.BAMBOO_SAPLING)
																|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																		.getMaterial() == net.minecraft.block.material.Material.BAMBOO)
																		|| (BlockTags.getCollection()
																				.getOrCreate(new ResourceLocation(
																						("flowers").toLowerCase(java.util.Locale.ENGLISH)))
																				.contains(
																						(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																								.getBlock())))))))))))) {
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)), world.getWorld(),
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
				{
					ItemStack _ist = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
					if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
						_ist.shrink(1);
						_ist.setDamage(0);
					}
				}
			} else {
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			}
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
			}
		} else if ((((EnchantmentHelper.getEnchantmentLevel(UprootEnchantmentEnchantment.enchantment,
				((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY))) >= 1)
				&& ((!(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)).isSolid())) && (((world
						.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getMaterial() == net.minecraft.block.material.Material.PLANTS)
						|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
								.getMaterial() == net.minecraft.block.material.Material.TALL_PLANTS)
								|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
										.getMaterial() == net.minecraft.block.material.Material.CACTUS)
										|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
												.getMaterial() == net.minecraft.block.material.Material.OCEAN_PLANT)
												|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
														.getMaterial() == net.minecraft.block.material.Material.SEA_GRASS)
														|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																.getMaterial() == net.minecraft.block.material.Material.BAMBOO_SAPLING)
																|| (((world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																		.getMaterial() == net.minecraft.block.material.Material.BAMBOO)
																		|| (BlockTags.getCollection()
																				.getOrCreate(new ResourceLocation(
																						("flowers").toLowerCase(java.util.Locale.ENGLISH)))
																				.contains(
																						(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)))
																								.getBlock())))))))))))) {
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				Block.spawnDrops(world.getBlockState(new BlockPos((int) x, (int) y, (int) z)), world.getWorld(),
						new BlockPos((int) x, (int) y, (int) z));
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
				{
					ItemStack _ist = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY);
					if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
						_ist.shrink(1);
						_ist.setDamage(0);
					}
				}
			} else {
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			}
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).swing(Hand.OFF_HAND, true);
			}
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
