
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.AzureSlabBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class AzureSlabFuelFuel extends NatureplusModElements.ModElement {
	public AzureSlabFuelFuel(NatureplusModElements instance) {
		super(instance, 246);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(AzureSlabBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
