/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class NatureplusModElements.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it in
 * "Workspace" -> "Source" menu.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.wenxin.natureplus;

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.LootTableLoadEvent;

import net.minecraft.world.storage.loot.TableLootEntry;
import net.minecraft.world.storage.loot.LootTables;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.util.ResourceLocation;

@NatureplusModElements.ModElement.Tag
public final class LootHandler extends NatureplusModElements.ModElement {
	/**
	 * Do not remove this constructor
	 */
	public LootHandler(NatureplusModElements instance) {
		super(instance, 213);
	}
	private static ResourceLocation abandoned_mineshaft = LootTables.CHESTS_ABANDONED_MINESHAFT;
	private static ResourceLocation desert_pyramid = LootTables.CHESTS_DESERT_PYRAMID;
	private static ResourceLocation igloo_chest = LootTables.CHESTS_IGLOO_CHEST;
	private static ResourceLocation simple_dungeon = LootTables.CHESTS_SIMPLE_DUNGEON;
	private static ResourceLocation stronghold_corridor = LootTables.CHESTS_STRONGHOLD_CORRIDOR;
	private static ResourceLocation underwater_ruin_big = LootTables.CHESTS_UNDERWATER_RUIN_BIG;
	private static ResourceLocation woodland_mansion = LootTables.CHESTS_WOODLAND_MANSION;
	private static ResourceLocation taiga = LootTables.CHESTS_VILLAGE_VILLAGE_TAIGA_HOUSE;
	private static ResourceLocation savanna = LootTables.CHESTS_VILLAGE_VILLAGE_SAVANNA_HOUSE;
	@SubscribeEvent
	public void lootTableLoad(LootTableLoadEvent event) {
		if (event.getName().equals(abandoned_mineshaft))
			event.getTable()
					.addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation("natureplus:inject/abandoned_mineshaft")))
							.name("natureplus_abandoned_mineshaft").build());
		if (event.getName().equals(desert_pyramid))
			event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation("natureplus:inject/desert_pyramid")))
					.name("natureplus_desert_pyramid").build());
		if (event.getName().equals(igloo_chest))
			event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation("natureplus:inject/igloo_chest")))
					.name("natureplus_igloo_chest").build());
		if (event.getName().equals(simple_dungeon))
			event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation("natureplus:inject/simple_dungeon")))
					.name("natureplus_simple_dungeon").build());
		if (event.getName().equals(stronghold_corridor))
			event.getTable()
					.addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation("natureplus:inject/stronghold_corridor")))
							.name("natureplus_stronghold_corridor").build());
		if (event.getName().equals(underwater_ruin_big))
			event.getTable()
					.addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation("natureplus:inject/underwater_ruin_big")))
							.name("natureplus_underwater_ruin_big").build());
		if (event.getName().equals(woodland_mansion))
			event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation("natureplus:inject/woodland_mansion")))
					.name("natureplus_woodland_mansion").build());
		if (event.getName().equals(taiga))
			event.getTable().addPool(
					LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation("natureplus:inject/village/village_taiga_house")))
							.name("natureplus_taiga").build());
		if (event.getName().equals(savanna))
			event.getTable()
					.addPool(LootPool.builder()
							.addEntry(TableLootEntry.builder(new ResourceLocation("natureplus:inject/village/village_savanna_house")))
							.name("natureplus_savanna").build());
	}

	@Override
	public void initElements() {
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
	}

	@Override
	public void serverLoad(FMLServerStartingEvent event) {
	}
}
