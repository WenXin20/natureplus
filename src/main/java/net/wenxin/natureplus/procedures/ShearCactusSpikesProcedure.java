package net.wenxin.natureplus.procedures;

import net.wenxin.natureplus.item.SpikeItem;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Hand;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.block.CactusBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

@NatureplusModElements.ModElement.Tag
public class ShearCactusSpikesProcedure extends NatureplusModElements.ModElement {
	public ShearCactusSpikesProcedure(NatureplusModElements instance) {
		super(instance, 845);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
		PlayerEntity entity = event.getPlayer();
		if (event.getHand() != entity.getActiveHand())
			return;
		int i = event.getPos().getX();
		int j = event.getPos().getY();
		int k = event.getPos().getZ();
		World world = event.getWorld();
		BlockState state = event.getWorld().getBlockState(event.getPos());
		if ((!(event.getWorld().isRemote))) {
			if (state.getBlock() instanceof CactusBlock) {
				if (event.getItemStack().getItem() instanceof ShearsItem) {
					if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
						event.getItemStack().attemptDamageItem(1, event.getWorld().getRandom(),
								event.getPlayer() instanceof ServerPlayerEntity ? (ServerPlayerEntity) event.getPlayer() : null);
					}
					event.getWorld().playSound(event.getPlayer(), event.getPos(), SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);
					if (entity instanceof LivingEntity) {
						((LivingEntity) entity).swing(Hand.MAIN_HAND, true);
					}
					if ((!(event.getWorld().isRemote))) {
						if ((Math.random() < 0.65)) {
							BlockState blockState = Blocks.AIR.getDefaultState();
							world.destroyBlock(event.getPos(), false);
							System.out.println("Break cactus");
						}
					}
					if ((!(event.getWorld().isRemote))) {
						if ((Math.random() < 1)) {
							if (!event.getWorld().isRemote) {
								ItemEntity entityToSpawn = new ItemEntity(event.getWorld(), event.getPos().getX(), event.getPos().getY(),
										event.getPos().getZ(), new ItemStack(SpikeItem.block, (int) (1)));
								entityToSpawn.setPickupDelay(10);
								world.addEntity(entityToSpawn);
								System.out.println("Shear cactus");
							}
						}
						if ((Math.random() < 0.05)) {
							if (!event.getWorld().isRemote) {
								ItemEntity entityToSpawn = new ItemEntity(event.getWorld(), event.getPos().getX(), event.getPos().getY(),
										event.getPos().getZ(), new ItemStack(SpikeItem.block, (int) (1)));
								entityToSpawn.setPickupDelay(10);
								world.addEntity(entityToSpawn);
								System.out.println("Shear cactus 2");
							}
						}
						if ((Math.random() < 0.015)) {
							if (!event.getWorld().isRemote) {
								ItemEntity entityToSpawn = new ItemEntity(event.getWorld(), event.getPos().getX(), event.getPos().getY(),
										event.getPos().getZ(), new ItemStack(SpikeItem.block, (int) (1)));
								entityToSpawn.setPickupDelay(10);
								world.addEntity(entityToSpawn);
								System.out.println("Shear cactus 3");
							}
						}
					}
				}
			}
		}
	}
}
