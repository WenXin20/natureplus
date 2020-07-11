package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.NatureplusModElements;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class GrowAzureTreeProcedure extends NatureplusModElements.ModElement {
	public GrowAzureTreeProcedure(NatureplusModElements instance) {
		super(instance, 220);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure GrowAzureTree!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure GrowAzureTree!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure GrowAzureTree!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure GrowAzureTree!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if (((Math.random() <= 0.2) && (!(world.isRemote)))) {
			if ((world.canBlockSeeSky(new BlockPos((int) x, (int) y, (int) z)))) {
				if ((Math.random() <= 0.25)) {
					if (!world.isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("natureplus", "azure_tree1"));
						if (template != null) {
							template.addBlocksToWorldChunk(world, new BlockPos((int) (x - 3), (int) y, (int) (z - 3)), new PlacementSettings()
									.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk((ChunkPos) null).setIgnoreEntities(false));
						}
					}
				} else if ((Math.random() <= 0.25)) {
					if (!world.isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("natureplus", "azure_tree2"));
						if (template != null) {
							template.addBlocksToWorldChunk(world, new BlockPos((int) (x - 3), (int) y, (int) (z - 3)), new PlacementSettings()
									.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk((ChunkPos) null).setIgnoreEntities(false));
						}
					}
				} else if ((Math.random() <= 0.25)) {
					if (!world.isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("natureplus", "azure_tree3"));
						if (template != null) {
							template.addBlocksToWorldChunk(world, new BlockPos((int) (x - 3), (int) y, (int) (z - 3)), new PlacementSettings()
									.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk((ChunkPos) null).setIgnoreEntities(false));
						}
					}
				} else {
					if (!world.isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("natureplus", "azure_tree4"));
						if (template != null) {
							template.addBlocksToWorldChunk(world, new BlockPos((int) (x - 3), (int) y, (int) (z - 3)), new PlacementSettings()
									.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk((ChunkPos) null).setIgnoreEntities(false));
						}
					}
				}
			}
		}
	}
}
