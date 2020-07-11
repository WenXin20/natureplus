
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.VerticalPlantStemStairsBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class VerticalPlantStemStairsFuelFuel extends NatureplusModElements.ModElement {
	public VerticalPlantStemStairsFuelFuel(NatureplusModElements instance) {
		super(instance, 350);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(VerticalPlantStemStairsBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
