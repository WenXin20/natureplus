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

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;

@NatureplusModElements.ModElement.Tag
public class MobNBTHelper extends NatureplusModElements.ModElement {
	/**
	 * Do not remove this constructor
	 */
	public MobNBTHelper(NatureplusModElements instance) {
		super(instance, 772);
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
	
	// Butterfly Net
	public static final String MOB = "capturedMob";
	public static final String MOB_NAME = "mobName";
	public static final String MOB_DATA = "mobData";
	public static final String MOB_HEALTH = "mobHealth";
	public static final String MOB_MAX_HEALTH = "mobMaxHealth";
	public static final String MOB_HOSTILE = "mobHostile";
	public static final String MOB_MOD = "mobMod";
	public static final String MOD_ID = "modId";

	public static CompoundNBT getBaseTag(ItemStack stack) {
		return stack.getOrCreateChildTag(MOB);
	}

	public static void setBaseTag(ItemStack stack, CompoundNBT nbt) {
		stack.getOrCreateTag().put(MOB, nbt);
	}

	public static boolean hasMob(ItemStack stack) {
		return stack.getOrCreateTag().contains(MOB);
	}

	public static boolean hasHostileMob(ItemStack stack) {
		if (!hasMob(stack))
			return false;
		CompoundNBT nbt = MobNBTHelper.getBaseTag(stack);
		return nbt.getBoolean(MobNBTHelper.MOB_HOSTILE);
	}
}
