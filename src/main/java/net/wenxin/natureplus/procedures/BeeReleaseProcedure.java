package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.ButterflyNetItem;
import net.wenxin.natureplus.item.BeeButterflyNetItem;
import net.wenxin.natureplus.NatureplusModElements;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class BeeReleaseProcedure extends NatureplusModElements.ModElement {
	public BeeReleaseProcedure(NatureplusModElements instance) {
		super(instance, 668);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure BeeRelease!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure BeeRelease!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure BeeRelease!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure BeeRelease!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure BeeRelease!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((!(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).isSolid()))
				&& (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(BeeButterflyNetItem.block, (int) (1)).getItem()))) {
			if (!world.isRemote) {
				Entity entityToSpawn = new BeeEntity(EntityType.BEE, world);
				entityToSpawn.setLocationAndAngles((x + 0.5), (y + 1), (z + 0.5), world.rand.nextFloat() * 360F, 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
			world.playSound((PlayerEntity) null, x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:swoosh")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1);
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, (x + 0.5), (y + 1.25), (z + 0.5), (int) 10, 0.25, 0.25, 0.25, 1);
			}
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).swingArm(Hand.MAIN_HAND);
			}
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).inventory.clearMatchingItems(
							p -> ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem() == p
									.getItem(),
							(int) 1);
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(ButterflyNetItem.block, (int) (1));
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			}
		} else if (((!(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).isSolid()))
				&& (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(BeeButterflyNetItem.block, (int) (1)).getItem()))) {
			if (!world.isRemote) {
				Entity entityToSpawn = new BeeEntity(EntityType.BEE, world);
				entityToSpawn.setLocationAndAngles((x + 0.5), (y + 1), (z + 0.5), world.rand.nextFloat() * 360F, 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
			world.playSound((PlayerEntity) null, x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:swoosh")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1);
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, (x + 0.5), (y + 1), (z + 0.5), (int) 10, 0.25, 0.25, 0.25, 1);
			}
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).swingArm(Hand.OFF_HAND);
			}
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).inventory.clearMatchingItems(
							p -> ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getItem() == p
									.getItem(),
							(int) 1);
				if (entity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(ButterflyNetItem.block, (int) (1));
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) entity), _setstack);
				}
			}
		}
	}
}
