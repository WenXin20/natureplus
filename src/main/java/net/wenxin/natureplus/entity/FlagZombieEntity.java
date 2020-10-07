
package net.wenxin.natureplus.entity;

import net.wenxin.natureplus.procedures.ZombieNaturalSpawnProcedure;
import net.wenxin.natureplus.procedures.GrantKillFlagZombieProcedure;
import net.wenxin.natureplus.procedures.FlagZombieSpawnZombiesProcedure;
import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;
import net.wenxin.natureplus.item.IronSpadeItem;
import net.wenxin.natureplus.NatureplusModElements;
import net.wenxin.natureplus.BannerPatternNew;

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
import net.minecraft.world.IWorld;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.Difficulty;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.tileentity.BannerPattern;
import net.minecraft.network.IPacket;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.DyeColor;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.block.BlockState;

import java.util.Map;
import java.util.HashMap;

import com.google.common.collect.ImmutableMap;

@NatureplusModElements.ModElement.Tag
public class FlagZombieEntity extends NatureplusModElements.ModElement {
	public static EntityType entity = null;
	public FlagZombieEntity(NatureplusModElements instance) {
		super(instance, 183);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.MONSTER).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.97f)).build("flag_zombie")
						.setRegistryName("flag_zombie");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -16737895, -6750208, new Item.Properties().group(PlantsVsZombiesTabItemGroup.tab))
				.setRegistryName("flag_zombie_spawn_egg"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.getSpawns(EntityClassification.MONSTER).add(new Biome.SpawnListEntry(entity, 10, 1, 1));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> {
					int x = pos.getX();
					int y = pos.getY();
					int z = pos.getZ();
					return ZombieNaturalSpawnProcedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world));
				});
		DungeonHooks.addDungeonMob(entity, 180);
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> {
			BipedRenderer customRender = new BipedRenderer(renderManager, new BipedModel(0), 0.5f) {
				@Override
				public ResourceLocation getEntityTexture(Entity entity) {
					return new ResourceLocation("natureplus:textures/pvz_zombie2.png");
				}
			};
			customRender.addLayer(new BipedArmorLayer(customRender, new BipedModel(0.5f), new BipedModel(1)));
			return customRender;
		});
	}
	public static class CustomEntity extends ZombieEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 10;
			setNoAI(false);
			this.setItemStackToSlot(EquipmentSlotType.HEAD, createBrainBanner());
		}

		public static ItemStack createBrainBanner() {
			ItemStack itemstack = new ItemStack(Items.RED_BANNER);
			CompoundNBT compoundnbt = itemstack.getOrCreateChildTag("BlockEntityTag");
			ListNBT listnbt = (new BannerPattern.Builder()).setPatternWithColor(BannerPatternNew.BRAIN, DyeColor.PINK).func_222476_a();
			compoundnbt.put("Patterns", listnbt);
			itemstack.setDisplayName((new TranslationTextComponent("block.natureplus.brain_banner")).applyTextStyle(TextFormatting.RED));
			return itemstack;
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
			this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::isBreakDoorsTaskSet));
			this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
			this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setCallsForHelp(this.getClass()));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, SunflowerEntity.CustomEntity.class, true));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
			this.targetSelector.addGoal(5,
					new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.TARGET_DRY_BABY));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEAD;
		}

		protected void setEquipmentBasedOnDifficulty(DifficultyInstance difficulty) {
			super.setEquipmentBasedOnDifficulty(difficulty);
			if (this.rand.nextFloat() < (this.world.getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F)) {
				int i = this.rand.nextInt(3);
				if (i == 0) {
					this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(IronSpadeItem.block));
				} else {
					this.setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
				}
			}
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
			Entity entity = source.getTrueSource();
			if (entity instanceof CreeperEntity) {
				CreeperEntity creeperentity = (CreeperEntity) entity;
				if (creeperentity.ableToCauseSkullDrop()) {
					creeperentity.incrementDroppedSkulls();
					ItemStack itemstack = this.getSkullDrop();
					if (!itemstack.isEmpty()) {
						this.entityDropItem(itemstack);
					}
				}
			}
		}

		protected ItemStack getSkullDrop() {
			return new ItemStack(Items.ZOMBIE_HEAD);
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:zombie_groan"));
		}

		@Override
		public void playStepSound(BlockPos pos, BlockState blockIn) {
			this.playSound((net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.step")), 0.15f,
					1);
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.hurt"));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.death"));
		}

		@Override
		public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, ILivingEntityData livingdata,
				CompoundNBT tag) {
			ILivingEntityData retval = super.onInitialSpawn(world, difficulty, reason, livingdata, tag);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("world", world);
				FlagZombieSpawnZombiesProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}

		@Override
		public void onDeath(DamageSource source) {
			super.onDeath(source);
			double x = this.getPosX();
			double y = this.getPosY();
			double z = this.getPosZ();
			Entity sourceentity = source.getTrueSource();
			Entity entity = this;
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("sourceentity", sourceentity);
				GrantKillFlagZombieProcedure.executeProcedure($_dependencies);
			}
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			this.getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}
	}
}
