
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.PlantStemPlanksBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class PlantStemPlanksFuelFuel extends NatureplusModElements.ModElement {
	public PlantStemPlanksFuelFuel(NatureplusModElements instance) {
		super(instance, 326);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(PlantStemPlanksBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
