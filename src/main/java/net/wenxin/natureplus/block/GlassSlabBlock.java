
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
import net.minecraft.state.properties.SlabType;
import net.minecraft.state.properties.Half;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.entity.EntityType;
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
public class GlassSlabBlock extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:glass_slab")
	public static final Block block = null;
	public GlassSlabBlock(NatureplusModElements instance) {
		super(instance, 82);
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
	public static class CustomBlock extends SlabBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.GLASS).sound(SoundType.GLASS).hardnessAndResistance(0.3f, 0.3f).lightValue(0).harvestLevel(0)
					.harvestTool(ToolType.PICKAXE).notSolid());
			setRegistryName("glass_slab");
		}

		@Override
		public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return false;
		}

		@Override
		public boolean isTransparent(BlockState state) {
			return state.get(SlabBlock.TYPE) == SlabType.DOUBLE;
		}

		@Override
		@OnlyIn(Dist.CLIENT)
		public boolean isSideInvisible(BlockState blockState, BlockState state, Direction direction) {
			if (state.getBlock() == Blocks.GLASS)
				return true;
			if (state.getBlock() == this)
				if (isInvisibleToGlassSlab(blockState, state, direction))
					return true;
			if (state.getBlock() == GlassStairsBlock.block)
				if (isInvisibleToGlassStairs(blockState, state, direction))
					return true;
			return super.isSideInvisible(blockState, state, direction);
		}

		private boolean isInvisibleToGlassSlab(BlockState blockState, BlockState state, Direction direction) {
			SlabType type1 = blockState.get(SlabBlock.TYPE);
			SlabType type2 = state.get(SlabBlock.TYPE);
			if (type2 == SlabType.DOUBLE)
				return true;
			switch (direction) {
				case UP :
				case DOWN :
					if (type1 != type2)
						return true;
					break;
				case NORTH :
				case EAST :
				case SOUTH :
				case WEST :
					if (type1 == type2)
						return true;
					break;
			}
			return false;
		}

		private boolean isInvisibleToGlassStairs(BlockState blockState, BlockState state, Direction direction) {
			SlabType type1 = blockState.get(SlabBlock.TYPE);
			Half half2 = state.get(StairsBlock.HALF);
			Direction facing2 = state.get(StairsBlock.FACING);
			// up
			if (direction == Direction.UP)
				if (half2 == Half.BOTTOM)
					return true;
			// down
			if (direction == Direction.DOWN)
				if (half2 == Half.TOP)
					return true;
			// other stairs rear
			if (facing2 == direction.getOpposite())
				return true;
			// sides
			if (direction.getHorizontalIndex() != -1)
				if (type1 == SlabType.BOTTOM && half2 == Half.BOTTOM)
					return true;
				else if (type1 == SlabType.TOP && half2 == Half.TOP)
					return true;
			return false;
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@OnlyIn(Dist.CLIENT)
		public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return 1.0F;
		}

		public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
			return false;
		}

		public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return false;
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
