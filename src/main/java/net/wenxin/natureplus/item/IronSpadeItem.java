
package net.wenxin.natureplus.item;

import net.wenxin.natureplus.procedures.SpadeRemovePeashooterProcedure;
import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.IItemTier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.client.util.ITooltipFlag;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class IronSpadeItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:iron_spade")
	public static final Item block = null;
	public IronSpadeItem(NatureplusModElements instance) {
		super(instance, 210);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ShovelItem(new IItemTier() {
			public int getMaxUses() {
				return 250;
			}

			public float getEfficiency() {
				return 6f;
			}

			public float getAttackDamage() {
				return 2.5f;
			}

			public int getHarvestLevel() {
				return 2;
			}

			public int getEnchantability() {
				return 14;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Items.IRON_INGOT, (int) (1)));
			}
		}, 1, -3f, new Item.Properties().group(PlantsVsZombiesTabItemGroup.tab)) {
			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("\u00A77\u00A7oRemoves PvZ plants,"));
				list.add(new StringTextComponent("\u00A77\u00A7ostunts insect growth"));
			}

			@Override
			public boolean hitEntity(ItemStack itemstack, LivingEntity entity, LivingEntity sourceentity) {
				boolean retval = super.hitEntity(itemstack, entity, sourceentity);
				double x = entity.getPosX();
				double y = entity.getPosY();
				double z = entity.getPosZ();
				World world = entity.world;
				{
					Map<String, Object> $_dependencies = new HashMap<>();
					$_dependencies.put("entity", entity);
					$_dependencies.put("sourceentity", sourceentity);
					$_dependencies.put("x", x);
					$_dependencies.put("y", y);
					$_dependencies.put("z", z);
					$_dependencies.put("world", world);
					SpadeRemovePeashooterProcedure.executeProcedure($_dependencies);
				}
				return retval;
			}
		}.setRegistryName("iron_spade"));
	}
}
