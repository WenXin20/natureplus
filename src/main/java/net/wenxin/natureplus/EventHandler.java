/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class NatureplusModElements.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser - New... and make sure to make the class
 * outside net.wenxin.natureplus as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.wenxin.natureplus;

import org.antlr.v4.runtime.misc.NotNull;

import net.wenxin.natureplus.entity.SunflowerEntity;
import net.wenxin.natureplus.entity.SnowPeaEntity;
import net.wenxin.natureplus.entity.RedDragonflyEntity;
import net.wenxin.natureplus.entity.PeashooterEntity;
import net.wenxin.natureplus.entity.KernelPultEntity;
import net.wenxin.natureplus.entity.CactusEntity;

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.MobEntity;

@NatureplusModElements.ModElement.Tag
public class EventHandler extends NatureplusModElements.ModElement {
	/**
	 * Do not remove this constructor
	 */
	public EventHandler(NatureplusModElements instance) {
		super(instance, 938);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onEntitySpawned(@NotNull final EntityJoinWorldEvent event) {
		if (!event.getWorld().isRemote) {
			if (event.getEntity() instanceof RedDragonflyEntity.CustomEntity
					|| event.getEntity() instanceof IMob && !(event.getEntity() instanceof CreeperEntity)) {
				MobEntity mob = (MobEntity) event.getEntity();
				mob.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(mob, SunflowerEntity.CustomEntity.class, false));
				mob.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(mob, PeashooterEntity.CustomEntity.class, false));
				mob.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(mob, SnowPeaEntity.CustomEntity.class, false));
				mob.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(mob, KernelPultEntity.CustomEntity.class, false));
				mob.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(mob, CactusEntity.CustomEntity.class, false));
			}
		}
	}

	@Override
	public void initElements() {
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}

	@Override
	public void serverLoad(FMLServerStartingEvent event) {
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void clientLoad(FMLClientSetupEvent event) {
	}
}
