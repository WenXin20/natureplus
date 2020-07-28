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

import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.resources.I18n;

@NatureplusModElements.ModElement.Tag
public class Message extends NatureplusModElements.ModElement {
	/**
	 * Do not remove this constructor
	 */
	public Message(NatureplusModElements instance) {
		super(instance, 773);
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

	public static void tellPlayer(PlayerEntity player, String text) {
		player.sendStatusMessage(new StringTextComponent(I18n.format(text)), true);
	}

	public static StringTextComponent tooltip(String text, Object... parameters) {
		StringTextComponent msg = new StringTextComponent(I18n.format(text, parameters));
		msg.applyTextStyle(TextFormatting.GRAY);
		return msg;
	}
}
