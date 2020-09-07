// Made with Blockbench 3.6.6
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelBucketHelmet extends EntityModel<Entity> {
	private final ModelRenderer bucket;

	public ModelBucketHelmet() {
		textureWidth = 128;
		textureHeight = 128;

		bucket = new ModelRenderer(this);
		bucket.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotationAngle(bucket, -0.1309F, 0.0F, 0.0F);
		bucket.setTextureOffset(20, 11).addBox(4.0F, -14.0F, -5.0F, 1.0F, 10.0F, 10.0F, 0.0F, false);
		bucket.setTextureOffset(1, 20).addBox(-4.0F, -14.0F, -5.0F, 8.0F, 10.0F, 1.0F, 0.0F, false);
		bucket.setTextureOffset(1, 41).addBox(-4.0F, -14.0F, 4.0F, 8.0F, 10.0F, 1.0F, 0.0F, false);
		bucket.setTextureOffset(20, 32).addBox(-5.0F, -14.0F, -5.0F, 1.0F, 10.0F, 10.0F, 0.0F, false);
		bucket.setTextureOffset(7, 1).addBox(-4.0F, -15.0F, -4.0F, 8.0F, 1.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		bucket.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.bucket.rotateAngleX = -0.5F;
	}
}