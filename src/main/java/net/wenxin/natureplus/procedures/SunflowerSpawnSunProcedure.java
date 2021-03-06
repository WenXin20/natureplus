package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.SunItem;
import net.wenxin.natureplus.entity.SunflowerEntity;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.Entity;

import java.util.function.Function;
import java.util.Map;
import java.util.Comparator;

@NatureplusModElements.ModElement.Tag
public class SunflowerSpawnSunProcedure extends NatureplusModElements.ModElement {
	public SunflowerSpawnSunProcedure(NatureplusModElements instance) {
		super(instance, 638);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure SunflowerSpawnSun!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure SunflowerSpawnSun!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure SunflowerSpawnSun!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure SunflowerSpawnSun!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure SunflowerSpawnSun!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity.getPersistentData().getDouble("timer_plant")) <= 1)
				&& ((entity instanceof SunflowerEntity.CustomEntity) && (world.getWorld().isDaytime())))) {
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
					ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (entity.getPosX()), ((entity.getPosY()) + 1.2), (entity.getPosZ()),
							new ItemStack(SunItem.block, (int) (1)));
					entityToSpawn.setPickupDelay((int) 10);
					world.addEntity(entityToSpawn);
				}
				entity.getPersistentData().putDouble("timer_plant", 2000);
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ())),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("natureplus:sunflower_collect")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					world.getWorld().playSound((entity.getPosX()), (entity.getPosY()), (entity.getPosZ()),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
									.getValue(new ResourceLocation("natureplus:sunflower_collect")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
				}
			}
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.FLAME, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (int) 5, 0.1,
						0.1, 0.1, 0.01);
			}
		} else if (((entity.getPersistentData().getDouble("timer_plant")) > 1)) {
			entity.getPersistentData().putDouble("timer_plant", ((entity.getPersistentData().getDouble("timer_plant")) - 1));
		}
	}
}
