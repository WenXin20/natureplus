
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.PlantStemTrapdoorBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class PlantStemTrapdoorFuelFuel extends NatureplusModElements.ModElement {
	public PlantStemTrapdoorFuelFuel(NatureplusModElements instance) {
		super(instance, 355);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(PlantStemTrapdoorBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
