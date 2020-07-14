// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelDragonfly extends EntityModel<Entity> {
	private final ModelRenderer main;
	private final ModelRenderer torso;
	private final ModelRenderer wing_front_left;
	private final ModelRenderer wing_back_left;
	private final ModelRenderer wing_front_right;
	private final ModelRenderer wing_back_right;
	private final ModelRenderer front_legs;
	private final ModelRenderer middle_legs;
	private final ModelRenderer back_legs;
	private final ModelRenderer head;

	public ModelDragonfly() {
		textureWidth = 64;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 21.0F, -5.0F);
		setRotationAngle(main, -0.0873F, 0.0F, 0.0F);
		main.setTextureOffset(0, 24).addBox(-1.0F, -1.0F, 8.0F, 2.0F, 2.0F, 14.0F, 0.0F, false);

		torso = new ModelRenderer(this);
		torso.setRotationPoint(0.0F, 0.5F, 7.0F);
		main.addChild(torso);
		torso.setTextureOffset(18, 24).addBox(-2.0F, -2.0F, -7.0F, 4.0F, 3.0F, 8.0F, 0.0F, false);

		wing_front_left = new ModelRenderer(this);
		wing_front_left.setRotationPoint(0.5F, -1.501F, 2.0F);
		main.addChild(wing_front_left);
		setRotationAngle(wing_front_left, 0.0F, 0.0F, -0.0873F);
		wing_front_left.setTextureOffset(0, 18).addBox(0.0F, 0.0F, -4.0F, 18.0F, 0.0F, 6.0F, 0.0F, false);

		wing_back_left = new ModelRenderer(this);
		wing_back_left.setRotationPoint(0.5F, -1.501F, 5.0F);
		main.addChild(wing_back_left);
		setRotationAngle(wing_back_left, 0.0F, -0.0873F, 0.0F);
		wing_back_left.setTextureOffset(0, 12).addBox(0.0F, 0.0F, -1.0F, 18.0F, 0.0F, 6.0F, 0.0F, false);

		wing_front_right = new ModelRenderer(this);
		wing_front_right.setRotationPoint(-0.5F, -1.501F, 2.0F);
		main.addChild(wing_front_right);
		wing_front_right.setTextureOffset(0, 6).addBox(-18.0F, 0.0F, -4.0F, 18.0F, 0.0F, 6.0F, 0.0F, false);

		wing_back_right = new ModelRenderer(this);
		wing_back_right.setRotationPoint(-0.5F, -1.501F, 5.0F);
		main.addChild(wing_back_right);
		setRotationAngle(wing_back_right, 0.0F, 0.0873F, 0.0F);
		wing_back_right.setTextureOffset(0, 0).addBox(-18.0F, 0.0F, -1.0F, 18.0F, 0.0F, 6.0F, 0.0F, false);

		front_legs = new ModelRenderer(this);
		front_legs.setRotationPoint(0.0F, 1.5F, 0.0F);
		main.addChild(front_legs);
		front_legs.setTextureOffset(2, 4).addBox(-1.5F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, false);
		front_legs.setTextureOffset(0, 4).addBox(0.5F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, false);

		middle_legs = new ModelRenderer(this);
		middle_legs.setRotationPoint(0.0F, 1.5F, 3.0F);
		main.addChild(middle_legs);
		middle_legs.setTextureOffset(2, 0).addBox(-1.5F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, false);
		middle_legs.setTextureOffset(2, 2).addBox(0.5F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, false);

		back_legs = new ModelRenderer(this);
		back_legs.setRotationPoint(0.0F, 1.5F, 6.0F);
		main.addChild(back_legs);
		back_legs.setTextureOffset(0, 2).addBox(-1.5F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, false);
		back_legs.setTextureOffset(0, 0).addBox(0.5F, -0.5F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 21.0F, -5.0F);
		head.setTextureOffset(0, 24).addBox(-1.5F, -2.0F, -3.0F, 3.0F, 3.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		main.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
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
	
		boolean flag = e.getMotion().lengthSquared() < 2.0E-7D; //e.onGround &&
		if (flag) {
			this.wing_front_right.rotateAngleZ = 0.8F + -(MathHelper.cos(f2 * 0.8F) * (float)Math.PI * 0.14F);
			this.wing_back_right.rotateAngleZ = 0.8F + -(MathHelper.cos(f2 * 0.8F) * (float)Math.PI * 0.13F);
			this.wing_front_left.rotateAngleZ = -0.8F + (MathHelper.cos(f2 * 0.8F) * (float)Math.PI * 0.14F);
			this.wing_back_left.rotateAngleZ = -0.8F + (MathHelper.cos(f2 * 0.8F) * (float)Math.PI * 0.13F);
			
			this.front_legs.rotateAngleX = 0.0F;
			this.middle_legs.rotateAngleX = 0.0F;
			this.back_legs.rotateAngleX = 0.0F;
		} else {
			this.wing_front_right.rotateAngleZ = 0.8F + -(MathHelper.cos(f2 * 1.7F) * (float)Math.PI * 0.18F);
			this.wing_back_right.rotateAngleZ = 0.8F + -(MathHelper.cos(f2 * 1.7F) * (float)Math.PI * 0.17F);
			this.wing_front_left.rotateAngleZ = -0.8F + MathHelper.cos(f2 * 1.7F) * (float)Math.PI * 0.18F;
			this.wing_back_left.rotateAngleZ = -0.8F + MathHelper.cos(f2 * 1.7F) * (float)Math.PI * 0.17F;
			
			this.front_legs.rotateAngleX = ((float)Math.PI / 4F);
			this.middle_legs.rotateAngleX = ((float)Math.PI / 4F);
			this.back_legs.rotateAngleX = ((float)Math.PI / 4F);
		}
	}
}