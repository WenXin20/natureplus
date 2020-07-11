
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.PlantStemWoodBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class PlantStemWoodFuelFuel extends NatureplusModElements.ModElement {
	public PlantStemWoodFuelFuel(NatureplusModElements instance) {
		super(instance, 308);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(PlantStemWoodBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
