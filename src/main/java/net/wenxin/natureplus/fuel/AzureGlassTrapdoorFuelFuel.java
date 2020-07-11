
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.AzureGlassTrapdoorBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class AzureGlassTrapdoorFuelFuel extends NatureplusModElements.ModElement {
	public AzureGlassTrapdoorFuelFuel(NatureplusModElements instance) {
		super(instance, 292);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(AzureGlassTrapdoorBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
