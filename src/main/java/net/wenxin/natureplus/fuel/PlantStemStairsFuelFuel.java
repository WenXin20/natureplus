
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.PlantStemStairsBlock;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

@NatureplusModElements.ModElement.Tag
public class PlantStemStairsFuelFuel extends NatureplusModElements.ModElement {
	public PlantStemStairsFuelFuel(NatureplusModElements instance) {
		super(instance, 382);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(PlantStemStairsBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
