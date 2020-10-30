
package net.wenxin.natureplus.entity;

import net.wenxin.natureplus.procedures.MonarchCocoonSpawnProcedure;
import net.wenxin.natureplus.procedures.EntityTimerProcedure;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.BlockState;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

import com.google.common.collect.ImmutableMap;

@NatureplusModElements.ModElement.Tag
public class MonarchCaterpillarEntity extends NatureplusModElements.ModElement {
	public static EntityType entity = null;
	public MonarchCaterpillarEntity(NatureplusModElements instance) {
		super(instance, 135);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.55f, 0.4f)).build("monarch_caterpillar")
						.setRegistryName("monarch_caterpillar");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -205, -16777216, new Item.Properties().group(NaturePlusTabItemGroup.tab))
				.setRegistryName("monarch_caterpillar_spawn_egg"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 8, 1, 5));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> {
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
			return new MobRenderer(renderManager, new ModelCaterpillar2(), 0.2f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("natureplus:textures/monarch_caterpillar2.png");
				}
			};
		});
	}
	public static class CustomEntity extends SpiderEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 1;
			setNoAI(false);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 0.3F));
			this.goalSelector.addGoal(1, new TemptGoal(this, 0.5F, Ingredient.fromTag(ItemTags.LEAVES), false));
			this.goalSelector.addGoal(1, new TemptGoal(this, 0.5F, Ingredient.fromTag(ItemTags.FLOWERS), false));
			this.goalSelector.addGoal(1, new TemptGoal(this, 0.5F,
					Ingredient.fromItems(Items.GRASS, Items.TALL_GRASS, Items.FERN, Items.LARGE_FERN, Items.SWEET_BERRIES), false));
			this.goalSelector.addGoal(2, new AvoidEntityGoal(this, SpiderEntity.class, (float) 6, 0.5F, 1));
			this.goalSelector.addGoal(2, new AvoidEntityGoal(this, CaveSpiderEntity.class, (float) 6, 0.5F, 1));
			this.goalSelector.addGoal(2, new AvoidEntityGoal(this, PhantomEntity.class, (float) 6, 0.5F, 1));
			this.goalSelector.addGoal(2, new AvoidEntityGoal(this, BlueDragonflyEntity.CustomEntity.class, (float) 6, 0.5F, 1));
			this.goalSelector.addGoal(2, new AvoidEntityGoal(this, GreenDragonflyEntity.CustomEntity.class, (float) 6, 0.5F, 1));
			this.goalSelector.addGoal(2, new AvoidEntityGoal(this, RedDragonflyEntity.CustomEntity.class, (float) 6, 0.5F, 1));
			this.goalSelector.addGoal(3, new PanicGoal(this, 0.5F));
			this.goalSelector.addGoal(4, new EatGrassGoal(this));
			this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(7, new SwimGoal(this));
		}

		// public static final DataParameter<Byte> CLIMBING =
		// EntityDataManager.createKey(CustomEntity.class, DataSerializers.BYTE);
		// /**
		// * Returns new PathNavigateGround instance
		// */
		// protected PathNavigator createNavigator(World worldIn) {
		// return new ClimberPathNavigator(this, worldIn);
		// }
		//
		// protected void registerData() {
		// super.registerData();
		// this.dataManager.register(CLIMBING, (byte) 0);
		// }
		//
		// /**
		// * Called to update the entity's position/logic.
		// */
		// public void tick() {
		// super.tick();
		// if (!this.world.isRemote) {
		// this.setBesideClimbableBlock(this.collidedHorizontally);
		// }
		// }
		//
		// /**
		// * Returns true if this entity should move as if it were on a ladder (either
		// * because it's actually on a ladder, or for AI reasons)
		// */
		// public boolean isOnLadder() {
		// return this.isBesideClimbableBlock();
		// }
		//
		// /**
		// * Returns true if the WatchableObject (Byte) is 0x01 otherwise returns false.
		// * The WatchableObject is updated using setBesideClimableBlock.
		// */
		// public boolean isBesideClimbableBlock() {
		// return (this.dataManager.get(CLIMBING) & 1) != 0;
		// }
		//
		// /**
		// * Updates the WatchableObject (Byte) created in entityInit(), setting it to
		// * 0x01 if par1 is true or 0x00 if it is false.
		// */
		// public void setBesideClimbableBlock(boolean climbing) {
		// byte b0 = this.dataManager.get(CLIMBING);
		// if (climbing) {
		// b0 = (byte) (b0 | 1);
		// } else {
		// b0 = (byte) (b0 & -2);
		// }
		// this.dataManager.set(CLIMBING, b0);
		// }
		public float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
			return sizeIn.height * 0.92F;
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.ARTHROPOD;
		}

		@Override
		public boolean isDespawnPeaceful() {
			return false;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.WHEAT_SEEDS, (int) (1)));
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
		public ILivingEntityData onInitialSpawn(IWorld iworld, DifficultyInstance difficulty, SpawnReason reason, ILivingEntityData livingdata,
				CompoundNBT tag) {
			ILivingEntityData retval = super.onInitialSpawn(iworld, difficulty, reason, livingdata, tag);
			World world = iworld.getWorld();
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				EntityTimerProcedure.executeProcedure($_dependencies);
			}
			return retval;
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
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				MonarchCocoonSpawnProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2D);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0);
		}
	}

	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelCaterpillar2 extends EntityModel<Entity> {
		private final ModelRenderer main;
		private final ModelRenderer head;
		private final ModelRenderer antenna_left;
		private final ModelRenderer antenna_right;
		private final ModelRenderer tail_left;
		private final ModelRenderer tail_right;
		public ModelCaterpillar2() {
			textureWidth = 64;
			textureHeight = 64;
			main = new ModelRenderer(this);
			main.setRotationPoint(0.0F, 22.0F, 0.0F);
			main.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 10.0F, 0.0F, false);
			main.setTextureOffset(29, 0).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 10.0F, 0.25F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 0.0F, 0.0F);
			main.addChild(head);
			head.setTextureOffset(0, 14).addBox(-2.0F, -2.0F, -10.0F, 4.0F, 4.0F, 10.0F, 0.0F, false);
			head.setTextureOffset(29, 14).addBox(-2.0F, -2.0F, -10.0F, 4.0F, 4.0F, 10.0F, 0.25F, false);
			antenna_left = new ModelRenderer(this);
			antenna_left.setRotationPoint(-1.25F, -1.5F, -10.0F);
			head.addChild(antenna_left);
			antenna_left.setTextureOffset(0, 15).addBox(2.5F, -3.5F, -4.0F, 0.0F, 4.0F, 4.0F, 0.0F, false);
			antenna_right = new ModelRenderer(this);
			antenna_right.setRotationPoint(1.25F, -1.5F, -10.0F);
			head.addChild(antenna_right);
			antenna_right.setTextureOffset(0, 11).addBox(-2.5F, -3.5F, -4.0F, 0.0F, 4.0F, 4.0F, 0.0F, false);
			tail_left = new ModelRenderer(this);
			tail_left.setRotationPoint(-1.25F, -1.5F, 10.0F);
			main.addChild(tail_left);
			tail_left.setTextureOffset(19, 16).addBox(2.5F, -2.5F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F, false);
			tail_right = new ModelRenderer(this);
			tail_right.setRotationPoint(1.25F, -1.5F, 10.0F);
			main.addChild(tail_right);
			tail_right.setTextureOffset(19, 13).addBox(-2.5F, -2.5F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			main.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (90F / (float) Math.PI);
			this.antenna_left.rotateAngleX = MathHelper.cos(f2 * 0.03F) * (float) Math.PI * 0.15F;
			this.antenna_right.rotateAngleX = MathHelper.cos(f2 * 0.031F) * (float) Math.PI * 0.15F;
		}
	}
}
