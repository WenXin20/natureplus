// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelCaterpillar extends EntityModel<Entity> {
	private final ModelRenderer main;
	private final ModelRenderer antenna_left;
	private final ModelRenderer anttena_right;
	private final ModelRenderer tail_left;
	private final ModelRenderer tail_right;

	public ModelCaterpillar() {
		textureWidth = 32;
		textureHeight = 32;

		main = new ModelRenderer(this);
		main.setRotationPoint(1.0F, 24.0F, -5.0F);
		main.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, 0.0F, 2.0F, 2.0F, 10.0F, 0.0F, false);

		antenna_left = new ModelRenderer(this);
		antenna_left.setRotationPoint(-0.75F, 22.5F, -5.0F);
		antenna_left.setTextureOffset(0, 9).addBox(1.5F, -1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, false);

		anttena_right = new ModelRenderer(this);
		anttena_right.setRotationPoint(0.75F, 22.5F, -5.0F);
		anttena_right.setTextureOffset(6, 9).addBox(-1.5F, -1.5F, -3.0F, 0.0F, 3.0F, 3.0F, 0.0F, false);

		tail_left = new ModelRenderer(this);
		tail_left.setRotationPoint(-0.75F, 22.5F, 5.0F);
		tail_left.setTextureOffset(0, 0).addBox(1.5F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F, false);

		tail_right = new ModelRenderer(this);
		tail_right.setRotationPoint(0.75F, 22.5F, 5.0F);
		tail_right.setTextureOffset(0, 3).addBox(-1.5F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		main.render(matrixStack, buffer, packedLight, packedOverlay);
		antenna_left.render(matrixStack, buffer, packedLight, packedOverlay);
		anttena_right.render(matrixStack, buffer, packedLight, packedOverlay);
		tail_left.render(matrixStack, buffer, packedLight, packedOverlay);
		tail_right.render(matrixStack, buffer, packedLight, packedOverlay);
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