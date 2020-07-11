
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.VerticalAzureSlabBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class VerticalAzureSlabFuelFuel extends NatureplusModElements.ModElement {
	public VerticalAzureSlabFuelFuel(NatureplusModElements instance) {
		super(instance, 340);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(VerticalAzureSlabBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
