// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelButterflyCocoon extends EntityModel<Entity> {
	private final ModelRenderer main;
	private final ModelRenderer stem;

	public ModelButterflyCocoon() {
		textureWidth = 16;
		textureHeight = 16;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 24.0F, 0.0F);
		main.setTextureOffset(0, 0).addBox(-1.5F, -6.0F, -1.5F, 3.0F, 6.0F, 3.0F, 0.0F, false);

		stem = new ModelRenderer(this);
		stem.setRotationPoint(0.0F, -6.0F, 0.0F);
		main.addChild(stem);
		setRotationAngle(stem, 0.0F, -0.7854F, 0.0F);
		stem.setTextureOffset(0, 0).addBox(0.0F, -1.0F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, false);
		stem.setTextureOffset(0, 0).addBox(-0.5F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		main.render(matrixStack, buffer, packedLight, packedOverlay);
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