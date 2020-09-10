package net.wenxin.natureplus.entity;

import net.wenxin.natureplus.procedures.SpadeRemoveCactusProcedure;
import net.wenxin.natureplus.procedures.DisablePushingOfMobsProcedure;
import net.wenxin.natureplus.procedures.CactusNaturalSpawnProcedure;
import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;
import net.wenxin.natureplus.item.SpikeItem;
import net.wenxin.natureplus.item.PeaItem;
import net.wenxin.natureplus.item.FrozenPeaItem;
import net.wenxin.natureplus.item.CornItem;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
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
import net.minecraft.network.IPacket;
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
import net.minecraft.entity.Pose;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySize;
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
public class CactusEntity extends NatureplusModElements.ModElement {
	public static EntityType entity = null;
	public CactusEntity(NatureplusModElements instance) {
		super(instance, 177);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.5f, 1f)).build("cactus")
						.setRegistryName("cactus");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -16764160, -10092544, new Item.Properties().group(PlantsVsZombiesTabItemGroup.tab))
				.setRegistryName("cactus_spawn_egg"));
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
					return CactusNaturalSpawnProcedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world));
				});
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelCactus(), 0.4f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("natureplus:textures/cactus.png");
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
			experienceValue = 1;
			setNoAI(false);
			enablePersistence();
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
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
			this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, (float) 6));
			this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(6, new SwimGoal(this));
			this.goalSelector.addGoal(1, new RangedAttackGoal(this, 0, 40, 20) {
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

		public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
			return sizeIn.height * 0.8F;
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(SpikeItem.block, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:cactus_hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:cactus_death"));
		}

		protected void collideWithEntity(Entity entityIn) {
			entityIn.applyEntityCollision(this);
			if (entityIn instanceof LivingEntity && !(entityIn instanceof CactusEntity.CustomEntity)) {
				entityIn.attackEntityFrom(DamageSource.causeThornsDamage(this), 1.0F);
			}
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
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("sourceentity", sourceentity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				SpadeRemoveCactusProcedure.executeProcedure($_dependencies);
			}
			if (source == DamageSource.CACTUS)
				return false;
			if (!source.isMagicDamage() && !source.isFireDamage() && !source.isProjectile() && source.getImmediateSource() instanceof LivingEntity) {
				LivingEntity livingentity = (LivingEntity) source.getImmediateSource();
				if (!source.isExplosion()) {
					livingentity.attackEntityFrom(DamageSource.causeThornsDamage(this), 2.0F);
				}
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
			SpikeItem.shoot(this, target);
		}
	}

	// Made with Blockbench 3.6.5
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelCactus<T extends Entity> extends AgeableModel<T> {
		private final ModelRenderer main;
		private final ModelRenderer main_layer;
		private final ModelRenderer head;
		private final ModelRenderer mouth;
		private final ModelRenderer head_layer;
		private final ModelRenderer mouth_layer;
		private final ModelRenderer left_arm;
		private final ModelRenderer left_arm_layer;
		private final ModelRenderer right_arm;
		private final ModelRenderer right_arm_layer;
		public ModelCactus() {
			textureWidth = 64;
			textureHeight = 64;
			main = new ModelRenderer(this);
			main.setRotationPoint(0.0F, 24.0F, 0.0F);
			main.setTextureOffset(0, 0).addBox(-3.0F, -10.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);
			main_layer = new ModelRenderer(this);
			main_layer.setRotationPoint(-0.5F, 24.0F, 0.5F);
			main_layer.setTextureOffset(24, 0).addBox(-2.5F, -10.0F, -3.5F, 6.0F, 10.0F, 6.0F, 0.25F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 14.0F, 0.0F);
			head.setTextureOffset(0, 16).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);
			mouth = new ModelRenderer(this);
			mouth.setRotationPoint(0.0F, -1.5F, -3.0F);
			head.addChild(mouth);
			mouth.setTextureOffset(0, 28).addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
			head_layer = new ModelRenderer(this);
			head_layer.setRotationPoint(0.0F, 14.0F, 0.0F);
			head_layer.setTextureOffset(24, 16).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.25F, false);
			mouth_layer = new ModelRenderer(this);
			mouth_layer.setRotationPoint(0.0F, -1.5F, -3.0F);
			head_layer.addChild(mouth_layer);
			mouth_layer.setTextureOffset(13, 28).addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 3.0F, 0.25F, false);
			left_arm = new ModelRenderer(this);
			left_arm.setRotationPoint(3.0F, 15.5F, 0.0F);
			left_arm.setTextureOffset(0, 43).addBox(0.0F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F, false);
			left_arm.setTextureOffset(0, 35).addBox(2.0F, -5.5F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
			left_arm_layer = new ModelRenderer(this);
			left_arm_layer.setRotationPoint(2.75F, 15.5F, 0.0F);
			left_arm_layer.setTextureOffset(17, 43).addBox(0.25F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.25F, false);
			left_arm_layer.setTextureOffset(13, 35).addBox(2.25F, -5.5F, -1.5F, 3.0F, 4.0F, 3.0F, 0.25F, false);
			right_arm = new ModelRenderer(this);
			right_arm.setRotationPoint(-3.0F, 15.5F, 0.0F);
			right_arm.setTextureOffset(0, 58).addBox(-5.0F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F, false);
			right_arm.setTextureOffset(0, 50).addBox(-5.0F, -5.5F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);
			right_arm_layer = new ModelRenderer(this);
			right_arm_layer.setRotationPoint(-3.0F, 15.5F, 0.0F);
			right_arm_layer.setTextureOffset(17, 58).addBox(-5.0F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.25F, false);
			right_arm_layer.setTextureOffset(13, 50).addBox(-5.0F, -5.5F, -1.5F, 3.0F, 4.0F, 3.0F, 0.25F, false);
		}

		protected Iterable<ModelRenderer> getHeadParts() {
			return ImmutableList.of();
		}

		protected Iterable<ModelRenderer> getBodyParts() {
			return ImmutableList.of(this.head, this.head_layer, this.main, this.main_layer, this.left_arm, this.left_arm_layer, this.right_arm,
					this.right_arm_layer);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.head_layer.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head_layer.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.right_arm_layer.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
			this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
			this.left_arm_layer.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		}
	}
}
