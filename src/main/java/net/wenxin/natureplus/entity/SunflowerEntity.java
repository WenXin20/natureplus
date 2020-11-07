
package net.wenxin.natureplus.entity;

import net.wenxin.natureplus.procedures.SunflowerSpawnSunProcedure;
import net.wenxin.natureplus.procedures.SunflowerNaturalSpawnProcedure;
import net.wenxin.natureplus.procedures.SpadeRemoveSunflowerProcedure;
import net.wenxin.natureplus.procedures.EntityTimerSunflowerProcedure;
import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;
import net.wenxin.natureplus.item.SunItem;
import net.wenxin.natureplus.item.SpikeItem;
import net.wenxin.natureplus.item.PeaItem;
import net.wenxin.natureplus.item.FrozenPeaItem;
import net.wenxin.natureplus.item.CornItem;
import net.wenxin.natureplus.RenderTypeDictionary;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableList;

@NatureplusModElements.ModElement.Tag
public class SunflowerEntity extends NatureplusModElements.ModElement {
	public static EntityType entity = null;
	public SunflowerEntity(NatureplusModElements instance) {
		super(instance, 175);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.7f, 1.2f)).build("sunflower")
						.setRegistryName("sunflower");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -13312, -3381760, new Item.Properties().group(PlantsVsZombiesTabItemGroup.tab))
				.setRegistryName("sunflower_spawn_egg"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 15, 1, 4));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> {
					int x = pos.getX();
					int y = pos.getY();
					int z = pos.getZ();
					return SunflowerNaturalSpawnProcedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world));
				});
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelSunflower(), 0.3f) {
				{
					this.addLayer(new GlowingLayer<>(this));
				}
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("natureplus:textures/sunflower.png");
				}
			};
		});
	}
	public static class CustomEntity extends AnimalEntity {
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
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new BreedGoal(this, 0.8));
			this.goalSelector.addGoal(2, new TemptGoal(this, 0, Ingredient.fromItems(SunItem.block), false));
			this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, (float) 6));
			this.goalSelector.addGoal(2, new LookAtGoal(this, MonsterEntity.class, (float) 6));
			this.goalSelector.addGoal(3, new LookAtGoal(this, MobEntity.class, (float) 6));
			this.goalSelector.addGoal(4, new SwimGoal(this));
			this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
		}

		@Override
		public boolean isBreedingItem(ItemStack stack) {
			if (stack == null)
				return false;
			if (new ItemStack(SunItem.block, (int) (1)).getItem() == stack.getItem())
				return true;
			return false;
		}

		@Override
		public AgeableEntity createChild(AgeableEntity ageable) {
			CustomEntity retval = (CustomEntity) entity.create(this.world);
			retval.onInitialSpawn(this.world, this.world.getDifficultyForLocation(new BlockPos(retval)), SpawnReason.BREEDING,
					(ILivingEntityData) null, (CompoundNBT) null);
			return retval;
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
		public boolean canBePushed() {
			return false;
		}

		@Override
		public void livingTick() {
			super.livingTick();
			this.setMotion(this.getMotion().mul(0, 1, 0));
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
				SpadeRemoveSunflowerProcedure.executeProcedure($_dependencies);
			}
			return super.attackEntityFrom(source, amount);
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
				EntityTimerSunflowerProcedure.executeProcedure($_dependencies);
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
				SunflowerSpawnSunProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.1);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0);
			this.getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class GlowingLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		public GlowingLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			if (!entitylivingbaseIn.isInvisible()) {
				IVertexBuilder ivertexbuilder = bufferIn
						.getBuffer(RenderTypeDictionary.getEntityGlow(new ResourceLocation("natureplus:textures/sunflower_glow.png")));
				this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
			}
		}
	}

	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelSunflower<T extends Entity> extends AgeableModel<T> {
		private final ModelRenderer main;
		private final ModelRenderer head;
		public ModelSunflower() {
			textureWidth = 64;
			textureHeight = 64;
			main = new ModelRenderer(this);
			main.setRotationPoint(0.0F, 24.0F, 1.0F);
			main.setTextureOffset(0, 18).addBox(-1.0F, -10.0F, -1.0F, 2.0F, 10.0F, 2.0F, 0.0F, false);
			main.setTextureOffset(13, 5).addBox(0.0F, -0.5F, 0.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);
			main.setTextureOffset(13, 0).addBox(-5.0F, -0.5F, 0.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);
			main.setTextureOffset(10, 10).addBox(0.0F, -0.5F, -5.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);
			main.setTextureOffset(0, 9).addBox(-5.0F, -0.5F, -5.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);
			main.setTextureOffset(8, 15).addBox(-4.0F, -3.51F, 0.0F, 4.0F, 0.0F, 4.0F, 0.0F, false);
			main.setTextureOffset(0, 14).addBox(0.0F, -2.75F, -4.0F, 4.0F, 0.0F, 4.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 13.0F, 1.0F);
			head.setTextureOffset(0, 0).addBox(-4.0F, -5.0F, -2.0F, 8.0F, 8.0F, 1.0F, 0.0F, false);
			head.setTextureOffset(28, 4).addBox(-7.0F, 2.0F, -1.95F, 4.0F, 4.0F, 0.0F, 0.0F, false);
			head.setTextureOffset(20, 20).addBox(-3.0F, 2.0F, -1.95F, 6.0F, 5.0F, 0.0F, 0.0F, false);
			head.setTextureOffset(28, 0).addBox(3.0F, 2.0F, -1.95F, 4.0F, 4.0F, 0.0F, 0.0F, false);
			head.setTextureOffset(18, 25).addBox(3.0F, -4.0F, -1.95F, 5.0F, 6.0F, 0.0F, 0.0F, false);
			head.setTextureOffset(28, 28).addBox(3.0F, -8.0F, -1.95F, 4.0F, 4.0F, 0.0F, 0.0F, false);
			head.setTextureOffset(8, 19).addBox(-3.0F, -9.0F, -1.95F, 6.0F, 5.0F, 0.0F, 0.0F, false);
			head.setTextureOffset(25, 10).addBox(-7.0F, -8.0F, -1.95F, 4.0F, 4.0F, 0.0F, 0.0F, false);
			head.setTextureOffset(8, 24).addBox(-8.0F, -4.0F, -1.95F, 5.0F, 6.0F, 0.0F, 0.0F, false);
			head.setTextureOffset(20, 15).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 3.0F, 2.0F, 0.0F, false);
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
			this.head.rotationPointY = 14.5F + -(MathHelper.cos(f2 * 0.4F) * (float) Math.PI * 0.025F);
		}
	}
}
