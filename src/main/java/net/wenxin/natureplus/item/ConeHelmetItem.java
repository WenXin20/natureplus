
package net.wenxin.natureplus.item;

import net.wenxin.natureplus.procedures.ConeHatGrantAdvancementProcedure;
import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.block.Blocks;

import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.mojang.blaze3d.matrix.MatrixStack;

@NatureplusModElements.ModElement.Tag
public class ConeHelmetItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:cone_helmet")
	public static final Item helmet = null;
	@ObjectHolder("natureplus:cone_chestplate")
	public static final Item body = null;
	@ObjectHolder("natureplus:cone_leggings")
	public static final Item legs = null;
	@ObjectHolder("natureplus:cone_boots")
	public static final Item boots = null;
	public ConeHelmetItem(NatureplusModElements instance) {
		super(instance, 191);
	}

	@Override
	public void initElements() {
		IArmorMaterial armormaterial = new IArmorMaterial() {
			public int getDurability(EquipmentSlotType slot) {
				return new int[]{13, 15, 16, 11}[slot.getIndex()] * 25;
			}

			public int getDamageReductionAmount(EquipmentSlotType slot) {
				return new int[]{2, 5, 6, 1}[slot.getIndex()];
			}

			public int getEnchantability() {
				return 9;
			}

			public net.minecraft.util.SoundEvent getSoundEvent() {
				return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather"));
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Blocks.ORANGE_CONCRETE, (int) (1)));
			}

			@OnlyIn(Dist.CLIENT)
			public String getName() {
				return "cone";
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
				armorModel.bipedHead = new ModelConeHelmet().cone;
				armorModel.isSneak = living.isSneaking();
				armorModel.isSitting = defaultModel.isSitting;
				armorModel.isChild = living.isChild();
				return armorModel;
			}

			@Override
			public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
				return "natureplus:textures/cone_helmet.png";
			}

			@Override
			public void onArmorTick(ItemStack itemstack, World world, PlayerEntity entity) {
				super.onArmorTick(itemstack, world, entity);
				double x = entity.getPosX();
				double y = entity.getPosY();
				double z = entity.getPosZ();
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					ConeHatGrantAdvancementProcedure.executeProcedure($_dependencies);
				}
			}
		}.setRegistryName("cone_helmet"));
	}
	// Made with Blockbench
	// Paste this code into your mod.
	// Make sure to generate all required imports
	public static class ModelConeHelmet extends EntityModel<Entity> {
		private final ModelRenderer cone;
		public ModelConeHelmet() {
			textureWidth = 128;
			textureHeight = 128;
			cone = new ModelRenderer(this);
			cone.setRotationPoint(0.0F, 24.0F, 0.0F);
			addBoxHelper(cone, 0, 13, 4.0F, -8.0F, -6.0F, 2, 1, 12, 0.0F, false);
			addBoxHelper(cone, 30, 18, -4.0F, -8.0F, -6.0F, 8, 1, 2, 0.0F, false);
			addBoxHelper(cone, 16, 0, -4.0F, -8.0F, 4.0F, 8, 1, 2, 0.0F, false);
			addBoxHelper(cone, 0, 0, -6.0F, -8.0F, -6.0F, 2, 1, 12, 0.0F, false);
			addBoxHelper(cone, 20, 5, 3.0F, -13.0F, -4.0F, 1, 5, 8, 0.0F, false);
			addBoxHelper(cone, 14, 33, -3.0F, -13.0F, -4.0F, 6, 5, 1, 0.0F, false);
			addBoxHelper(cone, 30, 21, -3.0F, -13.0F, 3.0F, 6, 5, 1, 0.0F, false);
			addBoxHelper(cone, 20, 20, -4.0F, -13.0F, -4.0F, 1, 5, 8, 0.0F, false);
			addBoxHelper(cone, 30, 0, 2.0F, -18.0F, -3.0F, 1, 5, 6, 0.0F, false);
			addBoxHelper(cone, 16, 18, -2.0F, -18.0F, -3.0F, 4, 5, 1, 0.0F, false);
			addBoxHelper(cone, 16, 3, -2.0F, -18.0F, 2.0F, 4, 5, 1, 0.0F, false);
			addBoxHelper(cone, 0, 26, -3.0F, -18.0F, -3.0F, 1, 5, 6, 0.0F, false);
			addBoxHelper(cone, 0, 13, 1.0F, -23.0F, -2.0F, 1, 5, 4, 0.0F, false);
			addBoxHelper(cone, 8, 26, -1.0F, -23.0F, -2.0F, 2, 5, 1, 0.0F, false);
			addBoxHelper(cone, 0, 26, -1.0F, -23.0F, 1.0F, 2, 5, 1, 0.0F, false);
			addBoxHelper(cone, 0, 0, -2.0F, -23.0F, -2.0F, 1, 5, 4, 0.0F, false);
		}

		@Override
		public void render(MatrixStack ms, IVertexBuilder vb, int i1, int i2, float f1, float f2, float f3, float f4) {
			cone.render(ms, vb, i1, i2, f1, f2, f3, f4);
		}

		public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
			this.cone.rotateAngleY = f3 / (180F / (float) Math.PI);
			this.cone.rotateAngleX = f4 / (180F / (float) Math.PI);
		}
	}
	@OnlyIn(Dist.CLIENT)
	public static void addBoxHelper(ModelRenderer renderer, int texU, int texV, float x, float y, float z, int dx, int dy, int dz, float delta) {
		addBoxHelper(renderer, texU, texV, x, y, z, dx, dy, dz, delta, renderer.mirror);
	}

	@OnlyIn(Dist.CLIENT)
	public static void addBoxHelper(ModelRenderer renderer, int texU, int texV, float x, float y, float z, int dx, int dy, int dz, float delta,
			boolean mirror) {
		renderer.mirror = mirror;
		renderer.addBox("", x, y, z, dx, dy, dz, delta, texU, texV);
	}
}
