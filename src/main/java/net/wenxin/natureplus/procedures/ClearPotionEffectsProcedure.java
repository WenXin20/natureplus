package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.NatureplusModElements;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class ClearPotionEffectsProcedure extends NatureplusModElements.ModElement {
	public ClearPotionEffectsProcedure(NatureplusModElements instance) {
		super(instance, 436);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ClearPotionEffects!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity instanceof LivingEntity)
			((LivingEntity) entity).clearActivePotions();
	}
}
