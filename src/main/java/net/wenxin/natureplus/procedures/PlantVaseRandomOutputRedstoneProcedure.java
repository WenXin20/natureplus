package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.SunflowerSeedPacketItem;
import net.wenxin.natureplus.item.SnowPeaSeedPacketItem;
import net.wenxin.natureplus.item.PeashooterSeedPacketItem;
import net.wenxin.natureplus.item.MonarchEggJarItem;
import net.wenxin.natureplus.item.KernelPultSeedPacketItem;
import net.wenxin.natureplus.item.CactusSeedPacketItem;
import net.wenxin.natureplus.block.PlantVaseBlock;
import net.wenxin.natureplus.NatureplusModVariables;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class PlantVaseRandomOutputRedstoneProcedure extends NatureplusModElements.ModElement {
	public PlantVaseRandomOutputRedstoneProcedure(NatureplusModElements instance) {
		super(instance, 704);
		MinecraftForge.EVENT_BUS.register(this);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				System.err.println("Failed to load dependency x for procedure PlantVaseRandomOutputRedstone!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				System.err.println("Failed to load dependency y for procedure PlantVaseRandomOutputRedstone!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				System.err.println("Failed to load dependency z for procedure PlantVaseRandomOutputRedstone!");
			return;
		}
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				System.err.println("Failed to load dependency world for procedure PlantVaseRandomOutputRedstone!");
			return;
		}
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((!(world.getWorld().isRemote))) {
			if ((((world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock() == PlantVaseBlock.block.getDefaultState().getBlock())
					&& (world.getWorld().isBlockPowered(new BlockPos((int) x, (int) y, (int) z))))) {
				if (!world.getWorld().isRemote) {
					world.playSound(null, new BlockPos((int) (x + 0.5), (int) y, (int) (z + 0.5)),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:vase_breaking")),
							SoundCategory.NEUTRAL, (float) 2, (float) 1);
				} else {
					world.getWorld().playSound((x + 0.5), y, (z + 0.5),
							(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:vase_breaking")),
							SoundCategory.NEUTRAL, (float) 2, (float) 1, false);
				}
				NatureplusModVariables.i = (double) (Math.random() * 100);
				if (((NatureplusModVariables.i) >= 60)) {
					if (!world.getWorld().isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (x + 0.5), y, (z + 0.5),
								new ItemStack(PeashooterSeedPacketItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				} else if ((((NatureplusModVariables.i) >= 30) && (!((NatureplusModVariables.i) >= 60)))) {
					if (!world.getWorld().isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (x + 0.5), y, (z + 0.5),
								new ItemStack(SunflowerSeedPacketItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				} else if ((((NatureplusModVariables.i) >= 20) && (!((NatureplusModVariables.i) >= 30)))) {
					if (!world.getWorld().isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (x + 0.5), y, (z + 0.5),
								new ItemStack(CactusSeedPacketItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				} else if ((((NatureplusModVariables.i) >= 10) && (!((NatureplusModVariables.i) >= 20)))) {
					if (!world.getWorld().isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (x + 0.5), y, (z + 0.5),
								new ItemStack(KernelPultSeedPacketItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				} else if ((((NatureplusModVariables.i) >= 5) && (!((NatureplusModVariables.i) >= 10)))) {
					if (!world.getWorld().isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (x + 0.5), y, (z + 0.5),
								new ItemStack(SnowPeaSeedPacketItem.block, (int) (1)));
						entityToSpawn.setPickupDelay(10);
						world.addEntity(entityToSpawn);
					}
				} else {
					if (!world.getWorld().isRemote) {
						ItemEntity entityToSpawn = new ItemEntity(world.getWorld(), (x + 0.5), y, (z + 0.5),
								new ItemStack(MonarchEggJarItem.block, (int) (1)));
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
		Map<String, Object> dependencies = new HashMap<>();
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
