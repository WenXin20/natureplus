package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.MexicanSunflowerSeedsItem;
import net.wenxin.natureplus.block.MexicanSunflowerCropStage1Block;
import net.wenxin.natureplus.NatureplusModElements;

import java.util.Map;
import java.util.Iterator;

@NatureplusModElements.ModElement.Tag
public class PlantMexicanSunflowerCropProcedure extends NatureplusModElements.ModElement {
	public PlantMexicanSunflowerCropProcedure(NatureplusModElements instance) {
		super(instance, 160);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure PlantMexicanSunflowerCrop!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure PlantMexicanSunflowerCrop!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure PlantMexicanSunflowerCrop!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure PlantMexicanSunflowerCrop!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure PlantMexicanSunflowerCrop!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((world.isAirBlock(new BlockPos((int) x, (int) (y + 1), (int) z)))
				&& ((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == Blocks.FARMLAND.getDefaultState().getBlock()))) {
			world.setBlockState(new BlockPos((int) x, (int) (y + 1), (int) z), MexicanSunflowerCropStage1Block.block.getDefaultState(), 3);
			if (entity instanceof ServerPlayerEntity) {
				Advancement _adv = ((MinecraftServer) ((ServerPlayerEntity) entity).server).getAdvancementManager()
						.getAdvancement(new ResourceLocation("natureplus:plantsunflowercrop"));
				AdvancementProgress _ap = ((ServerPlayerEntity) entity).getAdvancements().getProgress(_adv);
				if (!_ap.isDone()) {
					Iterator _iterator = _ap.getRemaningCriteria().iterator();
					while (_iterator.hasNext()) {
						String _criterion = (String) _iterator.next();
						((ServerPlayerEntity) entity).getAdvancements().grantCriterion(_adv, _criterion);
					}
				}
			}
			if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
					.getItem() == new ItemStack(MexicanSunflowerSeedsItem.block, (int) (1)).getItem())) {
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).swingArm(Hand.MAIN_HAND);
				}
			}
			if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
					.getItem() == new ItemStack(MexicanSunflowerSeedsItem.block, (int) (1)).getItem())) {
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).swingArm(Hand.OFF_HAND);
				}
			}
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(MexicanSunflowerSeedsItem.block, (int) (1)).getItem())) {
					if (entity instanceof PlayerEntity)
						((PlayerEntity) entity).inventory.clearMatchingItems(
								p -> ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
										.getItem() == p.getItem(),
								(int) 1);
				}
				if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
						.getItem() == new ItemStack(MexicanSunflowerSeedsItem.block, (int) (1)).getItem())) {
					if (entity instanceof PlayerEntity)
						((PlayerEntity) entity).inventory.clearMatchingItems(
								p -> ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemOffhand() : ItemStack.EMPTY)
										.getItem() == p.getItem(),
								(int) 1);
				}
			}
			world.playSound((PlayerEntity) null, x, y, z,
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.crop.plant")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1);
		}
	}
}
