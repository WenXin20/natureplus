//
//package net.wenxin.natureplus.block;
//
//import net.wenxin.natureplus.block.PeashooterHeadBlock;
//import net.wenxin.natureplus.NatureplusModElements;
//
//import net.minecraftforge.registries.ObjectHolder;
//import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
//import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//import net.minecraftforge.common.property.Properties;
//import net.minecraftforge.common.ToolType;
//import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.api.distmarker.Dist;
//
//import net.minecraft.world.IBlockReader;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.block.material.MaterialColor;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.WallSkullBlock;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.SkullBlock;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Block;
//
//@NatureplusModElements.ModElement.Tag
//public class PeashooterWallHeadBlock extends NatureplusModElements.ModElement {
//	@ObjectHolder("natureplus:peashooter_wall_head")
//	public static final Block block = null;
//	/**
//	 * Do not remove this constructor
//	 */
//	public PeashooterWallHeadBlock(NatureplusModElements instance) {
//		super(instance, 948);
//	}
//
//	@Override
//	public void init(FMLCommonSetupEvent event) {
//	}
//
//	@Override
//	public void serverLoad(FMLServerStartingEvent event) {
//	}
//
//	@OnlyIn(Dist.CLIENT)
//	@Override
//	public void clientLoad(FMLClientSetupEvent event) {
//	}
//
//	//
//	// @SubscribeEvent
//	// public void registerTileEntity(RegistryEvent.Register<TileEntityType<?>>
//	// event) {
//	// event.getRegistry().register(TileEntityType.Builder.create(NPSkullTileEntity.CustomTileEntity::new,
//	// block).build(null)
//	// .setRegistryName("peashooter_wall_head_tile"));
//	// }
//	@Override
//	public void initElements() {
//		elements.blocks.add(() -> new CustomBlock(PeashooterHeadBlock.CustomBlock.Types.PEASHOOTER,
//				Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(2f, 15f).lightValue(0).harvestLevel(0)
//						.harvestTool(ToolType.AXE)));
//	}
//	public static class CustomBlock extends WallSkullBlock {
//		public CustomBlock(SkullBlock.ISkullType type, Properties properties) {
//			super(type, properties);
//			setRegistryName("peashooter_wall_head");
//		}
//
//		@Override
//		public MaterialColor getMaterialColor(BlockState state, IBlockReader blockAccess, BlockPos pos) {
//			return MaterialColor.GREEN;
//		}
//		// @Override
//		// public boolean hasTileEntity(BlockState state) {
//		// return true;
//		// }
//		//
//		// @Override
//		// public TileEntity createTileEntity(BlockState state, IBlockReader world) {
//		// return PeashooterHeadBlock.block.createTileEntity(state, world);
//		// }
//	}
//}
