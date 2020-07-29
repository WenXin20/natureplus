
package net.wenxin.natureplus.block;

import net.wenxin.natureplus.item.MexicanSunflowerSeedsItem;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.IItemProvider;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.StateContainer;
import net.minecraft.state.IntegerProperty;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.PushReaction;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.IGrowable;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.Random;
import java.util.List;
import java.util.Collections;

@NatureplusModElements.ModElement.Tag
public class MexicanSunflowerCropBlock extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:mexican_sunflower_crop")
	public static final Block block = null;
	public MexicanSunflowerCropBlock(NatureplusModElements instance) {
		super(instance, 797);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
//		elements.items.add(() -> new BlockItem(block, new Item.Properties()).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}
	public static class CustomBlock extends CropsBlock implements IGrowable {
		public static final IntegerProperty AGE = BlockStateProperties.AGE_0_7;

		private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{Block.makeCuboidShape(3.2D, 0D, 3.2D, 12.8D, 2.4D, 12.8D),
				Block.makeCuboidShape(3.2D, 0D, 3.2D, 12.8D, 4D, 12.8D), Block.makeCuboidShape(3.2D, 0D, 3.2D, 12.8D, 4.25D, 12.8D),
				Block.makeCuboidShape(3.2D, 0D, 3.2D, 12.8D, 4.25D, 12.8D), Block.makeCuboidShape(3.2D, 0D, 3.2D, 12.8D, 7D, 12.8D),
				Block.makeCuboidShape(3.2D, 0D, 3.2D, 12.8D, 8D, 12.8D), Block.makeCuboidShape(3.2D, 0D, 3.2D, 12.8D, 9D, 12.8D),
				Block.makeCuboidShape(3.2D, 0D, 3.2D, 12.8D, 14.4D, 12.8D)};

		public CustomBlock() {
			super(Block.Properties.create(Material.PLANTS).sound(SoundType.CROP).tickRandomly().hardnessAndResistance(0f, 1f).lightValue(0).harvestLevel(0)
					.harvestTool(ToolType.AXE).doesNotBlockMovement().notSolid());
			this.setDefaultState(this.stateContainer.getBaseState().with(this.getAgeProperty(), Integer.valueOf(0)));
			setRegistryName("mexican_sunflower_crop");
		}

		public boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return (state.getBlock() instanceof FarmlandBlock);
		}

		@Override
		public int getMaxAge() {
			return 7;
		}

		public IntegerProperty getAgeProperty() {
			return AGE;
		}

		protected int getAge(BlockState state) {
			return state.get(this.getAgeProperty());
		}

		protected int getBonemealAgeIncrease(World worldIn) {
			return MathHelper.nextInt(worldIn.rand, 1, 3);
		}

		protected static float getGrowthChance(Block blockIn, IBlockReader worldIn, BlockPos pos) {
			float f = 1.0F;
			BlockPos blockpos = pos.down();
			for (int i = -1; i <= 1; ++i) {
				for (int j = -1; j <= 1; ++j) {
					float f1 = 0.0F;
					BlockState blockstate = worldIn.getBlockState(blockpos.add(i, 0, j));
					if (blockstate.canSustainPlant(worldIn, blockpos.add(i, 0, j), net.minecraft.util.Direction.UP,
							(net.minecraftforge.common.IPlantable) blockIn)) {
						f1 = 1.0F;
						if (blockstate.isFertile(worldIn, blockpos.add(i, 0, j))) {
							f1 = 3.0F;
						}
					}
					if (i != 0 || j != 0) {
						f1 /= 4.0F;
					}
					f += f1;
				}
			}
			BlockPos blockpos1 = pos.north();
			BlockPos blockpos2 = pos.south();
			BlockPos blockpos3 = pos.west();
			BlockPos blockpos4 = pos.east();
			boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
			boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();
			if (flag && flag1) {
				f /= 2.0F;
			} else {
				boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock()
						|| blockIn == worldIn.getBlockState(blockpos4.north()).getBlock()
						|| blockIn == worldIn.getBlockState(blockpos4.south()).getBlock()
						|| blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();
				if (flag2) {
					f /= 2.0F;
				}
			}
			return f;
		}

		public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
			return !this.isMaxAge(state);
		}

		public void grow(World worldIn, BlockPos pos, BlockState state) {
			int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
			int j = this.getMaxAge();
			if (i > j) {
				i = j;
			}
			worldIn.setBlockState(pos, this.withAge(i), 2);
		}

		public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
			this.grow(worldIn, pos, state);
		}

		public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
			super.tick(state, worldIn, pos, rand);
			if (!worldIn.isAreaLoaded(pos, 1))
				return; // Forge: prevent loading unloaded chunks when checking neighbor's light
			if (worldIn.getLightSubtracted(pos, 0) >= 9) {
				int i = this.getAge(state);
				if (i < this.getMaxAge()) {
					float f = this.getGrowthChance(this, worldIn, pos);
					if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt((int) (25.0F / f) + 1) == 0)) {
						worldIn.setBlockState(pos, this.withAge(i + 1), 2);
						net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
					}
				}
			}
		}

		@Override
		protected IItemProvider getSeedsItem() {
			return MexicanSunflowerSeedsItem.block;
		}

		protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
			builder.add(AGE);
		}

		@Override
		public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return false;
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
			return SHAPE_BY_AGE[state.get(this.getAgeProperty())];
		}

		@Override
		public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player) {
			return new ItemStack(MexicanSunflowerSeedsItem.block, (int) (1));
		}

		@Override
		public MaterialColor getMaterialColor(BlockState state, IBlockReader blockAccess, BlockPos pos) {
			return MaterialColor.GRASS;
		}

		@Override
		public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, MobEntity entity) {
			return PathNodeType.WALKABLE;
		}

		@Override
		public PushReaction getPushReaction(BlockState state) {
			return PushReaction.DESTROY;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(MexicanSunflowerSeedsItem.block, (int) (1)));
		}
	}
}
