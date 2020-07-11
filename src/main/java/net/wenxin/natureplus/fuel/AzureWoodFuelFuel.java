
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.AzureWoodBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class AzureWoodFuelFuel extends NatureplusModElements.ModElement {
	public AzureWoodFuelFuel(NatureplusModElements instance) {
		super(instance, 211);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(AzureWoodBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
