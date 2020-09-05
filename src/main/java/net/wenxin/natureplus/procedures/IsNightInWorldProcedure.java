package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.world.IWorld;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class IsNightInWorldProcedure extends NatureplusModElements.ModElement {
	public IsNightInWorldProcedure(NatureplusModElements instance) {
		super(instance, 758);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure IsNightInWorld!");
			return false;
		}
		IWorld world = (IWorld) dependencies.get("world");
		return (!(world.getWorld().isDaytime()));
	}
}
