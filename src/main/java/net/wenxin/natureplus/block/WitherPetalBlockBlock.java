
package net.wenxin.natureplus.block;

import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.World;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.Random;
import java.util.List;
import java.util.Collections;
import net.minecraft.world.ILightReader;
import net.minecraft.fluid.IFluidState;
import net.minecraft.entity.Entity;
import net.minecraft.world.Difficulty;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.shapes.ISelectionContext;

@NatureplusModElements.ModElement.Tag
public class WitherPetalBlockBlock extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:wither_petal_block")
	public static final Block block = null;
	public WitherPetalBlockBlock(NatureplusModElements instance) {
		super(instance, 60);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(NaturePlusTabItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}
	public static class CustomBlock extends LeavesBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.LEAVES).sound(SoundType.PLANT).hardnessAndResistance(0.2f, 1f).lightValue(0).harvestLevel(0)
					.harvestTool(ToolType.AXE).notSolid());
			setRegistryName("wither_petal_block");
		}

		@Override
		public boolean shouldDisplayFluidOverlay(BlockState state, ILightReader world, BlockPos pos, IFluidState fluidstate) {
			return true;
		}
	
	   	public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
	      	if (!worldIn.isRemote && worldIn.getDifficulty() != Difficulty.PEACEFUL) {
	        	if (entityIn instanceof LivingEntity) {
	            	LivingEntity livingentity = (LivingEntity)entityIn;
	            	if (!livingentity.isInvulnerableTo(DamageSource.WITHER)) {
	               		livingentity.addPotionEffect(new EffectInstance(Effects.WITHER, 40));
	    	       	}
	   	      	}
	   	   	}
	   	}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@Override
		public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
			return 30;
		}

		@Override
		public MaterialColor getMaterialColor(BlockState state, IBlockReader blockAccess, BlockPos pos) {
			return MaterialColor.BLACK;
		}

		@Override
		public PathNodeType getAiPathNodeType(BlockState state, IBlockReader world, BlockPos pos, MobEntity entity) {
			return PathNodeType.LEAVES;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(Blocks.AIR, (int) (1)));
		}

		@OnlyIn(Dist.CLIENT)
		@Override
	   	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
	      	VoxelShape voxelshape = this.getShape(stateIn, worldIn, pos, ISelectionContext.dummy());
	      	Vec3d vec3d = voxelshape.getBoundingBox().getCenter();
	      	double d0 = (double)pos.getX() + vec3d.x;
	      	double d1 = (double)pos.getZ() + vec3d.z;
	
	      	for(int i = 0; i < 3; ++i) {
	         	if (rand.nextBoolean()) {
	            	worldIn.addParticle(ParticleTypes.SMOKE, d0 + (double)(rand.nextFloat() / 5.0F), (double)pos.getY() + (1.2D - (double)rand.nextFloat()), d1 + (double)(rand.nextFloat() / 5.0F), 0.0D, 0.0D, 0.0D);
	         }
	      }
	   }
	}
}
