// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelCactus<T extends Entity> extends AgeableModel<T> {
	private final ModelRenderer main;
	private final ModelRenderer main_layer;
	private final ModelRenderer head;
	private final ModelRenderer mouth;
	private final ModelRenderer head_layer;
	private final ModelRenderer mouth_layer;
	private final ModelRenderer left_arm;
	private final ModelRenderer left_arm_layer;
	private final ModelRenderer right_arm;
	private final ModelRenderer right_arm_layer;

	public ModelCactus() {
		textureWidth = 64;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 24.0F, 0.0F);
		main.setTextureOffset(0, 0).addBox(-3.0F, -10.0F, -3.0F, 6.0F, 10.0F, 6.0F, 0.0F, false);

		main_layer = new ModelRenderer(this);
		main_layer.setRotationPoint(-0.5F, 24.0F, 0.5F);
		main_layer.setTextureOffset(24, 0).addBox(-2.5F, -10.0F, -3.5F, 6.0F, 10.0F, 6.0F, 0.25F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 14.0F, 0.0F);
		head.setTextureOffset(0, 16).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, false);

		mouth = new ModelRenderer(this);
		mouth.setRotationPoint(0.0F, -1.5F, -3.0F);
		head.addChild(mouth);
		mouth.setTextureOffset(0, 28).addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		head_layer = new ModelRenderer(this);
		head_layer.setRotationPoint(0.0F, 14.0F, 0.0F);
		head_layer.setTextureOffset(24, 16).addBox(-3.0F, -6.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.25F, false);

		mouth_layer = new ModelRenderer(this);
		mouth_layer.setRotationPoint(0.0F, -1.5F, -3.0F);
		head_layer.addChild(mouth_layer);
		mouth_layer.setTextureOffset(13, 28).addBox(-1.5F, -1.5F, -3.0F, 3.0F, 3.0F, 3.0F, 0.25F, false);

		left_arm = new ModelRenderer(this);
		left_arm.setRotationPoint(3.0F, 15.5F, 0.0F);
		left_arm.setTextureOffset(0, 43).addBox(0.0F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F, false);
		left_arm.setTextureOffset(0, 35).addBox(2.0F, -5.5F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		left_arm_layer = new ModelRenderer(this);
		left_arm_layer.setRotationPoint(2.75F, 15.5F, 0.0F);
		left_arm_layer.setTextureOffset(17, 43).addBox(0.25F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.25F, false);
		left_arm_layer.setTextureOffset(13, 35).addBox(2.25F, -5.5F, -1.5F, 3.0F, 4.0F, 3.0F, 0.25F, false);

		right_arm = new ModelRenderer(this);
		right_arm.setRotationPoint(-3.0F, 15.5F, 0.0F);
		right_arm.setTextureOffset(0, 58).addBox(-5.0F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.0F, false);
		right_arm.setTextureOffset(0, 50).addBox(-5.0F, -5.5F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, false);

		right_arm_layer = new ModelRenderer(this);
		right_arm_layer.setRotationPoint(-3.0F, 15.5F, 0.0F);
		right_arm_layer.setTextureOffset(17, 58).addBox(-5.0F, -1.5F, -1.5F, 5.0F, 3.0F, 3.0F, 0.25F, false);
		right_arm_layer.setTextureOffset(13, 50).addBox(-5.0F, -5.5F, -1.5F, 3.0F, 4.0F, 3.0F, 0.25F, false);
	}

	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of();
	}

	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(this.head, this.head_layer, this.main, this.main_layer, this.left_arm,
				this.left_arm_layer, this.right_arm, this.right_arm_layer);
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
		this.head_layer.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head_layer.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.right_arm_layer.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.right_arm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * f1;
		this.left_arm.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		this.left_arm_layer.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
	}
}