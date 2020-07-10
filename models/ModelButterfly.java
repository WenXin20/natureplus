// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelButterfly extends EntityModel<Entity> {
	private final ModelRenderer main;
	private final ModelRenderer wing_left;
	private final ModelRenderer wing_right;
	private final ModelRenderer head;
	private final ModelRenderer antenna_left;
	private final ModelRenderer antenna_right;

	public ModelButterfly() {
		textureWidth = 64;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 24.0F, 0.0F);
		main.setTextureOffset(0, 12).addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 8.0F, 0.0F, false);

		wing_left = new ModelRenderer(this);
		wing_left.setRotationPoint(0.5F, 23.5F, 1.0F);
		setRotationAngle(wing_left, 0.0F, 0.0F, -0.6109F);
		wing_left.setTextureOffset(0, 10).addBox(0.0F, 0.0F, -5.0F, 10.0F, 0.0F, 10.0F, 0.0F, false);

		wing_right = new ModelRenderer(this);
		wing_right.setRotationPoint(-0.5F, 23.5F, 1.0F);
		setRotationAngle(wing_right, 0.0F, 0.0F, 0.6109F);
		wing_right.setTextureOffset(0, 0).addBox(-10.0F, 0.0F, -5.0F, 10.0F, 0.0F, 10.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 23.5F, -3.0F);
		head.setTextureOffset(0, 0).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		antenna_left = new ModelRenderer(this);
		antenna_left.setRotationPoint(-0.5F, -0.25F, -1.0F);
		head.addChild(antenna_left);
		antenna_left.setTextureOffset(0, 0).addBox(1.0F, -1.25F, -3.0F, 0.0F, 2.0F, 3.0F, 0.0F, false);

		antenna_right = new ModelRenderer(this);
		antenna_right.setRotationPoint(0.5F, -0.25F, -1.0F);
		head.addChild(antenna_right);
		antenna_right.setTextureOffset(0, 2).addBox(-1.0F, -1.25F, -3.0F, 0.0F, 2.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		main.render(matrixStack, buffer, packedLight, packedOverlay);
		wing_left.render(matrixStack, buffer, packedLight, packedOverlay);
		wing_right.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
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