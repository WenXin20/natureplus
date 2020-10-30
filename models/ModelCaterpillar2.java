// Made with Blockbench 3.7.1
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public static class ModelCaterpillar2 extends EntityModel<Entity> {
	private final ModelRenderer main;
	private final ModelRenderer head;
	private final ModelRenderer antenna_left;
	private final ModelRenderer antenna_right;
	private final ModelRenderer tail_left;
	private final ModelRenderer tail_right;

	public ModelCaterpillar2() {
		textureWidth = 64;
		textureHeight = 64;

		main = new ModelRenderer(this);
		main.setRotationPoint(0.0F, 22.0F, 0.0F);
		main.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 10.0F, 0.0F, false);
		main.setTextureOffset(29, 0).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 10.0F, 0.25F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		main.addChild(head);
		head.setTextureOffset(0, 14).addBox(-2.0F, -2.0F, -10.0F, 4.0F, 4.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(29, 14).addBox(-2.0F, -2.0F, -10.0F, 4.0F, 4.0F, 10.0F, 0.25F, false);

		antenna_left = new ModelRenderer(this);
		antenna_left.setRotationPoint(-1.25F, -1.5F, -10.0F);
		head.addChild(antenna_left);
		antenna_left.setTextureOffset(0, 15).addBox(2.5F, -3.5F, -4.0F, 0.0F, 4.0F, 4.0F, 0.0F, false);

		antenna_right = new ModelRenderer(this);
		antenna_right.setRotationPoint(1.25F, -1.5F, -10.0F);
		head.addChild(antenna_right);
		antenna_right.setTextureOffset(0, 11).addBox(-2.5F, -3.5F, -4.0F, 0.0F, 4.0F, 4.0F, 0.0F, false);

		tail_left = new ModelRenderer(this);
		tail_left.setRotationPoint(-1.25F, -1.5F, 10.0F);
		main.addChild(tail_left);
		tail_left.setTextureOffset(19, 16).addBox(2.5F, -2.5F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F, false);

		tail_right = new ModelRenderer(this);
		tail_right.setRotationPoint(1.25F, -1.5F, 10.0F);
		main.addChild(tail_right);
		tail_right.setTextureOffset(19, 13).addBox(-2.5F, -2.5F, 0.0F, 0.0F, 3.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red,
			float green, float blue, float alpha) {
		main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4) {
		this.head.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.antenna_left.rotateAngleX = MathHelper.cos(f2 * 0.03F) * (float)Math.PI * 0.15F;
		this.antenna_right.rotateAngleX = MathHelper.cos(f2 * 0.031F) * (float)Math.PI * 0.15F;
	}
}