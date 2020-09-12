package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.SpadeItem;
import net.wenxin.natureplus.entity.SunflowerEntity;
import net.wenxin.natureplus.entity.MonarchButterflyEntity;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class StopTimerProcedure extends NatureplusModElements.ModElement {
	public StopTimerProcedure(NatureplusModElements instance) {
		super(instance, 591);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure StopTimer!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			if (!dependencies.containsKey("sourceentity"))
				System.err.println("Failed to load dependency sourceentity for procedure StopTimer!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure StopTimer!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure StopTimer!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure StopTimer!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure StopTimer!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(Items.POISONOUS_POTATO, (int) (1)).getItem())
				|| (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(SpadeItem.block, (int) (1)).getItem()))
				&& ((!(entity instanceof MonarchButterflyEntity.CustomEntity))
						&& ((!(entity instanceof SunflowerEntity.CustomEntity)) && ((entity.getPersistentData().getDouble("timer_insect")) > 0))))) {
			entity.getPersistentData().putDouble("timer_insect", 0);
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
			}
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.ANGRY_VILLAGER, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (int) 1,
						0.1, 0.1, 0.1, 0.05);
			}
			if (((!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false))
					&& (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getItem() == new ItemStack(Items.POISONOUS_POTATO, (int) (1)).getItem()))) {
				if (sourceentity instanceof PlayerEntity)
					((PlayerEntity) sourceentity).inventory.clearMatchingItems(
							p -> ((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getItem() == p.getItem(),
							(int) 1);
			} else if (((!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false))
					&& (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getItem() == new ItemStack(SpadeItem.block, (int) (1)).getItem()))) {
				{
					ItemStack _ist = ((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY);
					if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
						_ist.shrink(1);
						_ist.setDamage(0);
					}
				}
			}
		} else if ((((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(Items.POISONOUS_POTATO, (int) (1)).getItem())
				|| (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemOffhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(SpadeItem.block, (int) (1)).getItem()))
				&& ((!(entity instanceof MonarchButterflyEntity.CustomEntity))
						&& ((!(entity instanceof SunflowerEntity.CustomEntity)) && ((entity.getPersistentData().getDouble("timer_insect")) > 0))))) {
			entity.getPersistentData().putDouble("timer_insect", 0);
			if (!world.getWorld().isRemote) {
				world.playSound(null, new BlockPos((int) x, (int) y, (int) z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
			} else {
				world.getWorld().playSound(x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.fire.extinguish")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
			}
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.ANGRY_VILLAGER, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (int) 1,
						0.1, 0.1, 0.1, 0.05);
			}
			if (((!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false))
					&& (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemOffhand() : ItemStack.EMPTY)
							.getItem() == new ItemStack(Items.POISONOUS_POTATO, (int) (1)).getItem()))) {
				if (sourceentity instanceof PlayerEntity)
					((PlayerEntity) sourceentity).inventory.clearMatchingItems(
							p -> ((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemOffhand() : ItemStack.EMPTY)
									.getItem() == p.getItem(),
							(int) 1);
			} else if (((!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false))
					&& (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemOffhand() : ItemStack.EMPTY)
							.getItem() == new ItemStack(SpadeItem.block, (int) (1)).getItem()))) {
				{
					ItemStack _ist = ((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemOffhand() : ItemStack.EMPTY);
					if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
						_ist.shrink(1);
						_ist.setDamage(0);
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onRightClickEntity(PlayerInteractEvent.EntityInteract event) {
		Entity entity = event.getTarget();
		PlayerEntity sourceentity = event.getPlayer();
		if (event.getHand() != sourceentity.getActiveHand())
			return;
		int i = event.getPos().getX();
		int j = event.getPos().getY();
		int k = event.getPos().getZ();
		World world = event.getWorld();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("x", i);
		dependencies.put("y", j);
		dependencies.put("z", k);
		dependencies.put("world", world);
		dependencies.put("entity", entity);
		dependencies.put("sourceentity", sourceentity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
