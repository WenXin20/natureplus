package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.MonarchCocoonJarItem;
import net.wenxin.natureplus.item.EmptyJarItem;
import net.wenxin.natureplus.entity.MonarchCocoonEntity;
import net.wenxin.natureplus.NatureplusModElements;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class EmptyJarCaptureMonarchCocoonProcedure extends NatureplusModElements.ModElement {
	public EmptyJarCaptureMonarchCocoonProcedure(NatureplusModElements instance) {
		super(instance, 538);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure EmptyJarCaptureMonarchCocoon!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			System.err.println("Failed to load dependency sourceentity for procedure EmptyJarCaptureMonarchCocoon!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure EmptyJarCaptureMonarchCocoon!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure EmptyJarCaptureMonarchCocoon!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure EmptyJarCaptureMonarchCocoon!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure EmptyJarCaptureMonarchCocoon!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(EmptyJarItem.block, (int) (1)).getItem()) && (entity instanceof MonarchCocoonEntity.CustomEntity))) {
			world.playSound((PlayerEntity) null, x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.bottle.fill")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1);
			if (sourceentity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(MonarchCocoonJarItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
			}
			if (!entity.world.isRemote)
				entity.remove();
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (int) 5, 0.1,
						0.1, 0.1, 0.1);
			}
			if ((!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false))) {
				if (sourceentity instanceof PlayerEntity)
					((PlayerEntity) sourceentity).inventory.clearMatchingItems(
							p -> ((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
									.getItem() == p.getItem(),
							(int) 1);
			}
			if (sourceentity instanceof LivingEntity) {
				((LivingEntity) sourceentity).swingArm(Hand.MAIN_HAND);
			}
		} else if (((((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(EmptyJarItem.block, (int) (1)).getItem()) && (entity instanceof MonarchCocoonEntity.CustomEntity))) {
			world.playSound((PlayerEntity) null, x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.bottle.fill")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1);
			if (sourceentity instanceof PlayerEntity) {
				ItemStack _setstack = new ItemStack(MonarchCocoonJarItem.block, (int) (1));
				_setstack.setCount((int) 1);
				ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
			}
			if (!entity.world.isRemote)
				entity.remove();
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (int) 5, 0.1,
						0.1, 0.1, 0.1);
			}
			if ((!((sourceentity instanceof PlayerEntity) ? ((PlayerEntity) sourceentity).abilities.isCreativeMode : false))) {
				if (sourceentity instanceof PlayerEntity)
					((PlayerEntity) sourceentity).inventory.clearMatchingItems(
							p -> ((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemOffhand() : ItemStack.EMPTY)
									.getItem() == p.getItem(),
							(int) 1);
			}
			if (sourceentity instanceof LivingEntity) {
				((LivingEntity) sourceentity).swingArm(Hand.OFF_HAND);
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
		java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
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
