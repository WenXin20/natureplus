package net.wenxin.natureplus.client.renderer;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.LazyValue;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.block.Block;

import com.mojang.blaze3d.matrix.MatrixStack;

import com.google.common.base.Preconditions;

public class SkullItemStackRenderer extends ItemStackTileEntityRenderer {
	private final Block block;
	private final LazyValue<TileEntity> dummy;
	public SkullItemStackRenderer(Block block) {
		this.block = Preconditions.checkNotNull(block);
		this.dummy = new LazyValue<>(() -> {
			TileEntityType<?> type = Registry.BLOCK_ENTITY_TYPE.getValue(Registry.BLOCK.getKey(block)).get();
			return type.create();
		});
	}

	@Override
	public void render(ItemStack stack, MatrixStack ms, IRenderTypeBuffer buffers, int light, int overlay) {
		if (stack.getItem() == block.asItem()) {
			TileEntityRendererDispatcher.instance.getRenderer(dummy.getValue()).render(null, 0, ms, buffers, light, overlay);
		}
	}
}
