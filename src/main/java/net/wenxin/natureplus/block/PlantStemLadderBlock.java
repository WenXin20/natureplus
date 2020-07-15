
package net.wenxin.natureplus.block;

import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.Mirror;
import net.minecraft.util.Direction;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.StateContainer;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.BooleanProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.BlockItem;
import net.minecraft.fluid.IFluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.LadderBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.List;
import java.util.Collections;

@NatureplusModElements.ModElement.Tag
public class PlantStemLadderBlock extends NatureplusModElements.ModElement implements IWaterLoggable {
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	@ObjectHolder("natureplus:plant_stem_ladder")
	public static final Block block = null;
	public PlantStemLadderBlock(NatureplusModElements instance) {
		super(instance, 43);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(NaturePlusTabItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}
	public static class CustomBlock extends LadderBlock {
		public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
		public CustomBlock() {
			super(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(0.4f, 0.4f).lightValue(0).harvestLevel(0)
					.harvestTool(ToolType.AXE).notSolid());
			this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(WATERLOGGED, Boolean.valueOf(false)));
			setRegistryName("plant_stem_ladder");
		}

		@Override
		public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return false;
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@Override
		public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
			switch ((Direction) state.get(FACING)) {
				case UP :
				case DOWN :
				case SOUTH :
				default :
					return VoxelShapes.create(1D, 0D, 0.1875D, 0D, 1D, 0D);
				case NORTH :
					return VoxelShapes.create(0D, 0D, 0.8125D, 1D, 1D, 1D);
				case WEST :
					return VoxelShapes.create(0.8125D, 0D, 1D, 1D, 1D, 0D);
				case EAST :
					return VoxelShapes.create(0.1875D, 0D, 0D, 0D, 1D, 1D);
			}
		}

		@Override
		protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
			builder.add(FACING, WATERLOGGED);
		}

		public BlockState rotate(BlockState state, Rotation rot) {
			return state.with(FACING, rot.rotate(state.get(FACING)));
		}

		public BlockState mirror(BlockState state, Mirror mirrorIn) {
			return state.rotate(mirrorIn.toRotation(state.get(FACING)));
		}

		private boolean canAttachTo(IBlockReader p_196471_1_, BlockPos p_196471_2_, Direction p_196471_3_) {
			BlockState blockstate = p_196471_1_.getBlockState(p_196471_2_);
			return !blockstate.canProvidePower() && blockstate.isSolidSide(p_196471_1_, p_196471_2_, p_196471_3_);
		}

		public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
			Direction direction = state.get(FACING);
			return this.canAttachTo(worldIn, pos.offset(direction.getOpposite()), direction);
		}

		/**
		 * Update the provided state given the provided neighbor facing and neighbor
		 * state, returning a new state. For example, fences make their connections to
		 * the passed in state if possible, and wet concrete powder immediately returns
		 * its solidified counterpart. Note that this method should ideally consider
		 * only the specific face passed in.
		 */
		public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos,
				BlockPos facingPos) {
			if (facing.getOpposite() == stateIn.get(FACING) && !stateIn.isValidPosition(worldIn, currentPos)) {
				return Blocks.AIR.getDefaultState();
			} else {
				if (stateIn.get(WATERLOGGED)) {
					worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
				}
				return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
			}
		}

		@Override
		public BlockState getStateForPlacement(BlockItemUseContext context) {
			if (!context.replacingClickedOnBlock()) {
				BlockState blockstate = context.getWorld().getBlockState(context.getPos().offset(context.getFace().getOpposite()));
				if (blockstate.getBlock() == this && blockstate.get(FACING) == context.getFace()) {
					return null;
				}
			}
			BlockState blockstate1 = this.getDefaultState();
			IWorldReader iworldreader = context.getWorld();
			BlockPos blockpos = context.getPos();
			IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
			for (Direction direction : context.getNearestLookingDirections()) {
				if (direction.getAxis().isHorizontal()) {
					blockstate1 = blockstate1.with(FACING, direction.getOpposite());
					if (blockstate1.isValidPosition(iworldreader, blockpos)) {
						return blockstate1.with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
					}
				}
			}
			return null;
		}

		public IFluidState getFluidState(BlockState state) {
			return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
		}

		@Override
		public MaterialColor getMaterialColor(BlockState state, IBlockReader blockAccess, BlockPos pos) {
			return MaterialColor.LIME;
		}

		@Override
		public boolean isLadder(BlockState state, IWorldReader world, BlockPos pos, LivingEntity entity) {
			return true;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
		}
	}
}
