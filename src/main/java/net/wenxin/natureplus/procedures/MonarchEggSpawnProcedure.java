package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.entity.MonarchEggEntity;
import net.wenxin.natureplus.entity.MonarchButterflyEntity;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.tags.ItemTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class MonarchEggSpawnProcedure extends NatureplusModElements.ModElement {
	public MonarchEggSpawnProcedure(NatureplusModElements instance) {
		super(instance, 563);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure MonarchEggSpawn!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				System.err.println("Failed to load dependency sourceentity for procedure MonarchEggSpawn!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure MonarchEggSpawn!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure MonarchEggSpawn!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure MonarchEggSpawn!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure MonarchEggSpawn!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity.getPersistentData().getDouble("timer_insect")) > 1) && ((entity instanceof MonarchButterflyEntity.CustomEntity)
				&& (ItemTags.getCollection().getOrCreate(new ResourceLocation(("flowers").toLowerCase(java.util.Locale.ENGLISH)))
						.contains(((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
								.getItem()))))) {
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.SMOKE, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (int) 5, 0.1,
						0.1, 0.1, 0.025);
			}
		}
		if (((entity.getPersistentData().getDouble("timer_insect")) <= 1)) {
			if (((entity instanceof MonarchButterflyEntity.CustomEntity)
					&& (ItemTags.getCollection().getOrCreate(new ResourceLocation(("flowers").toLowerCase(java.util.Locale.ENGLISH)))
							.contains(((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getItem())))) {
				entity.getPersistentData().putDouble("timer_insect", 2000);
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new MonarchEggEntity.CustomEntity(MonarchEggEntity.entity, world.getWorld());
					entityToSpawn.setLocationAndAngles((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
							world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
					world.addEntity(entityToSpawn);
				}
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.HEART, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (int) 5,
							0.25, 0.25, 0.25, 0.5);
				}
				if ((!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false))) {
					if (sourceentity instanceof PlayerEntity)
						((PlayerEntity) sourceentity).inventory.clearMatchingItems(
								p -> ((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
										.getItem() == p.getItem(),
								(int) 1);
				}
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.turtle.lay_egg")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.turtle.lay_egg")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
				}
				if (sourceentity instanceof ServerPlayerEntity) {
					Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) sourceentity).server).getAdvancementManager()
							.getAdvancement(new ResourceLocation("natureplus:butterfly_advancement"));
					AdvancementProgress _ap = ((ServerPlayerEntity) sourceentity).getAdvancements().getProgress(_adv);
					if (!_ap.isDone()) {
						Iterator _iterator = _ap.getRemaningCriteria().iterator();
						while (_iterator.hasNext()) {
							String _criterion = (String) _iterator.next();
							((ServerPlayerEntity) sourceentity).getAdvancements().grantCriterion(_adv, _criterion);
						}
					}
				}
				if (sourceentity instanceof LivingEntity) {
					((LivingEntity) sourceentity).swing(Hand.MAIN_HAND, true);
				}
			} else if (((entity instanceof MonarchButterflyEntity.CustomEntity)
					&& (ItemTags.getCollection().getOrCreate(new ResourceLocation(("flowers").toLowerCase(java.util.Locale.ENGLISH)))
							.contains(((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemOffhand() : ItemStack.EMPTY)
									.getItem())))) {
				entity.getPersistentData().putDouble("timer_insect", 2000);
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new MonarchEggEntity.CustomEntity(MonarchEggEntity.entity, world.getWorld());
					entityToSpawn.setLocationAndAngles((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
							world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
					world.addEntity(entityToSpawn);
				}
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.HEART, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (int) 5,
							0.25, 0.25, 0.25, 0.5);
				}
				if ((!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false))) {
					if (sourceentity instanceof PlayerEntity)
						((PlayerEntity) sourceentity).inventory.clearMatchingItems(
								p -> ((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemOffhand() : ItemStack.EMPTY)
										.getItem() == p.getItem(),
								(int) 1);
				}
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.turtle.lay_egg")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					world.getWorld().playSound(x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.turtle.lay_egg")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
				}
				if (sourceentity instanceof ServerPlayerEntity) {
					Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) sourceentity).server).getAdvancementManager()
							.getAdvancement(new ResourceLocation("natureplus:butterfly_advancement"));
					AdvancementProgress _ap = ((ServerPlayerEntity) sourceentity).getAdvancements().getProgress(_adv);
					if (!_ap.isDone()) {
						Iterator _iterator = _ap.getRemaningCriteria().iterator();
						while (_iterator.hasNext()) {
							String _criterion = (String) _iterator.next();
							((ServerPlayerEntity) sourceentity).getAdvancements().grantCriterion(_adv, _criterion);
						}
					}
				}
				if (sourceentity instanceof LivingEntity) {
					((LivingEntity) sourceentity).swing(Hand.OFF_HAND, true);
				}
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
		Map<String, Object> dependencies = new HashMap<>();
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
