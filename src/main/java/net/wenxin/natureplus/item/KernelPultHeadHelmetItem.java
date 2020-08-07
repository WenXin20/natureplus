
package net.wenxin.natureplus.item;

import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
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
public class KernelPultHeadHelmetItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:kernel_pult_head_helmet")
	public static final Item helmet = null;
	@ObjectHolder("natureplus:kernel_pult_head_chestplate")
	public static final Item body = null;
	@ObjectHolder("natureplus:kernel_pult_head_leggings")
	public static final Item legs = null;
	@ObjectHolder("natureplus:kernel_pult_head_boots")
	public static final Item boots = null;
	public KernelPultHeadHelmetItem(NatureplusModElements instance) {
		super(instance, 184);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 0;
			}

			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{2, 5, 6, 0}[slot.getIndex()];
			}

			public int getEnchantability() {
				return 9;
			}

			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_generic"));
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.EMPTY;
			}

			@OnlyIn(Dist.CLIENT)
			public String getName() {
				return "kernel_pult_head";
			}

			public float getToughness() {
				return 0f;
			}
		};
		elements.items.add(() -> new ArmorItem(armormaterial, EquipmentSlotType.HEAD, new Item.Properties().group(PlantsVsZombiesTabItemGroup.tab)) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public BipedModel getArmorModel(LivingEntity living, ItemStack stack, EquipmentSlotType slot, BipedModel defaultModel) {
				BipedModel armorModel = new BipedModel(1);
				armorModel.bipedHead = new ModelKernelPultHead().head;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "natureplus:textures/kernel_pult_head_helmet.png";
			}
		}.setRegistryName("kernel_pult_head_helmet"));
	}
	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelKernelPultHead extends EntityModel<Entity> {
		private final ModelRenderer head;
		private final ModelRenderer catapult;
		private final ModelRenderer catapult_arm1;
		private final ModelRenderer catapult_arm2;
		private final ModelRenderer catapult_arm3;
		private final ModelRenderer catapult_base;
		private final ModelRenderer corn_kernel;
		public ModelKernelPultHead() {
			textureWidth = 128;
			textureHeight = 128;
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 24.0F, 0.0F);
			head.setTextureOffset(0, 12).addBox(-3.0F, -12.0F, -3.0F, 6.0F, 5.0F, 6.0F, 0.0F, false);
			head.setTextureOffset(20, 31).addBox(-2.0F, -14.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);
			catapult = new ModelRenderer(this);
			catapult.setRotationPoint(0.0F, -13.0F, -0.5F);
			head.addChild(catapult);
			setRotationAngle(catapult, -0.6109F, 0.0F, 0.0F);
			catapult_arm1 = new ModelRenderer(this);
			catapult_arm1.setRotationPoint(0.0F, 0.0F, 0.0F);
			catapult.addChild(catapult_arm1);
			catapult_arm1.setTextureOffset(0, 12).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, 0.0F, false);
			catapult_arm2 = new ModelRenderer(this);
			catapult_arm2.setRotationPoint(0.0F, -4.5F, 0.0F);
			catapult.addChild(catapult_arm2);
			catapult_arm2.setTextureOffset(36, 9).addBox(-0.5F, -0.5F, 0.5F, 1.0F, 1.0F, 4.0F, 0.0F, false);
			catapult_arm3 = new ModelRenderer(this);
			catapult_arm3.setRotationPoint(0.0F, -4.5F, 4.5F);
			catapult.addChild(catapult_arm3);
			setRotationAngle(catapult_arm3, 0.6109F, 0.0F, 0.0F);
			catapult_arm3.setTextureOffset(0, 36).addBox(-0.5F, -0.6F, -0.25F, 1.0F, 1.0F, 4.0F, 0.0F, false);
			catapult_base = new ModelRenderer(this);
			catapult_base.setRotationPoint(0.0F, -6.75F, 7.5F);
			catapult.addChild(catapult_base);
			setRotationAngle(catapult_base, 0.6109F, 0.0F, 0.0F);
			catapult_base.setTextureOffset(32, 31).addBox(-3.0F, -1.5F, -0.25F, 6.0F, 3.0F, 1.0F, 0.0F, false);
			catapult_base.setTextureOffset(0, 32).addBox(-3.0F, -1.5F, 4.75F, 6.0F, 3.0F, 1.0F, 0.0F, false);
			catapult_base.setTextureOffset(32, 35).addBox(2.0F, -1.5F, 0.75F, 1.0F, 3.0F, 4.0F, 0.0F, false);
			catapult_base.setTextureOffset(10, 33).addBox(-3.0F, -1.5F, 0.75F, 1.0F, 3.0F, 4.0F, 0.0F, false);
			catapult_base.setTextureOffset(31, 20).addBox(-2.0F, 0.5F, 0.75F, 4.0F, 1.0F, 4.0F, 0.0F, false);
			corn_kernel = new ModelRenderer(this);
			corn_kernel.setRotationPoint(0.0F, 0.5F, 2.25F);
			catapult_base.addChild(corn_kernel);
			setRotationAngle(corn_kernel, -0.4363F, 0.0F, 0.0F);
			corn_kernel.setTextureOffset(33, 14).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
			corn_kernel.setTextureOffset(0, 0).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 1.0F, 2.0F, 0.0F, false);
		}

		@Override
		public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
				float alpha) {
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
			this.catapult.rotateAngleX = -0.5F + MathHelper.cos(f2 * 0.25F) * (float) Math.PI * 0.05F;
			this.corn_kernel.rotateAngleX = -0.4363F + MathHelper.cos(f2 * 0.25F) * (float) Math.PI * 0.1F;
		}
	}
}
