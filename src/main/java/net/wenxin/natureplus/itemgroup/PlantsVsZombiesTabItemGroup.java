
package net.wenxin.natureplus.itemgroup;

import net.wenxin.natureplus.item.PeashooterSeedPacketItem;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

@NatureplusModElements.ModElement.Tag
public class PlantsVsZombiesTabItemGroup extends NatureplusModElements.ModElement {
	public PlantsVsZombiesTabItemGroup(NatureplusModElements instance) {
		super(instance, 532);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabplants_vs_zombies_tab") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(PeashooterSeedPacketItem.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}
	public static ItemGroup tab;
}
