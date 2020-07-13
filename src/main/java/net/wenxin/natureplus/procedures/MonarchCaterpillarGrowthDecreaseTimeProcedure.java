package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.entity.MonarchCaterpillarEntity;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.tags.ItemTags;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.BlockItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class MonarchCaterpillarGrowthDecreaseTimeProcedure extends NatureplusModElements.ModElement {
	public MonarchCaterpillarGrowthDecreaseTimeProcedure(NatureplusModElements instance) {
		super(instance, 705);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MonarchCaterpillarGrowthDecreaseTime!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			System.err.println("Failed to load dependency sourceentity for procedure MonarchCaterpillarGrowthDecreaseTime!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MonarchCaterpillarGrowthDecreaseTime!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MonarchCaterpillarGrowthDecreaseTime!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MonarchCaterpillarGrowthDecreaseTime!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MonarchCaterpillarGrowthDecreaseTime!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((((ItemTags.getCollection().getOrCreate(new ResourceLocation(("minecraft:leaves").toLowerCase(java.util.Locale.ENGLISH)))
				.contains(((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()))
				|| ((ItemTags.getCollection().getOrCreate(new ResourceLocation(("minecraft:flowers").toLowerCase(java.util.Locale.ENGLISH))).contains(
						((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY).getItem()))
						|| ((/* @BlockState */(new Object() {
							public BlockState toBlock(ItemStack _stk) {
								if (_stk.getItem() instanceof BlockItem) {
									return ((BlockItem) _stk.getItem()).getBlock().getDefaultState();
								}
								return Blocks.AIR.getDefaultState();
							}
						}.toBlock(((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)))
								.getMaterial() == net.minecraft.block.material.Material.LEAVES) || ((/* @BlockState */(new Object() {
									public BlockState toBlock(ItemStack _stk) {
										if (_stk.getItem() instanceof BlockItem) {
											return ((BlockItem) _stk.getItem()).getBlock().getDefaultState();
										}
										return Blocks.AIR.getDefaultState();
									}
								}.toBlock(((sourceentity instanceof LivingEntity)
										? ((LivingEntity) sourceentity).getHeldItemMainhand()
										: ItemStack.EMPTY))).getMaterial() == net.minecraft.block.material.Material.PLANTS)
										|| (/* @BlockState */(new Object() {
											public BlockState toBlock(ItemStack _stk) {
												if (_stk.getItem() instanceof BlockItem) {
													return ((BlockItem) _stk.getItem()).getBlock().getDefaultState();
												}
												return Blocks.AIR.getDefaultState();
											}
										}.toBlock(((sourceentity instanceof LivingEntity)
												? ((LivingEntity) sourceentity).getHeldItemMainhand()
												: ItemStack.EMPTY))).getMaterial() == net.minecraft.block.material.Material.TALL_PLANTS)))))
				&& ((entity instanceof MonarchCaterpillarEntity.CustomEntity) && ((entity.getPersistentData().getDouble("timer_insect")) > 100)))) {
			entity.getPersistentData().putDouble("timer_insect", ((entity.getPersistentData().getDouble("timer_insect")) - 100));
			world.playSound((PlayerEntity) null, x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.eat")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1);
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (int) 5,
						0.25, 0.25, 0.25, 0.05);
			}
			if (sourceentity instanceof LivingEntity) {
				((LivingEntity) sourceentity).swingArm(Hand.MAIN_HAND);
			}
			if ((!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false))) {
				if (sourceentity instanceof PlayerEntity)
					((PlayerEntity) sourceentity).inventory.clearMatchingItems(
							p -> ((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getItem() == p.getItem(),
							(int) 1);
			}
		} else if ((((ItemTags.getCollection().getOrCreate(new ResourceLocation(("minecraft:leaves").toLowerCase(java.util.Locale.ENGLISH)))
				.contains(((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemOffhand() : ItemStack.EMPTY).getItem()))
				|| ((ItemTags.getCollection().getOrCreate(new ResourceLocation(("minecraft:flowers").toLowerCase(java.util.Locale.ENGLISH))).contains(
						((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemOffhand() : ItemStack.EMPTY).getItem()))
						|| ((/* @BlockState */(new Object() {
							public BlockState toBlock(ItemStack _stk) {
								if (_stk.getItem() instanceof BlockItem) {
									return ((BlockItem) _stk.getItem()).getBlock().getDefaultState();
								}
								return Blocks.AIR.getDefaultState();
							}
						}.toBlock(((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemOffhand() : ItemStack.EMPTY)))
								.getMaterial() == net.minecraft.block.material.Material.LEAVES) || ((/* @BlockState */(new Object() {
									public BlockState toBlock(ItemStack _stk) {
										if (_stk.getItem() instanceof BlockItem) {
											return ((BlockItem) _stk.getItem()).getBlock().getDefaultState();
										}
										return Blocks.AIR.getDefaultState();
									}
								}.toBlock(((sourceentity instanceof LivingEntity)
										? ((LivingEntity) sourceentity).getHeldItemOffhand()
										: ItemStack.EMPTY))).getMaterial() == net.minecraft.block.material.Material.PLANTS)
										|| (/* @BlockState */(new Object() {
											public BlockState toBlock(ItemStack _stk) {
												if (_stk.getItem() instanceof BlockItem) {
													return ((BlockItem) _stk.getItem()).getBlock().getDefaultState();
												}
												return Blocks.AIR.getDefaultState();
											}
										}.toBlock(((sourceentity instanceof LivingEntity)
												? ((LivingEntity) sourceentity).getHeldItemOffhand()
												: ItemStack.EMPTY))).getMaterial() == net.minecraft.block.material.Material.TALL_PLANTS)))))
				&& ((entity instanceof MonarchCaterpillarEntity.CustomEntity) && ((entity.getPersistentData().getDouble("timer_insect")) > 100)))) {
			entity.getPersistentData().putDouble("timer_insect", ((entity.getPersistentData().getDouble("timer_insect")) - 100));
			world.playSound((PlayerEntity) null, x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.eat")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1);
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (int) 5,
						0.25, 0.25, 0.25, 0.05);
			}
			if (sourceentity instanceof LivingEntity) {
				((LivingEntity) sourceentity).swingArm(Hand.OFF_HAND);
			}
			if ((!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false))) {
				if (sourceentity instanceof PlayerEntity)
					((PlayerEntity) sourceentity).inventory.clearMatchingItems(
							p -> ((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemOffhand() : ItemStack.EMPTY)
									.getItem() == p.getItem(),
							(int) 1);
			}
		}
	}

	@SubscribeEvent
	public void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
		Entity entity = event.getTarget();
		PlayerEntity sourceentity = event.getPlayer();
		if (event.getHand() != sourceentity.getActiveHand())
			return;
		int i = event.getPos().getX();
		int j = event.getPos().getY();
		int k = event.getPos().getZ();
		World world = event.getWorld();
		java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("sourceentity", sourceentity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
