package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.entity.MonarchButterflyEntity;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class MonarchButterflySpawnProcedure extends NatureplusModElements.ModElement {
	public MonarchButterflySpawnProcedure(NatureplusModElements instance) {
		super(instance, 572);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure MonarchButterflySpawn!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure MonarchButterflySpawn!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure MonarchButterflySpawn!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure MonarchButterflySpawn!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure MonarchButterflySpawn!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((entity.getPersistentData().getDouble("timer_insect")) > 0)) {
			entity.getPersistentData().putDouble("timer_insect", ((entity.getPersistentData().getDouble("timer_insect")) - 1));
			if (((entity.getPersistentData().getDouble("timer_insect")) == 1)) {
				if (!entity.world.isRemote)
					entity.remove();
				if (world instanceof World && !world.getWorld().isRemote) {
					Entity entityToSpawn = new MonarchButterflyEntity.CustomEntity(MonarchButterflyEntity.entity, world.getWorld());
					entityToSpawn.setLocationAndAngles(x, y, z, world.getRandom().nextFloat() * 360F, 0);
					if (entityToSpawn instanceof MobEntity)
						((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
								SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
					world.addEntity(entityToSpawn);
				}
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
							(int) 10, 0.25, 0.25, 0.25, 0.5);
				}
				world.playSound(world.getWorld().isRemote ? Minecraft.getInstance().player : (PlayerEntity) null, new BlockPos(x, y, z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.turtle.egg_hatch")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
			}
		}
	}
}
