
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.StrippedPlantStemWallBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class StrippedPlantStemWallFuelFuel extends NatureplusModElements.ModElement {
	public StrippedPlantStemWallFuelFuel(NatureplusModElements instance) {
		super(instance, 322);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(StrippedPlantStemWallBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
