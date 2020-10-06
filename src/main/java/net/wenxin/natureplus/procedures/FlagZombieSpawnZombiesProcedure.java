package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.entity.PVZZombieEntity;
import net.wenxin.natureplus.entity.FlagZombieEntity;
import net.wenxin.natureplus.entity.ConeheadZombieEntity;
import net.wenxin.natureplus.entity.BucketheadZombieEntity;
import net.wenxin.natureplus.NatureplusModVariables;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class FlagZombieSpawnZombiesProcedure extends NatureplusModElements.ModElement {
	public FlagZombieSpawnZombiesProcedure(NatureplusModElements instance) {
		super(instance, 865);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure FlagZombieSpawnZombies!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure FlagZombieSpawnZombies!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(world.getWorld().isRemote))) {
			if ((entity instanceof FlagZombieEntity.CustomEntity)) {
				NatureplusModVariables.i = (double) (Math.random() * 100);
				if (((NatureplusModVariables.i) <= 100)) {
					if (world instanceof World && !world.getWorld().isRemote) {
						Entity entityToSpawn = new PVZZombieEntity.CustomEntity(PVZZombieEntity.entity, world.getWorld());
						entityToSpawn.setLocationAndAngles((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
								world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof MobEntity)
							((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
									SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
						world.addEntity(entityToSpawn);
					}
					if (world instanceof World && !world.getWorld().isRemote) {
						Entity entityToSpawn = new PVZZombieEntity.CustomEntity(PVZZombieEntity.entity, world.getWorld());
						entityToSpawn.setLocationAndAngles((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
								world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof MobEntity)
							((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
									SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
						world.addEntity(entityToSpawn);
					}
					if (world instanceof World && !world.getWorld().isRemote) {
						Entity entityToSpawn = new ZombieEntity(EntityType.ZOMBIE, world.getWorld());
						entityToSpawn.setLocationAndAngles((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
								world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof MobEntity)
							((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
									SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
						world.addEntity(entityToSpawn);
					}
				}
				if (((NatureplusModVariables.i) <= 50)) {
					if (world instanceof World && !world.getWorld().isRemote) {
						Entity entityToSpawn = new ConeheadZombieEntity.CustomEntity(ConeheadZombieEntity.entity, world.getWorld());
						entityToSpawn.setLocationAndAngles((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
								world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof MobEntity)
							((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
									SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
						world.addEntity(entityToSpawn);
					}
					if (world instanceof World && !world.getWorld().isRemote) {
						Entity entityToSpawn = new ConeheadZombieEntity.CustomEntity(ConeheadZombieEntity.entity, world.getWorld());
						entityToSpawn.setLocationAndAngles((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
								world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof MobEntity)
							((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
									SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
						world.addEntity(entityToSpawn);
					}
				}
				if (((NatureplusModVariables.i) <= 30)) {
					if (world instanceof World && !world.getWorld().isRemote) {
						Entity entityToSpawn = new BucketheadZombieEntity.CustomEntity(BucketheadZombieEntity.entity, world.getWorld());
						entityToSpawn.setLocationAndAngles((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
								world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof MobEntity)
							((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
									SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
						world.addEntity(entityToSpawn);
					}
					if (world instanceof World && !world.getWorld().isRemote) {
						Entity entityToSpawn = new BucketheadZombieEntity.CustomEntity(BucketheadZombieEntity.entity, world.getWorld());
						entityToSpawn.setLocationAndAngles((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
								world.getRandom().nextFloat() * 360F, 0);
						if (entityToSpawn instanceof MobEntity)
							((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
									SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
						world.addEntity(entityToSpawn);
					}
				}
			}
		}
	}
}
