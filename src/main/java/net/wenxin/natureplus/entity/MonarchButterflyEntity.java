
package net.wenxin.natureplus.entity;

import net.wenxin.natureplus.procedures.TickUpdateProcedure;
import net.wenxin.natureplus.procedures.EntityTimerButterflyProcedure;
import net.wenxin.natureplus.procedures.ButterflyEggNaturalSpawnProcedure;
import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
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
import net.minecraft.world.IWorld;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.entity.passive.ParrotEntity;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.BlockState;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

import com.google.common.collect.ImmutableMap;

@NatureplusModElements.ModElement.Tag
public class MonarchButterflyEntity extends NatureplusModElements.ModElement {
	public static EntityType entity = null;
	public MonarchButterflyEntity(NatureplusModElements instance) {
		super(instance, 126);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 0.4f)).build("monarch_butterfly")
						.setRegistryName("monarch_butterfly");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -39424, -16777216, new Item.Properties().group(NaturePlusTabItemGroup.tab))
				.setRegistryName("monarch_butterfly"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 8, 1, 3));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS,
				Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, (entityType, world, reason, pos, random) -> {
					int x = pos.getX();
					int y = pos.getY();
					int z = pos.getZ();
					return ButterflyEggNaturalSpawnProcedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world));
				});
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelButterfly3(), 0.3f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("natureplus:textures/monarch_butterfly3.png");
				}
			};
		});
	}
	public static class CustomEntity extends CreatureEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 2;
			setNoAI(false);
			this.moveController = new FlyingMovementController(this, 10, true);
			this.navigator = new FlyingPathNavigator(this, this.world);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			// this.sitGoal = new SitGoal(this);
			this.goalSelector.addGoal(1, new TemptGoal(this, 1.8, Ingredient.fromTag(ItemTags.FLOWERS), false));
			this.goalSelector.addGoal(2, new RandomWalkingGoal(this, 1.5, 20) {
				@Override
				protected Vec3d getPosition() {
					Random random = CustomEntity.this.getRNG();
					double dir_x = CustomEntity.this.getPosX() + ((random.nextFloat() * 2 - 1) * 32);
					double dir_y = CustomEntity.this.getPosY() + ((random.nextFloat() * 2 - 1) * 24);
					double dir_z = CustomEntity.this.getPosZ() + ((random.nextFloat() * 2 - 1) * 32);
					return new Vec3d(dir_x, dir_y, dir_z);
				}
			});
			this.goalSelector.addGoal(2, new FollowMobGoal(this, (float) 1, 10, 5));
			// this.goalSelector.addGoal(3, sitGoal);
			this.goalSelector.addGoal(3, new AvoidEntityGoal(this, ParrotEntity.class, (float) 6, 1, 2.5));
			this.goalSelector.addGoal(3, new PanicGoal(this, 2.0));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.5));
			this.goalSelector.addGoal(5, new SwimGoal(this));
		}

		// protected PathNavigator createNavigator(World world) {
		// return new ClimberPathNavigator(this, this.world);
		// }
		protected PathNavigator createNavigator(World worldIn) {
			FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn) {
				public boolean canEntityStandOnPos(BlockPos pos) {
					return !this.world.getBlockState(pos.down()).isAir();
				}
			};
			flyingpathnavigator.setCanOpenDoors(false);
			flyingpathnavigator.setCanSwim(false);
			flyingpathnavigator.setCanEnterDoors(true);
			return flyingpathnavigator;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
			return this.isChild() ? sizeIn.height * 0.5F : sizeIn.height * 0.4F;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:squitch"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:bug_squish"));
		}

		protected void playStepSound(BlockPos pos, BlockState blockIn) {
			this.playSound(SoundEvents.ENTITY_PARROT_STEP, 0.15F, 1.0F);
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public boolean onLivingFall(float l, float d) {
			return false;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			if (source == DamageSource.FALL)
				return false;
			return super.attackEntityFrom(source, amount);
		}

		protected boolean makeFlySound() {
			return true;
		}

		@Override
		public void baseTick() {
			super.baseTick();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				TickUpdateProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		public ILivingEntityData onInitialSpawn(IWorld iworld, DifficultyInstance difficulty, SpawnReason reason, ILivingEntityData livingdata,
				CompoundNBT tag) {
			ILivingEntityData retval = super.onInitialSpawn(iworld, difficulty, reason, livingdata, tag);
			World world = iworld.getWorld();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				EntityTimerButterflyProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.FLYING_SPEED) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
			this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(0.4);
		}

		@Override
		protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
		}

		@Override
		public void setNoGravity(boolean ignored) {
			super.setNoGravity(true);
		}

		public void livingTick() {
			super.livingTick();
			this.setNoGravity(true);
		}
	}

	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports

	public static class ModelButterfly3 extends EntityModel<Entity> {
		private final ModelRenderer main;
		private final ModelRenderer wing_left;
		private final ModelRenderer wing_right;
		private final ModelRenderer head;
		private final ModelRenderer antenna_left;
		private final ModelRenderer antenna_right;
	
		public ModelButterfly3() {
			textureWidth = 64;
			textureHeight = 64;
	
			main = new ModelRenderer(this);
			main.setRotationPoint(0.0F, 24.0F, 0.0F);
			main.setTextureOffset(0, 20).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 7.0F, 0.0F, false);
	
			wing_left = new ModelRenderer(this);
			wing_left.setRotationPoint(1.0F, 23.0F, 1.0F);
			wing_left.setTextureOffset(0, 10).addBox(-0.5F, 0.0F, -5.0F, 14.0F, 0.0F, 10.0F, 0.0F, false);
	
			wing_right = new ModelRenderer(this);
			wing_right.setRotationPoint(-1.0F, 23.0F, 1.0F);
			wing_right.setTextureOffset(0, 0).addBox(-13.5F, 0.0F, -5.0F, 14.0F, 0.0F, 10.0F, 0.0F, false);
	
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 23.0F, -2.0F);
			head.setTextureOffset(0, 9).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
	
			antenna_left = new ModelRenderer(this);
			antenna_left.setRotationPoint(-0.5F, -0.5F, -2.0F);
			head.addChild(antenna_left);
			antenna_left.setTextureOffset(0, 3).addBox(1.0F, -1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, false);
	
			antenna_right = new ModelRenderer(this);
			antenna_right.setRotationPoint(0.5F, -0.5F, -2.0F);
			head.addChild(antenna_right);
			antenna_right.setTextureOffset(0, 0).addBox(-1.0F, -1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, false);
		}
	
		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
				float green, float blue, float alpha) {
			main.render(matrixStack, buffer, packedLight, packedOverlay);
			wing_left.render(matrixStack, buffer, packedLight, packedOverlay);
			wing_right.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
		}
	
		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}
	
		@Override
		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.antenna_left.rotateAngleX = MathHelper.cos(f2 * 0.03F) * (float)Math.PI * 0.15F;
			this.antenna_right.rotateAngleX = MathHelper.cos(f2 * 0.031F) * (float)Math.PI * 0.15F;
			
			boolean flag = e.getMotion().lengthSquared() < 2.0E-7D; //e.onGround &&
			if (flag) {
				this.wing_right.rotateAngleZ = 1.0F + -(MathHelper.cos(f2 * 0.4F) * (float)Math.PI * 0.18F);
				this.wing_left.rotateAngleZ = -1.0F + (MathHelper.cos(f2 * 0.4F) * (float)Math.PI * 0.18F);
			} else {
				this.wing_right.rotateAngleZ = -(MathHelper.cos(f2 * 1.7F) * (float)Math.PI * 0.3F);
				this.wing_left.rotateAngleZ = MathHelper.cos(f2 * 1.7F) * (float)Math.PI * 0.3F;
			}
		}
	}
}
