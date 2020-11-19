///**
// * This mod element is always locked. Enter your code in the methods below.
// * If you don't need some of these methods, you can remove them as they
// * are overrides of the base class NatureplusModElements.ModElement.
// *
// * You can register new events in this class too.
// *
// * As this class is loaded into mod element list, it NEEDS to extend
// * ModElement class. If you remove this extend statement or remove the
// * constructor, the compilation will fail.
// *
// * If you want to make a plain independent class, create it using
// * Project Browser - New... and make sure to make the class
// * outside net.wenxin.natureplus as this package is managed by MCreator.
// *
// * If you change workspace package, modid or prefix, you will need
// * to manually adapt this file to these changes or remake it.
//*/
//package net.wenxin.natureplus;
//
//import net.wenxin.natureplus.block.PeashooterWallHeadBlock;
//import net.wenxin.natureplus.block.PeashooterHeadBlock;
//
//import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.event.RegistryEvent;
//
//import net.minecraft.tileentity.TileEntityType;
//import net.minecraft.tileentity.SkullTileEntity;
//import net.minecraft.tileentity.ITickableTileEntity;
//import net.minecraft.network.play.server.SUpdateTileEntityPacket;
//import net.minecraft.nbt.CompoundNBT;
//import net.minecraft.block.Block;
//
//import javax.annotation.Nullable;
//
//@NatureplusModElements.ModElement.Tag
//public class NPSkullTileEntity extends NatureplusModElements.ModElement {
//	public static final TileEntityType<CustomTileEntity> tileEntityType = null;
//	@SubscribeEvent
//	public static void registerTileEntityTypes(RegistryEvent.Register<TileEntityType<?>> event) {
//		event.getRegistry()
//				.register(TileEntityType.Builder
//						.create(NPSkullTileEntity.CustomTileEntity::new, PeashooterHeadBlock.block, PeashooterWallHeadBlock.block).build(null)
//						.setRegistryName("peashooter_head_tile"));
//	}
//	public static class CustomTileEntity extends SkullTileEntity implements ITickableTileEntity {
//		@Nullable
//		private CompoundNBT itemData;
//		public CustomTileEntity() {
//			super();
////			FMLJavaModLoadingContext.get().getModEventBus().register(this);
//		}
//
//		@Override
//		public TileEntityType<?> getType() {
//			return NPTileEntityTypes.PEASHOOTER;
//		}
//
//		@Override
//		public void tick() {
//			Block block = this.getBlockState().getBlock();
//		}
//
//		@Override
//		public SUpdateTileEntityPacket getUpdatePacket() {
//			return new SUpdateTileEntityPacket(this.pos, 0, this.getUpdateTag());
//		}
//
//		// @Nullable
//		// public CompoundNBT getItemData() {
//		// return this.itemData;
//		// }
//		//
//		// public void setItemData(@Nullable CompoundNBT skullData) {
//		// this.itemData = skullData;
//		// this.markDirty();
//		// }
//		@Override
//		public CompoundNBT write(CompoundNBT compound) {
//			return compound;
//		}
//
//		@Override
//		public void read(CompoundNBT compound) {
//		}
//		// @Nullable
//		// private CompoundNBT itemData;
//		// @Override
//		// public TileEntityType<?> getType() {
//		// return NPTileEntityTypes.PEASHOOTER;
//		// }
//		//
//		// @Override
//		// public void tick() {
//		// }
//		//
//		// @Nullable
//		// public CompoundNBT getItemData() {
//		// return this.itemData;
//		// }
//		//
//		// public void setItemData(@Nullable CompoundNBT skullData) {
//		// this.itemData = skullData;
//		// this.markDirty();
//		// }
//		//
//		// @Override
//		// public CompoundNBT write(CompoundNBT compound) {
//		// super.write(compound);
//		// if (this.itemData != null) {
//		// compound.put("ItemTag", this.itemData);
//		// }
//		// return compound;
//		// }
//		//
//		// @Override
//		// public void read(CompoundNBT compound) {
//		// super.read(compound);
//		// if (compound.contains("Item", 10)) {
//		// this.itemData = compound.getCompound("Item");
//		// } else if (compound.contains("ItemTag", 10)) {
//		// this.itemData = compound.getCompound("ItemTag");
//		// }
//		// }
//	}
//
//	public static class NPTileEntityTypes {
//		public static final TileEntityType<NPSkullTileEntity.CustomTileEntity> PEASHOOTER = null;
//	}
//	/**
//	 * Do not remove this constructor
//	 */
//	public NPSkullTileEntity(NatureplusModElements instance) {
//		super(instance, 955);
//		FMLJavaModLoadingContext.get().getModEventBus().register(this);
//	}
//	// @Override
//	// public void initElements() {
//	// }
//	//
//	// @Override
//	// public void init(FMLCommonSetupEvent event) {
//	// }
//	//
//	// @Override
//	// public void serverLoad(FMLServerStartingEvent event) {
//	// }
//	//
//	// @OnlyIn(Dist.CLIENT)
//	// @Override
//	// public void clientLoad(FMLClientSetupEvent event) {
//	// }
//}
