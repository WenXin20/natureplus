// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelSunflower extends EntityModel<Entity> {
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
		
		this.head.rotationPointY = 14.5F + -(MathHelper.cos(f2 * 0.4F) * (float)Math.PI * 0.025F);
	}
}