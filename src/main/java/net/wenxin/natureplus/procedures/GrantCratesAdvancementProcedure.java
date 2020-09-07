package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.block.StrippedPlantStemCrateBlock;
import net.wenxin.natureplus.block.StrippedAzureCrateBlock;
import net.wenxin.natureplus.block.PlantStemCrateBlock;
import net.wenxin.natureplus.block.AzureCrateBlock;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
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
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class GrantCratesAdvancementProcedure extends NatureplusModElements.ModElement {
	public GrantCratesAdvancementProcedure(NatureplusModElements instance) {
		super(instance, 309);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure GrantCratesAdvancement!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(AzureCrateBlock.block, (int) (1)))
				: false)
				|| (((entity instanceof PlayerEntity)
						? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(StrippedAzureCrateBlock.block, (int) (1)))
						: false)
						|| (((entity instanceof PlayerEntity)
								? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(PlantStemCrateBlock.block, (int) (1)))
								: false)
								|| ((entity instanceof PlayerEntity)
										? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(StrippedPlantStemCrateBlock.block, (int) (1)))
										: false))))) {
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("natureplus:crate_advancement"));
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

	@SubscribeEvent
	public void onItemCrafted(PlayerEvent.ItemCraftedEvent event) {
		Entity entity = event.getPlayer();
		World world = entity.world;
		double i = entity.getPosX();
		double j = entity.getPosY();
		double k = entity.getPosZ();
		ItemStack itemStack = event.getCrafting();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("itemstack", itemStack);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
