
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.PlantStemGlassDoorBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class PlantStemGlassDoorFuelFuel extends NatureplusModElements.ModElement {
	public PlantStemGlassDoorFuelFuel(NatureplusModElements instance) {
		super(instance, 526);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(PlantStemGlassDoorBlock.block, (int) (1)).getItem())
			event.setBurnTime(200);
	}
}
