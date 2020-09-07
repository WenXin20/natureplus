// Made with Blockbench 3.5.4
// Exported for Minecraft version 1.15
// Paste this class into your mod and generate all required imports

public class ModelZombie<T extends MonsterEntity> extends BipedModel<T> {
   protected ModelZombie(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
      super(modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
		textureWidth = 64;
		textureHeight = 64;

		this.bipedLeftArm = new ModelRenderer(this);
		this.bipedLeftArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.bipedLeftArm.setTextureOffset(40, 16).addBox(9.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, true);
   }

   protected ModelZombie(float f1, float f2, int f3, int f4) {
      super(f1, f2, f3, f4);
   }

   public boolean isAggressive(T entityIn) {
      return entityIn.isAggressive();
   }
}