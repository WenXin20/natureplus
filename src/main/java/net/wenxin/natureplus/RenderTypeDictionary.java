/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class NatureplusModElements.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser - New... and make sure to make the class
 * outside net.wenxin.natureplus as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.wenxin.natureplus;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderState;

@OnlyIn(Dist.CLIENT)
public class RenderTypeDictionary extends RenderState {
	public RenderTypeDictionary(String stringIn, Runnable runIn, Runnable runIn2) {
		super(stringIn, runIn, runIn2);
	}

	public static RenderType getEntityGlow(ResourceLocation resourceLocation) {
		RenderState.TextureState textureState = new RenderState.TextureState(resourceLocation, false, false);
		return RenderType.makeType("glow", DefaultVertexFormats.ENTITY, 7, 256, false, true,
				RenderType.State.getBuilder().texture(textureState).transparency(NO_TRANSPARENCY).diffuseLighting(DIFFUSE_LIGHTING_DISABLED)
						.alpha(DEFAULT_ALPHA).cull(CULL_DISABLED).lightmap(LIGHTMAP_DISABLED).build(false));
	}

	public static RenderType getEntityTranslucent(ResourceLocation resourceLocation) {
		RenderState.TextureState textureState = new RenderState.TextureState(resourceLocation, false, false);
		return RenderType.makeType("translucent", DefaultVertexFormats.ENTITY, 7, 256, false, true,
				RenderType.State.getBuilder().texture(textureState).transparency(TRANSLUCENT_TRANSPARENCY).diffuseLighting(DIFFUSE_LIGHTING_ENABLED)
						.alpha(HALF_ALPHA).cull(CULL_DISABLED).lightmap(LIGHTMAP_ENABLED).shadeModel(SHADE_DISABLED).overlay(OVERLAY_ENABLED).build(false));
	}
}
