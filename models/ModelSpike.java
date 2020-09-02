// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelSpike extends EntityModel<Entity> {
	private final ModelRenderer arrow;
	private final ModelRenderer bone;

	public ModelSpike() {
		textureWidth = 32;
		textureHeight = 32;

		arrow = new ModelRenderer(this);
		arrow.setRotationPoint(0.0F, 21.5F, 0.0F);
		setRotationAngle(arrow, 0.0F, 0.0F, 1.5708F);
		arrow.setTextureOffset(0, 16).addBox(-13.5F, -7.75F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 0.0F, 0.0F);
		arrow.addChild(bone);
		setRotationAngle(bone, 1.5708F, 0.0F, 0.0F);
		bone.setTextureOffset(0, 0).addBox(-13.5F, -7.75F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
		// previously the render function, render code was moved to a method below
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		arrow.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}