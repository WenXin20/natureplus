// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelSnowPeaHead extends EntityModel<Entity> {
	private final ModelRenderer head;

	public ModelSnowPeaHead() {
		textureWidth = 128;
		textureHeight = 128;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 24.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-3.0F, -14.0F, -4.0F, 6.0F, 6.0F, 9.0F, 0.0F, false);
		head.setTextureOffset(15, 20).addBox(-2.0F, -13.0F, -8.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);
		head.setTextureOffset(0, 30).addBox(0.5F, -13.5F, 5.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(29, 26).addBox(-2.5F, -13.5F, 5.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(29, 3).addBox(0.5F, -10.5F, 5.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(27, 20).addBox(-2.5F, -10.5F, 5.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(22, 28).addBox(-1.0F, -13.9F, 4.25F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		head.setTextureOffset(21, 5).addBox(-1.0F, -10.1F, 4.75F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(0, 4).addBox(0.75F, -12.0F, 4.75F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(-2.75F, -12.0F, 4.75F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		head.setTextureOffset(25, 15).addBox(-1.0F, -12.0F, 4.5F, 2.0F, 2.0F, 3.0F, 0.0F, false);
		head.setTextureOffset(0, 25).addBox(-2.0F, -8.0F, -1.0F, 4.0F, 1.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
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