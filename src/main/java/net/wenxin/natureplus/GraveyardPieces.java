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
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.AbstractVillagePiece;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.nbt.CompoundNBT;

import java.util.List;

@NatureplusModElements.ModElement.Tag
public class GraveyardPieces extends NatureplusModElements.ModElement {
	/**
	 * Do not remove this constructor
	 */
	public GraveyardPieces(NatureplusModElements instance) {
		super(instance, 932);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	public static void func_215139_a(ChunkGenerator<?> chunkGeneratorIn, TemplateManager templateManagerIn, BlockPos posIn,
			List<StructurePiece> structurePieces, SharedSeedRandom seedRand) {
		JigsawManager.addPieces(new ResourceLocation("natureplus", "structures/graveyard"), 7, GraveyardPieces.Graveyard::new, chunkGeneratorIn,
				templateManagerIn, posIn, structurePieces, seedRand);
	}
	public static class Graveyard extends AbstractVillagePiece {
		public Graveyard(TemplateManager templateManagerIn, JigsawPiece jigsawPieceIn, BlockPos posIn, int num, Rotation rotationIn,
				MutableBoundingBox boundsIn) {
			super(IStructurePieceType.PCP, templateManagerIn, jigsawPieceIn, posIn, num, rotationIn, boundsIn);
		}

		public Graveyard(TemplateManager templateManagerIn, CompoundNBT nbt) {
			super(templateManagerIn, nbt, IStructurePieceType.PCP);
		}
	}
	@Override
	public void initElements() {
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}

	@Override
	public void serverLoad(FMLServerStartingEvent event) {
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void clientLoad(FMLClientSetupEvent event) {
	}
}
