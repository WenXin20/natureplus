package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.SunflowerSeedPacketItem;
import net.wenxin.natureplus.item.SnowPeaSeedPacketItem;
import net.wenxin.natureplus.item.PeashooterSeedPacketItem;
import net.wenxin.natureplus.item.MonarchEggJarItem;
import net.wenxin.natureplus.item.KernelPultSeedPacketItem;
import net.wenxin.natureplus.block.PlantVaseBlock;
import net.wenxin.natureplus.NatureplusModVariables;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

@NatureplusModElements.ModElement.Tag
public class PlantVaseRandomOutputRedstoneProcedure extends NatureplusModElements.ModElement {
	public PlantVaseRandomOutputRedstoneProcedure(NatureplusModElements instance) {
		super(instance, 708);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure PlantVaseRandomOutputRedstone!");
			return;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure PlantVaseRandomOutputRedstone!");
			return;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure PlantVaseRandomOutputRedstone!");
			return;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure PlantVaseRandomOutputRedstone!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		World world = (World) dependencies.get("world");
		if ((!(world.isRemote))) {
			if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == PlantVaseBlock.block.getDefaultState().getBlock())
					&& (world.isBlockPowered(new BlockPos((int) x, (int) y, (int) z))))) {
				world.playSound((PlayerEntity) null, (x + 0.5), y, (z + 0.5),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:vase_breaking")),
						SoundCategory.NEUTRAL, (float) 2, (float) 1);
				NatureplusModVariables.i = (double) (Math.random() * 100);
				if (((NatureplusModVariables.i) >= 60)) {
					if (!world.isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world, (x + 0.5), y, (z + 0.5),
								new ItemStack(PeashooterSeedPacketItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				} else if ((((NatureplusModVariables.i) >= 40) && (!((NatureplusModVariables.i) >= 60)))) {
					if (!world.isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world, (x + 0.5), y, (z + 0.5),
								new ItemStack(SunflowerSeedPacketItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				} else if ((((NatureplusModVariables.i) >= 20) && (!((NatureplusModVariables.i) >= 40)))) {
					if (!world.isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world, (x + 0.5), y, (z + 0.5),
								new ItemStack(KernelPultSeedPacketItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				} else if ((((NatureplusModVariables.i) >= 10) && (!((NatureplusModVariables.i) >= 20)))) {
					if (!world.isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world, (x + 0.5), y, (z + 0.5),
								new ItemStack(SnowPeaSeedPacketItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				} else {
					if (!world.isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world, (x + 0.5), y, (z + 0.5), new ItemStack(MonarchEggJarItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				}
				world.destroyBlock(new BlockPos((int) x, (int) y, (int) z), false);
			}
		}
	}

	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent event) {
		Entity entity = event.getPlayer();
		java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
		dependencies.put("xpAmount", event.getExpToDrop());
		dependencies.put("x", (int) event.getPos().getX());
		dependencies.put("y", (int) event.getPos().getY());
		dependencies.put("z", (int) event.getPos().getZ());
		dependencies.put("px", entity.getPosX());
		dependencies.put("py", entity.getPosY());
		dependencies.put("pz", entity.getPosZ());
		dependencies.put("world", event.getWorld().getWorld());
		dependencies.put("entity", entity);
		dependencies.put("event", event);
		this.executeProcedure(dependencies);
	}
}
