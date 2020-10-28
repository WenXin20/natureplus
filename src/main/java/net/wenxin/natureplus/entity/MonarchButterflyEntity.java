
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
import net.minecraft.tags.BlockTags;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.state.IntegerProperty;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.particles.IParticleData;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.monster.SpiderEntity;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.RandomPositionGenerator;
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
import net.minecraft.entity.AgeableEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.block.DoublePlantBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import javax.annotation.Nullable;

import java.util.function.Predicate;
import java.util.Random;
import java.util.Optional;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumSet;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

import com.google.common.collect.ImmutableMap;

@NatureplusModElements.ModElement.Tag
public class MonarchButterflyEntity extends NatureplusModElements.ModElement {
	public static EntityType entity = null;
	public MonarchButterflyEntity(NatureplusModElements instance) {
		super(instance, 133);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.8f, 0.5f)).build("monarch_butterfly")
						.setRegistryName("monarch_butterfly");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -39424, -16777216, new Item.Properties().group(NaturePlusTabItemGroup.tab))
				.setRegistryName("monarch_butterfly_spawn_egg"));
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
			return new MobRenderer(renderManager, new ModelButterfly4(), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("natureplus:textures/monarch_butterfly4.png");
				}
			};
		});
	}
	public static class CustomEntity extends AnimalEntity {
		private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.createKey(MonarchButterflyEntity.CustomEntity.class,
				DataSerializers.BYTE);
		@Nullable
		private BlockPos savedFlowerPos = null;
		private MonarchButterflyEntity.CustomEntity.FindFlowerGoal findFlowerGoal;
		private MonarchButterflyEntity.CustomEntity.PollinateGoal pollinateGoal;
		private int remainingCooldownBeforeLocatingNewFlower = 0;
		private int ticksWithoutNectarSinceExitingHive;
		private int numCropsGrownSincePollination;
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
			this.goalSelector.addGoal(1, new BreedGoal(this, 1.8));
			this.goalSelector.addGoal(2, new TemptGoal(this, 1.8, Ingredient.fromTag(ItemTags.FLOWERS), false));
			this.goalSelector.addGoal(2, new AvoidEntityGoal(this, SpiderEntity.class, (float) 6, 2.8, 1));
			this.goalSelector.addGoal(2, new AvoidEntityGoal(this, CaveSpiderEntity.class, (float) 6, 2.8, 1));
			this.goalSelector.addGoal(2, new AvoidEntityGoal(this, PhantomEntity.class, (float) 6, 2.8, 1));
			this.goalSelector.addGoal(2, new AvoidEntityGoal(this, BlueDragonflyEntity.CustomEntity.class, (float) 6, 2.8, 2.0));
			this.goalSelector.addGoal(2, new AvoidEntityGoal(this, GreenDragonflyEntity.CustomEntity.class, (float) 6, 2.8, 2.0));
			this.goalSelector.addGoal(2, new AvoidEntityGoal(this, RedDragonflyEntity.CustomEntity.class, (float) 6, 2.8, 2.0));
			this.goalSelector.addGoal(3, new MonarchButterflyEntity.CustomEntity.WanderGoal());
			this.pollinateGoal = new MonarchButterflyEntity.CustomEntity.PollinateGoal();
			this.goalSelector.addGoal(3, this.pollinateGoal);
			this.goalSelector.addGoal(4, new FollowMobGoal(this, (float) 1, 10, 5));
			this.findFlowerGoal = new MonarchButterflyEntity.CustomEntity.FindFlowerGoal();
			this.goalSelector.addGoal(5, this.findFlowerGoal);
			this.goalSelector.addGoal(6, new MonarchButterflyEntity.CustomEntity.FindPollinationTargetGoal());
			this.goalSelector.addGoal(7, new PanicGoal(this, 2.8));
			this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(11, new SwimGoal(this));
		}

		protected PathNavigator createNavigator(World worldIn) {
			FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn) {
				public boolean canEntityStandOnPos(BlockPos pos) {
					return !this.world.getBlockState(pos.down()).isAir();
				}

				public void tick() {
					if (!MonarchButterflyEntity.CustomEntity.this.pollinateGoal.isRunning()) {
						super.tick();
					}
				}
			};
			flyingpathnavigator.setCanOpenDoors(false);
			flyingpathnavigator.setCanSwim(false);
			flyingpathnavigator.setCanEnterDoors(true);
			return flyingpathnavigator;
		}

		@Override
		public boolean isBreedingItem(ItemStack stack) {
			return stack.getItem().isIn(ItemTags.FLOWERS);
		}

		@Override
		public AgeableEntity createChild(AgeableEntity ageable) {
			MonarchEggEntity.CustomEntity retval = (MonarchEggEntity.CustomEntity) MonarchEggEntity.entity.create(this.world);
			retval.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(retval)), SpawnReason.BREEDING,
					(ILivingEntityData) null, (CompoundNBT) null);
			return retval;
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
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:butterfly_flying"));
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
			if (source == DamageSource.FALL) {
				return false;
			} else {
				Entity entity = source.getTrueSource();
				if (!this.world.isRemote && entity instanceof PlayerEntity && !((PlayerEntity) entity).isCreative() && this.canEntityBeSeen(entity)
						&& !this.isAIDisabled()) {
					this.pollinateGoal.cancel();
				}
				return super.attackEntityFrom(source, amount);
			}
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
			if (!this.world.isRemote) {
				if (this.remainingCooldownBeforeLocatingNewFlower > 0) {
					--this.remainingCooldownBeforeLocatingNewFlower;
				}
			}
		}

		private boolean isFlowers(BlockPos pos) {
			return this.world.isBlockPresent(pos) && this.world.getBlockState(pos).getBlock().isIn(BlockTags.FLOWERS);
		}

		private boolean isWithinDistance(BlockPos pos, int distance) {
			return pos.withinDistance(new BlockPos(this), (double) distance);
		}

		private boolean isTooFar(BlockPos pos) {
			return !this.isWithinDistance(pos, 48);
		}

		public void writeAdditional(CompoundNBT compound) {
			super.writeAdditional(compound);
			if (this.hasFlower()) {
				compound.put("FlowerPos", NBTUtil.writeBlockPos(this.getFlowerPos()));
			}
			compound.putBoolean("HasNectar", this.hasNectar());
			compound.putInt("CropsGrownSincePollination", this.numCropsGrownSincePollination);
		}

		public void readAdditional(CompoundNBT compound) {
			this.savedFlowerPos = null;
			if (compound.contains("FlowerPos")) {
				this.savedFlowerPos = NBTUtil.readBlockPos(compound.getCompound("FlowerPos"));
			}
			super.readAdditional(compound);
			this.setHasNectar(compound.getBoolean("HasNectar"));
			this.numCropsGrownSincePollination = compound.getInt("CropsGrownSincePollination");
		}

		/**
		 * Called to update the entity's position/logic.
		 */
		public void tick() {
			super.tick();
			if (this.hasNectar() && this.getCropsGrownSincePollination() < 10 && this.rand.nextFloat() < 0.05F) {
				for (int i = 0; i < this.rand.nextInt(2) + 1; ++i) {
					this.addParticle(this.world, this.getPosX() - (double) 0.3F, this.getPosX() + (double) 0.3F, this.getPosZ() - (double) 0.3F,
							this.getPosZ() + (double) 0.3F, this.getPosYHeight(0.5D), ParticleTypes.FALLING_NECTAR);
				}
			}
		}

		private void addParticle(World worldIn, double p_226397_2_, double p_226397_4_, double p_226397_6_, double p_226397_8_, double posY,
				IParticleData particleData) {
			worldIn.addParticle(particleData, MathHelper.lerp(worldIn.rand.nextDouble(), p_226397_2_, p_226397_4_), posY,
					MathHelper.lerp(worldIn.rand.nextDouble(), p_226397_6_, p_226397_8_), 0.0D, 0.0D, 0.0D);
		}

		public void resetTicksWithoutNectar() {
			this.ticksWithoutNectarSinceExitingHive = 0;
		}

		public boolean hasNectar() {
			return this.getButterflyFlag(8);
		}

		private void setHasNectar(boolean nectar) {
			if (nectar) {
				this.resetTicksWithoutNectar();
			}
			this.setButterflyFlag(8, nectar);
		}

		protected void registerData() {
			super.registerData();
			this.dataManager.register(DATA_FLAGS_ID, (byte) 0);
		}

		private void setButterflyFlag(int flagId, boolean flag) {
			if (flag) {
				this.dataManager.set(DATA_FLAGS_ID, (byte) (this.dataManager.get(DATA_FLAGS_ID) | flagId));
			} else {
				this.dataManager.set(DATA_FLAGS_ID, (byte) (this.dataManager.get(DATA_FLAGS_ID) & ~flagId));
			}
		}

		private boolean getButterflyFlag(int flagId) {
			return (this.dataManager.get(DATA_FLAGS_ID) & flagId) != 0;
		}

		@Nullable
		public BlockPos getFlowerPos() {
			return this.savedFlowerPos;
		}

		public boolean hasFlower() {
			return this.savedFlowerPos != null;
		}

		public void setFlowerPos(BlockPos pos) {
			this.savedFlowerPos = pos;
		}

		private void startMovingTo(BlockPos pos) {
			Vec3d vec3d = new Vec3d(pos);
			int i = 0;
			BlockPos blockpos = new BlockPos(this);
			int j = (int) vec3d.y - blockpos.getY();
			if (j > 2) {
				i = 4;
			} else if (j < -2) {
				i = -4;
			}
			int k = 6;
			int l = 8;
			int i1 = blockpos.manhattanDistance(pos);
			if (i1 < 15) {
				k = i1 / 2;
				l = i1 / 2;
			}
			Vec3d vec3d1 = RandomPositionGenerator.func_226344_b_(this, k, l, i, vec3d, (double) ((float) Math.PI / 10F));
			if (vec3d1 != null) {
				this.navigator.setRangeMultiplier(0.5F);
				this.navigator.tryMoveToXYZ(vec3d1.x, vec3d1.y, vec3d1.z, 1.0D);
			}
		}

		private int getCropsGrownSincePollination() {
			return this.numCropsGrownSincePollination;
		}

		private void resetCropCounter() {
			this.numCropsGrownSincePollination = 0;
		}

		private void addCropCounter() {
			++this.numCropsGrownSincePollination;
		}

		public void onNectarUsed() {
			this.setHasNectar(false);
			this.resetCropCounter();
		}
		public class FindFlowerGoal extends MonarchButterflyEntity.CustomEntity.PassiveGoal {
			private int ticks = MonarchButterflyEntity.CustomEntity.this.world.rand.nextInt(10);
			FindFlowerGoal() {
				this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
			}

			public boolean canButterflyStart() {
				return MonarchButterflyEntity.CustomEntity.this.savedFlowerPos != null
						&& MonarchButterflyEntity.CustomEntity.this.isFlowers(MonarchButterflyEntity.CustomEntity.this.savedFlowerPos)
						&& !MonarchButterflyEntity.CustomEntity.this.isWithinDistance(MonarchButterflyEntity.CustomEntity.this.savedFlowerPos, 2);
			}

			public boolean canButterflyContinue() {
				return this.canButterflyStart();
			}

			/**
			 * Execute a one shot task or start executing a continuous task
			 */
			public void startExecuting() {
				this.ticks = 0;
				super.startExecuting();
			}

			/**
			 * Reset the task's internal state. Called when this task is interrupted by
			 * another one
			 */
			public void resetTask() {
				this.ticks = 0;
				MonarchButterflyEntity.CustomEntity.this.navigator.clearPath();
				MonarchButterflyEntity.CustomEntity.this.navigator.resetRangeMultiplier();
			}

			/**
			 * Keep ticking a continuous task that has already been started
			 */
			public void tick() {
				if (MonarchButterflyEntity.CustomEntity.this.savedFlowerPos != null) {
					++this.ticks;
					if (this.ticks > 600) {
						MonarchButterflyEntity.CustomEntity.this.savedFlowerPos = null;
					} else if (!MonarchButterflyEntity.CustomEntity.this.navigator.func_226337_n_()) {
						if (MonarchButterflyEntity.CustomEntity.this.isTooFar(MonarchButterflyEntity.CustomEntity.this.savedFlowerPos)) {
							MonarchButterflyEntity.CustomEntity.this.savedFlowerPos = null;
						} else {
							MonarchButterflyEntity.CustomEntity.this.startMovingTo(MonarchButterflyEntity.CustomEntity.this.savedFlowerPos);
						}
					}
				}
			}

			private boolean shouldMoveToFlower() {
				return MonarchButterflyEntity.CustomEntity.this.ticksWithoutNectarSinceExitingHive > 2400;
			}
		}

		abstract class PassiveGoal extends Goal {
			private PassiveGoal() {
			}

			public abstract boolean canButterflyStart();

			public abstract boolean canButterflyContinue();

			/**
			 * Returns whether execution should begin. You can also read and cache any state
			 * necessary for execution in this method as well.
			 */
			public boolean shouldExecute() {
				return this.canButterflyStart();
			}

			/**
			 * Returns whether an in-progress EntityAIBase should continue executing
			 */
			public boolean shouldContinueExecuting() {
				return this.canButterflyContinue();
			}
		}

		class PollinateGoal extends MonarchButterflyEntity.CustomEntity.PassiveGoal {
			private final Predicate<BlockState> flowerPredicate = (flower) -> {
				if (flower.isIn(BlockTags.TALL_FLOWERS)) {
					if (flower.getBlock() == Blocks.SUNFLOWER) {
						return flower.get(DoublePlantBlock.HALF) == DoubleBlockHalf.UPPER;
					} else {
						return true;
					}
				} else {
					return flower.isIn(BlockTags.SMALL_FLOWERS);
				}
			};
			private int pollinationTicks = 0;
			private int lastPollinationTick = 0;
			private boolean running;
			private Vec3d nextTarget;
			private int ticks = 0;
			PollinateGoal() {
				this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
			}

			public boolean canButterflyStart() {
				if (MonarchButterflyEntity.CustomEntity.this.remainingCooldownBeforeLocatingNewFlower > 0) {
					return false;
				} else if (MonarchButterflyEntity.CustomEntity.this.hasNectar()) {
					return false;
				} else if (MonarchButterflyEntity.CustomEntity.this.world.isRaining()) {
					return false;
				} else if (MonarchButterflyEntity.CustomEntity.this.rand.nextFloat() < 0.7F) {
					return false;
				} else {
					Optional<BlockPos> optional = this.getFlower();
					if (optional.isPresent()) {
						MonarchButterflyEntity.CustomEntity.this.savedFlowerPos = optional.get();
						MonarchButterflyEntity.CustomEntity.this.navigator.tryMoveToXYZ(
								(double) MonarchButterflyEntity.CustomEntity.this.savedFlowerPos.getX() + 0.5D,
								(double) MonarchButterflyEntity.CustomEntity.this.savedFlowerPos.getY() + 0.5D,
								(double) MonarchButterflyEntity.CustomEntity.this.savedFlowerPos.getZ() + 0.5D, (double) 1.2F);
						return true;
					} else {
						return false;
					}
				}
			}

			public boolean canButterflyContinue() {
				if (!this.running) {
					return false;
				} else if (!MonarchButterflyEntity.CustomEntity.this.hasFlower()) {
					return false;
				} else if (MonarchButterflyEntity.CustomEntity.this.world.isRaining()) {
					return false;
				} else if (this.completedPollination()) {
					return MonarchButterflyEntity.CustomEntity.this.rand.nextFloat() < 0.2F;
				} else if (MonarchButterflyEntity.CustomEntity.this.ticksExisted % 20 == 0
						&& !MonarchButterflyEntity.CustomEntity.this.isFlowers(MonarchButterflyEntity.CustomEntity.this.savedFlowerPos)) {
					MonarchButterflyEntity.CustomEntity.this.savedFlowerPos = null;
					return false;
				} else {
					return true;
				}
			}

			private boolean completedPollination() {
				return this.pollinationTicks > 400;
			}

			private boolean isRunning() {
				return this.running;
			}

			private void cancel() {
				this.running = false;
			}

			/**
			 * Execute a one shot task or start executing a continuous task
			 */
			public void startExecuting() {
				this.pollinationTicks = 0;
				this.ticks = 0;
				this.lastPollinationTick = 0;
				this.running = true;
				MonarchButterflyEntity.CustomEntity.this.resetTicksWithoutNectar();
			}

			/**
			 * Reset the task's internal state. Called when this task is interrupted by
			 * another one
			 */
			public void resetTask() {
				if (this.completedPollination()) {
					MonarchButterflyEntity.CustomEntity.this.setHasNectar(true);
				}
				this.running = false;
				MonarchButterflyEntity.CustomEntity.this.navigator.clearPath();
				MonarchButterflyEntity.CustomEntity.this.remainingCooldownBeforeLocatingNewFlower = 200;
			}

			/**
			 * Keep ticking a continuous task that has already been started
			 */
			public void tick() {
				++this.ticks;
				if (this.ticks > 600) {
					MonarchButterflyEntity.CustomEntity.this.savedFlowerPos = null;
				} else {
					Vec3d vec3d = (new Vec3d(MonarchButterflyEntity.CustomEntity.this.savedFlowerPos)).add(0.5D, (double) 0.6F, 0.5D);
					if (vec3d.distanceTo(MonarchButterflyEntity.CustomEntity.this.getPositionVec()) > 1.0D) {
						this.nextTarget = vec3d;
						this.moveToNextTarget();
					} else {
						if (this.nextTarget == null) {
							this.nextTarget = vec3d;
						}
						boolean flag = MonarchButterflyEntity.CustomEntity.this.getPositionVec().distanceTo(this.nextTarget) <= 0.1D;
						boolean flag1 = true;
						if (!flag && this.ticks > 600) {
							MonarchButterflyEntity.CustomEntity.this.savedFlowerPos = null;
						} else {
							if (flag) {
								boolean flag2 = MonarchButterflyEntity.CustomEntity.this.rand.nextInt(100) == 0;
								if (flag2) {
									this.nextTarget = new Vec3d(vec3d.getX() + (double) this.getRandomOffset(), vec3d.getY(),
											vec3d.getZ() + (double) this.getRandomOffset());
									MonarchButterflyEntity.CustomEntity.this.navigator.clearPath();
								} else {
									flag1 = false;
								}
								MonarchButterflyEntity.CustomEntity.this.getLookController().setLookPosition(vec3d.getX(), vec3d.getY(),
										vec3d.getZ());
							}
							if (flag1) {
								this.moveToNextTarget();
							}
							++this.pollinationTicks;
							if (MonarchButterflyEntity.CustomEntity.this.rand.nextFloat() < 0.05F
									&& this.pollinationTicks > this.lastPollinationTick + 60) {
								this.lastPollinationTick = this.pollinationTicks;
								MonarchButterflyEntity.CustomEntity.this.playSound(
										ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:butterfly_flying")), 1.0F, 1.0F);
							}
						}
					}
				}
			}

			private void moveToNextTarget() {
				MonarchButterflyEntity.CustomEntity.this.getMoveHelper().setMoveTo(this.nextTarget.getX(), this.nextTarget.getY(),
						this.nextTarget.getZ(), (double) 0.35F);
			}

			private float getRandomOffset() {
				return (MonarchButterflyEntity.CustomEntity.this.rand.nextFloat() * 2.0F - 1.0F) * 0.33333334F;
			}

			private Optional<BlockPos> getFlower() {
				return this.findFlower(this.flowerPredicate, 5.0D);
			}

			private Optional<BlockPos> findFlower(Predicate<BlockState> p_226500_1_, double distance) {
				BlockPos blockpos = new BlockPos(MonarchButterflyEntity.CustomEntity.this);
				BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
				for (int i = 0; (double) i <= distance; i = i > 0 ? -i : 1 - i) {
					for (int j = 0; (double) j < distance; ++j) {
						for (int k = 0; k <= j; k = k > 0 ? -k : 1 - k) {
							for (int l = k < j && k > -j ? j : 0; l <= j; l = l > 0 ? -l : 1 - l) {
								blockpos$mutable.setPos(blockpos).move(k, i - 1, l);
								if (blockpos.withinDistance(blockpos$mutable, distance)
										&& p_226500_1_.test(MonarchButterflyEntity.CustomEntity.this.world.getBlockState(blockpos$mutable))) {
									return Optional.of(blockpos$mutable);
								}
							}
						}
					}
				}
				return Optional.empty();
			}
		}

		class WanderGoal extends Goal {
			WanderGoal() {
				this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
			}

			/**
			 * Returns whether execution should begin. You can also read and cache any state
			 * necessary for execution in this method as well.
			 */
			public boolean shouldExecute() {
				return MonarchButterflyEntity.CustomEntity.this.navigator.noPath() && MonarchButterflyEntity.CustomEntity.this.rand.nextInt(10) == 0;
			}

			/**
			 * Returns whether an in-progress EntityAIBase should continue executing
			 */
			public boolean shouldContinueExecuting() {
				return MonarchButterflyEntity.CustomEntity.this.navigator.func_226337_n_();
			}

			/**
			 * Execute a one shot task or start executing a continuous task
			 */
			public void startExecuting() {
				Vec3d vec3d = this.getPosition();
				if (vec3d != null) {
					MonarchButterflyEntity.CustomEntity.this.navigator
							.setPath(MonarchButterflyEntity.CustomEntity.this.navigator.getPathToPos(new BlockPos(vec3d), 1), 1.0D);
				}
			}

			protected Vec3d getPosition() {
				Random random = CustomEntity.this.getRNG();
				double dir_x = CustomEntity.this.getPosX() + ((random.nextFloat() * 2 - 1) * 32);
				double dir_y = CustomEntity.this.getPosY() + ((random.nextFloat() * 2 - 1) * 24);
				double dir_z = CustomEntity.this.getPosZ() + ((random.nextFloat() * 2 - 1) * 32);
				return new Vec3d(dir_x, dir_y, dir_z);
			}
		}

		class FindPollinationTargetGoal extends MonarchButterflyEntity.CustomEntity.PassiveGoal {
			private FindPollinationTargetGoal() {
			}

			public boolean canButterflyStart() {
				if (MonarchButterflyEntity.CustomEntity.this.getCropsGrownSincePollination() >= 10) {
					MonarchButterflyEntity.CustomEntity.this.onNectarUsed();
					return false;
				} else if (MonarchButterflyEntity.CustomEntity.this.rand.nextFloat() < 0.3F) {
					return false;
				} else {
					return MonarchButterflyEntity.CustomEntity.this.hasNectar();
				}
			}

			public boolean canButterflyContinue() {
				return this.canButterflyStart();
			}

			/**
			 * Keep ticking a continuous task that has already been started
			 */
			public void tick() {
				if (MonarchButterflyEntity.CustomEntity.this.rand.nextInt(30) == 0) {
					for (int i = 1; i <= 2; ++i) {
						BlockPos blockpos = (new BlockPos(MonarchButterflyEntity.CustomEntity.this)).down(i);
						BlockState blockstate = MonarchButterflyEntity.CustomEntity.this.world.getBlockState(blockpos);
						Block block = blockstate.getBlock();
						boolean flag = false;
						IntegerProperty integerproperty = null;
						if (block.isIn(BlockTags.BEE_GROWABLES)) {
							if (block instanceof CropsBlock) {
								CropsBlock cropsblock = (CropsBlock) block;
								if (!cropsblock.isMaxAge(blockstate)) {
									flag = true;
									integerproperty = cropsblock.getAgeProperty();
								}
							} else if (block instanceof StemBlock) {
								int j = blockstate.get(StemBlock.AGE);
								if (j < 7) {
									flag = true;
									integerproperty = StemBlock.AGE;
								}
							} else if (block == Blocks.SWEET_BERRY_BUSH) {
								int k = blockstate.get(SweetBerryBushBlock.AGE);
								if (k < 3) {
									flag = true;
									integerproperty = SweetBerryBushBlock.AGE;
								}
							}
							if (flag) {
								MonarchButterflyEntity.CustomEntity.this.world.playEvent(2005, blockpos, 0);
								MonarchButterflyEntity.CustomEntity.this.world.setBlockState(blockpos,
										blockstate.with(integerproperty, Integer.valueOf(blockstate.get(integerproperty) + 1)));
								MonarchButterflyEntity.CustomEntity.this.addCropCounter();
							}
						}
					}
				}
			}
		}
	}

	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelButterfly4 extends EntityModel<Entity> {
		private final ModelRenderer main;
		private final ModelRenderer wing_left;
		private final ModelRenderer wing_right;
		private final ModelRenderer head;
		private final ModelRenderer antenna_left;
		private final ModelRenderer antenna_right;
		public ModelButterfly4() {
			textureWidth = 64;
			textureHeight = 64;
			main = new ModelRenderer(this);
			main.setRotationPoint(0.0F, 22.0F, 0.0F);
			main.setTextureOffset(17, 42).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);
			wing_left = new ModelRenderer(this);
			wing_left.setRotationPoint(2.0F, 0.0F, 0.0F);
			main.addChild(wing_left);
			wing_left.setTextureOffset(-19, 21).addBox(-0.5F, 0.0F, -10.0F, 24.0F, 0.0F, 20.0F, 0.0F, false);
			wing_right = new ModelRenderer(this);
			wing_right.setRotationPoint(-1.0F, 0.0F, 0.0F);
			main.addChild(wing_right);
			wing_right.setTextureOffset(-19, 1).addBox(-24.5F, 0.0F, -10.0F, 24.0F, 0.0F, 20.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 0.0F, -4.0F);
			main.addChild(head);
			head.setTextureOffset(1, 42).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
			antenna_left = new ModelRenderer(this);
			antenna_left.setRotationPoint(-1.5F, -1.5F, -4.0F);
			head.addChild(antenna_left);
			antenna_left.setTextureOffset(1, 51).addBox(3.0F, -3.5F, -4.0F, 0.0F, 4.0F, 4.0F, 0.0F, false);
			antenna_right = new ModelRenderer(this);
			antenna_right.setRotationPoint(1.5F, -1.0F, -4.0F);
			head.addChild(antenna_right);
			antenna_right.setTextureOffset(1, 46).addBox(-3.0F, -4.0F, -4.0F, 0.0F, 4.0F, 4.0F, 0.0F, false);
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

		@Override
		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			this.antenna_left.rotateAngleX = MathHelper.cos(f2 * 0.03F) * (float) Math.PI * 0.15F;
			this.antenna_right.rotateAngleX = MathHelper.cos(f2 * 0.031F) * (float) Math.PI * 0.15F;
			boolean flag = e.onGround && e.getMotion().lengthSquared() < 2.0E-7D;
			if (flag) {
				this.main.rotateAngleX = -0.0873F;
				this.wing_right.rotateAngleZ = 1.0F + -(MathHelper.cos(f2 * 2.1F) * (float) Math.PI * 0.15F);
				this.wing_left.rotateAngleZ = -this.wing_right.rotateAngleZ;
			} else {
				this.wing_right.rotateAngleZ = -(MathHelper.cos(f2 * 1.7F) * (float) Math.PI * 0.3F);
				this.wing_left.rotateAngleZ = MathHelper.cos(f2 * 1.7F) * (float) Math.PI * 0.3F;
				this.main.rotateAngleX = -0.0873F + -(MathHelper.cos(f2 * 0.05F) * (float) Math.PI * 0.01F);
			}
		}
	}
}
