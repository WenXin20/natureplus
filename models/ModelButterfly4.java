// Made with Blockbench 3.7.1
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelButterfly4 extends EntityModel<Entity> {
	private final ModelRenderer main;
	private final ModelRenderer wing_left;
	private final ModelRenderer wing_right;
	private final ModelRenderer head;
	private final ModelRenderer antenna_left;
	private final ModelRenderer antenna_right;

	public ModelButterfly4() {
		textureWidth = 64;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 22.0F, 0.0F);
		main.setTextureOffset(17, 42).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 12.0F, 0.0F, false);

		wing_left = new ModelRenderer(this);
		wing_left.setRotationPoint(2.0F, 0.0F, 0.0F);
		main.addChild(wing_left);
		wing_left.setTextureOffset(-19, 21).addBox(-0.5F, 0.0F, -10.0F, 24.0F, 0.0F, 20.0F, 0.0F, false);

		wing_right = new ModelRenderer(this);
		wing_right.setRotationPoint(-1.0F, 0.0F, 0.0F);
		main.addChild(wing_right);
		wing_right.setTextureOffset(-19, 1).addBox(-24.5F, 0.0F, -10.0F, 24.0F, 0.0F, 20.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, -4.0F);
		main.addChild(head);
		head.setTextureOffset(1, 42).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 4.0F, 0.0F, false);

		antenna_left = new ModelRenderer(this);
		antenna_left.setRotationPoint(-1.5F, -1.5F, -4.0F);
		head.addChild(antenna_left);
		antenna_left.setTextureOffset(1, 51).addBox(3.0F, -3.5F, -4.0F, 0.0F, 4.0F, 4.0F, 0.0F, false);

		antenna_right = new ModelRenderer(this);
		antenna_right.setRotationPoint(1.5F, -1.0F, -4.0F);
		head.addChild(antenna_right);
		antenna_right.setTextureOffset(1, 46).addBox(-3.0F, -4.0F, -4.0F, 0.0F, 4.0F, 4.0F, 0.0F, false);
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

	@Override
	public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.head.rotateAngleX = f4 / (180F / (float) Math.PI);
		this.antenna_left.rotateAngleX = MathHelper.cos(f2 * 0.03F) * (float)Math.PI * 0.15F;
		this.antenna_right.rotateAngleX = MathHelper.cos(f2 * 0.031F) * (float)Math.PI * 0.15F;
		
		boolean flag = e.onGround && e.getMotion().lengthSquared() < 2.0E-7D;
		if (flag) {
			this.wing_right.rotateAngleZ = 1.0F + -(MathHelper.cos(f2 * 0.4F) * (float)Math.PI * 0.18F);
			this.wing_left.rotateAngleZ = -1.0F + (MathHelper.cos(f2 * 0.4F) * (float)Math.PI * 0.18F);
		} else {
			this.wing_right.rotateAngleZ = -(MathHelper.cos(f2 * 1.7F) * (float)Math.PI * 0.3F);
			this.wing_left.rotateAngleZ = MathHelper.cos(f2 * 1.7F) * (float)Math.PI * 0.3F;
		}
	}
}