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
//import net.minecraftforge.registries.ObjectHolder;
//import net.minecraftforge.common.ToolType;
//
//import net.minecraft.world.IBlockReader;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.block.material.MaterialColor;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.WallSkullBlock;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Block;
//
//import javax.annotation.Nullable;
//
//@NatureplusModElements.ModElement.Tag
//public class PeashooterWallHeadBlock extends NatureplusModElements.ModElement {
//	@ObjectHolder("natureplus:peashooter_wall_head")
//	public static final Block block = null;
//	/**
//	 * Do not remove this constructor
//	 */
//	public PeashooterWallHeadBlock(NatureplusModElements instance) {
//		super(instance, 949);
//	}
//
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
//	// public PeashooterWallHeadBlock(SkullBlock.ISkullType type, Properties
//	// properties) {
//	// super(type, properties);
//	// }
//	@Override
//	public void initElements() {
//		elements.blocks.add(() -> new CustomBlock());
//	}
//	public static class CustomBlock extends WallSkullBlock {
//		public CustomBlock() {
//			super(PeashooterHeadBlock.CustomBlock.Types.PEASHOOTER, Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT)
//					.hardnessAndResistance(2f, 15f).lightValue(0).harvestLevel(0).harvestTool(ToolType.AXE));
//			setRegistryName("peashooter_wall_head");
//		}
//
//		@Override
//		public MaterialColor getMaterialColor(BlockState state, IBlockReader blockAccess, BlockPos pos) {
//			return MaterialColor.GREEN;
//		}
//
//		@Override
//		public boolean hasTileEntity(BlockState state) {
//			return true;
//		}
//
//		@Override
//		@Nullable
//		public TileEntity createTileEntity(BlockState state, IBlockReader world) {
//			return PeashooterHeadBlock.block.createTileEntity(state, world);
//		}
//	}
//}
