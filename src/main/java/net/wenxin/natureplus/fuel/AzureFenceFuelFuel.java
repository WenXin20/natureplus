
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.AzureFenceBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class AzureFenceFuelFuel extends NatureplusModElements.ModElement {
	public AzureFenceFuelFuel(NatureplusModElements instance) {
		super(instance, 238);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(AzureFenceBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
