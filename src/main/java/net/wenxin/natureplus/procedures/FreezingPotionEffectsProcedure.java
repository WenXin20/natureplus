package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.potion.FreezingPotionPotion;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.Collection;

@NatureplusModElements.ModElement.Tag
public class FreezingPotionEffectsProcedure extends NatureplusModElements.ModElement {
	public FreezingPotionEffectsProcedure(NatureplusModElements instance) {
		super(instance, 657);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure FreezingPotionEffects!");
			return false;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure FreezingPotionEffects!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		IWorld world = (IWorld) dependencies.get("world");
		if ((new Object() {
			boolean check(LivingEntity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = _entity.getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == FreezingPotionPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check((LivingEntity) entity))) {
			entity.extinguish();
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				entity.setMotion(0, ((entity.getMotion().getY()) - 0.025), 0);
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 30, (int) 1, (true), (false)));
			}
			if (world instanceof ServerWorld) {
				((ServerWorld) world).spawnParticle(ParticleTypes.ENCHANTED_HIT, (entity.getPosX()), (entity.getPosY()), (entity.getPosZ()), (int) 5,
						((entity.getWidth()) / 2), ((entity.getHeight()) / 2), ((entity.getWidth()) / 2), 0.05);
			}
		}
		return (new Object() {
			boolean check(LivingEntity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = _entity.getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == FreezingPotionPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check((LivingEntity) entity));
	}
}
