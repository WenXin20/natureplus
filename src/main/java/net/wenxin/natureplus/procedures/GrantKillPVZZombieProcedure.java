package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.entity.PVZZombieEntity;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Map;
import java.util.Iterator;

@NatureplusModElements.ModElement.Tag
public class GrantKillPVZZombieProcedure extends NatureplusModElements.ModElement {
	public GrantKillPVZZombieProcedure(NatureplusModElements instance) {
		super(instance, 695);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure GrantKillPVZZombie!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				System.err.println("Failed to load dependency sourceentity for procedure GrantKillPVZZombie!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		if ((entity instanceof PVZZombieEntity.CustomEntity)) {
			if (sourceentity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) sourceentity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("natureplus:kill_pvz_zombie"));
				AdvancementProgress _ap = ((ServerPlayerEntity) sourceentity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) sourceentity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
		}
	}
}
