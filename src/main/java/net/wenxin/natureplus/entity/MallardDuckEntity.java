
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
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.Pose;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
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

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

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
			this.goalSelector.addGoal(2, new BreedGoal(this, 1));
			this.goalSelector.addGoal(3, new TemptGoal(this, 1, Ingredient.fromItems(new ItemStack(Items.WHEAT_SEEDS, (int) (1)).getItem()), false));
			this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
			this.targetSelector.addGoal(5, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
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
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
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
			if (stack == null)
				return false;
			if (new ItemStack(Items.WHEAT_SEEDS, (int) (1)).getItem() == stack.getItem())
				return true;
			return false;
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
	public static class ModelDuck extends AgeableModel<Entity> {
		private final ModelRenderer head;
		private final ModelRenderer beak;
		private final ModelRenderer body;
		private final ModelRenderer left_wing;
		private final ModelRenderer right_wing;
		private final ModelRenderer left_leg;
		private final ModelRenderer right_leg;
		public ModelDuck() {
			textureWidth = 64;
			textureHeight = 64;
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 15.0F, -4.0F);
			setRotationAngle(head, 0.0F, 0.0F, 0.0F);
			head.setTextureOffset(22, 15).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 8.0F, 3.0F, 0.0F, false);
			beak = new ModelRenderer(this);
			beak.setRotationPoint(0.0F, 0.0F, 0.0F);
			head.addChild(beak);
			beak.setTextureOffset(31, 31).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
			body = new ModelRenderer(this);
			body.setRotationPoint(0.0F, 16.0F, 0.0F);
			setRotationAngle(body, 0.0F, 0.0F, 0.0F);
			body.setTextureOffset(0, 0).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 6.0F, 9.0F, 0.0F, false);
			left_wing = new ModelRenderer(this);
			left_wing.setRotationPoint(3.0F, -2.0F, 0.0F);
			body.addChild(left_wing);
			left_wing.setTextureOffset(11, 20).addBox(0.0F, 0.0F, -3.0F, 1.0F, 5.0F, 9.0F, 0.0F, false);
			right_wing = new ModelRenderer(this);
			right_wing.setRotationPoint(-3.0F, -2.0F, 0.0F);
			body.addChild(right_wing);
			right_wing.setTextureOffset(0, 15).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 5.0F, 9.0F, 0.0F, false);
			left_leg = new ModelRenderer(this);
			left_leg.setRotationPoint(2.0F, 19.0F, 3.0F);
			left_leg.setTextureOffset(30, 5).addBox(-2.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
			right_leg = new ModelRenderer(this);
			right_leg.setRotationPoint(-1.0F, 19.0F, 3.0F);
			right_leg.setTextureOffset(21, 0).addBox(-2.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
		}

		protected Iterable<ModelRenderer> getHeadParts() {
			return ImmutableList.of(this.head, this.beak);
		}

		protected Iterable<ModelRenderer> getBodyParts() {
			return ImmutableList.of(this.body, this.right_leg, this.left_leg, this.right_wing, this.left_wing);
		}

		@Override
		public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green,
				float blue, float alpha) {
			head.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
			body.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
			left_leg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
			right_leg.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
			this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);
			this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
			this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
//			 this.right_wing.rotateAngleZ = 0.1F + -(MathHelper.cos(f2 * 2.0F) * (float) Math.PI * 0.18F);
//			 this.left_wing.rotateAngleZ = -0.1F + (MathHelper.cos(f2 * 0.8F) * (float) Math.PI * 0.14F);

			boolean flag = entityIn.getMotion().lengthSquared() < 0.5E-7D; // e.onGround &&
			if (!flag) {
				this.right_wing.rotateAngleZ = -4.0F + (MathHelper.cos(ageInTicks * 1.0F) * (float) Math.PI * 0.2F);
				this.left_wing.rotateAngleZ = 4.0F + -(MathHelper.cos(ageInTicks * 1.0F) * (float) Math.PI * 0.2F);
			} else {
				this.right_wing.rotateAngleZ = 0.0F;
				this.left_wing.rotateAngleZ = 0.0F;
			}

		}

		public void setLivingAnimations(LivingEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
			CustomEntity livingBase = (CustomEntity) entitylivingbaseIn;
			float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTickTime;
			float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTickTime;
			right_wing.rotateAngleZ = (MathHelper.sin(f) + 1.0F) * f1;
			left_wing.rotateAngleZ = -((MathHelper.sin(f) + 1.0F) * f1);
		}
	}
}