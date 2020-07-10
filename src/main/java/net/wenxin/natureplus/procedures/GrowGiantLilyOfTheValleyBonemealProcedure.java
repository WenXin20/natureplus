package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.BoneMealJarItem;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.World;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.util.Hand;
import net.minecraft.server.MinecraftServer;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Map;
import java.util.Iterator;

@NatureplusModElements.ModElement.Tag
public class GrowGiantLilyOfTheValleyBonemealProcedure extends NatureplusModElements.ModElement {
	public GrowGiantLilyOfTheValleyBonemealProcedure(NatureplusModElements instance) {
		super(instance, 573);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure GrowGiantLilyOfTheValleyBonemeal!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure GrowGiantLilyOfTheValleyBonemeal!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure GrowGiantLilyOfTheValleyBonemeal!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure GrowGiantLilyOfTheValleyBonemeal!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure GrowGiantLilyOfTheValleyBonemeal!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((world.canBlockSeeSky(new BlockPos((int) x, (int) y, (int) z)))
				&& ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(BoneMealJarItem.block, (int) (1)).getItem())
						&& ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.LILY_OF_THE_VALLEY.getDefaultState()
								.getBlock())))) {
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 0.5), (z + 0.5), (int) 25, 0.25, 0.25, 0.25, 5);
			}
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).swingArm(Hand.MAIN_HAND);
			}
			if ((!(world.isRemote))) {
				if (!world.isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("natureplus", "giant_lily_of_the_valley1"));
					if (template != null) {
						template.addBlocksToWorldChunk(world, new BlockPos((int) (x - 4), (int) y, (int) (z - 2)), new PlacementSettings()
								.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk((ChunkPos) null).setIgnoreEntities(false));
					}
				}
				if (entity instanceof ServerPlayerEntity) {
					Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
							.getAdvancement(new ResourceLocation("natureplus:giant_lily_of_the_valley_advancement"));
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
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).inventory.clearMatchingItems(
							p -> ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem() == p
									.getItem(),
							(int) 1);
			}
		} else if (((world.canBlockSeeSky(new BlockPos((int) x, (int) y, (int) z)))
				&& ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(BoneMealJarItem.block, (int) (1)).getItem())
						&& ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.LILY_OF_THE_VALLEY.getDefaultState()
								.getBlock())))) {
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.HAPPY_VILLAGER, (x + 0.5), (y + 0.5), (z + 0.5), (int) 25, 0.25, 0.25, 0.25, 5);
			}
			if (entity instanceof LivingEntity) {
				((LivingEntity) entity).swingArm(Hand.OFF_HAND);
			}
			if ((!(world.isRemote))) {
				if (!world.isRemote) {
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("natureplus", "giant_lily_of_the_valley1"));
					if (template != null) {
						template.addBlocksToWorldChunk(world, new BlockPos((int) (x - 4), (int) y, (int) (z - 2)), new PlacementSettings()
								.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk((ChunkPos) null).setIgnoreEntities(false));
					}
				}
				if (entity instanceof ServerPlayerEntity) {
					Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
							.getAdvancement(new ResourceLocation("natureplus:giant_lily_of_the_valley_advancement"));
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
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				if (entity instanceof PlayerEntity)
					((PlayerEntity) entity).inventory.clearMatchingItems(
							p -> ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY).getItem() == p
									.getItem(),
							(int) 1);
			}
		}
	}

	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		PlayerEntity entity = event.getPlayer();
		if (event.getHand() != entity.getActiveHand())
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
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
