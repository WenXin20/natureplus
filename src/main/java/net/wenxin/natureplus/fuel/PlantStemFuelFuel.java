
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.PlantStemBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class PlantStemFuelFuel extends NatureplusModElements.ModElement {
	public PlantStemFuelFuel(NatureplusModElements instance) {
		super(instance, 305);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(PlantStemBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
