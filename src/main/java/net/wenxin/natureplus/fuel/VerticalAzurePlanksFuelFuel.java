
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.VerticalAzurePlanksBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class VerticalAzurePlanksFuelFuel extends NatureplusModElements.ModElement {
	public VerticalAzurePlanksFuelFuel(NatureplusModElements instance) {
		super(instance, 234);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(VerticalAzurePlanksBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
