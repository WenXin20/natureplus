package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.GreenBalloonItemItem;
import net.wenxin.natureplus.entity.GreenBalloonEntity;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.items.ItemHandlerHelper;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class RemoveGreenBalloonProcedure extends NatureplusModElements.ModElement {
	public RemoveGreenBalloonProcedure(NatureplusModElements instance) {
		super(instance, 740);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure RemoveGreenBalloon!");
			return;
		}
		if (dependencies.get("sourceentity") == null) {
			System.err.println("Failed to load dependency sourceentity for procedure RemoveGreenBalloon!");
			return;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure RemoveGreenBalloon!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure RemoveGreenBalloon!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure RemoveGreenBalloon!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure RemoveGreenBalloon!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		Entity sourceentity = (Entity) dependencies.get("sourceentity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(world.getWorld().isRemote))) {
			if (((entity instanceof GreenBalloonEntity.CustomEntity) && ((sourceentity.isSneaking())
					&& (((sourceentity instanceof LivingEntity) ? ((LivingEntity) sourceentity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getItem() == (ItemStack.EMPTY).getItem())))) {
				if (!entity.world.isRemote)
					entity.remove();
				world.playSound(world.getWorld().isRemote ? Minecraft.getInstance().player : (PlayerEntity) null, new BlockPos(x, y, z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:balloon_rub")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
				if (world instanceof ServerWorld) {
					((ServerWorld) world).spawnParticle(ParticleTypes.POOF, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (int) 10,
							0.15, 2.15, 0.15, 0.15);
				}
				if (sourceentity instanceof PlayerEntity) {
					ItemStack _setstack = new ItemStack(GreenBalloonItemItem.block, (int) (1));
					_setstack.setCount((int) 1);
					ItemHandlerHelper.giveItemToPlayer(((PlayerEntity) sourceentity), _setstack);
				}
				if (sourceentity instanceof LivingEntity) {
					((LivingEntity) sourceentity).swing(Hand.MAIN_HAND, true);
				}
			}
		}
	}
}
