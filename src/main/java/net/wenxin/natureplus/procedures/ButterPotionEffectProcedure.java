package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.potion.ButterPotionPotion;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.Collection;

@NatureplusModElements.ModElement.Tag
public class ButterPotionEffectProcedure extends NatureplusModElements.ModElement {
	public ButterPotionEffectProcedure(NatureplusModElements instance) {
		super(instance, 673);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ButterPotionEffect!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((new Object() {
			boolean check(LivingEntity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = _entity.getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == ButterPotionPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check((LivingEntity) entity))) {
			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
				entity.setMotion(0, (entity.getMotion().getY()), 0);
				if (entity instanceof LivingEntity)
					((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 30, (int) 5, (true), (false)));
			}
		}
		return (new Object() {
			boolean check(LivingEntity _entity) {
				if (_entity instanceof LivingEntity) {
					Collection<EffectInstance> effects = _entity.getActivePotionEffects();
					for (EffectInstance effect : effects) {
						if (effect.getPotion() == ButterPotionPotion.potion)
							return true;
					}
				}
				return false;
			}
		}.check((LivingEntity) entity));
	}
}
