package net.wenxin.natureplus.tileentity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraft.tileentity.SkullTileEntity;

import javax.annotation.Nonnull;

public class NatureplusSkullTileEntity extends SkullTileEntity {
	@Nonnull
	@Override
	public TileEntityType<NatureplusSkullTileEntity> getType() {
		return RegisterHeadTileEntities.SNOW_PEA_HEAD_TILE;
	}
}
