package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.SunflowerSeedPacketItem;
import net.wenxin.natureplus.item.SnowPeaSeedPacketItem;
import net.wenxin.natureplus.item.PeashooterSeedPacketItem;
import net.wenxin.natureplus.item.MonarchEggJarItem;
import net.wenxin.natureplus.item.KernelPultSeedPacketItem;
import net.wenxin.natureplus.item.CactusSeedPacketItem;
import net.wenxin.natureplus.entity.PVZZombieEntity;
import net.wenxin.natureplus.entity.MonarchCaterpillarEntity;
import net.wenxin.natureplus.entity.ConeheadZombieEntity;
import net.wenxin.natureplus.block.RandomVaseBlock;
import net.wenxin.natureplus.NatureplusModVariables;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Map;
import java.util.Iterator;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class RandomVaseRandomOutputProcedure extends NatureplusModElements.ModElement {
	public RandomVaseRandomOutputProcedure(NatureplusModElements instance) {
		super(instance, 635);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure RandomVaseRandomOutput!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure RandomVaseRandomOutput!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure RandomVaseRandomOutput!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure RandomVaseRandomOutput!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure RandomVaseRandomOutput!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(world.getWorld().isRemote))) {
			if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == RandomVaseBlock.block.getDefaultState().getBlock())
					&& ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))
							&& (!((EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH,
									((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY))) >= 1))))) {
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) (x + 0.5), (int) y, (int) (z + 0.5)),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:vase_breaking")),
							SoundCategory.NEUTRAL, (float) 2, (float) 1);
				} else {
					world.getWorld().playSound((x + 0.5), y, (z + 0.5),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:vase_breaking")),
							SoundCategory.NEUTRAL, (float) 2, (float) 1, false);
				}
				if (entity instanceof ServerPlayerEntity) {
					Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
							.getAdvancement(new ResourceLocation("natureplus:break_random_vase"));
					AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
					if (!_ap.isDone()) {
						Iterator _iterator = _ap.getRemaningCriteria().iterator();
						while (_iterator.hasNext()) {
							String _criterion = (String) _iterator.next();
							((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
						}
					}
				}
				NatureplusModVariables.i = (double) (Math.random() * 100);
				if (((NatureplusModVariables.i) >= 30)) {
					if (((NatureplusModVariables.i) >= 50)) {
						if (world instanceof World && !world.getWorld().isRemote) {
							Entity entityToSpawn = new PVZZombieEntity.CustomEntity(PVZZombieEntity.entity, world.getWorld());
							entityToSpawn.setLocationAndAngles((x + 0.5), y, (z + 0.5), world.getRandom().nextFloat() * 360F, 0);
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
										SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
					} else if ((((NatureplusModVariables.i) >= 30) && (!((NatureplusModVariables.i) >= 50)))) {
						if (world instanceof World && !world.getWorld().isRemote) {
							Entity entityToSpawn = new ConeheadZombieEntity.CustomEntity(ConeheadZombieEntity.entity, world.getWorld());
							entityToSpawn.setLocationAndAngles((x + 0.5), y, (z + 0.5), world.getRandom().nextFloat() * 360F, 0);
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
										SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
					} else if ((((NatureplusModVariables.i) >= 10) && (!((NatureplusModVariables.i) >= 30)))) {
						if (world instanceof World && !world.getWorld().isRemote) {
							Entity entityToSpawn = new ZombieEntity(EntityType.ZOMBIE, world.getWorld());
							entityToSpawn.setLocationAndAngles((x + 0.5), y, (z + 0.5), world.getRandom().nextFloat() * 360F, 0);
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
										SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
					} else {
						if (world instanceof World && !world.getWorld().isRemote) {
							Entity entityToSpawn = new MonarchCaterpillarEntity.CustomEntity(MonarchCaterpillarEntity.entity, world.getWorld());
							entityToSpawn.setLocationAndAngles((x + 0.5), y, (z + 0.5), world.getRandom().nextFloat() * 360F, 0);
							if (entityToSpawn instanceof MobEntity)
								((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
										SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
							world.addEntity(entityToSpawn);
						}
					}
				} else {
					if (((NatureplusModVariables.i) >= 60)) {
						if (!world.getWorld().isRemote) {
							ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (x + 0.5), y, (z + 0.5),
									new ItemStack(PeashooterSeedPacketItem.block, (int) (1)));
							entityToSpawn.setPickupDelay(10);
							world.addEntity(entityToSpawn);
						}
					} else if ((((NatureplusModVariables.i) >= 30) && (!((NatureplusModVariables.i) >= 60)))) {
						if (!world.getWorld().isRemote) {
							ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (x + 0.5), y, (z + 0.5),
									new ItemStack(SunflowerSeedPacketItem.block, (int) (1)));
							entityToSpawn.setPickupDelay(10);
							world.addEntity(entityToSpawn);
						}
					} else if ((((NatureplusModVariables.i) >= 20) && (!((NatureplusModVariables.i) >= 30)))) {
						if (!world.getWorld().isRemote) {
							ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (x + 0.5), y, (z + 0.5),
									new ItemStack(CactusSeedPacketItem.block, (int) (1)));
							entityToSpawn.setPickupDelay(10);
							world.addEntity(entityToSpawn);
						}
					} else if ((((NatureplusModVariables.i) >= 10) && (!((NatureplusModVariables.i) >= 20)))) {
						if (!world.getWorld().isRemote) {
							ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (x + 0.5), y, (z + 0.5),
									new ItemStack(KernelPultSeedPacketItem.block, (int) (1)));
							entityToSpawn.setPickupDelay(10);
							world.addEntity(entityToSpawn);
						}
					} else if ((((NatureplusModVariables.i) >= 5) && (!((NatureplusModVariables.i) >= 10)))) {
						if (!world.getWorld().isRemote) {
							ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (x + 0.5), y, (z + 0.5),
									new ItemStack(SnowPeaSeedPacketItem.block, (int) (1)));
							entityToSpawn.setPickupDelay(10);
							world.addEntity(entityToSpawn);
						}
					} else {
						if (!world.getWorld().isRemote) {
							ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (x + 0.5), y, (z + 0.5),
									new ItemStack(MonarchEggJarItem.block, (int) (1)));
							entityToSpawn.setPickupDelay(10);
							world.addEntity(entityToSpawn);
						}
					}
				}
			}
		}
	}

	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent event) {
		Entity entity = event.getPlayer();
		Map<String, Object> dependencies = new HashMap<>();
		dependencies.put("xpAmount", event.getExpToDrop());
		dependencies.put("x", (int) event.getPos().getX());
		dependencies.put("y", (int) event.getPos().getY());
		dependencies.put("z", (int) event.getPos().getZ());
		dependencies.put("px", entity.getPosX());
		dependencies.put("py", entity.getPosY());
		dependencies.put("pz", entity.getPosZ());
		dependencies.put("world", event.getWorld().getWorld());
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
