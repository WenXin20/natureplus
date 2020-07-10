// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelKernelPult extends EntityModel<Entity> {
	private final ModelRenderer main;
	private final ModelRenderer leaves;
	private final ModelRenderer head;
	private final ModelRenderer catapult;
	private final ModelRenderer catapult_arm1;
	private final ModelRenderer catapult_arm2;
	private final ModelRenderer catapult_arm3;
	private final ModelRenderer catapult_base;
	private final ModelRenderer corn_kernel;

	public ModelKernelPult() {
		textureWidth = 64;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 24.0F, 0.0F);
		main.setTextureOffset(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 8.0F, 0.0F, false);

		leaves = new ModelRenderer(this);
		leaves.setRotationPoint(0.0F, 0.0F, 0.0F);
		main.addChild(leaves);
		leaves.setTextureOffset(26, 26).addBox(-4.0F, -0.5F, -9.0F, 4.0F, 0.0F, 5.0F, 0.0F, false);
		leaves.setTextureOffset(18, 24).addBox(0.0F, -0.5F, -9.0F, 4.0F, 0.0F, 5.0F, 0.0F, false);
		leaves.setTextureOffset(0, 23).addBox(4.0F, -0.5F, -9.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);
		leaves.setTextureOffset(30, 0).addBox(4.0F, -0.5F, -4.0F, 5.0F, 0.0F, 4.0F, 0.0F, false);
		leaves.setTextureOffset(10, 29).addBox(4.0F, -0.5F, 0.0F, 5.0F, 0.0F, 4.0F, 0.0F, false);
		leaves.setTextureOffset(19, 0).addBox(4.0F, -0.5F, 4.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);
		leaves.setTextureOffset(23, 12).addBox(0.0F, -0.5F, 4.0F, 4.0F, 0.0F, 5.0F, 0.0F, false);
		leaves.setTextureOffset(10, 23).addBox(-4.0F, -0.5F, 4.0F, 4.0F, 0.0F, 5.0F, 0.0F, false);
		leaves.setTextureOffset(19, 19).addBox(-9.0F, -0.5F, 4.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);
		leaves.setTextureOffset(28, 5).addBox(-9.0F, -0.5F, 0.0F, 5.0F, 0.0F, 4.0F, 0.0F, false);
		leaves.setTextureOffset(0, 28).addBox(-9.0F, -0.5F, -4.0F, 5.0F, 0.0F, 4.0F, 0.0F, false);
		leaves.setTextureOffset(13, 13).addBox(-9.0F, -0.5F, -9.0F, 5.0F, 0.0F, 5.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 21.0F, 0.0F);
		head.setTextureOffset(0, 12).addBox(-3.0F, -4.0F, -3.0F, 6.0F, 5.0F, 6.0F, 0.0F, false);
		head.setTextureOffset(20, 31).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 2.0F, 4.0F, 0.0F, false);

		catapult = new ModelRenderer(this);
		catapult.setRotationPoint(0.0F, -5.0F, -0.5F);
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
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		main.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
	}
}