package net.wenxin.natureplus.client.renderer;

import net.wenxin.natureplus.NatureplusHeadBlock;

import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.SkullTileEntityRenderer;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.block.SkullBlock;

import java.util.Map;

public class RenderSkullTileEntity extends SkullTileEntityRenderer {
	private static final Map<SkullBlock.ISkullType, GenericHeadModel> MODELS = ObfuscationReflectionHelper
			.getPrivateValue(SkullTileEntityRenderer.class, null, "field_199358_e");
	private static final Map<SkullBlock.ISkullType, ResourceLocation> SKINS = ObfuscationReflectionHelper
			.getPrivateValue(SkullTileEntityRenderer.class, null, "field_199357_d");
	static {
		MODELS.put(NatureplusHeadBlock.SNOW_PEA, MODELS.get(NatureplusHeadBlock.Types.SNOW_PEA));
		SKINS.put(NatureplusHeadBlock.SNOW_PEA, SKINS.get(NatureplusHeadBlock.Types.SNOW_PEA));
	}
	public RenderSkullTileEntity(TileEntityRendererDispatcher manager) {
		super(manager);
	}
}
