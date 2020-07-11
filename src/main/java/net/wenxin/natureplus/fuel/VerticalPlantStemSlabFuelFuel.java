
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.VerticalPlantStemSlabBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class VerticalPlantStemSlabFuelFuel extends NatureplusModElements.ModElement {
	public VerticalPlantStemSlabFuelFuel(NatureplusModElements instance) {
		super(instance, 341);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(VerticalPlantStemSlabBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
