// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelCorn extends EntityModel<Entity> {
	private final ModelRenderer corn;

	public ModelCorn() {
		textureWidth = 16;
		textureHeight = 16;

		corn = new ModelRenderer(this);
		corn.setRotationPoint(0.0F, 22.0F, 0.0F);
		setRotationAngle(corn, 0.0F, 0.0F, 1.5708F);
		corn.setTextureOffset(0, 0).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 3.0F, 4.0F, 0.0F, false);
		corn.setTextureOffset(0, 7).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		corn.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}