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

import net.wenxin.natureplus.potion.FreezingPotionPotion;
import net.wenxin.natureplus.entity.SunflowerEntity;
import net.wenxin.natureplus.entity.SnowPeaEntity;
import net.wenxin.natureplus.entity.RedDragonflyEntity;
import net.wenxin.natureplus.entity.PeashooterEntity;
import net.wenxin.natureplus.entity.MonarchCaterpillarEntity;
import net.wenxin.natureplus.entity.MonarchButterflyEntity;
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

import net.minecraft.potion.Potions;
import net.minecraft.potion.Potion;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.Item;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.entity.ai.goal.TargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;

import java.util.List;

import com.google.common.collect.Lists;

@NatureplusModElements.ModElement.Tag
public class EventHandler extends NatureplusModElements.ModElement {
	private static final List<EventHandler.MixPredicate<Potion>> POTION_TYPE_CONVERSIONS = Lists.newArrayList();
	private static final List<EventHandler.MixPredicate<Item>> POTION_ITEM_CONVERSIONS = Lists.newArrayList();
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
			if (event.getEntity() instanceof PhantomEntity) {
				MobEntity mob = (MobEntity) event.getEntity();
				mob.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(mob, MonarchCaterpillarEntity.CustomEntity.class, false));
				mob.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(mob, MonarchButterflyEntity.CustomEntity.class, false));
			}
			if (event.getEntity() instanceof SpiderEntity || event.getEntity() instanceof CaveSpiderEntity) {
				MobEntity mob = (MobEntity) event.getEntity();
				mob.targetSelector.addGoal(5, new EventHandler.TargetGoal<>(mob, MonarchCaterpillarEntity.CustomEntity.class));
				mob.targetSelector.addGoal(5, new EventHandler.TargetGoal<>(mob, MonarchButterflyEntity.CustomEntity.class));
			}
		}
	}
	static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
		public TargetGoal(MobEntity mob, Class<T> classTarget) {
			super(mob, classTarget, true);
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state
		 * necessary for execution in this method as well.
		 */
		public boolean shouldExecute() {
			float f = this.goalOwner.getBrightness();
			return f >= 0.5F ? false : super.shouldExecute();
		}
	}
	@Override
	public void initElements() {
	}

	@SubscribeEvent
	public void init(FMLCommonSetupEvent event) {
		EventHandler.addMix(Potions.SLOWNESS, Items.SNOWBALL, FreezingPotionPotion.potionType);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void addMix(Potion potionBase, Item ingredient, Potion potionFinal) {
		POTION_TYPE_CONVERSIONS.add(new EventHandler.MixPredicate<>(potionBase, Ingredient.fromItems(ingredient), potionFinal));
	}
	static class MixPredicate<T extends net.minecraftforge.registries.ForgeRegistryEntry<T>> {
		private final net.minecraftforge.registries.IRegistryDelegate<T> input;
		private final Ingredient reagent;
		private final net.minecraftforge.registries.IRegistryDelegate<T> output;
		public MixPredicate(T inputIn, Ingredient reagentIn, T outputIn) {
			this.input = inputIn.delegate;
			this.reagent = reagentIn;
			this.output = outputIn.delegate;
		}
	}
	@Override
	public void serverLoad(FMLServerStartingEvent event) {
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void clientLoad(FMLClientSetupEvent event) {
	}
}
