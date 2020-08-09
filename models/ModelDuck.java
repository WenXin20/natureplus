
// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelDuck extends AgeableModel<Entity> {
	private final ModelRenderer head;
	private final ModelRenderer beak;
	private final ModelRenderer body;
	private final ModelRenderer left_wing;
	private final ModelRenderer right_wing;
	private final ModelRenderer left_leg;
	private final ModelRenderer right_leg;
	public ModelDuck() {
		textureWidth = 64;
		textureHeight = 64;
		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 15.0F, -4.0F);
		setRotationAngle(head, 0.0F, 0.0F, 0.0F);
		head.setTextureOffset(22, 15).addBox(-2.0F, -6.0F, -2.0F, 4.0F, 8.0F, 3.0F, 0.0F, false);
		beak = new ModelRenderer(this);
		beak.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(beak);
		beak.setTextureOffset(31, 31).addBox(-2.0F, -4.0F, -4.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 16.0F, 0.0F);
		setRotationAngle(body, 0.0F, 0.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-3.0F, -2.0F, -3.0F, 6.0F, 6.0F, 9.0F, 0.0F, false);
		left_wing = new ModelRenderer(this);
		left_wing.setRotationPoint(3.0F, -2.0F, 0.0F);
		body.addChild(left_wing);
		left_wing.setTextureOffset(11, 20).addBox(0.0F, 0.0F, -3.0F, 1.0F, 5.0F, 9.0F, 0.0F, false);
		right_wing = new ModelRenderer(this);
		right_wing.setRotationPoint(-3.0F, -2.0F, 0.0F);
		body.addChild(right_wing);
		right_wing.setTextureOffset(0, 15).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 5.0F, 9.0F, 0.0F, false);
		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(2.0F, 19.0F, 3.0F);
		left_leg.setTextureOffset(30, 5).addBox(-2.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-1.0F, 19.0F, 3.0F);
		right_leg.setTextureOffset(21, 0).addBox(-2.0F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
	}

	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of(this.head, this.beak);
	}

	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(this.body, this.right_leg, this.left_leg, this.right_wing, this.left_wing);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue,
			float alpha) {
		head.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		left_leg.render(matrixStack, buffer, packedLight, packedOverlay);
		right_leg.render(matrixStack, buffer, packedLight, packedOverlay);
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
		this.right_leg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
		this.left_leg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
		this.right_wing.rotateAngleZ = f2;
		this.left_wing.rotateAngleZ = -f2;
	}
}
