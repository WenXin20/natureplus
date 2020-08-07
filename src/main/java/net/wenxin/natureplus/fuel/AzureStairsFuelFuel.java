
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.AzureStairsBlock;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.item.ItemStack;

@NatureplusModElements.ModElement.Tag
public class AzureStairsFuelFuel extends NatureplusModElements.ModElement {
	public AzureStairsFuelFuel(NatureplusModElements instance) {
		super(instance, 284);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(AzureStairsBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
