package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.world.IWorld;
import net.minecraft.world.Difficulty;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class IsPeacefulProcedure extends NatureplusModElements.ModElement {
	public IsPeacefulProcedure(NatureplusModElements instance) {
		super(instance, 837);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure IsPeaceful!");
			return false;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure IsPeaceful!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if ((entity instanceof PlayerEntity)) {
			return (!(world.getDifficulty() == Difficulty.PEACEFUL));
		}
		return (true);
	}
}
