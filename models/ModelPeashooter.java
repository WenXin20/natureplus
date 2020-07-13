// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelPeashooter extends EntityModel<Entity> {
	private final ModelRenderer main;
	private final ModelRenderer disk;
	private final ModelRenderer head;

	public ModelPeashooter() {
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
		head.setTextureOffset(17, 5).addBox(-2.0F, -5.01F, 3.0F, 4.0F, 0.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(15, 20).addBox(-2.0F, -4.0F, -9.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
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

		this.head.rotationPointY = 14.5F + -(MathHelper.cos(f2 * 0.4F) * (float)Math.PI * 0.05F);
		
		this.disk.rotationPointY = -7.5F + -(MathHelper.cos(f2 * 0.4F) * (float)Math.PI * 0.05F);
	}
}