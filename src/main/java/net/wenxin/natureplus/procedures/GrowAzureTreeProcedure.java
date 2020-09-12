package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.NatureplusModElements;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class GrowAzureTreeProcedure extends NatureplusModElements.ModElement {
	public GrowAzureTreeProcedure(NatureplusModElements instance) {
		super(instance, 274);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure GrowAzureTree!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure GrowAzureTree!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure GrowAzureTree!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure GrowAzureTree!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if (((Math.random() <= 0.2) && (!(world.getWorld().isRemote)))) {
			if ((world.canBlockSeeSky(new BlockPos((int) x, (int) y, (int) z)))) {
				if ((Math.random() <= 0.25)) {
					if (!world.getWorld().isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("natureplus", "azure_tree1"));
						if (template != null) {
							template.addBlocksToWorld(world, new BlockPos((int) (x - 3), (int) y, (int) (z - 3)), new PlacementSettings()
									.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
						}
					}
				} else if ((Math.random() <= 0.25)) {
					if (!world.getWorld().isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("natureplus", "azure_tree2"));
						if (template != null) {
							template.addBlocksToWorld(world, new BlockPos((int) (x - 3), (int) y, (int) (z - 3)), new PlacementSettings()
									.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
						}
					}
				} else if ((Math.random() <= 0.25)) {
					if (!world.getWorld().isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("natureplus", "azure_tree3"));
						if (template != null) {
							template.addBlocksToWorld(world, new BlockPos((int) (x - 3), (int) y, (int) (z - 3)), new PlacementSettings()
									.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
						}
					}
				} else {
					if (!world.getWorld().isRemote) {
						Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
								.getTemplateDefaulted(new ResourceLocation("natureplus", "azure_tree4"));
						if (template != null) {
							template.addBlocksToWorld(world, new BlockPos((int) (x - 3), (int) y, (int) (z - 3)), new PlacementSettings()
									.setRotation(Rotation.NONE).setMirror(Mirror.NONE).setChunk(null).setIgnoreEntities(false));
						}
					}
				}
			}
		}
	}
}
