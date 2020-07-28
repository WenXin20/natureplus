
package net.wenxin.natureplus.item;

import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;
import net.wenxin.natureplus.MobNBTHelper;
import net.wenxin.natureplus.Message;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.ModList;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.util.Direction;
import net.minecraft.util.ActionResultType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.monster.RavagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ElderGuardianEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Entity;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import java.util.List;

@NatureplusModElements.ModElement.Tag
public class ButterflyNetItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:butterfly_net")
	public static final Item block = null;
	public ButterflyNetItem(NatureplusModElements instance) {
		super(instance, 116);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemToolCustom() {
			// @Override
			// public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity
			// entity, Hand hand) {
			// ActionResult<ItemStack> retval = super.onItemRightClick(world, entity, hand);
			// ItemStack itemstack = retval.getResult();
			// double x = entity.getPosX();
			// double y = entity.getPosY();
			// double z = entity.getPosZ();
			// {
			// Map<String, Object> $_dependencies = new HashMap<>();
			// $_dependencies.put("entity", entity);
			// $_dependencies.put("x", x);
			// $_dependencies.put("y", y);
			// $_dependencies.put("z", z);
			// $_dependencies.put("world", world);
			// ButterflyNetSoundAndParticlesProcedure.executeProcedure($_dependencies);
			// }
			// return retval;
			// }
		}.setRegistryName("butterfly_net"));
	}
	private static class ItemToolCustom extends Item {
		protected ItemToolCustom() {
			super(new Item.Properties().group(NaturePlusTabItemGroup.tab).maxDamage(0));
		}

		@Override
		public float getDestroySpeed(ItemStack itemstack, BlockState blockstate) {
			return 1;
		}

		@Override
		public boolean onBlockDestroyed(ItemStack stack, World worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
			stack.damageItem(1, entityLiving, i -> i.sendBreakAnimation(EquipmentSlotType.MAINHAND));
			return true;
		}

		@Override
		public boolean hitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
			stack.damageItem(2, attacker, i -> i.sendBreakAnimation(EquipmentSlotType.MAINHAND));
			return true;
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public boolean itemInteractionForEntity(ItemStack stack, PlayerEntity player, LivingEntity target, Hand hand) {
//			World world = context.getWorld();
			if (MobNBTHelper.hasMob(stack) || !target.isAlive() || !(target instanceof MobEntity))
				return false;
			CompoundNBT nbt = MobNBTHelper.getBaseTag(stack);
			// Cannot capture bosses
			if (!target.isNonBoss() || target instanceof RavagerEntity || target instanceof ElderGuardianEntity) {
				if (!player.world.isRemote) {
					Message.tellPlayer(player, "natureplus.error.butterfly_net.cannot_capture_boss");
				}
				return true;
			}
			if (!player.world.isRemote()) {
				String modId = target.getType().getRegistryName().getNamespace();
				String modName = ModList.get().getModObjectById(modId).get().getClass().getSimpleName();
				CompoundNBT mobData = target.serializeNBT();
				nbt.put(MobNBTHelper.MOB_DATA, mobData);
				nbt.putString(MobNBTHelper.MOB_NAME, target.getName().getUnformattedComponentText());
				nbt.putString(MobNBTHelper.MOB_MOD, ModList.get().getModObjectById(modId).get().getClass().getSimpleName());
				nbt.putString(MobNBTHelper.MOD_ID, target.getType().getRegistryName().getNamespace());
				nbt.putDouble(MobNBTHelper.MOB_HEALTH, Math.round(target.getHealth() * 10) / 10.0);
				nbt.putDouble(MobNBTHelper.MOB_MAX_HEALTH, target.getMaxHealth());
				nbt.putBoolean(MobNBTHelper.MOB_HOSTILE, target instanceof MonsterEntity);
				if (player.isCreative()) {
					ItemStack newNet = new ItemStack(this);
					MobNBTHelper.setBaseTag(newNet, nbt);
					player.addItemStackToInventory(newNet);
				}
				target.remove();
				player.inventory.markDirty();
				player.world.playSound((PlayerEntity) null, player.getPosX(), player.getPosY(), player.getPosZ(),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:swoosh")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
			}
			return true;
		}

		@Override
		public ActionResultType onItemUse(ItemUseContext context) {
			PlayerEntity player = context.getPlayer();
			ItemStack stack = context.getItem();
			Direction facing = context.getFace();
			BlockPos pos = context.getPos().offset(facing);
			World world = context.getWorld();
			world.playSound((PlayerEntity) null, player.getPosX(), player.getPosY(), player.getPosZ(),
					(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("natureplus:swoosh")),
					SoundCategory.NEUTRAL, (float) 1, (float) 1);
			if (!MobNBTHelper.hasMob(stack))
				return ActionResultType.FAIL;
			if (!player.canPlayerEdit(pos, facing, stack))
				return ActionResultType.FAIL;
			if (!context.getWorld().isRemote()) {
				CompoundNBT nbt = MobNBTHelper.getBaseTag(stack);
				CompoundNBT mobData = nbt.getCompound(MobNBTHelper.MOB_DATA);
				Entity mob = EntityType.func_220335_a(mobData, world, entity -> {
					return entity;
				});
				BlockPos blockPos = pos.offset(context.getFace());
				mob.setPositionAndRotation(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, world.getRandom().nextFloat() * 360F, 0);
				if (mob != null)
					world.addEntity(mob);
//				if (IWorld instanceof ServerWorld) {
//					((ServerWorld) world).spawnParticle(ParticleTypes.CLOUD, (pos.getX() + 0.5), pos.getY(), (pos.getZ() + 0.5), (int) 10, 0.25, 0.25,
//							0.25, 0.05);
//				}
				if (!player.isCreative()) {
				}
				stack.removeChildTag(MobNBTHelper.MOB);
				stack.shrink(1);
			}
			return ActionResultType.SUCCESS;
		}

		// public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity
		// playerIn, Hand handIn) {
		// ItemStack itemstack = playerIn.getHeldItem(handIn);
		// return ActionResult.resultSuccess(itemstack);
		// }
		@Override
		public void addInformation(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
			if (MobNBTHelper.hasMob(stack)) {
				CompoundNBT nbt = MobNBTHelper.getBaseTag(stack);
				String name = nbt.getString(MobNBTHelper.MOB_NAME);
				String modName = nbt.getString(MobNBTHelper.MOB_MOD);
				String modId = nbt.getString(MobNBTHelper.MOD_ID);
				double health = nbt.getDouble(MobNBTHelper.MOB_HEALTH);
				double maxHealth = nbt.getDouble(MobNBTHelper.MOB_MAX_HEALTH);
				tooltip.add(Message.tooltip("natureplus.tooltip.butterfly_net.mob_name.key", name));
				if (modId.equals("minecraft")) {
					tooltip.add(Message.tooltip("\u00A79Mod: Minecraft", modName));
				} else if (modId.equals("natureplus")) {
					tooltip.add(Message.tooltip("\u00A79Mod: Nature +", modName));
				} else {
					tooltip.add(Message.tooltip("natureplus.tooltip.butterfly_net.mob_mod.key", modName));
				}
				tooltip.add(Message.tooltip("natureplus.tooltip.butterfly_net.health.key", health, maxHealth));
				if (nbt.getBoolean(MobNBTHelper.MOB_HOSTILE))
					tooltip.add(Message.tooltip("natureplus.tooltip.butterfly_net.hostile.key"));
				tooltip.add(Message.tooltip("natureplus.tooltip.butterfly_net.release_mob.key"));
			} else {
				tooltip.add(Message.tooltip("natureplus.tooltip.butterfly_net.capture.key"));
			}
		}

		@Override
		public boolean hasEffect(ItemStack stack) {
			return MobNBTHelper.hasMob(stack);
		}

		public static CompoundNBT getNBTfromEntity(Entity entity) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putString("entity", entity.getType().getRegistryName().toString());
			nbt.putString("id", EntityType.getKey(entity.getType()).toString());
			entity.writeUnlessPassenger(nbt);
			return nbt;
		}
	}
}
