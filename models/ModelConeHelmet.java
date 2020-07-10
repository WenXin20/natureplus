// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public static class ModelConeHelmet extends ModelBase {
	private final ModelRenderer cone;

	public ModelConeHelmet() {
		textureWidth = 128;
		textureHeight = 128;

		cone = new ModelRenderer(this);
		cone.setRotationPoint(0.0F, 24.0F, 0.0F);
		cone.cubeList.add(new ModelBox(cone, 0, 13, 4.0F, -8.0F, -6.0F, 2, 1, 12, 0.0F, false));
		cone.cubeList.add(new ModelBox(cone, 30, 18, -4.0F, -8.0F, -6.0F, 8, 1, 2, 0.0F, false));
		cone.cubeList.add(new ModelBox(cone, 16, 0, -4.0F, -8.0F, 4.0F, 8, 1, 2, 0.0F, false));
		cone.cubeList.add(new ModelBox(cone, 0, 0, -6.0F, -8.0F, -6.0F, 2, 1, 12, 0.0F, false));
		cone.cubeList.add(new ModelBox(cone, 20, 5, 3.0F, -13.0F, -4.0F, 1, 5, 8, 0.0F, false));
		cone.cubeList.add(new ModelBox(cone, 14, 33, -3.0F, -13.0F, -4.0F, 6, 5, 1, 0.0F, false));
		cone.cubeList.add(new ModelBox(cone, 30, 21, -3.0F, -13.0F, 3.0F, 6, 5, 1, 0.0F, false));
		cone.cubeList.add(new ModelBox(cone, 20, 20, -4.0F, -13.0F, -4.0F, 1, 5, 8, 0.0F, false));
		cone.cubeList.add(new ModelBox(cone, 30, 0, 2.0F, -18.0F, -3.0F, 1, 5, 6, 0.0F, false));
		cone.cubeList.add(new ModelBox(cone, 16, 18, -2.0F, -18.0F, -3.0F, 4, 5, 1, 0.0F, false));
		cone.cubeList.add(new ModelBox(cone, 16, 3, -2.0F, -18.0F, 2.0F, 4, 5, 1, 0.0F, false));
		cone.cubeList.add(new ModelBox(cone, 0, 26, -3.0F, -18.0F, -3.0F, 1, 5, 6, 0.0F, false));
		cone.cubeList.add(new ModelBox(cone, 0, 13, 1.0F, -23.0F, -2.0F, 1, 5, 4, 0.0F, false));
		cone.cubeList.add(new ModelBox(cone, 8, 26, -1.0F, -23.0F, -2.0F, 2, 5, 1, 0.0F, false));
		cone.cubeList.add(new ModelBox(cone, 0, 26, -1.0F, -23.0F, 1.0F, 2, 5, 1, 0.0F, false));
		cone.cubeList.add(new ModelBox(cone, 0, 0, -2.0F, -23.0F, -2.0F, 1, 5, 4, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		cone.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
		this.cone.rotateAngleY = f3 / (180F / (float) Math.PI);
		this.cone.rotateAngleX = f4 / (180F / (float) Math.PI);
	}
}