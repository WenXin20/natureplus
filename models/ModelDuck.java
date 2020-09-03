// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelDuck extends EntityModel<Entity> {
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
		head.setRotationPoint(0.0F, 16.0F, -3.0F);
		head.setTextureOffset(1, 1).addBox(-2.0F, -9.0F, -3.0F, 4.0F, 10.0F, 3.0F, 0.0F, false);

		beak = new ModelRenderer(this);
		beak.setRotationPoint(0.0F, -6.0F, -3.0F);
		head.addChild(beak);
		beak.setTextureOffset(16, 1).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 16.0F, 2.0F);
		body.setTextureOffset(1, 29).addBox(-3.0F, -2.0F, -5.0F, 6.0F, 6.0F, 9.0F, 0.0F, false);

		left_wing = new ModelRenderer(this);
		left_wing.setRotationPoint(3.0F, -2.0F, 0.0F);
		body.addChild(left_wing);
		left_wing.setTextureOffset(20, 15).addBox(0.0F, 0.0F, -4.0F, 1.0F, 5.0F, 8.0F, 0.0F, false);

		right_wing = new ModelRenderer(this);
		right_wing.setRotationPoint(-3.0F, -2.0F, 0.0F);
		body.addChild(right_wing);
		right_wing.setTextureOffset(1, 15).addBox(-1.0F, 0.0F, -4.0F, 1.0F, 5.0F, 8.0F, 0.0F, false);

		left_leg = new ModelRenderer(this);
		left_leg.setRotationPoint(1.5F, 19.0F, 3.0F);
		left_leg.setTextureOffset(18, 45).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);

		right_leg = new ModelRenderer(this);
		right_leg.setRotationPoint(-1.5F, 19.0F, 3.0F);
		right_leg.setTextureOffset(1, 45).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
	}


	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of(this.head, this.beak);
	}

	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(this.body, this.right_leg, this.left_leg, this.right_wing, this.left_wing);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
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

	public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
		this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);
		this.right_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.left_leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;

		boolean flag = entityIn.onGround;
		if (!flag) {
			this.right_wing.rotateAngleZ = -5.0F + (MathHelper.cos(ageInTicks * 1.5F) * (float) Math.PI * 0.3F);
			this.left_wing.rotateAngleZ = 5.0F + -(MathHelper.cos(ageInTicks * 1.5F) * (float) Math.PI * 0.3F);
		} else {
			this.right_wing.rotateAngleZ = 0.0F;
			this.left_wing.rotateAngleZ = 0.0F;
		}
	}
}