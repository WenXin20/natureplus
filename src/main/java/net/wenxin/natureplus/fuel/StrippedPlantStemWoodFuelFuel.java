
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.StrippedPlantStemWoodBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class StrippedPlantStemWoodFuelFuel extends NatureplusModElements.ModElement {
	public StrippedPlantStemWoodFuelFuel(NatureplusModElements instance) {
		super(instance, 313);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(StrippedPlantStemWoodBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
