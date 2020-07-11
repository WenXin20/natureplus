
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.PlantStemGlassTrapdoorBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class PlantStemGlassTrapdoorFuelFuel extends NatureplusModElements.ModElement {
	public PlantStemGlassTrapdoorFuelFuel(NatureplusModElements instance) {
		super(instance, 357);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(PlantStemGlassTrapdoorBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
