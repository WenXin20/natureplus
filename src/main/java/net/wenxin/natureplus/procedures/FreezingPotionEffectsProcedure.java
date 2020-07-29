package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.potion.FreezingPotionPotion;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.Collection;

@NatureplusModElements.ModElement.Tag
public class FreezingPotionEffectsProcedure extends NatureplusModElements.ModElement {
	public FreezingPotionEffectsProcedure(NatureplusModElements instance) {
		super(instance, 652);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure FreezingPotionEffects!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
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
