
package net.wenxin.natureplus.entity;

import net.wenxin.natureplus.procedures.SpadeRemoveSnowPeaProcedure;
import net.wenxin.natureplus.procedures.SnowPeaNaturalSpawnProcedure;
import net.wenxin.natureplus.procedures.DisablePushingOfMobsProcedure;
import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;

import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

import com.google.common.collect.ImmutableMap;

@NatureplusModElements.ModElement.Tag
public class SnowPeaEntity extends NatureplusModElements.ModElement {
	public static EntityType entity = null;
	public SnowPeaEntity(NatureplusModElements instance) {
		super(instance, 162);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.5f, 1f)).build("snow_pea")
						.setRegistryName("snow_pea");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -16724788, -16751002, new Item.Properties().group(PlantsVsZombiesTabItemGroup.tab))
				.setRegistryName("snow_pea"));
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
					return SnowPeaNaturalSpawnProcedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world));
				});
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			return new MobRenderer(renderManager, new ModelSnowPea(), 0.4f) {
				{
					this.addLayer(new TranslucentLayer<>(this));
				}
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("natureplus:textures/snow_pea.png");
				}
			};
		});
	}
	public static class CustomEntity extends CreatureEntity implements IRangedAttackMob {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public boolean hasTransparency() {
			return true;
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
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, MobEntity.class, 10, true, true, (entity) -> {
				return entity instanceof IMob && !(entity instanceof CreeperEntity);
			}));
			this.targetSelector.addGoal(2, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, (float) 6));
			this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(5, new SwimGoal(this));
			this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.25, 40, 20) {
				@Override
				public boolean shouldContinueExecuting() {
					return this.shouldExecute();
				}
			});
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		@Override
		public boolean canDespawn(double distanceToClosestPlayer) {
			return false;
		}

		// protected void dropSpecialItems(DamageSource source, int looting, boolean
		// recentlyHitIn) {
		// super.dropSpecialItems(source, looting, recentlyHitIn);
		// Entity entity = source.getTrueSource();
		// if (entity instanceof CreeperEntity) {
		// CreeperEntity creeperentity = (CreeperEntity) entity;
		// if (creeperentity.ableToCauseSkullDrop()) {
		// creeperentity.incrementDroppedSkulls();
		// ItemStack itemstack = this.getSkullDrop();
		// if (!itemstack.isEmpty()) {
		// this.entityDropItem(itemstack);
		// }
		// }
		// }
		// }
		//
		// protected ItemStack getSkullDrop() {
		// return new ItemStack(SnowPeaHeadBlock.block);
		// }
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
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.glass.break"));
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

		@Override
		public boolean attackEntityFrom(DamageSource source, float amount) {
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			Entity sourceentity = source.getTrueSource();
			if (source.getImmediateSource() instanceof PeaItem.ArrowCustomEntity)
				return false;
			if (source.getImmediateSource() instanceof FrozenPeaItem.ArrowCustomEntity)
				return false;
			if (source.getImmediateSource() instanceof CornItem.ArrowCustomEntity)
				return false;
			{
				java.util.HashMap<String, Object> $_dependencies = new java.util.HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("sourceentity", sourceentity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				SpadeRemoveSnowPeaProcedure.executeProcedure($_dependencies);
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
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
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
			FrozenPeaItem.shoot(this, target);
		}
	}

	@OnlyIn(Dist.CLIENT)
	private static class TranslucentLayer<T extends Entity, M extends EntityModel<T>> extends LayerRenderer<T, M> {
		public TranslucentLayer(IEntityRenderer<T, M> er) {
			super(er);
		}

		public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing,
				float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
			if (!entitylivingbaseIn.isInvisible()) {
				IVertexBuilder ivertexbuilder = bufferIn
						.getBuffer(RenderTypeDictionary.getEntityTranslucent(new ResourceLocation("natureplus:textures/snow_pea_translucent.png")));
				this.getEntityModel().render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
			}
		}
	}

	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelSnowPea extends EntityModel<Entity> {
		private final ModelRenderer main;
		private final ModelRenderer disk;
		private final ModelRenderer head;
		public ModelSnowPea() {
			textureWidth = 64;
			textureHeight = 64;
			main = new ModelRenderer(this);
			main.setRotationPoint(0.0F, 24.0F, 1.0F);
			main.setTextureOffset(14, 28).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 2.0F, 0.0F, false);
			main.setTextureOffset(0, 20).addBox(0.0F, -0.5F, 0.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);
			main.setTextureOffset(16, 0).addBox(-5.0F, -0.5F, 0.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);
			main.setTextureOffset(10, 15).addBox(0.0F, -0.5F, -5.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);
			main.setTextureOffset(0, 15).addBox(-5.0F, -0.5F, -5.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);
			disk = new ModelRenderer(this);
			disk.setRotationPoint(0.0F, -8.0F, 0.0F);
			main.addChild(disk);
			disk.setTextureOffset(0, 25).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 14.0F, 1.0F);
			head.setTextureOffset(0, 0).addBox(-3.0F, -5.0F, -5.0F, 6.0F, 6.0F, 9.0F, 0.0F, false);
			head.setTextureOffset(15, 20).addBox(-2.0F, -4.0F, -9.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(0, 30).addBox(0.5F, -4.5F, 4.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(29, 26).addBox(-2.5F, -4.5F, 4.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(29, 3).addBox(0.5F, -1.5F, 4.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(27, 20).addBox(-2.5F, -1.5F, 4.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(22, 28).addBox(-1.0F, -4.9F, 3.25F, 2.0F, 2.0F, 3.0F, 0.0F, false);
			head.setTextureOffset(21, 5).addBox(-1.0F, -1.1F, 3.75F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(0, 4).addBox(0.75F, -3.0F, 3.75F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(0, 0).addBox(-2.75F, -3.0F, 3.75F, 2.0F, 2.0F, 2.0F, 0.0F, false);
			head.setTextureOffset(25, 15).addBox(-1.0F, -3.0F, 3.5F, 2.0F, 2.0F, 3.0F, 0.0F, false);
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
			this.head.rotationPointY = 14.5F + -(MathHelper.cos(f2 * 0.4F) * (float) Math.PI * 0.05F);
			this.disk.rotationPointY = -7.5F + -(MathHelper.cos(f2 * 0.4F) * (float) Math.PI * 0.05F);
		}
	}
}
