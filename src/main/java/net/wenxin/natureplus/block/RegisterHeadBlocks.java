package net.wenxin.natureplus.block;

import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;
import net.wenxin.natureplus.client.renderer.SkullItemStackRenderer;
import net.wenxin.natureplus.NatureplusWallHeadBlock;
import net.wenxin.natureplus.NatureplusHeadItem;
import net.wenxin.natureplus.NatureplusHeadBlock;

import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.Rarity;
import net.minecraft.item.Item;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.Block;

public final class RegisterHeadBlocks {
	public static final String SNOW_PEA_HEAD = "snow_pea_head2";
	public static final String SNOW_PEA_WALL_HEAD = "snow_pea_wall_head";
	public static Block snowPeaHead = new NatureplusHeadBlock(
			Block.Properties.create(Material.LEAVES).sound(SoundType.GLASS).hardnessAndResistance(1));
	public static Block snowPeaHeadWall = new NatureplusWallHeadBlock(
			Block.Properties.create(Material.LEAVES).sound(SoundType.GLASS).hardnessAndResistance(1));
	public static void registerBlocks(RegistryEvent.Register<Block> evt) {
		IForgeRegistry<Block> r = evt.getRegistry();
		register(r, SNOW_PEA_HEAD, snowPeaHead);
		register(r, SNOW_PEA_WALL_HEAD, snowPeaHeadWall);
	}

	@OnlyIn(Dist.DEDICATED_SERVER)
	private static Void registerWithoutTEISRS(IForgeRegistry<Item> r) {
		Item.Properties props = RegisterHeadBlocks.defaultBuilder();
		Item head = new NatureplusHeadItem(snowPeaHead, snowPeaHeadWall, RegisterHeadBlocks.defaultBuilder().rarity(Rarity.UNCOMMON));
		register(r, Registry.BLOCK.getKey(snowPeaHead), head);
		return null;
	}

	@OnlyIn(Dist.CLIENT)
	private static Void registerWithTEISRS(IForgeRegistry<Item> r) {
		Item head = new NatureplusHeadItem(snowPeaHead, snowPeaHeadWall,
				RegisterHeadBlocks.defaultBuilder().rarity(Rarity.UNCOMMON).setISTER(() -> () -> new SkullItemStackRenderer(snowPeaHead)));
		register(r, Registry.BLOCK.getKey(snowPeaHead), head);
		return null;
	}

	public static Item.Properties defaultBuilder() {
		return new Item.Properties().group(PlantsVsZombiesTabItemGroup.tab);
	}

	public static <V extends IForgeRegistryEntry<V>> void register(IForgeRegistry<V> reg, ResourceLocation name, IForgeRegistryEntry<V> thing) {
		reg.register(thing.setRegistryName(name));
	}

	public static <V extends IForgeRegistryEntry<V>> void register(IForgeRegistry<V> reg, String name, IForgeRegistryEntry<V> thing) {
		register(reg, new ResourceLocation("natureplus", name), thing);
	}
}
