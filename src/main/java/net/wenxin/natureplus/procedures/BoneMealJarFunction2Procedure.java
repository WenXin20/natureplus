package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.block.EmptyJarBlock;
import net.wenxin.natureplus.block.BoneMealJarBlock;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.Direction;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.BoneMealItem;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class BoneMealJarFunction2Procedure extends NatureplusModElements.ModElement {
	public BoneMealJarFunction2Procedure(NatureplusModElements instance) {
		super(instance, 939);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure BoneMealJarFunction2!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure BoneMealJarFunction2!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure BoneMealJarFunction2!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure BoneMealJarFunction2!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure BoneMealJarFunction2!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		BlockPos blockpos1 = (BlockPos) dependencies.get("blockpos1");
		if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(BoneMealJarBlock.block, (int) (1)).getItem()) && (!(entity.isSneaking())))) {
			if ((!(world.getWorld().isRemote))) {
				if (BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), world.getWorld(), new BlockPos((int) x, (int) y, (int) z))
						|| BoneMealItem.growSeagrass(new ItemStack(Items.BONE_MEAL), world.getWorld(), blockpos1, (Direction) null)) {
					System.out.println("Right-clicked");
					if (!world.getWorld().isRemote)
						world.getWorld().playEvent(2005, new BlockPos((int) x, (int) y, (int) z), 0);
					System.out.println("bone meal triggered");
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
					}
					if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
						{
							ItemStack _ist = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
							if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
								_ist.shrink(1);
								_ist.setDamage(0);
							}
						}
						((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
								.putDouble("damageTaken",
										((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
												.getOrCreateTag().getDouble("damageTaken")) + 1));
						if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
								.getDouble("damageTaken")) == 27)) {
							if (entity instanceof LivingEntity) {
								ItemStack _setstack = new ItemStack(EmptyJarBlock.block, (int) (1));
								_setstack.setCount((int) 1);
								((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
								if (entity instanceof ServerPlayerEntity)
									((ServerPlayerEntity) entity).inventory.markDirty();
							}
						}
					}
					if (!world.getWorld().isRemote) {
						world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.bottle.empty")),
								SoundCategory.NEUTRAL, (float) 1, (float) 1);
					} else {
						world.getWorld().playSound(x, y, z,
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.bottle.empty")),
								SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
					}
				}
			}
		}
		if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(BoneMealJarBlock.block, (int) (1)).getItem()) && (!(entity.isSneaking())))) {
			if ((!(world.getWorld().isRemote))) {
				if (BoneMealItem.applyBonemeal(new ItemStack(Items.BONE_MEAL), world.getWorld(), new BlockPos((int) x, (int) y, (int) z))
						|| BoneMealItem.growSeagrass(new ItemStack(Items.BONE_MEAL), world.getWorld(), blockpos1, (Direction) null)) {
					System.out.println("Right-clicked");
					if (!world.getWorld().isRemote)
						world.getWorld().playEvent(2005, new BlockPos((int) x, (int) y, (int) z), 0);
					System.out.println("bone meal triggered");
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).swing(Hand.OFF_HAND, true);
					}
					if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
						{
							ItemStack _ist = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY);
							if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
								_ist.shrink(1);
								_ist.setDamage(0);
							}
						}
						((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getOrCreateTag()
								.putDouble("damageTaken",
										((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
												.getOrCreateTag().getDouble("damageTaken")) + 1));
						if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getOrCreateTag()
								.getDouble("damageTaken")) == 27)) {
							if (entity instanceof LivingEntity) {
								ItemStack _setstack = new ItemStack(EmptyJarBlock.block, (int) (1));
								_setstack.setCount((int) 1);
								((LivingEntity) entity).setHeldItem(Hand.OFF_HAND, _setstack);
								if (entity instanceof ServerPlayerEntity)
									((ServerPlayerEntity) entity).inventory.markDirty();
							}
						}
					}
					if (!world.getWorld().isRemote) {
						world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.bottle.empty")),
								SoundCategory.NEUTRAL, (float) 1, (float) 1);
					} else {
						world.getWorld().playSound(x, y, z,
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.bottle.empty")),
								SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
					}
				}
			}
		}

	}

	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		PlayerEntity entity = event.getPlayer();
		BlockPos blockpos = event.getPos();
		BlockPos blockpos1 = blockpos.offset(event.getFace());
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
		dependencies.put("blockpos1", blockpos1);
		this.executeProcedure(dependencies);
	}
}
