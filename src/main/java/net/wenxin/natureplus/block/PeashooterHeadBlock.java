//
//package net.wenxin.natureplus.block;
//
//import net.wenxin.natureplus.itemgroup.PlantsVsZombiesTabItemGroup;
//import net.wenxin.natureplus.block.PeashooterWallHeadBlock;
//import net.wenxin.natureplus.PeashooterHeadModel;
//import net.wenxin.natureplus.NatureplusModElements;
//
//import net.minecraftforge.registries.ObjectHolder;
//import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
//import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//import net.minecraftforge.common.property.Properties;
//import net.minecraftforge.common.ToolType;
//import net.minecraftforge.api.distmarker.OnlyIn;
//import net.minecraftforge.api.distmarker.Dist;
//
//import net.minecraft.world.IBlockReader;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.Util;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.item.WallOrFloorItem;
//import net.minecraft.item.Rarity;
//import net.minecraft.item.Item;
//import net.minecraft.client.renderer.entity.model.GenericHeadModel;
//import net.minecraft.block.material.MaterialColor;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.SoundType;
//import net.minecraft.block.SkullBlock;
//import net.minecraft.block.BlockState;
//import net.minecraft.block.Block;
//
//import java.util.Map;
//
//import com.google.common.collect.Maps;
//
//@NatureplusModElements.ModElement.Tag
//public class PeashooterHeadBlock extends NatureplusModElements.ModElement {
//	@ObjectHolder("natureplus:peashooter_head")
//	public static final Block block = null;
//	/**
//	 * Do not remove this constructor
//	 */
//	public PeashooterHeadBlock(NatureplusModElements instance) {
//		super(instance, 948);
//	}
//
//	@Override
//	public void init(FMLCommonSetupEvent event) {
//	}
//
//	@Override
//	public void serverLoad(FMLServerStartingEvent event) {
//	}
//
//	@OnlyIn(Dist.CLIENT)
//	@Override
//	public void clientLoad(FMLClientSetupEvent event) {
//	}
//
//	// @SubscribeEvent
//	// public void registerTileEntity(RegistryEvent.Register<TileEntityType<?>>
//	// event) {
//	// event.getRegistry().register(
//	// TileEntityType.Builder.create(NPSkullTileEntity.CustomTileEntity::new,
//	// block).build(null).setRegistryName("peashooter_head_tile"));
//	// }
//	@Override
//	public void initElements() {
//		elements.blocks.add(() -> new CustomBlock(PeashooterHeadBlock.CustomBlock.Types.PEASHOOTER, Block.Properties.create(Material.PLANTS)
//				.sound(SoundType.PLANT).hardnessAndResistance(2f, 15f).lightValue(0).harvestLevel(0).harvestTool(ToolType.AXE)));
//		elements.items.add(() -> new WallOrFloorItem(block, PeashooterWallHeadBlock.block,
//				new Item.Properties().group(PlantsVsZombiesTabItemGroup.tab).rarity(Rarity.UNCOMMON)).setRegistryName(block.getRegistryName()));
//	}
//	public static class CustomBlock extends SkullBlock {
//		public CustomBlock(ISkullType type, Properties properties) {
//			super(type, properties);
//			setRegistryName("peashooter_head");
//		}
//
//		@Override
//		public MaterialColor getMaterialColor(BlockState state, IBlockReader blockAccess, BlockPos pos) {
//			return MaterialColor.GREEN;
//		}
//		public enum Types implements SkullBlock.ISkullType {
//			PEASHOOTER;
//		}
//		// @Override
//		// public void onBlockAdded(BlockState state, World world, BlockPos pos,
//		// BlockState oldState, boolean moving) {
//		// super.onBlockAdded(state, world, pos, oldState, moving);
//		// int x = pos.getX();
//		// int y = pos.getY();
//		// int z = pos.getZ();
//		// world.getPendingBlockTicks().scheduleTick(new BlockPos(x, y, z), this,
//		// this.tickRate(world));
//		// }
//		//
//		// @Override
//		// public void tick(BlockState state, ServerWorld world, BlockPos pos, Random
//		// random) {
//		// super.tick(state, world, pos, random);
//		// int x = pos.getX();
//		// int y = pos.getY();
//		// int z = pos.getZ();
//		// world.getPendingBlockTicks().scheduleTick(new BlockPos(x, y, z), this,
//		// this.tickRate(world));
//		// }
//	}
//	public static final Map<SkullBlock.ISkullType, GenericHeadModel> MODELS = Util.make(Maps.newHashMap(), (map) -> {
//		GenericHeadModel genericheadmodel = new GenericHeadModel(0, 0, 64, 32);
//		map.put(PeashooterHeadBlock.CustomBlock.Types.PEASHOOTER, new PeashooterHeadModel());
//		System.out.println("model1");
//	});
//	public static final Map<SkullBlock.ISkullType, ResourceLocation> SKINS = Util.make(Maps.newHashMap(), (map) -> {
//		map.put(PeashooterHeadBlock.CustomBlock.Types.PEASHOOTER, new ResourceLocation("textures/entity/zombie/zombie.png"));
//		System.out.println("map1");
//	});
//}
