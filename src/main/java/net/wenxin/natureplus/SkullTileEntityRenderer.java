///**
// * This mod element is always locked. Enter your code in the methods below.
// * If you don't need some of these methods, you can remove them as they
// * are overrides of the base class NatureplusModElements.ModElement.
// *
// * You can register new events in this class too.
// *
// * As this class is loaded into mod element list, it NEEDS to extend
// * ModElement class. If you remove this extend statement or remove the
// * constructor, the compilation will fail.
// *
// * If you want to make a plain independent class, create it using
// * Project Browser - New... and make sure to make the class
// * outside net.wenxin.natureplus as this package is managed by MCreator.
// *
// * If you change workspace package, modid or prefix, you will need
// * to manually adapt this file to these changes or remake it.
//*/
//package net.wenxin.natureplus;
//
//import net.wenxin.natureplus.block.PeashooterHeadBlock;
//
//import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.api.distmarker.Dist;
//
//import net.minecraft.util.Util;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.Direction;
//import net.minecraft.tileentity.SkullTileEntity;
//import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
//import net.minecraft.client.renderer.texture.OverlayTexture;
//import net.minecraft.client.renderer.entity.model.GenericHeadModel;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.IRenderTypeBuffer;
//import net.minecraft.block.WallSkullBlock;
//import net.minecraft.block.SkullBlock;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.AbstractSkullBlock;
//
//import javax.annotation.Nullable;
//
//import java.util.Map;
//
//import com.mojang.blaze3d.vertex.IVertexBuilder;
//import com.mojang.blaze3d.matrix.MatrixStack;
//import com.mojang.authlib.GameProfile;
//
//import com.google.common.collect.Maps;
//import net.minecraft.client.renderer.tileentity.SkullTileEntityRenderer;
//
//@OnlyIn(Dist.CLIENT)
//@NatureplusModElements.ModElement.Tag
//public class SkullTileEntityRenderer extends SkullTileEntityRenderer {
//	private static final Map<SkullBlock.ISkullType, GenericHeadModel> MODELS = Util.make(Maps.newHashMap(), (map) -> {
//		GenericHeadModel genericheadmodel = new GenericHeadModel(0, 0, 64, 32);
//		map.put(PeashooterHeadBlock.CustomBlock.Types.PEASHOOTER, genericheadmodel);
//	});
//	private static final Map<SkullBlock.ISkullType, ResourceLocation> SKINS = Util.make(Maps.newHashMap(), (map) -> {
//		map.put(PeashooterHeadBlock.CustomBlock.Types.PEASHOOTER, new ResourceLocation("natureplus:textures/peashooter2.png"));
//	});
//	public SkullTileEntityRenderer(TileEntityRendererDispatcher render) {
//		super(render);
//	}
//
//	public void render(SkullTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn,
//			int combinedOverlayIn) {
//		float f = tileEntityIn.getAnimationProgress(partialTicks);
//		BlockState blockstate = tileEntityIn.getBlockState();
//		boolean flag = blockstate.getBlock() instanceof WallSkullBlock;
//		Direction direction = flag ? blockstate.get(WallSkullBlock.FACING) : null;
//		float f1 = 22.5F * (float) (flag ? (2 + direction.getHorizontalIndex()) * 4 : blockstate.get(SkullBlock.ROTATION));
//		render(direction, f1, ((AbstractSkullBlock) blockstate.getBlock()).getSkullType(), tileEntityIn.getPlayerProfile(), f, matrixStackIn,
//				bufferIn, combinedLightIn);
//	}
//
//	public static void render(@Nullable Direction directionIn, float p_228879_1_, SkullBlock.ISkullType skullType,
//			@Nullable GameProfile gameProfileIn, float animationProgress, MatrixStack matrixStackIn, IRenderTypeBuffer buffer, int combinedLight) {
//		GenericHeadModel genericheadmodel = MODELS.get(skullType);
//		matrixStackIn.push();
//		if (directionIn == null) {
//			matrixStackIn.translate(0.5D, 0.0D, 0.5D);
//		} else {
//			switch (directionIn) {
//				case NORTH :
//					matrixStackIn.translate(0.5D, 0.25D, (double) 0.74F);
//					break;
//				case SOUTH :
//					matrixStackIn.translate(0.5D, 0.25D, (double) 0.26F);
//					break;
//				case WEST :
//					matrixStackIn.translate((double) 0.74F, 0.25D, 0.5D);
//					break;
//				case EAST :
//				default :
//					matrixStackIn.translate((double) 0.26F, 0.25D, 0.5D);
//			}
//		}
//		matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
//		IVertexBuilder ivertexbuilder = buffer.getBuffer(getRenderType(skullType, gameProfileIn));
//		genericheadmodel.func_225603_a_(animationProgress, p_228879_1_, 0.0F);
//		genericheadmodel.render(matrixStackIn, ivertexbuilder, combinedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
//		matrixStackIn.pop();
//	}
//
//	@OnlyIn(Dist.CLIENT)
//	private static RenderType getRenderType(SkullBlock.ISkullType skullType, @Nullable GameProfile gameProfileIn) {
//		ResourceLocation resourcelocation = SKINS.get(skullType);
//		return RenderType.getEntityCutoutNoCull(resourcelocation);
//	}
//	/**
//	 * Do not remove this constructor
//	 */
//	// public SkullTileEntityRenderer(NatureplusModElements instance) {
//	// super(instance, 951);
//	// }
//	//
//	// @Override
//	// public void initElements() {
//	// }
//	//
//	// @Override
//	// public void init(FMLCommonSetupEvent event) {
//	// }
//	//
//	// @Override
//	// public void serverLoad(FMLServerStartingEvent event) {
//	// }
//	//
//	// @OnlyIn(Dist.CLIENT)
//	// @Override
//	// public void clientLoad(FMLClientSetupEvent event) {
//	// }
//}
