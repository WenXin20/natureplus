package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.ConeHelmetItem;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Map;
import java.util.Iterator;

@NatureplusModElements.ModElement.Tag
public class ConeHatGrantAdvancementProcedure extends NatureplusModElements.ModElement {
	public ConeHatGrantAdvancementProcedure(NatureplusModElements instance) {
		super(instance, 682);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ConeHatGrantAdvancement!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((new ItemStack(ConeHelmetItem.helmet, (int) (1))
				.getItem() == ((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).inventory.armorInventory.get(3) : ItemStack.EMPTY)
						.getItem())) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("natureplus:cone_hat"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
		}
	}
}
