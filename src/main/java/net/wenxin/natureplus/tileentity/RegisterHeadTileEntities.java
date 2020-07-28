package net.wenxin.natureplus.tileentity;

import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.tileentity.TileEntityType;
import static net.wenxin.natureplus.block.RegisterHeadBlocks.*;

public class RegisterHeadTileEntities {
	public static final String SNOW_PEA_HEAD = "snow_pea_head";
	public static final String SNOW_PEA_WALL_HEAD = "snow_pea_wall_head";
	
	public static final TileEntityType<NatureplusSkullTileEntity> SNOW_PEA_HEAD_TILE = TileEntityType.Builder
			.create(NatureplusSkullTileEntity::new, snowPeaHead, snowPeaHeadWall).build(null);

	public static void registerTiles(RegistryEvent.Register<TileEntityType<?>> evt) {
		IForgeRegistry<TileEntityType<?>> r = evt.getRegistry();
		register(r, SNOW_PEA_HEAD, SNOW_PEA_HEAD_TILE);
	}
}
