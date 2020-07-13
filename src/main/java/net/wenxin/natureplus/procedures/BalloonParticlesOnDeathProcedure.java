package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.entity.Entity;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class BalloonParticlesOnDeathProcedure extends NatureplusModElements.ModElement {
	public BalloonParticlesOnDeathProcedure(NatureplusModElements instance) {
		super(instance, 717);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure BalloonParticlesOnDeath!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure BalloonParticlesOnDeath!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if (world instanceof ServerWorld) {
			((ServerWorld) world).spawnParticle(ParticleTypes.EXPLOSION, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (int) 1, 0.15,
					2.15, 0.15, 0.5);
		}
	}
}
