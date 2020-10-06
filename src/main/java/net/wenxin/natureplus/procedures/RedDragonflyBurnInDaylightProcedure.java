package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class RedDragonflyBurnInDaylightProcedure extends NatureplusModElements.ModElement {
	public RedDragonflyBurnInDaylightProcedure(NatureplusModElements instance) {
		super(instance, 906);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure RedDragonflyBurnInDaylight!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure RedDragonflyBurnInDaylight!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			$_dependencies.put("world", world);
			DespawnPeacefulProcedure.executeProcedure($_dependencies);
		}
		if ((!(world.getWorld().isRemote))) {
			if (((world.getWorld().isDaytime()) && ((!(entity.isInWaterRainOrBubbleColumn()))
					&& (world.canBlockSeeSky(new BlockPos((int) (entity.getPosX()), (int) (entity.getPosY()), (int) (entity.getPosZ()))))))) {
				entity.setFire((int) 8);
			}
		}
	}
}
