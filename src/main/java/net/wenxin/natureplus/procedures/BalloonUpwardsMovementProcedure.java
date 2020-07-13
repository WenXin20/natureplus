package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.potion.FreezingPotionPotion;
import net.wenxin.natureplus.potion.ButterPotionPotion;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.Collection;

@NatureplusModElements.ModElement.Tag
public class BalloonUpwardsMovementProcedure extends NatureplusModElements.ModElement {
	public BalloonUpwardsMovementProcedure(NatureplusModElements instance) {
		super(instance, 706);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure BalloonUpwardsMovement!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure BalloonUpwardsMovement!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		World world = (World) dependencies.get("world");
		if ((!(world.isRemote))) {
			if (((!(world.isRaining())) && ((!(world.isThundering())) && ((!(new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == FreezingPotionPotion.potion)
								return true;
						}
					}
					return false;
				}
			}.check())) && ((!(new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == ButterPotionPotion.potion)
								return true;
						}
					}
					return false;
				}
			}.check())) && ((!(new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.SLOWNESS)
								return true;
						}
					}
					return false;
				}
			}.check())) && (!(new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.SLOW_FALLING)
								return true;
						}
					}
					return false;
				}
			}.check())))))))) {
				if ((!(world.isAirBlock(new BlockPos((int) ((entity.getPosX()) - 1), (int) ((entity.getPosY()) + 3), (int) (entity.getPosZ())))))) {
					entity.setMotion((entity.getMotion().getX()), (entity.getMotion().getY()), (entity.getMotion().getZ()));
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.LEVITATION, (int) 60, (int) 2, (false), (false)));
				} else {
					entity.setMotion((((entity.getMotion().getX()) / 4) + (Math.random() - 0.5)), (entity.getMotion().getY()),
							(((entity.getMotion().getZ()) / 4) + (Math.random() - 0.5)));
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.LEVITATION, (int) 60, (int) 3, (false), (false)));
				}
			} else if (((world.isRaining()) && ((!(world.isThundering())) && ((!(new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == FreezingPotionPotion.potion)
								return true;
						}
					}
					return false;
				}
			}.check())) && ((!(new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == ButterPotionPotion.potion)
								return true;
						}
					}
					return false;
				}
			}.check())) && ((!(new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.SLOWNESS)
								return true;
						}
					}
					return false;
				}
			}.check())) && (!(new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.SLOW_FALLING)
								return true;
						}
					}
					return false;
				}
			}.check())))))))) {
				if ((!(world.isAirBlock(new BlockPos((int) ((entity.getPosX()) - 1), (int) ((entity.getPosY()) + 3), (int) (entity.getPosZ())))))) {
					entity.setMotion((entity.getMotion().getX()), (entity.getMotion().getY()), (entity.getMotion().getZ()));
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.LEVITATION, (int) 60, (int) 2, (false), (false)));
				} else if ((!(world
						.canBlockSeeSky(new BlockPos((int) ((entity.getPosX()) - 1), (int) ((entity.getPosY()) + 3), (int) (entity.getPosZ())))))) {
					entity.setMotion((Math.random() - 0.5), (entity.getMotion().getY()), (Math.random() - 0.5));
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.LEVITATION, (int) 60, (int) 2, (false), (false)));
				} else {
					entity.setMotion((((entity.getMotion().getX()) / 2) + ((Math.random() - 0.55) * 1.5)), (entity.getMotion().getY()),
							(((entity.getMotion().getZ()) / 2) + ((Math.random() - 0.55) * 1.5)));
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.LEVITATION, (int) 60, (int) 3, (false), (false)));
				}
			} else if (((world.isRaining()) && ((world.isThundering()) && ((!(new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == FreezingPotionPotion.potion)
								return true;
						}
					}
					return false;
				}
			}.check())) && ((!(new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == ButterPotionPotion.potion)
								return true;
						}
					}
					return false;
				}
			}.check())) && ((!(new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.SLOWNESS)
								return true;
						}
					}
					return false;
				}
			}.check())) && (!(new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.SLOW_FALLING)
								return true;
						}
					}
					return false;
				}
			}.check())))))))) {
				if ((!(world.isAirBlock(new BlockPos((int) ((entity.getPosX()) - 1), (int) ((entity.getPosY()) + 3), (int) (entity.getPosZ())))))) {
					entity.setMotion((entity.getMotion().getX()), (entity.getMotion().getY()), (entity.getMotion().getZ()));
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.LEVITATION, (int) 60, (int) 2, (false), (false)));
				} else if ((!(world
						.canBlockSeeSky(new BlockPos((int) ((entity.getPosX()) - 1), (int) ((entity.getPosY()) + 3), (int) (entity.getPosZ())))))) {
					entity.setMotion((Math.random() - 0.5), (entity.getMotion().getY()), (Math.random() - 0.5));
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.LEVITATION, (int) 60, (int) 2, (false), (false)));
				} else {
					entity.setMotion((((entity.getMotion().getX()) / 2) + ((Math.random() - 0.625) * 2.25)), (entity.getMotion().getY()),
							(((entity.getMotion().getZ()) / 2) + ((Math.random() - 0.625) * 2.25)));
					if (entity instanceof LivingEntity)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.LEVITATION, (int) 60, (int) 3, (false), (false)));
				}
			}
			if (((new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == FreezingPotionPotion.potion)
								return true;
						}
					}
					return false;
				}
			}.check()) || ((new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == ButterPotionPotion.potion)
								return true;
						}
					}
					return false;
				}
			}.check()) || ((new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.SLOWNESS)
								return true;
						}
					}
					return false;
				}
			}.check()) || (new Object() {
				boolean check() {
					if (entity instanceof LivingEntity) {
						Collection<EffectInstance> effects = ((LivingEntity) entity).getActivePotionEffects();
						for (EffectInstance effect : effects) {
							if (effect.getPotion() == Effects.SLOW_FALLING)
								return true;
						}
					}
					return false;
				}
			}.check()))))) {
				entity.setMotion((entity.getMotion().getX()), (-0.1), (entity.getMotion().getZ()));
			}
		}
	}
}