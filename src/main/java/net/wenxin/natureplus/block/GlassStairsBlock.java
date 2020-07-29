
package net.wenxin.natureplus.block;

import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.state.properties.StairsShape;
import net.minecraft.state.properties.SlabType;
import net.minecraft.state.properties.Half;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.List;
import java.util.Collections;

@NatureplusModElements.ModElement.Tag
public class GlassStairsBlock extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:glass_stairs")
	public static final Block block = null;
	public GlassStairsBlock(NatureplusModElements instance) {
		super(instance, 81);
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
	public static class CustomBlock extends StairsBlock {
		public CustomBlock() {
			super(new Block(Block.Properties.create(Material.ROCK)).getDefaultState(), Block.Properties.create(Material.GLASS).sound(SoundType.GLASS)
					.hardnessAndResistance(0.3f, 0.3f).lightValue(0).harvestLevel(0).harvestTool(ToolType.PICKAXE).notSolid());
			setRegistryName("glass_stairs");
		}

		@Override
		public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return false;
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public boolean isSideInvisible(BlockState blockState, BlockState state, Direction direction) {
			if (state.getBlock() == Blocks.GLASS)
				return true;
			if (state.getBlock() == this)
				if (isInvisibleToGlassStairs(blockState, state, direction))
					return true;
			if (state.getBlock() == GlassSlabBlock.block)
				if (isInvisibleToGlassSlab(blockState, state, direction))
					return true;
			return super.isSideInvisible(blockState, state, direction);
		}

		private boolean isInvisibleToGlassStairs(BlockState blockState, BlockState state, Direction direction) {
			Half half1 = blockState.get(StairsBlock.HALF);
			Half half2 = state.get(StairsBlock.HALF);
			Direction facing1 = blockState.get(StairsBlock.FACING);
			Direction facing2 = state.get(StairsBlock.FACING);
			StairsShape shape1 = blockState.get(StairsBlock.SHAPE);
			StairsShape shape2 = state.get(StairsBlock.SHAPE);
			// up
			if (direction == Direction.UP)
				if (half2 == Half.BOTTOM)
					return true;
				else if (facing1 == facing2 && half1 != half2)
					return true;
			// down
			if (direction == Direction.DOWN)
				if (half2 == Half.TOP)
					return true;
				else if (facing1 == facing2 && half1 != half2)
					return true;
			// other stairs rear
			if (facing2 == direction.getOpposite())
				return true;
			// rear
			if (direction == facing1)
				if (half1 == half2 && shape1 != StairsShape.STRAIGHT)
					if (facing2 == facing1.rotateYCCW() && shape2 != StairsShape.OUTER_RIGHT)
						return true;
					else if (facing2 == facing1.rotateY() && shape2 != StairsShape.OUTER_LEFT)
						return true;
			// front
			if (direction == facing1.getOpposite())
				if (half1 == half2)
					if (facing2 == facing1.rotateYCCW() && shape2 != StairsShape.OUTER_LEFT)
						return true;
					else if (facing2 == facing1.rotateY() && shape2 != StairsShape.OUTER_RIGHT)
						return true;
			// left
			if (direction == facing1.rotateYCCW())
				if (half1 == half2)
					if (facing2 == direction && shape1 != StairsShape.INNER_LEFT && shape2 == StairsShape.INNER_RIGHT)
						return true;
					else if (facing2 == facing1 && shape2 != StairsShape.OUTER_LEFT)
						return true;
					else if (facing2 == facing1.getOpposite() && shape1 == StairsShape.OUTER_RIGHT)
						return true;
			// right
			if (direction == facing1.rotateY())
				if (half1 == half2)
					if (facing2 == direction && shape1 != StairsShape.INNER_RIGHT && shape2 == StairsShape.INNER_LEFT)
						return true;
					else if (facing2 == facing1 && shape2 != StairsShape.OUTER_RIGHT)
						return true;
					else if (facing2 == facing1.getOpposite() && shape1 == StairsShape.OUTER_LEFT)
						return true;
			return false;
		}

		private boolean isInvisibleToGlassSlab(BlockState blockState, BlockState state, Direction direction) {
			Half half1 = blockState.get(StairsBlock.HALF);
			Direction facing1 = blockState.get(StairsBlock.FACING);
			StairsShape shape1 = blockState.get(StairsBlock.SHAPE);
			SlabType type2 = state.get(SlabBlock.TYPE);
			if (direction == Direction.UP)
				if (type2 != SlabType.TOP)
					return true;
			if (direction == Direction.DOWN)
				if (type2 != SlabType.BOTTOM)
					return true;
			if (type2 == SlabType.DOUBLE)
				return true;
			// front
			if (direction == facing1.getOpposite())
				if (type2 == SlabType.BOTTOM && half1 == Half.BOTTOM)
					return true;
				else if (type2 == SlabType.TOP && half1 == Half.TOP)
					return true;
			// right
			if (direction == facing1.rotateY() && shape1 == StairsShape.OUTER_LEFT)
				if (type2 == SlabType.BOTTOM && half1 == Half.BOTTOM)
					return true;
				else if (type2 == SlabType.TOP && half1 == Half.TOP)
					return true;
			// left
			if (direction == facing1.rotateYCCW() && shape1 == StairsShape.OUTER_RIGHT)
				if (type2 == SlabType.BOTTOM && half1 == Half.BOTTOM)
					return true;
				else if (type2 == SlabType.TOP && half1 == Half.TOP)
					return true;
			return false;
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@Override
		public MaterialColor getMaterialColor(BlockState state, IBlockReader blockAccess, BlockPos pos) {
			return MaterialColor.AIR;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 0));
		}
	}
}
