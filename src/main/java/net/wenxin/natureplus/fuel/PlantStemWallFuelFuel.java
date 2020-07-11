
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.PlantStemWallBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class PlantStemWallFuelFuel extends NatureplusModElements.ModElement {
	public PlantStemWallFuelFuel(NatureplusModElements instance) {
		super(instance, 319);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(PlantStemWallBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
