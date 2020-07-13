
package net.wenxin.natureplus.block;

import net.wenxin.natureplus.itemgroup.NaturePlusTabItemGroup;
import net.wenxin.natureplus.NatureplusModElements;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.World;
import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Direction;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.BlockItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.Minecraft;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;
import net.minecraft.world.ILightReader;
import net.minecraft.fluid.IFluidState;
import net.minecraft.entity.Entity;
import net.minecraft.world.Difficulty;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;

import java.util.Random;
import java.util.List;
import java.util.Collections;
import net.minecraft.util.math.Vec3d;

@NatureplusModElements.ModElement.Tag
public class WitherPetalCarpetBlock extends NatureplusModElements.ModElement {
	@ObjectHolder("natureplus:wither_petal_carpet")
	public static final Block block = null;
	public WitherPetalCarpetBlock(NatureplusModElements instance) {
		super(instance, 78);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(NaturePlusTabItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}
	public static class CustomBlock extends FallingBlock {
		public CustomBlock() {
			super(Block.Properties.create(Material.PLANTS).sound(SoundType.PLANT).hardnessAndResistance(0.2f, 1f).lightValue(0).harvestLevel(0)
					.harvestTool(ToolType.AXE).notSolid());
			setRegistryName("wither_petal_carpet");
		}
	
	   	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
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
		public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return false;
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@Override
		public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
			return VoxelShapes.create(0D, 0D, 0D, 1D, 0.0625D, 1D);
		}

		@Override
		public boolean isReplaceable(BlockState state, BlockItemUseContext context) {
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
			return PathNodeType.WALKABLE;
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(this, 1));
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
	            	worldIn.addParticle(ParticleTypes.SMOKE, d0 + (double)(rand.nextFloat() / 5.0F), (double)pos.getY() + (0.5D - (double)rand.nextFloat()), d1 + (double)(rand.nextFloat() / 5.0F), 0.0D, 0.0D, 0.0D);
	         }
	      }
	   }
	}
}
