
package net.wenxin.natureplus.item;

import org.omg.CORBA.ObjectHolder;

import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class PeashooterHeadHelmetItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:peashooter_head_helmet")
	public static final Item helmet = null;
	@ObjectHolder("natureplus:peashooter_head_body")
	public static final Item body = null;
	@ObjectHolder("natureplus:peashooter_head_legs")
	public static final Item legs = null;
	@ObjectHolder("natureplus:peashooter_head_boots")
	public static final Item boots = null;
	public PeashooterHeadHelmetItem(NatureplusModElements instance) {
		super(instance, 134);
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
				return "peashooter_head_";
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
				armorModel.bipedHead = new ModelPeashooterHead().head;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "natureplus:textures/peashooter2.png";
			}
		}.setRegistryName("peashooter_head_helmet"));
	}
	// Made with Blockbench 3.5.4
	// Exported for Minecraft version 1.15
	// Paste this class into your mod and generate all required imports
	public static class ModelPeashooterHead extends EntityModel<Entity> {
		private final ModelRenderer head;
		public ModelPeashooterHead() {
			textureWidth = 64;
			textureHeight = 64;
			head = new ModelRenderer(this);
			head.setRotationPoint(0.0F, 24.0F, 0.0F);
			head.setTextureOffset(0, 25).addBox(-2.0F, -8.0F, -1.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(17, 5).addBox(-2.0F, -14.01F, 4.0F, 4.0F, 0.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(15, 20).addBox(-2.0F, -13.0F, -8.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
			head.setTextureOffset(0, 0).addBox(-3.0F, -14.0F, -4.0F, 6.0F, 6.0F, 9.0F, 0.0F, false);
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
		}
	}
}
