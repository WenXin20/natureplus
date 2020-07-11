
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.PlantStemSlabBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class PlantStemSlabFuelFuel extends NatureplusModElements.ModElement {
	public PlantStemSlabFuelFuel(NatureplusModElements instance) {
		super(instance, 338);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(PlantStemSlabBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
