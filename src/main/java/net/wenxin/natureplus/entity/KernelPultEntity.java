
package net.wenxin.natureplus.entity;

import net.wenxin.natureplus.procedures.SpadeRemoveKernelPultProcedure;
import net.wenxin.natureplus.procedures.KernelPultNaturalSpawnProcedure;
import net.wenxin.natureplus.procedures.DisablePushingOfMobsProcedure;
import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;
import net.wenxin.natureplus.item.SpikeItem;
import net.wenxin.natureplus.item.PeaItem;
import net.wenxin.natureplus.item.FrozenPeaItem;
import net.wenxin.natureplus.item.CornItem;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.MobRenderer;

import java.util.Map;
import java.util.HashMap;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableList;

@NatureplusModElements.ModElement.Tag
public class KernelPultEntity extends NatureplusModElements.ModElement {
	public static EntityType entity = null;
	public KernelPultEntity(NatureplusModElements instance) {
		super(instance, 176);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 0.8f)).build("kernel_pult")
						.setRegistryName("kernel_pult");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -13312, -3368704, new Item.Properties().group(PlantsVsZombiesTabItemGroup.tab))
				.setRegistryName("kernel_pult_spawn_egg"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 15, 1, 3));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> {
					int x = pos.getX();
					int y = pos.getY();
					int z = pos.getZ();
					return KernelPultNaturalSpawnProcedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world));
				});
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelKernelPult(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("natureplus:textures/kernel_pult.png");
				}
			};
		});
	}
	public static class CustomEntity extends AnimalEntity implements IRangedAttackMob {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 2;
			setNoAI(false);
			enablePersistence();
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new BreedGoal(this, 0.8));
			this.goalSelector.addGoal(2, new TemptGoal(this, 0, Ingredient.fromItems(Items.BONE_MEAL), false));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, RedDragonflyEntity.CustomEntity.class, true, true));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, MobEntity.class, 10, true, true, (entity) -> {
				return entity instanceof IMob && !(entity instanceof CreeperEntity);
			}));
			this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, (float) 6));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(5, new SwimGoal(this));
			this.goalSelector.addGoal(1, new RangedAttackGoal(this, 0, 20, 10) {
				@Override
				public boolean shouldContinueExecuting() {
					return this.shouldExecute();
				}
			});
		}

		@Override
		public boolean isBreedingItem(ItemStack stack) {
			if (stack == null)
				return false;
			if (new ItemStack(Items.BONE_MEAL, (int) (1)).getItem() == stack.getItem())
				return true;
			return false;
		}

		@Override
		public AgeableEntity createChild(AgeableEntity ageable) {
			return (CustomEntity) entity.create(this.world);
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.generic.death"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public boolean hitByEntity(Entity entity) {
			if (entity instanceof FrozenPeaItem.ArrowCustomEntity || entity instanceof CornItem.ArrowCustomEntity)
				return true;
			return false;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			Entity sourceentity = source.getTrueSource();
			if (source.getImmediateSource() instanceof PeaItem.ArrowCustomEntity)
				return sourceentity.canBeCollidedWith();
			if (source.getImmediateSource() instanceof SpikeItem.ArrowCustomEntity)
				return sourceentity.canBeCollidedWith();
			if (source.getImmediateSource() instanceof FrozenPeaItem.ArrowCustomEntity)
				return hitByEntity(sourceentity);
			if (source.getImmediateSource() instanceof CornItem.ArrowCustomEntity)
				return hitByEntity(sourceentity);
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("sourceentity", sourceentity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				SpadeRemoveKernelPultProcedure.executeProcedure($_dependencies);
			}
			return super.attackEntityFrom(source, amount);
		}

		@Override
		public void baseTick() {
			super.baseTick();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				DisablePushingOfMobsProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
			this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
		}

		public void attackEntityWithRangedAttack(LivingEntity target, float flval) {
			CornItem.shoot(this, target);
		}
	}

	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelKernelPult<T extends Entity> extends AgeableModel<T> {
		private final ModelRenderer main;
		private final ModelRenderer main_layer;
		private final ModelRenderer leaves;
		private final ModelRenderer head;
		private final ModelRenderer head_layer;
		private final ModelRenderer catapult;
		private final ModelRenderer catapult_arm1;
		private final ModelRenderer catapult_arm2;
		private final ModelRenderer catapult_arm3;
		private final ModelRenderer catapult_base;
		private final ModelRenderer corn_kernel;
		public ModelKernelPult() {
			textureWidth = 64;
			textureHeight = 64;
			main = new ModelRenderer(this);
			main.setRotationPoint(0.0F, 24.0F, 0.0F);
			main.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 8.0F, 0.0F, false);
			main_layer = new ModelRenderer(this);
			main_layer.setRotationPoint(0.0F, 0.0F, 0.0F);
			main.addChild(main_layer);
			main_layer.setTextureOffset(0, 41).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 8.0F, 0.25F, false);
			leaves = new ModelRenderer(this);
			leaves.setRotationPoint(0.0F, 0.0F, 0.0F);
			main.addChild(leaves);
			leaves.setTextureOffset(26, 26).addBox(-4.0F, -0.5F, -9.0F, 4.0F, 0.0F, 5.0F, 0.0F, false);
			leaves.setTextureOffset(18, 24).addBox(0.0F, -0.5F, -9.0F, 4.0F, 0.0F, 5.0F, 0.0F, false);
			leaves.setTextureOffset(0, 23).addBox(4.0F, -0.5F, -9.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);
			leaves.setTextureOffset(30, 0).addBox(4.0F, -0.5F, -4.0F, 5.0F, 0.0F, 4.0F, 0.0F, false);
			leaves.setTextureOffset(10, 29).addBox(4.0F, -0.5F, 0.0F, 5.0F, 0.0F, 4.0F, 0.0F, false);
			leaves.setTextureOffset(19, 0).addBox(4.0F, -0.5F, 4.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);
			leaves.setTextureOffset(23, 12).addBox(0.0F, -0.5F, 4.0F, 4.0F, 0.0F, 5.0F, 0.0F, false);
			leaves.setTextureOffset(10, 23).addBox(-4.0F, -0.5F, 4.0F, 4.0F, 0.0F, 5.0F, 0.0F, false);
			leaves.setTextureOffset(19, 19).addBox(-9.0F, -0.5F, 4.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);
			leaves.setTextureOffset(28, 5).addBox(-9.0F, -0.5F, 0.0F, 5.0F, 0.0F, 4.0F, 0.0F, false);
			leaves.setTextureOffset(0, 28).addBox(-9.0F, -0.5F, -4.0F, 5.0F, 0.0F, 4.0F, 0.0F, false);
			leaves.setTextureOffset(13, 13).addBox(-9.0F, -0.5F, -9.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 21.0F, 0.0F);
			head.setTextureOffset(0, 12).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 5.0F, 6.0F, 0.0F, false);
			head.setTextureOffset(20, 31).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
			head_layer = new ModelRenderer(this);
			head_layer.setRotationPoint(0.0F, 3.0F, 0.0F);
			head.addChild(head_layer);
			head_layer.setTextureOffset(0, 53).addBox(-3.0F, -7.0F, -3.0F, 6.0F, 5.0F, 6.0F, 0.25F, false);
			head_layer.setTextureOffset(25, 58).addBox(-2.0F, -9.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.25F, false);
			catapult = new ModelRenderer(this);
			catapult.setRotationPoint(0.0F, -5.0F, -0.5F);
			head.addChild(catapult);
			setRotationAngle(catapult, -0.6109F, 0.0F, 0.0F);
			catapult_arm1 = new ModelRenderer(this);
			catapult_arm1.setRotationPoint(0.0F, 0.0F, 0.0F);
			catapult.addChild(catapult_arm1);
			catapult_arm1.setTextureOffset(0, 12).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
			catapult_arm2 = new ModelRenderer(this);
			catapult_arm2.setRotationPoint(0.0F, -4.5F, 0.0F);
			catapult.addChild(catapult_arm2);
			catapult_arm2.setTextureOffset(36, 9).addBox(-0.5F, -0.5F, 0.5F, 1.0F, 1.0F, 4.0F, 0.0F, false);
			catapult_arm3 = new ModelRenderer(this);
			catapult_arm3.setRotationPoint(0.0F, -4.5F, 4.5F);
			catapult.addChild(catapult_arm3);
			setRotationAngle(catapult_arm3, 0.6109F, 0.0F, 0.0F);
			catapult_arm3.setTextureOffset(0, 36).addBox(-0.5F, -0.6F, -0.25F, 1.0F, 1.0F, 4.0F, 0.0F, false);
			catapult_base = new ModelRenderer(this);
			catapult_base.setRotationPoint(0.0F, -6.75F, 7.5F);
			catapult.addChild(catapult_base);
			setRotationAngle(catapult_base, 0.6109F, 0.0F, 0.0F);
			catapult_base.setTextureOffset(32, 31).addBox(-3.0F, -1.5F, -0.25F, 6.0F, 3.0F, 1.0F, 0.0F, false);
			catapult_base.setTextureOffset(0, 32).addBox(-3.0F, -1.5F, 4.75F, 6.0F, 3.0F, 1.0F, 0.0F, false);
			catapult_base.setTextureOffset(32, 35).addBox(2.0F, -1.5F, 0.75F, 1.0F, 3.0F, 4.0F, 0.0F, false);
			catapult_base.setTextureOffset(10, 33).addBox(-3.0F, -1.5F, 0.75F, 1.0F, 3.0F, 4.0F, 0.0F, false);
			catapult_base.setTextureOffset(31, 20).addBox(-2.0F, 0.5F, 0.75F, 4.0F, 1.0F, 4.0F, 0.0F, false);
			corn_kernel = new ModelRenderer(this);
			corn_kernel.setRotationPoint(0.0F, 0.5F, 2.25F);
			catapult_base.addChild(corn_kernel);
			setRotationAngle(corn_kernel, -0.4363F, 0.0F, 0.0F);
			corn_kernel.setTextureOffset(33, 14).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
			corn_kernel.setTextureOffset(0, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		}

		protected Iterable<ModelRenderer> getHeadParts() {
			return ImmutableList.of();
		}

		protected Iterable<ModelRenderer> getBodyParts() {
			return ImmutableList.of(this.head, this.main);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.catapult.rotateAngleX = -0.5F + MathHelper.cos(f2 * 0.25F) * (float) Math.PI * 0.05F;
			this.corn_kernel.rotateAngleX = -0.4363F + MathHelper.cos(f2 * 0.25F) * (float) Math.PI * 0.1F;
		}
	}
}
