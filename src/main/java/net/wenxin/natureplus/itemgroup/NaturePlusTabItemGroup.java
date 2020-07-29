
package net.wenxin.natureplus.itemgroup;

import net.wenxin.natureplus.block.AzureSaplingBlock;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

@NatureplusModElements.ModElement.Tag
public class NaturePlusTabItemGroup extends NatureplusModElements.ModElement {
	public NaturePlusTabItemGroup(NatureplusModElements instance) {
		super(instance, 237);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabnature_plus_tab") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(AzureSaplingBlock.block, (int) (1));
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundImageName("item_search.png");
	}
	public static ItemGroup tab;
}
