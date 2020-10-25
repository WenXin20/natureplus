package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.entity.PVZZombieEntity;
import net.wenxin.natureplus.entity.ConeheadZombieEntity;
import net.wenxin.natureplus.entity.BucketheadZombieEntity;
import net.wenxin.natureplus.NatureplusModVariables;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.Difficulty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import java.util.function.Function;
import java.util.Map;
import java.util.Comparator;

@NatureplusModElements.ModElement.Tag
public class GraveSpawnZombiesProcedure extends NatureplusModElements.ModElement {
	public GraveSpawnZombiesProcedure(NatureplusModElements instance) {
		super(instance, 872);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure GraveSpawnZombies!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure GraveSpawnZombies!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure GraveSpawnZombies!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure GraveSpawnZombies!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "timer_grave")) <= 1)
				&& ((!(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).isSolid()))
						&& ((!(world.getDifficulty() == Difficulty.PEACEFUL))
								&& ((!(world.getWorld().isDaytime())) || ((world.getLight(new BlockPos((int) x, (int) y, (int) z))) <= 7)))))) {
			if (((((Entity) world
					.getEntitiesWithinAABB(PlayerEntity.class,
							new AxisAlignedBB(x - (32 / 2d), y - (32 / 2d), z - (32 / 2d), x + (32 / 2d), y + (32 / 2d), z + (32 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null)) != null) || (((Entity) world
							.getEntitiesWithinAABB(ServerPlayerEntity.class,
									new AxisAlignedBB(x - (32 / 2d), y - (32 / 2d), z - (32 / 2d), x + (32 / 2d), y + (32 / 2d), z + (32 / 2d)), null)
							.stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)) != null))) {
				if (!world.getWorld().isRemote) {
					BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
					TileEntity _tileEntity = world.getTileEntity(_bp);
					BlockState _bs = world.getBlockState(_bp);
					if (_tileEntity != null)
						_tileEntity.getTileData().putDouble("timer_grave", Math.round((Math.random() * 200)));
					world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
				}
				if ((!(world.getWorld().isRemote))) {
					NatureplusModVariables.i = (double) (Math.random() * 100);
					if (!world.getWorld().isRemote) {
						world.playSound(null, new BlockPos((int) (x + 0.5), (int) y, (int) (z + 0.5)),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("natureplus:grave_rumble")),
								SoundCategory.NEUTRAL, (float) 1, (float) 1);
					} else {
						world.getWorld().playSound((x + 0.5), y, (z + 0.5),
								(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
										.getValue(new ResourceLocation("natureplus:grave_rumble")),
								SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
					}
					if (((NatureplusModVariables.i) >= 60)) {
						if (world instanceof World && !world.getWorld().isRemote) {
							Entity entityToSpawn = new PVZZombieEntity.CustomEntity(PVZZombieEntity.entity, world.getWorld());
							entityToSpawn.setLocationAndAngles((x + 0.5), y, (z + 0.5), world.getRandom().nextFloat() * 360F, 0);
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
										SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
					} else if ((((NatureplusModVariables.i) >= 30) && (!((NatureplusModVariables.i) >= 60)))) {
						if (world instanceof World && !world.getWorld().isRemote) {
							Entity entityToSpawn = new ZombieEntity(EntityType.ZOMBIE, world.getWorld());
							entityToSpawn.setLocationAndAngles((x + 0.5), y, (z + 0.5), world.getRandom().nextFloat() * 360F, 0);
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
										SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
					} else if ((((NatureplusModVariables.i) >= 20) && (!((NatureplusModVariables.i) >= 30)))) {
						if (world instanceof World && !world.getWorld().isRemote) {
							Entity entityToSpawn = new ConeheadZombieEntity.CustomEntity(ConeheadZombieEntity.entity, world.getWorld());
							entityToSpawn.setLocationAndAngles((x + 0.5), y, (z + 0.5), world.getRandom().nextFloat() * 360F, 0);
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
										SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
					} else if ((((NatureplusModVariables.i) >= 10) && (!((NatureplusModVariables.i) >= 20)))) {
						if (world instanceof World && !world.getWorld().isRemote) {
							Entity entityToSpawn = new BucketheadZombieEntity.CustomEntity(BucketheadZombieEntity.entity, world.getWorld());
							entityToSpawn.setLocationAndAngles((x + 0.5), y, (z + 0.5), world.getRandom().nextFloat() * 360F, 0);
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
										SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
					} else {
						if (world instanceof World && !world.getWorld().isRemote) {
							Entity entityToSpawn = new PVZZombieEntity.CustomEntity(PVZZombieEntity.entity, world.getWorld());
							entityToSpawn.setLocationAndAngles((x + 0.5), y, (z + 0.5), world.getRandom().nextFloat() * 360F, 0);
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
										SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
					}
				}
			}
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, (x + 0.5), y, (z + 0.5), (int) 20, 0.5, 0.5, 0.5, 0.01);
			}
		} else if (((new Object() {
			public double getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getDouble(tag);
				return -1;
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "timer_grave")) > 1)) {
			if (!world.getWorld().isRemote) {
				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("timer_grave", ((new Object() {
						public double getValue(BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(new BlockPos((int) x, (int) y, (int) z), "timer_grave")) - 1));
				world.getWorld().notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
		}
	}
}
