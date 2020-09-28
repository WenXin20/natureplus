package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.common.BiomeDictionary;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class KernelPultNaturalSpawnProcedure extends NatureplusModElements.ModElement {
	public KernelPultNaturalSpawnProcedure(NatureplusModElements instance) {
		super(instance, 780);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure KernelPultNaturalSpawn!");
			return false;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure KernelPultNaturalSpawn!");
			return false;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure KernelPultNaturalSpawn!");
			return false;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure KernelPultNaturalSpawn!");
			return false;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		return (((world.getLight(new BlockPos((int) x, (int) y, (int) z))) >= 7) && (((BiomeDictionary
				.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)), BiomeDictionary.Type.HILLS))
				|| ((BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)), BiomeDictionary.Type.PLAINS))
						|| ((BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)), BiomeDictionary.Type.FOREST))
								|| ((BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)), BiomeDictionary.Type.JUNGLE))
										|| (BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)),
												BiomeDictionary.Type.HILLS))))))
				&& ((!(BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)), BiomeDictionary.Type.COLD)))
						&& ((!(BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)), BiomeDictionary.Type.WASTELAND)))
								&& ((!(BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)), BiomeDictionary.Type.SNOWY)))
										&& ((!(BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)),
												BiomeDictionary.Type.NETHER)))
												&& ((!(BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)),
														BiomeDictionary.Type.END)))
														&& (!(BiomeDictionary.hasType(world.getBiome(new BlockPos((int) x, (int) y, (int) z)),
																BiomeDictionary.Type.VOID))))))))));
	}
}
