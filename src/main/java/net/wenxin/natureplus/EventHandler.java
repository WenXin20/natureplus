package net.wenxin.natureplus;

import org.antlr.v4.runtime.misc.NotNull;

import net.wenxin.natureplus.entity.SunflowerEntity;
import net.wenxin.natureplus.entity.SnowPeaEntity;
import net.wenxin.natureplus.entity.PeashooterEntity;
import net.wenxin.natureplus.entity.KernelPultEntity;
import net.wenxin.natureplus.entity.CactusEntity;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.monster.CreeperEntity;

public class EventHandler {
	@SubscribeEvent
	public static void onEntityAdded(EntityJoinWorldEvent event) {
		if (!event.getWorld().isRemote) {
			if (event.getEntity() instanceof IMob && !(event.getEntity() instanceof CreeperEntity)) {
				MobEntity mob = (MobEntity) event.getEntity();
				mob.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(mob, SunflowerEntity.CustomEntity.class, true, true));
				mob.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(mob, CactusEntity.CustomEntity.class, true, true));
				mob.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(mob, PeashooterEntity.CustomEntity.class, true, true));
				mob.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(mob, SnowPeaEntity.CustomEntity.class, true, true));
				mob.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(mob, KernelPultEntity.CustomEntity.class, true, true));
			}
		}
	}
}
