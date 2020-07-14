package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.YellowBalloonItemItem;
import net.wenxin.natureplus.entity.YellowBalloonEntity;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class RemoveYellowBalloonProcedure extends NatureplusModElements.ModElement {
	public RemoveYellowBalloonProcedure(NatureplusModElements instance) {
		super(instance, 716);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure RemoveYellowBalloon!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			System.err.println("Failed to load dependency sourceentity for procedure RemoveYellowBalloon!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure RemoveYellowBalloon!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure RemoveYellowBalloon!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure RemoveYellowBalloon!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure RemoveYellowBalloon!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((!(world.isRemote))) {
			if (((entity instanceof YellowBalloonEntity.CustomEntity) && ((sourceentity.isSneaking())
					&& (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getItem() == (ItemStack.EMPTY).getItem())))) {
				if (!entity.world.isRemote)
					entity.remove();
				world.playSound((PlayerEntity) null, x, y, z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:balloon_rub")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.POOF, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (int) 10,
							0.15, 2.15, 0.15, 0.15);
				}
				if (sourceentity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(YellowBalloonItemItem.block, (int) (1));
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
				}
				if (sourceentity instanceof LivingEntity) {
					((LivingEntity) sourceentity).swingArm(Hand.MAIN_HAND);
				}
			}
		}
	}
}
