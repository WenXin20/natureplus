///**
// * This mod element is always locked. Enter your code in the methods below.
// * If you don't need some of these methods, you can remove them as they
// * are overrides of the base class NatureplusModElements.ModElement.
// *
// * You can register new events in this class too.
// *
// * As this class is loaded into mod element list, it NEEDS to extend
// * ModElement class. If you remove this extend statement or remove the
// * constructor, the compilation will fail.
// *
// * If you want to make a plain independent class, create it using
// * Project Browser - New... and make sure to make the class
// * outside net.wenxin.natureplus as this package is managed by MCreator.
// *
// * If you change workspace package, modid or prefix, you will need
// * to manually adapt this file to these changes or remake it.
//*/
//package net.wenxin.natureplus;
//
//import net.minecraft.entity.Entity;
//import net.minecraft.client.renderer.model.ModelRenderer;
//import net.minecraft.client.renderer.entity.model.GenericHeadModel;
//import net.minecraft.client.renderer.Quaternion;
//
//import com.mojang.blaze3d.vertex.IVertexBuilder;
//import com.mojang.blaze3d.matrix.MatrixStack;
//
//@NatureplusModElements.ModElement.Tag
//public class PeashooterHeadModel extends GenericHeadModel {
//	/**
//	 * Do not remove this constructor
//	 */
//	// public PeashooterHeadModel(NatureplusModElements instance) {
//	// super(instance, 950);
//	// }
//	//
//	// @Override
//	// public void initElements() {
//	// }
//	//
//	// @Override
//	// public void init(FMLCommonSetupEvent event) {
//	// }
//	//
//	// @Override
//	// public void serverLoad(FMLServerStartingEvent event) {
//	// }
//	//
//	// @OnlyIn(Dist.CLIENT)
//	// @Override
//	// public void clientLoad(FMLClientSetupEvent event) {
//	// }
//	private final ModelRenderer head;
//	public PeashooterHeadModel() {
//		textureWidth = 64;
//		textureHeight = 64;
//		head = new ModelRenderer(this);
//		head.setRotationPoint(0.0F, 24.0F, 0.0F);
//		head.setTextureOffset(0, 25).addBox(-2.0F, -8.0F, -1.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
//		head.setTextureOffset(17, 5).addBox(-2.0F, -14.01F, 4.0F, 4.0F, 0.0F, 4.0F, 0.0F, false);
//		head.setTextureOffset(15, 20).addBox(-2.0F, -13.0F, -8.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
//		head.setTextureOffset(0, 0).addBox(-3.0F, -14.0F, -4.0F, 6.0F, 6.0F, 9.0F, 0.0F, false);
//	}
//
//	@Override
//	public void func_225603_a_(float p_225603_1_, float p_225603_2_, float p_225603_3_) {
//		this.head.rotateAngleY = p_225603_2_ * ((float) Math.PI / 180F);
//		this.head.rotateAngleX = p_225603_3_ * ((float) Math.PI / 180F);
//	}
//
//	@Override
//	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
//			float alpha) {
//		matrixStack.push();
//		matrixStack.translate(0, -0.75F, 0);
//		matrixStack.scale(0.5F, 0.5F, 0.5F);
//		matrixStack.rotate(new Quaternion(0, 180, 0, true));
//		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
//		matrixStack.pop();
//		System.out.println("render1");
//
//	}
//
//	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//		modelRenderer.rotateAngleX = x;
//		modelRenderer.rotateAngleY = y;
//		modelRenderer.rotateAngleZ = z;
//	}
//
//	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
//		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
//		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
//	}
//}
