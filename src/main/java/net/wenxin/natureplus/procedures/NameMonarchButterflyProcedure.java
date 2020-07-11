package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.MonarchButterflyNetItem;
import net.wenxin.natureplus.entity.MonarchButterflyEntity;
import net.wenxin.natureplus.NatureplusModElements;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class NameMonarchButterflyProcedure extends NatureplusModElements.ModElement {
	public NameMonarchButterflyProcedure(NatureplusModElements instance) {
		super(instance, 648);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure NameMonarchButterfly!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity instanceof MonarchButterflyEntity.CustomEntity)
				&& (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(MonarchButterflyNetItem.block, (int) (1)).getItem()))) {
			entity.setCustomName(
					new StringTextComponent((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getDisplayName().getString())));
		}
	}

	@SubscribeEvent
	public void onEntitySpawned(EntityJoinWorldEvent event) {
		Entity entity = event.getEntity();
		double i = entity.getPosX();
		double j = entity.getPosY();
		double k = entity.getPosZ();
		World world = event.getWorld().getWorld();
		java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
