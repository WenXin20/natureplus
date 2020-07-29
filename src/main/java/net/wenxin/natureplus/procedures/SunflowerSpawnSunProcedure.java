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
import net.minecraft.client.Minecraft;

import java.util.Map;
import java.util.Comparator;

@NatureplusModElements.ModElement.Tag
public class SunflowerSpawnSunProcedure extends NatureplusModElements.ModElement {
	public SunflowerSpawnSunProcedure(NatureplusModElements instance) {
		super(instance, 619);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SunflowerSpawnSun!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure SunflowerSpawnSun!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure SunflowerSpawnSun!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure SunflowerSpawnSun!");
			return;
		}
		if (dependencies.get("world") == null) {
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
			if ((((world
					.getEntitiesWithinAABB(PlayerEntity.class,
							new AxisAlignedBB(x - 32 / 2, y - 32 / 2, z - 32 / 2, x + 32 / 2, y + 32 / 2, z + 32 / 2), null)
					.stream().sorted(Comparator.comparing(_ent -> _ent.getDistanceSq(x, y, z))).findFirst().orElse(null)) != null)
					|| ((world
							.getEntitiesWithinAABB(ServerPlayerEntity.class,
									new AxisAlignedBB(x - 32 / 2, y - 32 / 2, z - 32 / 2, x + 32 / 2, y + 32 / 2, z + 32 / 2), null)
							.stream().sorted(Comparator.comparing(_ent -> _ent.getDistanceSq(x, y, z))).findFirst().orElse(null)) != null))) {
				if (!world.getWorld().isRemote) {
					ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (entity.getPosX()), ((entity.getPosY()) + 1.2), (entity.getPosZ()),
							new ItemStack(SunItem.block, (int) (1)));
					entityToSpawn.setPickupDelay(10);
					world.addEntity(entityToSpawn);
				}
				entity.getPersistentData().putDouble("timer_plant", 2000);
				world.playSound(world.getWorld().isRemote ? Minecraft.getInstance().player : (PlayerEntity) null,
						new BlockPos((entity.getPosX()), (entity.getPosY()), (entity.getPosZ())),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:sunflower_collect")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
			}
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.FLAME, x, y, z, (int) 5, 0.1, 0.1, 0.1, 0.01);
			}
		} else if (((entity.getPersistentData().getDouble("timer_plant")) > 1)) {
			entity.getPersistentData().putDouble("timer_plant", ((entity.getPersistentData().getDouble("timer_plant")) - 1));
		}
		entity.setMotion(0, (entity.getMotion().getY()), 0);
	}
}
