package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.entity.Entity;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class DisablePushingOfMobsProcedure extends NatureplusModElements.ModElement {
	public DisablePushingOfMobsProcedure(NatureplusModElements instance) {
		super(instance, 664);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure DisablePushingOfMobs!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.setMotion(0, (entity.getMotion().getY()), 0);
	}
}
