package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.KernelPultSeedPacketItem;
import net.wenxin.natureplus.entity.KernelPultEntity;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.server.MinecraftServer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Map;
import java.util.Iterator;

@NatureplusModElements.ModElement.Tag
public class KernelPultSpawnProcedure extends NatureplusModElements.ModElement {
	public KernelPultSpawnProcedure(NatureplusModElements instance) {
		super(instance, 656);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure KernelPultSpawn!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure KernelPultSpawn!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure KernelPultSpawn!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure KernelPultSpawn!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure KernelPultSpawn!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(KernelPultSeedPacketItem.block, (int) (1)).getItem())
				&& (!(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).isSolid())))) {
			if (!world.isRemote) {
				Entity entityToSpawn = new KernelPultEntity.CustomEntity(KernelPultEntity.entity, world);
				entityToSpawn.setLocationAndAngles((x + 0.5), (y + 1), (z + 0.5), world.rand.nextFloat() * 360F, 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("natureplus:spawn_kernel_pult_seed_packet"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
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
			}
			if ((!(world.isRemote))) {
				if ((Math.random() > 0.5)) {
					world.playSound((PlayerEntity) null, x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:plant")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else if (((Math.random() > 0) && (!(Math.random() > 0.5)))) {
					world.playSound((PlayerEntity) null, x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:plant2")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					world.playSound((PlayerEntity) null, x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:plant2")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				}
			}
		} else if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(KernelPultSeedPacketItem.block, (int) (1)).getItem())
				&& (!(world.getBlockState(new BlockPos((int) x, (int) (y + 1), (int) z)).isSolid())))) {
			if (!world.isRemote) {
				Entity entityToSpawn = new KernelPultEntity.CustomEntity(KernelPultEntity.entity, world);
				entityToSpawn.setLocationAndAngles((x + 0.5), (y + 1), (z + 0.5), world.rand.nextFloat() * 360F, 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("natureplus:spawn_kernel_pult_seed_packet"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
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
			}
			if ((!(world.isRemote))) {
				if ((Math.random() > 0.5)) {
					world.playSound((PlayerEntity) null, x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:plant")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else if (((Math.random() > 0) && (!(Math.random() > 0.5)))) {
					world.playSound((PlayerEntity) null, x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:plant2")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				} else {
					world.playSound((PlayerEntity) null, x, y, z,
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:plant2")),
							SoundCategory.NEUTRAL, (float) 1, (float) 1);
				}
			}
		}
	}
}
