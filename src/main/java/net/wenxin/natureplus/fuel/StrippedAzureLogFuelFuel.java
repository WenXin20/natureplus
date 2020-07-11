
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.StrippedAzureLogBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class StrippedAzureLogFuelFuel extends NatureplusModElements.ModElement {
	public StrippedAzureLogFuelFuel(NatureplusModElements instance) {
		super(instance, 217);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(StrippedAzureLogBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
