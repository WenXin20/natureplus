
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.StrippedAzureWoodBlock;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

@NatureplusModElements.ModElement.Tag
public class StrippedAzureWoodFuelFuel extends NatureplusModElements.ModElement {
	public StrippedAzureWoodFuelFuel(NatureplusModElements instance) {
		super(instance, 258);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(StrippedAzureWoodBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
