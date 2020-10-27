package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.particle.IcicleBreakParticle;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class IcicleBreakTriggerProcedure extends NatureplusModElements.ModElement {
	public IcicleBreakTriggerProcedure(NatureplusModElements instance) {
		super(instance, 942);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure IcicleBreakTrigger!");
			return false;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure IcicleBreakTrigger!");
			return false;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure IcicleBreakTrigger!");
			return false;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure IcicleBreakTrigger!");
			return false;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((true)) {
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(IcicleBreakParticle.particle, x, y, z, (int) 5, 1, 1, 1, 1);
			}
		}
		return (true);
	}
}
