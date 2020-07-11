package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.NatureplusModElements;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class ThornsDamageProcedure extends NatureplusModElements.ModElement {
	public ThornsDamageProcedure(NatureplusModElements instance) {
		super(instance, 304);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ThornsDamage!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((!(entity instanceof ItemEntity))) {
			entity.attackEntityFrom(DamageSource.CACTUS, (float) 2);
		}
	}
}
