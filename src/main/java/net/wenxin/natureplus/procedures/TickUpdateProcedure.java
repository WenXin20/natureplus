package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.entity.Entity;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class TickUpdateProcedure extends NatureplusModElements.ModElement {
	public TickUpdateProcedure(NatureplusModElements instance) {
		super(instance, 565);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure TickUpdate!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity.getPersistentData().getDouble("timer_insect")) > 0)) {
			entity.getPersistentData().putDouble("timer_insect", ((entity.getPersistentData().getDouble("timer_insect")) - 1));
		}
	}
}
