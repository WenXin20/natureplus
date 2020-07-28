package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.common.BiomeDictionary;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class HostileDragonflyNaturalSpawnProcedure extends NatureplusModElements.ModElement {
	public HostileDragonflyNaturalSpawnProcedure(NatureplusModElements instance) {
		super(instance, 787);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure HostileDragonflyNaturalSpawn!");
			return false;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure HostileDragonflyNaturalSpawn!");
			return false;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure HostileDragonflyNaturalSpawn!");
			return false;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure HostileDragonflyNaturalSpawn!");
			return false;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		return (((world.getLight(new BlockPos((int) x, (int) y, (int) z))) < 7) && (((BiomeDictionary
				.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)), BiomeDictionary.Type.HILLS))
				|| ((BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)), BiomeDictionary.Type.PLAINS))
						|| ((BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)), BiomeDictionary.Type.FOREST))
								|| ((BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)), BiomeDictionary.Type.JUNGLE))
										|| (BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)),
												BiomeDictionary.Type.HILLS))))))
				&& ((!(BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)), BiomeDictionary.Type.MUSHROOM)))
						&& ((!(BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)), BiomeDictionary.Type.WASTELAND)))
								&& ((!(BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)), BiomeDictionary.Type.NETHER)))
										&& ((!(BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)),
												BiomeDictionary.Type.END)))
												&& (!(BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)),
														BiomeDictionary.Type.VOID)))))))));
	}
}
