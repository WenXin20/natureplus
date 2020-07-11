
package net.wenxin.natureplus.fuel;

import net.wenxin.natureplus.block.StrippedAzureWoodWallBlock;
import net.wenxin.natureplus.NatureplusModElements;

@NatureplusModElements.ModElement.Tag
public class AzureWoodWallFuelFuel extends NatureplusModElements.ModElement {
	public AzureWoodWallFuelFuel(NatureplusModElements instance) {
		super(instance, 215);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
		if (event.getItemStack().getItem() == new ItemStack(StrippedAzureWoodWallBlock.block, (int) (1)).getItem())
			event.setBurnTime(300);
	}
}
