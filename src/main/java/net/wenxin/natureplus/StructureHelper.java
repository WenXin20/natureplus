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
 * If you want to make a plain independent class, create it using
 * Project Browser - New... and make sure to make the class
 * outside net.wenxin.natureplus as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.wenxin.natureplus;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Util;

import java.util.Locale;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.BiMap;

@NatureplusModElements.ModElement.Tag
public class StructureHelper extends NatureplusModElements.ModElement {
	public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<>(ForgeRegistries.FEATURES, "natureplus");
	public static final Structure<NoFeatureConfig> GRAVEYARD = register("natureplus:graveyard", new GraveyardStructure(NoFeatureConfig::deserialize));
	public static final BiMap<String, Structure<?>> STRUCTURES = Util.make(net.minecraftforge.registries.GameData.getStructureMap(),
			(p_205170_0_) -> {
				if (true)
					return; // Forge: This is now a slave map to the feature registry, leave this code here
							// to reduce patch size
				p_205170_0_.put("Graveyard".toLowerCase(Locale.ROOT), GRAVEYARD);
			});
	/**
	 * Do not remove this constructor
	 */
	public StructureHelper(NatureplusModElements instance) {
		super(instance, 931);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		final List<Structure<?>> list = Lists.newArrayList(Feature.ILLAGER_STRUCTURES);
		list.add(GRAVEYARD);
	}

	private static <C extends IFeatureConfig, F extends Feature<C>> F register(String key, F value) {
		return (F) (Registry.<Feature<?>>register(Registry.FEATURE, key, value));
	}

	@Override
	public void serverLoad(FMLServerStartingEvent event) {
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void clientLoad(FMLClientSetupEvent event) {
	}
}
