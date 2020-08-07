
package net.wenxin.natureplus.entity;

import net.wenxin.natureplus.procedures.HostileDragonflyNaturalSpawnProcedure;
import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.DungeonHooks;
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
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.BlockState;

import java.util.Random;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

import com.google.common.collect.ImmutableMap;

@NatureplusModElements.ModElement.Tag
public class RedDragonflyEntity extends NatureplusModElements.ModElement {
	public static EntityType entity = null;
	public RedDragonflyEntity(NatureplusModElements instance) {
		super(instance, 132);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(1f, 0.4f)).build("red_dragonfly")
						.setRegistryName("red_dragonfly");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -3407872, -39322, new Item.Properties().group(NaturePlusTabItemGroup.tab))
				.setRegistryName("red_dragonfly"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 15, 1, 4));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> {
					int x = pos.getX();
					int y = pos.getY();
					int z = pos.getZ();
					return HostileDragonflyNaturalSpawnProcedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world));
				});
		DungeonHooks.addDungeonMob(entity, 180);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelDragonfly(), 0.4f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("natureplus:textures/red_dragonfly.png");
				}
			};
		});
	}
	public static class CustomEntity extends MonsterEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			this.moveController = new FlyingMovementController(this, 10, true);
			this.navigator = new FlyingPathNavigator(this, this.world);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.8, false));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, MonarchButterflyEntity.CustomEntity.class, true, false));
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, MonarchCaterpillarEntity.CustomEntity.class, true, false));
			this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, PlayerEntity.class, true, false));
			this.goalSelector.addGoal(6, new RandomWalkingGoal(this, 1.5, 20) {
				@Override
				protected Vec3d getPosition() {
					Random random = CustomEntity.this.getRNG();
					double dir_x = CustomEntity.this.getPosX() + ((random.nextFloat() * 2 - 1) * 16);
					double dir_y = CustomEntity.this.getPosY() + ((random.nextFloat() * 2 - 1) * 16);
					double dir_z = CustomEntity.this.getPosZ() + ((random.nextFloat() * 2 - 1) * 16);
					return new Vec3d(dir_x, dir_y, dir_z);
				}
			});
			this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(8, new SwimGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.ARTHROPOD;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			this.entityDropItem(new ItemStack(Items.STRING, (int) (1)));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:squitch"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:bug_squish"));
		}

		@Override
		public boolean onLivingFall(float l, float d) {
			return false;
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.5);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2);
			if (this.getAttribute(SharedMonsterAttributes.FLYING_SPEED) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.FLYING_SPEED);
			this.getAttribute(SharedMonsterAttributes.FLYING_SPEED).setBaseValue(1.5);
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
	public static class ModelDragonfly extends EntityModel<Entity> {
		private final ModelRenderer main;
		private final ModelRenderer torso;
		private final ModelRenderer wing_front_left;
		private final ModelRenderer wing_back_left;
		private final ModelRenderer wing_front_right;
		private final ModelRenderer wing_back_right;
		private final ModelRenderer front_legs;
		private final ModelRenderer middle_legs;
		private final ModelRenderer back_legs;
		private final ModelRenderer head;
		public ModelDragonfly() {
			textureWidth = 64;
			textureHeight = 64;
			main = new ModelRenderer(this);
			main.setRotationPoint(0.0F, 21.0F, -5.0F);
			setRotationAngle(main, -0.0873F, 0.0F, 0.0F);
			main.setTextureOffset(0, 24).addBox(-1.0F, -1.0F, 8.0F, 2.0F, 2.0F, 14.0F, 0.0F, false);
			torso = new ModelRenderer(this);
			torso.setRotationPoint(0.0F, 0.5F, 7.0F);
			main.addChild(torso);
			torso.setTextureOffset(18, 24).addBox(-2.0F, -2.0F, -7.0F, 4.0F, 3.0F, 8.0F, 0.0F, false);
			wing_front_left = new ModelRenderer(this);
			wing_front_left.setRotationPoint(0.5F, -1.501F, 2.0F);
			main.addChild(wing_front_left);
			wing_front_left.setTextureOffset(0, 18).addBox(0.0F, 0.0F, -4.0F, 18.0F, 0.0F, 6.0F, 0.0F, false);
			wing_back_left = new ModelRenderer(this);
			wing_back_left.setRotationPoint(0.5F, -1.501F, 5.0F);
			main.addChild(wing_back_left);
			setRotationAngle(wing_back_left, 0.0F, -0.0873F, 0.0F);
			wing_back_left.setTextureOffset(0, 12).addBox(0.0F, 0.0F, -1.0F, 18.0F, 0.0F, 6.0F, 0.0F, false);
			wing_front_right = new ModelRenderer(this);
			wing_front_right.setRotationPoint(-0.5F, -1.501F, 2.0F);
			main.addChild(wing_front_right);
			wing_front_right.setTextureOffset(0, 6).addBox(-18.0F, 0.0F, -4.0F, 18.0F, 0.0F, 6.0F, 0.0F, false);
			wing_back_right = new ModelRenderer(this);
			wing_back_right.setRotationPoint(-0.5F, -1.501F, 5.0F);
			main.addChild(wing_back_right);
			setRotationAngle(wing_back_right, 0.0F, 0.0873F, 0.0F);
			wing_back_right.setTextureOffset(0, 0).addBox(-18.0F, 0.0F, -1.0F, 18.0F, 0.0F, 6.0F, 0.0F, false);
			front_legs = new ModelRenderer(this);
			front_legs.setRotationPoint(0.0F, 1.5F, 1.0F);
			main.addChild(front_legs);
			front_legs.setTextureOffset(2, 4).addBox(-1.5F, -0.5F, 0.0F, 1.0F, 2.0F, 0.0F, 0.0F, false);
			front_legs.setTextureOffset(0, 4).addBox(0.5F, -0.5F, 0.0F, 1.0F, 2.0F, 0.0F, 0.0F, false);
			middle_legs = new ModelRenderer(this);
			middle_legs.setRotationPoint(0.0F, 1.5F, 4.0F);
			main.addChild(middle_legs);
			middle_legs.setTextureOffset(2, 0).addBox(-1.5F, -0.5F, 0.0F, 1.0F, 2.0F, 0.0F, 0.0F, false);
			middle_legs.setTextureOffset(2, 2).addBox(0.5F, -0.5F, 0.0F, 1.0F, 2.0F, 0.0F, 0.0F, false);
			back_legs = new ModelRenderer(this);
			back_legs.setRotationPoint(0.0F, 1.5F, 7.0F);
			main.addChild(back_legs);
			back_legs.setTextureOffset(0, 2).addBox(-1.5F, -0.5F, 0.0F, 1.0F, 2.0F, 0.0F, 0.0F, false);
			back_legs.setTextureOffset(0, 0).addBox(0.5F, -0.5F, 0.0F, 1.0F, 2.0F, 0.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 21.0F, -5.0F);
			head.setTextureOffset(0, 24).addBox(-1.5F, -2.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			main.render(matrixStack, buffer, packedLight, packedOverlay);
			head.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
			boolean flag = e.getMotion().lengthSquared() < 2.0E-7D; // e.onGround &&
			boolean flag2 = e.onGround && e.getMotion().lengthSquared() < 2.0E-7D;
			if (flag) {
				this.wing_front_right.rotateAngleZ = 0.1F + -(MathHelper.cos(f2 * 0.8F) * (float) Math.PI * 0.14F);
				this.wing_back_right.rotateAngleZ = 0.1F + -(MathHelper.cos(f2 * 0.8F) * (float) Math.PI * 0.10F);
				this.wing_front_left.rotateAngleZ = -0.1F + (MathHelper.cos(f2 * 0.8F) * (float) Math.PI * 0.14F);
				this.wing_back_left.rotateAngleZ = -0.1F + (MathHelper.cos(f2 * 0.8F) * (float) Math.PI * 0.10F);
				this.front_legs.rotateAngleX = 0.0F;
				this.middle_legs.rotateAngleX = 0.0F;
				this.back_legs.rotateAngleX = 0.0F;
			} else {
				this.wing_front_right.rotateAngleZ = -(MathHelper.cos(f2 * 2.0F) * (float) Math.PI * 0.18F);
				this.wing_back_right.rotateAngleZ = -(MathHelper.cos(f2 * 2.0F) * (float) Math.PI * 0.15F);
				this.wing_front_left.rotateAngleZ = MathHelper.cos(f2 * 2.0F) * (float) Math.PI * 0.18F;
				this.wing_back_left.rotateAngleZ = MathHelper.cos(f2 * 2.0F) * (float) Math.PI * 0.15F;
				this.front_legs.rotateAngleX = ((float) Math.PI / 4F);
				this.middle_legs.rotateAngleX = ((float) Math.PI / 4F);
				this.back_legs.rotateAngleX = ((float) Math.PI / 4F);
			}
			if (flag2) {
				this.main.rotateAngleX = -0.0873F;
			} else {
				this.main.rotateAngleX = -0.0873F + -(MathHelper.cos(f2 * 0.05F) * (float) Math.PI * 0.01F);
			}
		}
	}
}
