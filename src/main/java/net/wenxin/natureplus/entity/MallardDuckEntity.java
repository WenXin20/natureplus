
package net.wenxin.natureplus.entity;

import org.lwjgl.opengl.GL11;

import net.wenxin.natureplus.procedures.DuckNaturalSpawnProcedure;
import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.Tags.Items;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.network.IPacket;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.Pose;
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
import net.minecraft.block.BlockState;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableList;

@NatureplusModElements.ModElement.Tag
public class MallardDuckEntity extends NatureplusModElements.ModElement {
	public static EntityType entity = null;
	public MallardDuckEntity(NatureplusModElements instance) {
		super(instance, 133);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 0.7f)).build("mallard_duck")
						.setRegistryName("mallard_duck");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -6710887, -16751053, new Item.Properties().group(NaturePlusTabItemGroup.tab))
				.setRegistryName("mallard_duck"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 10, 1, 4));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> {
					int x = pos.getX();
					int y = pos.getY();
					int z = pos.getZ();
					return DuckNaturalSpawnProcedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world));
				});
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelDuck(), 0.3f) {
				public ResourceLocation getEntityTexture(Entity entity) {
					if (entity.getEntityId() % 2 == 0) {
						GL11.glGetInteger(GL11.GL_BLEND);
						return new ResourceLocation("natureplus:textures/mallard_duck_female.png");
					}
					return new ResourceLocation("natureplus:textures/mallard_duck_male.png");
				}

				public float handleRotationFloat(CustomEntity livingBase, float partialTicks) {
					float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
					float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
					return (MathHelper.sin(f) + 1.0F) * f1;
				}
			};
		});
	}
	public static class CustomEntity extends AnimalEntity {
		public float wingRotation;
		public float destPos;
		public float oFlapSpeed;
		public float oFlap;
		public float wingRotDelta = 1.0F;
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 1;
			setNoAI(false);
			enablePersistence();
			this.setPathPriority(PathNodeType.WATER, 0.0F);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new SwimGoal(this));
			this.goalSelector.addGoal(1, new BreedGoal(this, 1));
			this.goalSelector.addGoal(2, new TemptGoal(this, 1, Ingredient.fromTag(Items.SEEDS), false));
			this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setCallsForHelp());
			this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2, false));
			this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
			this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
			this.goalSelector.addGoal(6, new RandomSwimmingGoal(this, 1, 40));
			this.goalSelector.addGoal(7, new RandomWalkingGoal(this, 0.8));
			this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, (float) 6));
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
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:duck_ambient"));
		}

		@Override
		public void playStepSound(BlockPos pos, BlockState blockIn) {
			this.playSound((net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.chicken.step")), 0.15f,
					1);
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:duck_hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:duck_death"));
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}

		@Override
		public AgeableEntity createChild(AgeableEntity ageable) {
			return (CustomEntity) entity.create(this.world);
		}

		@Override
		public boolean isBreedingItem(ItemStack stack) {
			return stack.getItem().isIn(Items.SEEDS);
		}

		public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
			return this.isChild() ? sizeIn.height * 0.85F : sizeIn.height * 0.92F;
		}

		/**
		 * Called frequently so the entity can update its state every tick as required.
		 * For example, zombies and skeletons use this to react to sunlight and start to
		 * burn.
		 */
		public void livingTick() {
			super.livingTick();
			this.oFlap = this.wingRotation;
			this.oFlapSpeed = this.destPos;
			this.destPos = (float) ((double) this.destPos + (double) (this.onGround ? -1 : 4) * 0.3D);
			this.destPos = MathHelper.clamp(this.destPos, 0.0F, 1.0F);
			if (!this.onGround && this.wingRotDelta < 0.55F) {
				this.wingRotDelta = 0.55F;
			}
			this.wingRotDelta = (float) ((double) this.wingRotDelta * 0.9D);
			Vec3d vec3d = this.getMotion();
			if (!this.onGround && vec3d.y < 0.0D) {
				this.setMotion(vec3d.mul(1.0D, 0.75D, 1.0D));
			}
			this.wingRotation += this.wingRotDelta * 2.0F;
		}

		public boolean onLivingFall(float distance, float damageMultiplier) {
			return false;
		}
	}

	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelDuck<T extends Entity> extends AgeableModel<T> {
		private final ModelRenderer head;
		private final ModelRenderer bill;
		private final ModelRenderer body;
		private final ModelRenderer leftWing;
		private final ModelRenderer rightWing;
		private final ModelRenderer leftLeg;
		private final ModelRenderer rightLeg;
		public ModelDuck() {
			int i = 16;
			textureWidth = 64;
			textureHeight = 64;
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 16.0F, -3.0F);
			head.setTextureOffset(1, 1).addBox(-2.0F, -9.0F, -3.0F, 4.0F, 10.0F, 3.0F, 0.0F, false);
			bill = new ModelRenderer(this);
			bill.setRotationPoint(0.0F, -6.0F, -3.0F);
			head.addChild(bill);
			bill.setTextureOffset(16, 1).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 16.0F, 2.0F);
			body.setTextureOffset(1, 29).addBox(-3.0F, -2.0F, -5.0F, 6.0F, 6.0F, 9.0F, 0.0F, false);
			leftWing = new ModelRenderer(this);
			leftWing.setRotationPoint(3.0F, -2.0F, 0.0F);
			body.addChild(leftWing);
			leftWing.setTextureOffset(20, 15).addBox(0.0F, 0.0F, -4.0F, 1.0F, 5.0F, 8.0F, 0.0F, false);
			rightWing = new ModelRenderer(this);
			rightWing.setRotationPoint(-3.0F, -2.0F, 0.0F);
			body.addChild(rightWing);
			rightWing.setTextureOffset(1, 15).addBox(-1.0F, 0.0F, -4.0F, 1.0F, 5.0F, 8.0F, 0.0F, false);
			leftLeg = new ModelRenderer(this);
			leftLeg.setRotationPoint(1.5F, 19.0F, 3.0F);
			leftLeg.setTextureOffset(18, 45).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
			rightLeg = new ModelRenderer(this);
			rightLeg.setRotationPoint(-1.5F, 19.0F, 3.0F);
			rightLeg.setTextureOffset(1, 45).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
		}

		protected Iterable<ModelRenderer> getHeadParts() {
			return ImmutableList.of(this.head);
		}

		protected Iterable<ModelRenderer> getBodyParts() {
			return ImmutableList.of(this.body, this.rightLeg, this.leftLeg);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
			this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);
			this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
			boolean flag = !entityIn.onGround;
			if (flag) {
				this.rightWing.rotateAngleZ = -5.0F + (MathHelper.cos(ageInTicks * 2.0F) * (float) Math.PI * 0.3F);
				this.leftWing.rotateAngleZ = 5.0F + -(MathHelper.cos(ageInTicks * 2.0F) * (float) Math.PI * 0.3F);
			} else {
				this.rightWing.rotateAngleZ = 0.0F;
				this.leftWing.rotateAngleZ = 0.0F;
			}
		}
	}
}
