
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.PlantStemGlassTrapdoorBlock;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

@NatureplusModElements.ModElement.Tag
public class PlantStemGlassTrapdoorFuelFuel extends NatureplusModElements.ModElement {
	public PlantStemGlassTrapdoorFuelFuel(NatureplusModElements instance) {
		super(instance, 391);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(PlantStemGlassTrapdoorBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
