//package net.wenxin.natureplus.world.biome;
//
//import net.wenxin.natureplus.block.AzureSaplingBlock;
//import net.wenxin.natureplus.block.AzureLogBlock;
//import net.wenxin.natureplus.block.AzureLeavesBlock;
//
//import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
//import net.minecraft.world.gen.feature.TreeFeatureConfig;
//import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Block;
//
//public class NaturePlusDefaultBiomeFeatures {
//	// logs
//	private static final BlockState AZURE_LOG = AzureLogBlock.block.getDefaultState();
//	// leaves
//	private static final BlockState AZURE_LEAVES = AzureLeavesBlock.block.getDefaultState();
//	// features
//	public static final TreeFeatureConfig FANCY_AZURE_TREE_CONFIG = fancy_tree(AZURE_LOG, AZURE_LEAVES, AzureSaplingBlock.block, 6, 4, 1, 3, 2);
//	private static TreeFeatureConfig fancy_tree(BlockState log, BlockState leaf, Block sapling, int height, int heightRand, int heightTrunk,
//			int heightTrunkRand, int trunkOffset) {
//		return (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(log), new SimpleBlockStateProvider(leaf), new SpruceFoliagePlacer(1, 0)))
//				.baseHeight(height).heightRandA(heightRand).trunkHeight(heightTrunk).trunkHeightRandom(heightTrunkRand)
//				.trunkTopOffsetRandom(trunkOffset).ignoreVines().setSapling((net.minecraftforge.common.IPlantable) sapling).build();
//	}
//}
