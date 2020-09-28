package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.entity.RedDragonflyEntity;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.world.IWorld;
import net.minecraft.world.Difficulty;
import net.minecraft.entity.Entity;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class DespawnPeacefulProcedure extends NatureplusModElements.ModElement {
	public DespawnPeacefulProcedure(NatureplusModElements instance) {
		super(instance, 897);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure DespawnPeaceful!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure DespawnPeaceful!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if (((world.getDifficulty() == Difficulty.PEACEFUL) && (entity instanceof RedDragonflyEntity.CustomEntity))) {
			if (!entity.world.isRemote)
				entity.remove();
		}
	}
}
