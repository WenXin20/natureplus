
package net.wenxin.natureplus.world.structure;

import net.wenxin.natureplus.NatureplusModElements;

import java.util.Random;

@NatureplusModElements.ModElement.Tag
public class GiantAlliumStructure extends NatureplusModElements.ModElement {
	public GiantAlliumStructure(NatureplusModElements instance) {
		super(instance, 444);
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		Feature<NoFeatureConfig> feature = new Feature<NoFeatureConfig>(NoFeatureConfig::deserialize) {
			@Override
			public boolean place(IWorld iworld, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
				int ci = pos.getX();
				int ck = pos.getZ();
				DimensionType dimensionType = iworld.getDimension().getType();
				boolean dimensionCriteria = false;
				if (dimensionType == DimensionType.OVERWORLD)
					dimensionCriteria = true;
				if (!dimensionCriteria)
					return false;
				if ((random.nextInt(1000000) + 1) <= 100000) {
					int count = random.nextInt(1) + 1;
					for (int a = 0; a < count; a++) {
						int i = ci + random.nextInt(16) + 8;
						int k = ck + random.nextInt(16) + 8;
						int j = iworld.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, i, k);
						j -= 1;
						BlockState blockAt = iworld.getBlockState(new BlockPos(i, j, k));
						boolean blockCriteria = false;
						if (blockAt.getBlock() == Blocks.GRASS_BLOCK.getDefaultState().getBlock())
							blockCriteria = true;
						if (blockAt.getBlock() == Blocks.DIRT.getDefaultState().getBlock())
							blockCriteria = true;
						if (!blockCriteria)
							continue;
						Template template = ((ServerWorld) iworld.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("natureplus", "giant_allium1"));
						if (template == null)
							return false;
						Rotation rotation = Rotation.values()[random.nextInt(3)];
						Mirror mirror = Mirror.values()[random.nextInt(2)];
						BlockPos spawnTo = new BlockPos(i, j + 1, k);
						ServerWorld world = (ServerWorld) iworld.getWorld();
						int x = spawnTo.getX();
						int y = spawnTo.getY();
						int z = spawnTo.getZ();
						template.addBlocksToWorldChunk(iworld, spawnTo,
								new PlacementSettings().setRotation(rotation).setRandom(random).setMirror(mirror)
										.addProcessor(BlockIgnoreStructureProcessor.AIR).setChunk((ChunkPos) null).setIgnoreEntities(false));
					}
				}
				return true;
			}
		};
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("natureplus:giant_flora")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, feature.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
					.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		}
	}
}
