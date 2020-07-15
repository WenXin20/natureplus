package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.world.World;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class IsNightInWorldProcedure extends NatureplusModElements.ModElement {
	public IsNightInWorldProcedure(NatureplusModElements instance) {
		super(instance, 766);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure IsNightInWorld!");
			return false;
		}
		World world = (World) dependencies.get("world");
		return (!(world.isDaytime()));
	}
}
