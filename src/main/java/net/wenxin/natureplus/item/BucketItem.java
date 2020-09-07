
package net.wenxin.natureplus.item;

import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.BipedModel;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@NatureplusModElements.ModElement.Tag
public class BucketItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:bucket_helmet")
	public static final Item helmet = null;
	@ObjectHolder("natureplus:bucket_chestplate")
	public static final Item body = null;
	@ObjectHolder("natureplus:bucket_leggings")
	public static final Item legs = null;
	@ObjectHolder("natureplus:bucket_boots")
	public static final Item boots = null;
	public BucketItem(NatureplusModElements instance) {
		super(instance, 186);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 165;
			}

			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{2, 5, 6, 2}[slot.getIndex()];
			}

			public int getEnchantability() {
				return 9;
			}

			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_iron"));
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Items.IRON_INGOT, (int) (1)));
			}

			@OnlyIn(Dist.CLIENT)
			public String getName() {
				return "bucket";
			}

			public float getToughness() {
				return 1f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.HEAD, new Item.Properties().group(PlantsVsZombiesTabItemGroup.tab)) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel(1);
				armorModel.bipedHead = new ModelBucketHelmet().bucket;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "natureplus:textures/bucket_helmet.png";
			}
		}.setRegistryName("bucket_helmet"));
	}
	// Made with Blockbench 3.6.6
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelBucketHelmet extends EntityModel<Entity> {
		private final ModelRenderer bucket;
		public ModelBucketHelmet() {
			textureWidth = 128;
			textureHeight = 128;
			bucket = new ModelRenderer(this);
			bucket.setRotationPoint(0.0F, 24.0F, 0.0F);
			setRotationAngle(bucket, -0.1309F, 0.0F, 0.0F);
			bucket.setTextureOffset(20, 11).addBox(4.0F, -14.0F, -5.0F, 1.0F, 10.0F, 10.0F, 0.0F, false);
			bucket.setTextureOffset(1, 20).addBox(-4.0F, -14.0F, -5.0F, 8.0F, 10.0F, 1.0F, 0.0F, false);
			bucket.setTextureOffset(1, 41).addBox(-4.0F, -14.0F, 4.0F, 8.0F, 10.0F, 1.0F, 0.0F, false);
			bucket.setTextureOffset(20, 32).addBox(-5.0F, -14.0F, -5.0F, 1.0F, 10.0F, 10.0F, 0.0F, false);
			bucket.setTextureOffset(7, 1).addBox(-4.0F, -15.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
			bucket.render(matrixStack, buffer, packedLight, packedOverlay);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.bucket.rotateAngleX = -0.5F;
		}
	}
}
