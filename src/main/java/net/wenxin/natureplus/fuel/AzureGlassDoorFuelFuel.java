
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.AzureGlassDoorBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class AzureGlassDoorFuelFuel extends NatureplusModElements.ModElement {
	public AzureGlassDoorFuelFuel(NatureplusModElements instance) {
		super(instance, 522);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(AzureGlassDoorBlock.block, (int) (1)).getItem())
			event.setBurnTime(200);
	}
}
