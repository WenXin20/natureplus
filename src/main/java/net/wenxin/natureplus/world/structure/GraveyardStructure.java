//
//package net.wenxin.natureplus.world.structure;
//
//import net.wenxin.natureplus.procedures.GraveyardSpawnProcedure;
//import net.wenxin.natureplus.StructureHelper;
//import net.wenxin.natureplus.NatureplusModElements;
//import net.wenxin.natureplus.GraveyardPieces;
//
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//
//import net.minecraft.world.server.ServerWorld;
//import net.minecraft.world.gen.placement.Placement;
//import net.minecraft.world.gen.placement.IPlacementConfig;
//import net.minecraft.world.gen.feature.template.TemplateManager;
//import net.minecraft.world.gen.feature.template.Template;
//import net.minecraft.world.gen.feature.template.PlacementSettings;
//import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
//import net.minecraft.world.gen.feature.structure.SwampHutPiece;
//import net.minecraft.world.gen.feature.structure.StructureStart;
//import net.minecraft.world.gen.feature.structure.StructurePiece;
//import net.minecraft.world.gen.feature.structure.Structure;
//import net.minecraft.world.gen.feature.structure.ScatteredStructure;
//import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
//import net.minecraft.world.gen.feature.NoFeatureConfig;
//import net.minecraft.world.gen.feature.IFeatureConfig;
//import net.minecraft.world.gen.feature.Feature;
//import net.minecraft.world.gen.Heightmap;
//import net.minecraft.world.gen.GenerationStage;
//import net.minecraft.world.gen.ChunkGenerator;
//import net.minecraft.world.dimension.DimensionType;
//import net.minecraft.world.biome.BiomeManager;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.IWorld;
//import net.minecraft.util.math.MutableBoundingBox;
//import net.minecraft.util.math.ChunkPos;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.SharedSeedRandom;
//import net.minecraft.util.Rotation;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.Mirror;
//import net.minecraft.entity.EntityType;
//
//import mcp.client.Start;
//
//import java.util.function.Function;
//import java.util.Random;
//import java.util.List;
//
//import com.mojang.datafixers.Dynamic;
//
//import com.google.common.collect.Lists;
//import com.google.common.collect.ImmutableMap;
//
//@NatureplusModElements.ModElement.Tag
//// public class GraveyardStructure extends NatureplusModElements.ModElement {
//public class GraveyardStructure extends ScatteredStructure<NoFeatureConfig> {
//	// public GraveyardStructure(NatureplusModElements instance) {
//	// super(instance, 926);
//	// }
//	// public static class CustomStructure extends
//	// ScatteredStructure<NoFeatureConfig> {
//	/** List of enemies that can spawn in the Pillage Outpost. */
//	private static final List<Biome.SpawnListEntry> GRAVEYARD_ENEMIES = Lists.newArrayList(new Biome.SpawnListEntry(EntityType.ZOMBIE, 1, 1, 1));
//	public GraveyardStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> graveyardConfigIn) {
//		super(graveyardConfigIn);
//	}
//
//	public String getStructureName() {
//		return "natureplus:Graveyard";
//	}
//
//	public int getSize() {
//		return 1;
//	}
//
//	public List<Biome.SpawnListEntry> getSpawnList() {
//		return GRAVEYARD_ENEMIES;
//	}
//
//	@Override
//	protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> generator, Random random, int chunkX, int chunkZ, int offsetX, int offsetZ) {
//		int chunkPosX = chunkX + 35 * offsetX;
//		int chunkPosZ = chunkZ + 35 * offsetZ;
//		int chunkPosX1 = chunkPosX < 0 ? chunkPosX - 35 + 1 : chunkPosX;
//		int chunkPosZ1 = chunkPosZ < 0 ? chunkPosZ - 35 + 1 : chunkPosZ;
//		int lvt_13_1_ = chunkPosX1 / 35;
//		int lvt_14_1_ = chunkPosZ1 / 35;
//		((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(generator.getSeed(), lvt_13_1_, lvt_14_1_, 16897777);
//		lvt_13_1_ *= 35;
//		lvt_14_1_ *= 35;
//		lvt_13_1_ += random.nextInt(35 - 5);
//		lvt_14_1_ += random.nextInt(35 - 5);
//		return new ChunkPos(lvt_13_1_, lvt_14_1_);
//	}
//
//	// @Override
//	// protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> generator,
//	// Random random, int chunkX, int chunkZ, int offsetX, int offsetZ) {
//	// int chunkPosX = chunkX + 35 * offsetX;
//	// int chunkPosZ = chunkZ + 35 * offsetZ;
//	// int chunkPosX1 = chunkPosX < 0 ? chunkPosX - 35 + 1 : chunkPosX;
//	// int chunkPosZ1 = chunkPosZ < 0 ? chunkPosZ - 35 + 1 : chunkPosZ;
//	// int lvt_13_1_ = chunkPosX1 / 35;
//	// int lvt_14_1_ = chunkPosZ1 / 35;
//	// ((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(generator.getSeed(),
//	// lvt_13_1_, lvt_14_1_, 16897777);
//	// lvt_13_1_ *= 35;
//	// lvt_14_1_ *= 35;
//	// lvt_13_1_ += random.nextInt(35 - 5);
//	// lvt_14_1_ += random.nextInt(35 - 5);
//	// return new ChunkPos(lvt_13_1_, lvt_14_1_);
//	// }
//	/**
//	 * decide whether the Structure can be generated
//	 */
//	public boolean canBeGenerated(BiomeManager biomeManagerIn, ChunkGenerator<?> generatorIn, Random randIn, int chunkX, int chunkZ, Biome biomeIn,
//			IWorld world, BlockPos pos) {
//		ChunkPos chunkpos = this.getStartPositionForPosition(generatorIn, randIn, chunkX, chunkZ, 0, 0);
//		return chunkX == chunkpos.x && chunkZ == chunkpos.z ? generatorIn.hasStructure(biomeIn, this) : false;
//		// ChunkPos chunkpos = this.getStartPositionForPosition(generatorIn, randIn,
//		// chunkX, chunkZ, 0, 0);
//		// if (chunkX == chunkpos.x && chunkZ == chunkpos.z) {
//		// int i = chunkX >> 4;
//		// int j = chunkZ >> 4;
//		// randIn.setSeed((long) (i ^ j << 4) ^ generatorIn.getSeed());
//		// randIn.nextInt();
//		// if (randIn.nextInt(5) != 0) {
//		// return false;
//		// }
//		// if (generatorIn.hasStructure(biomeIn, this)) {
//		// for (int k = chunkX - 10; k <= chunkX + 10; ++k) {
//		// for (int l = chunkZ - 10; l <= chunkZ + 10; ++l) {
//		// if (Feature.VILLAGE.canBeGenerated(biomeManagerIn, generatorIn, randIn, k, l,
//		// biomeManagerIn.getBiome(new BlockPos((k << 4) + 9, 0, (l << 4) + 9)))) {
//		// return false;
//		// }
//		// }
//		// }
//		// return true;
//		// }
//		// }
//		// return false;
//	}
//
//	public Structure.IStartFactory getStartFactory() {
//		return GraveyardStructure.Start::new;
//	}
//
//	protected int getSeedModifier() {
//		return 165745296;
//	}
//	public static class Start extends MarginedStructureStart {
//		public Start(Structure<?> struc, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int x1, long x2) {
//			super(struc, chunkX, chunkZ, boundingBox, x1, x2);
//		}
//
//		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
//			BlockPos blockpos = new BlockPos(chunkX * 16, 90, chunkZ * 16);
//			GraveyardPieces.func_215139_a(generator, templateManagerIn, blockpos, this.components, this.rand);
//			this.recalculateStructureSize();
//			NoFeatureConfig nofeatureconfig = (NoFeatureConfig) generator.getStructureConfig(biomeIn, StructureHelper.GRAVEYARD);
//			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
//		}
//	}
//
//	public boolean func_202383_b(IWorld worldIn, BlockPos pos) {
//		StructureStart structurestart = this.getStart(worldIn, pos, true);
//		if (structurestart != StructureStart.DUMMY && structurestart instanceof GraveyardStructure.Start
//				&& !structurestart.getComponents().isEmpty()) {
//			StructurePiece structurepiece = structurestart.getComponents().get(0);
//			return structurepiece instanceof SwampHutPiece;
//		} else {
//			return false;
//		}
//	}
//
////	public void init(FMLCommonSetupEvent event) {
////		Feature<NoFeatureConfig> feature = new Feature<NoFeatureConfig>(NoFeatureConfig::deserialize) {
////			@Override
////			public boolean place(IWorld world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
////				int ci = (pos.getX() >> 4) << 4;
////				int ck = (pos.getZ() >> 4) << 4;
////				DimensionType dimensionType = world.getDimension().getType();
////				boolean dimensionCriteria = false;
////				if (dimensionType == DimensionType.OVERWORLD)
////					dimensionCriteria = true;
////				if (!dimensionCriteria)
////					return false;
////				if ((random.nextInt(1000000) + 1) <= 1000000) {
////					int count = random.nextInt(1) + 1;
////					for (int a = 0; a < count; a++) {
////						int i = ci + random.nextInt(16);
////						int k = ck + random.nextInt(16);
////						int j = world.getHeight(Heightmap.Type.OCEAN_FLOOR_WG, i, k);
////						j -= 1;
////						Rotation rotation = Rotation.values()[random.nextInt(3)];
////						Mirror mirror = Mirror.values()[random.nextInt(2)];
////						BlockPos spawnTo = new BlockPos(i, j + -1, k);
////						int x = spawnTo.getX();
////						int y = spawnTo.getY();
////						int z = spawnTo.getZ();
////						if (!GraveyardSpawnProcedure.executeProcedure(ImmutableMap.of("x", x, "y", y, "z", z, "world", world)))
////							continue;
////						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
////								.getTemplateDefaulted(new ResourceLocation("natureplus", "graveyard"));
////						if (template == null)
////							return false;
////						template.addBlocksToWorld(world, spawnTo, new PlacementSettings().setRotation(rotation).setRandom(random).setMirror(mirror)
////								.addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK).setChunk(null).setIgnoreEntities(false));
////					}
////				}
////				return true;
////			}
////		};
////		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
////			biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, feature.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
////					.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
////		}
////	}
//}
