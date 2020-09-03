// Made with Blockbench 3.6.5
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelDuck<T extends Entity> extends AgeableModel<T> {
	private final ModelRenderer head;
	private final ModelRenderer bill;
	private final ModelRenderer body;
	private final ModelRenderer leftWing;
	private final ModelRenderer rightWing;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;
	public ModelDuck() {
        int i = 16;
		textureWidth = 64;
		textureHeight = 64;
		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 16.0F, -3.0F);
		head.setTextureOffset(1, 1).addBox(-2.0F, -9.0F, -3.0F, 4.0F, 10.0F, 3.0F, 0.0F, false);
		bill = new ModelRenderer(this);
		bill.setRotationPoint(0.0F, -6.0F, -3.0F);
		head.addChild(bill);
		bill.setTextureOffset(16, 1).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 2.0F, 2.0F, 0.0F, false);
		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 16.0F, 2.0F);
		body.setTextureOffset(1, 29).addBox(-3.0F, -2.0F, -5.0F, 6.0F, 6.0F, 9.0F, 0.0F, false);
		leftWing = new ModelRenderer(this);
		leftWing.setRotationPoint(3.0F, -2.0F, 0.0F);
		body.addChild(leftWing);
		leftWing.setTextureOffset(20, 15).addBox(0.0F, 0.0F, -4.0F, 1.0F, 5.0F, 8.0F, 0.0F, false);
		rightWing = new ModelRenderer(this);
		rightWing.setRotationPoint(-3.0F, -2.0F, 0.0F);
		body.addChild(rightWing);
		rightWing.setTextureOffset(1, 15).addBox(-1.0F, 0.0F, -4.0F, 1.0F, 5.0F, 8.0F, 0.0F, false);
		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(1.5F, 19.0F, 3.0F);
		leftLeg.setTextureOffset(18, 45).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(-1.5F, 19.0F, 3.0F);
		rightLeg.setTextureOffset(1, 45).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 5.0F, 3.0F, 0.0F, false);
	}

	protected Iterable<ModelRenderer> getHeadParts() {
		return ImmutableList.of(this.head);
	}

	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(this.body, this.rightLeg, this.leftLeg);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.rotateAngleY = netHeadYaw / (180F / (float) Math.PI);
		this.head.rotateAngleX = headPitch / (180F / (float) Math.PI);
		this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
		this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.4F * limbSwingAmount;
		boolean flag = entityIn.onGround;
		if (!flag) {
			this.rightWing.rotateAngleZ = -5.0F + (MathHelper.cos(ageInTicks * 2.0F) * (float) Math.PI * 0.3F);
			this.leftWing.rotateAngleZ = 5.0F + -(MathHelper.cos(ageInTicks * 2.0F) * (float) Math.PI * 0.3F);
		} else {
			this.rightWing.rotateAngleZ = 0.0F;
			this.leftWing.rotateAngleZ = 0.0F;
		}
	}
}
