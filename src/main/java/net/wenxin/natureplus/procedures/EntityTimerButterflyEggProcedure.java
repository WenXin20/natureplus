package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.entity.MonarchEggEntity;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class EntityTimerButterflyEggProcedure extends NatureplusModElements.ModElement {
	public EntityTimerButterflyEggProcedure(NatureplusModElements instance) {
		super(instance, 872);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure EntityTimerButterflyEgg!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity instanceof MonarchEggEntity.CustomEntity)) {
			entity.getPersistentData().putDouble("timer_insect", 8000);
		}
	}

	@SubscribeEvent
	public void onEntitySpawned(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		double i = entity.getPosX();
		double j = entity.getPosY();
		double k = entity.getPosZ();
		World world = event.getWorld().getWorld();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
