/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class NatureplusModElements.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser - New... and make sure to make the class
 * outside net.wenxin.natureplus as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.wenxin.natureplus;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.feature.structure.SwampHutPiece;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.ScatteredStructure;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.biome.BiomeManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Rotation;
import net.minecraft.entity.EntityType;

import mcp.client.Start;

import java.util.function.Function;
import java.util.Random;
import java.util.List;

import com.mojang.datafixers.Dynamic;

import com.google.common.collect.Lists;

// WIP
@NatureplusModElements.ModElement.Tag
public class GraveyardStructure extends ScatteredStructure<NoFeatureConfig> {
	private static final List<Biome.SpawnListEntry> GRAVEYARD_ENEMIES = Lists.newArrayList(new Biome.SpawnListEntry(EntityType.ZOMBIE, 1, 1, 1));
	public GraveyardStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> GraveyardConfigIn) {
		super(GraveyardConfigIn);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	public String getStructureName() {
		return "natureplus:Graveyard";
	}

	public int getSize() {
		return 1;
	}

	public List<Biome.SpawnListEntry> getSpawnList() {
		return GRAVEYARD_ENEMIES;
	}

	@Override
	protected ChunkPos getStartPositionForPosition(ChunkGenerator<?> generator, Random random, int chunkX, int chunkZ, int offsetX, int offsetZ) {
		int chunkPosX = chunkX + 35 * offsetX;
		int chunkPosZ = chunkZ + 35 * offsetZ;
		int chunkPosX1 = chunkPosX < 0 ? chunkPosX - 35 + 1 : chunkPosX;
		int chunkPosZ1 = chunkPosZ < 0 ? chunkPosZ - 35 + 1 : chunkPosZ;
		int lvt_13_1_ = chunkPosX1 / 35;
		int lvt_14_1_ = chunkPosZ1 / 35;
		((SharedSeedRandom) random).setLargeFeatureSeedWithSalt(generator.getSeed(), lvt_13_1_, lvt_14_1_, 16897777);
		lvt_13_1_ *= 35;
		lvt_14_1_ *= 35;
		lvt_13_1_ += random.nextInt(35 - 5);
		lvt_14_1_ += random.nextInt(35 - 5);
		return new ChunkPos(lvt_13_1_, lvt_14_1_);
	}

	public boolean canBeGenerated(BiomeManager biomeManagerIn, ChunkGenerator<?> generatorIn, Random randIn, int chunkX, int chunkZ, Biome biomeIn,
			IWorld world, BlockPos pos) {
		ChunkPos chunkpos = this.getStartPositionForPosition(generatorIn, randIn, chunkX, chunkZ, 0, 0);
		return chunkX == chunkpos.x && chunkZ == chunkpos.z ? generatorIn.hasStructure(biomeIn, this) : false;
	}

	@Override
	public Structure.IStartFactory getStartFactory() {
		return GraveyardStructure.Start::new;
	}

	protected int getSeedModifier() {
		return 165745296;
	}
	public static class Start extends MarginedStructureStart {
		public Start(Structure<?> struc, int chunkX, int chunkZ, MutableBoundingBox boundingBox, int x1, long x2) {
			super(struc, chunkX, chunkZ, boundingBox, x1, x2);
		}

		@Override
		public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
			BlockPos blockpos = new BlockPos(chunkX * 16, 90, chunkZ * 16);
			GraveyardPieces.func_215139_a(generator, templateManagerIn, blockpos, this.components, this.rand);
			this.recalculateStructureSize();
			NoFeatureConfig nofeatureconfig = (NoFeatureConfig) generator.getStructureConfig(biomeIn, StructureHelper.GRAVEYARD);
			Rotation rotation = Rotation.values()[this.rand.nextInt(Rotation.values().length)];
		}
	}
	public boolean func_202383_b(IWorld worldIn, BlockPos pos) {
		StructureStart structurestart = this.getStart(worldIn, pos, true);
		if (structurestart != StructureStart.DUMMY && structurestart instanceof GraveyardStructure.Start
				&& !structurestart.getComponents().isEmpty()) {
			StructurePiece structurepiece = structurestart.getComponents().get(0);
			return structurepiece instanceof SwampHutPiece;
		} else {
			return false;
		}
	}
}
