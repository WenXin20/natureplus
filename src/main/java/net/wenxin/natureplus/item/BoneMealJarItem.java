
package net.wenxin.natureplus.item;

import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import java.util.List;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.block.IGrowable;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.block.Blocks;
import javax.annotation.Nullable;
import net.minecraft.world.biome.Biome;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.block.DeadCoralWallFanBlock;

@NatureplusModElements.ModElement.Tag
public class BoneMealJarItem extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:bone_meal_jar")
	public static final Item block = null;
	public BoneMealJarItem(NatureplusModElements instance) {
		super(instance, 113);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}
	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(NaturePlusTabItemGroup.tab).maxStackSize(64));
			setRegistryName("bone_meal_jar");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("\u00A7bGrows giant flowers"));
		}

   /**
    * Called when this item is used when targetting a Block
    */
	   public ActionResultType onItemUse(ItemUseContext context) {
	      World world = context.getWorld();
	      BlockPos blockpos = context.getPos();
	      BlockPos blockpos1 = blockpos.offset(context.getFace());
	      if (applyBonemeal(context.getItem(), world, blockpos, context.getPlayer())) {
	         if (!world.isRemote) {
	            world.playEvent(2005, blockpos, 0);
	         }
	
	         return ActionResultType.SUCCESS;
	      } else {
	         BlockState blockstate = world.getBlockState(blockpos);
	         boolean flag = blockstate.isSolidSide(world, blockpos, context.getFace());
	         if (flag && growSeagrass(context.getItem(), world, blockpos1, context.getFace())) {
	            if (!world.isRemote) {
	               world.playEvent(2005, blockpos1, 0);
	            }
	
	            return ActionResultType.SUCCESS;
	         } else {
	            return ActionResultType.PASS;
	         }
	      }
	   }

	   @Deprecated //Forge: Use Player/Hand version
	   public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos pos) {
	      if (worldIn instanceof net.minecraft.world.server.ServerWorld)
	         return applyBonemeal(stack, worldIn, pos, net.minecraftforge.common.util.FakePlayerFactory.getMinecraft((net.minecraft.world.server.ServerWorld)worldIn));
	      return false;
	   }
	
	   public static boolean applyBonemeal(ItemStack stack, World worldIn, BlockPos pos, net.minecraft.entity.player.PlayerEntity player) {
	      BlockState blockstate = worldIn.getBlockState(pos);
	      int hook = net.minecraftforge.event.ForgeEventFactory.onApplyBonemeal(player, worldIn, pos, blockstate, stack);
	      if (hook != 0) return hook > 0;
	      if (blockstate.getBlock() instanceof IGrowable) {
	         IGrowable igrowable = (IGrowable)blockstate.getBlock();
	         if (igrowable.canGrow(worldIn, pos, blockstate, worldIn.isRemote)) {
	            if (worldIn instanceof ServerWorld) {
	               if (igrowable.canUseBonemeal(worldIn, worldIn.rand, pos, blockstate)) {
	                  igrowable.grow((ServerWorld)worldIn, worldIn.rand, pos, blockstate);
	               }
	
	               stack.shrink(1);
	            }
	
	            return true;
	         }
	      }
	
	      return false;
	   }
	
	   public static boolean growSeagrass(ItemStack stack, World worldIn, BlockPos pos, @Nullable Direction side) {
	      if (worldIn.getBlockState(pos).getBlock() == Blocks.WATER && worldIn.getFluidState(pos).getLevel() == 8) {
	         if (!(worldIn instanceof ServerWorld)) {
	            return true;
	         } else {
	            label80:
	            for(int i = 0; i < 128; ++i) {
	               BlockPos blockpos = pos;
	               Biome biome = worldIn.getBiome(pos);
	               BlockState blockstate = Blocks.SEAGRASS.getDefaultState();
	
	               for(int j = 0; j < i / 16; ++j) {
	                  blockpos = blockpos.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2, random.nextInt(3) - 1);
	                  biome = worldIn.getBiome(blockpos);
	                  if (worldIn.getBlockState(blockpos).isCollisionShapeOpaque(worldIn, blockpos)) {
	                     continue label80;
	                  }
	               }
	
	               // FORGE: Use BiomeDictionary here to allow modded warm ocean biomes to spawn coral from bonemeal
	               if (net.minecraftforge.common.BiomeDictionary.hasType(biome, net.minecraftforge.common.BiomeDictionary.Type.OCEAN)
	                       && net.minecraftforge.common.BiomeDictionary.hasType(biome, net.minecraftforge.common.BiomeDictionary.Type.HOT)) {
	                  if (i == 0 && side != null && side.getAxis().isHorizontal()) {
	                     blockstate = BlockTags.WALL_CORALS.getRandomElement(worldIn.rand).getDefaultState().with(DeadCoralWallFanBlock.FACING, side);
	                  } else if (random.nextInt(4) == 0) {
	                     blockstate = BlockTags.UNDERWATER_BONEMEALS.getRandomElement(random).getDefaultState();
	                  }
	               }
	
	               if (blockstate.getBlock().isIn(BlockTags.WALL_CORALS)) {
	                  for(int k = 0; !blockstate.isValidPosition(worldIn, blockpos) && k < 4; ++k) {
	                     blockstate = blockstate.with(DeadCoralWallFanBlock.FACING, Direction.Plane.HORIZONTAL.random(random));
	                  }
	               }
	
	               if (blockstate.isValidPosition(worldIn, blockpos)) {
	                  BlockState blockstate1 = worldIn.getBlockState(blockpos);
	                  if (blockstate1.getBlock() == Blocks.WATER && worldIn.getFluidState(blockpos).getLevel() == 8) {
	                     worldIn.setBlockState(blockpos, blockstate, 3);
	                  } else if (blockstate1.getBlock() == Blocks.SEAGRASS && random.nextInt(10) == 0) {
	                     ((IGrowable)Blocks.SEAGRASS).grow((ServerWorld)worldIn, random, blockpos, blockstate1);
	                  }
	               }
	            }
	
	            stack.shrink(1);
	            return true;
	         }
	      } else {
	         return false;
	      }
	   }
	}
}
