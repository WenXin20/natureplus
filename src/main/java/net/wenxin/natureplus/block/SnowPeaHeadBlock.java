//
//package net.wenxin.natureplus.block;
//
//import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;
//import net.wenxin.natureplus.SnowPeaHeadPreciseHitbox;
//import net.wenxin.natureplus.NatureplusModElements;
//
//import net.minecraftforge.registries.ObjectHolder;
//import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.api.distmarker.Dist;
//
//import net.minecraft.world.storage.loot.LootContext;
//import net.minecraft.world.IWorldReader;
//import net.minecraft.world.IBlockReader;
//import net.minecraft.util.math.shapes.VoxelShape;
//import net.minecraft.util.math.shapes.ISelectionContext;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.Rotation;
//import net.minecraft.util.Mirror;
//import net.minecraft.util.Direction;
//import net.minecraft.tags.FluidTags;
//import net.minecraft.state.properties.BlockStateProperties;
//import net.minecraft.state.StateContainer;
//import net.minecraft.state.DirectionProperty;
//import net.minecraft.state.BooleanProperty;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.Item;
//import net.minecraft.item.BlockItemUseContext;
//import net.minecraft.item.BlockItem;
//import net.minecraft.fluid.IFluidState;
//import net.minecraft.fluid.Fluids;
//import net.minecraft.client.renderer.RenderTypeLookup;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.block.material.MaterialColor;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.IWaterLoggable;
//import net.minecraft.block.HorizontalBlock;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Block;
//
//import java.util.List;
//import java.util.Collections;
//
//@NatureplusModElements.ModElement.Tag
//public class SnowPeaHeadBlock extends NatureplusModElements.ModElement implements IWaterLoggable {
//	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
//	@ObjectHolder("natureplus:snow_pea_head")
//	public static final Block block = null;
//	public SnowPeaHeadBlock(NatureplusModElements instance) {
//		super(instance, 181);
//	}
//
//	@Override
//	public void initElements() {
//		elements.blocks.add(() -> new CustomBlock());
//		elements.items.add(
//				() -> new BlockItem(block, new Item.Properties().group(PlantsVsZombiesTabItemGroup.tab)).setRegistryName(block.getRegistryName()));
//	}
//
//	@Override
//	@OnlyIn(Dist.CLIENT)
//	public void clientLoad(FMLClientSetupEvent event) {
//		RenderTypeLookup.setRenderLayer(block, RenderType.getTranslucent());
//	}
//	public static class CustomBlock extends Block implements IWaterLoggable {
//		public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
//		public CustomBlock() {
//			super(Block.Properties.create(Material.LEAVES).sound(SoundType.GLASS).hardnessAndResistance(2f, 1f).lightValue(0).notSolid());
//			this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, false));
//			setRegistryName("snow_pea_head");
//		}
//
//		@Override
//		public float[] getBeaconColorMultiplier(BlockState state, IWorldReader world, BlockPos pos, BlockPos beaconPos) {
//			return new float[]{0.4f, 0.8f, 1f};
//		}
//
//		@Override
//		public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
//			return false;
//		}
//
//		@Override
//		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
//			return true;
//		}
//
//		@Override
//		public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
//			return SnowPeaHeadPreciseHitbox.SNOWPEA.get(state.get(FACING));
//		}
//
//		@Override
//		protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
//			builder.add(FACING, WATERLOGGED);
//		}
//
//		public BlockState rotate(BlockState state, Rotation rot) {
//			return state.with(FACING, rot.rotate(state.get(FACING)));
//		}
//
//		public BlockState mirror(BlockState state, Mirror mirrorIn) {
//			return state.rotate(mirrorIn.toRotation(state.get(FACING)));
//		}
//
//		@Override
//		public BlockState getStateForPlacement(BlockItemUseContext context) {
//			IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
//			return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(WATERLOGGED,
//					ifluidstate.isTagged(FluidTags.WATER) && ifluidstate.getLevel() == 8);
//		}
//
//		@SuppressWarnings("deprecation")
//		public IFluidState getFluidState(BlockState state) {
//			return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
//		}
//
//		@Override
//		public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
//			return 20;
//		}
//
//		@Override
//		public MaterialColor getMaterialColor(BlockState state, IBlockReader blockAccess, BlockPos pos) {
//			return MaterialColor.LIGHT_BLUE;
//		}
//
//		@Override
//		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
//			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
//			if (!dropsOriginal.isEmpty())
//				return dropsOriginal;
//			return Collections.singletonList(new ItemStack(this, 1));
//		}
//	}
//}
