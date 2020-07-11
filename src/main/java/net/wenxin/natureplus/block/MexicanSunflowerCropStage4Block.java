
package net.wenxin.natureplus.block;

import org.omg.CORBA.ObjectHolder;

import net.wenxin.natureplus.procedures.BreakPlantIfAirBelowProcedure;
import net.wenxin.natureplus.item.MexicanSunflowerSeedsItem;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.block.material.Material;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Collections;

@NatureplusModElements.ModElement.Tag
public class MexicanSunflowerCropStage4Block extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:mexican_sunflower_crop_age4")
	public static final Block block = null;
	public MexicanSunflowerCropStage4Block(NatureplusModElements instance) {
		super(instance, 159);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items.add(() -> new BlockItem(block, new Item.Properties().group(null)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}
	public static class CustomBlock extends Block {
		public CustomBlock() {
			super(Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0f, 1f).lightValue(0).harvestLevel(0)
					.harvestTool(ToolType.AXE).doesNotBlockMovement().notSolid());
			setRegistryName("mexican_sunflower_crop_age4");
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
			return VoxelShapes.create(0.2D, 0D, 0.2D, 0.7999999999999999D, 0.9D, 0.7999999999999999D);
		}

		@Override
		public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
			return new ItemStack(MexicanSunflowerSeedsItem.block, (int) (1));
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(Blocks.AIR, (int) (2)));
		}

		@Override
		public void neighborChanged(BlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean moving) {
			super.neighborChanged(state, world, pos, neighborBlock, fromPos, moving);
			int x = pos.getX();
			int y = pos.getY();
			int z = pos.getZ();
			if (world.getRedstonePowerFromNeighbors(new BlockPos(x, y, z)) > 0) {
			} else {
			}
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				BreakPlantIfAirBelowProcedure.executeProcedure($_dependencies);
			}
		}
	}
}
