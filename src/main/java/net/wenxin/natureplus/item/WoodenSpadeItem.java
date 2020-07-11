
package net.wenxin.natureplus.item;

import org.omg.CORBA.ObjectHolder;

import net.wenxin.natureplus.procedures.SpadeRemovePeashooterProcedure;
import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

@NatureplusModElements.ModElement.Tag
public class WoodenSpadeItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:wooden_spade")
	public static final Item block = null;
	public WoodenSpadeItem(NatureplusModElements instance) {
		super(instance, 144);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ShovelItem(new IItemTier() {
			public int getMaxUses() {
				return 59;
			}

			public float getEfficiency() {
				return 2f;
			}

			public float getAttackDamage() {
				return 0.5f;
			}

			public int getHarvestLevel() {
				return 0;
			}

			public int getEnchantability() {
				return 15;
			}

			public Ingredient getRepairMaterial() {
				return Ingredient.fromStacks(new ItemStack(Blocks.OAK_PLANKS, (int) (1)), new ItemStack(Blocks.SPRUCE_PLANKS, (int) (1)),
						new ItemStack(Blocks.BIRCH_PLANKS, (int) (1)), new ItemStack(Blocks.JUNGLE_PLANKS, (int) (1)),
						new ItemStack(Blocks.ACACIA_PLANKS, (int) (1)), new ItemStack(Blocks.DARK_OAK_PLANKS, (int) (1)));
			}
		}, 1, -3f, new Item.Properties().group(PlantsVsZombiesTabItemGroup.tab)) {
			@Override
			public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
				super.addInformation(itemstack, world, list, flag);
				list.add(new StringTextComponent("\u00A7bRemoves plants,"));
				list.add(new StringTextComponent("\u00A7bstunts insect growth"));
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
		}.setRegistryName("wooden_spade"));
	}
}
