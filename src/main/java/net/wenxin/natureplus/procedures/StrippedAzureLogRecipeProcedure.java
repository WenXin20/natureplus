//package net.wenxin.natureplus.procedures;
//
//import net.wenxin.natureplus.block.StrippedAzureLogBlock;
//import net.wenxin.natureplus.block.AzureLogBlock;
//import net.wenxin.natureplus.NatureplusModElements;
//
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.event.entity.player.PlayerInteractEvent;
//import net.minecraftforge.common.ToolType;
//import net.minecraftforge.common.MinecraftForge;
//
//import net.minecraft.world.World;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.SoundCategory;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.Hand;
//import net.minecraft.state.IProperty;
//import net.minecraft.item.ItemStack;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.Entity;
//import net.minecraft.block.BlockState;
//
//import java.util.Random;
//import java.util.Map;
//
//@NatureplusModElements.ModElement.Tag
//public class StrippedAzureLogRecipeProcedure extends NatureplusModElements.ModElement {
//	public StrippedAzureLogRecipeProcedure(NatureplusModElements instance) {
//		super(instance, 92);
//		MinecraftForge.EVENT_BUS.register(this);
//	}
//
//	public static void executeProcedure(java.util.HashMap<String, Object> dependencies) {
//		if (dependencies.get("entity") == null) {
//			System.err.println("Failed to load dependency entity for procedure StrippedAzureLogRecipe!");
//			return;
//		}
//		if (dependencies.get("x") == null) {
//			System.err.println("Failed to load dependency x for procedure StrippedAzureLogRecipe!");
//			return;
//		}
//		if (dependencies.get("y") == null) {
//			System.err.println("Failed to load dependency y for procedure StrippedAzureLogRecipe!");
//			return;
//		}
//		if (dependencies.get("z") == null) {
//			System.err.println("Failed to load dependency z for procedure StrippedAzureLogRecipe!");
//			return;
//		}
//		if (dependencies.get("world") == null) {
//			System.err.println("Failed to load dependency world for procedure StrippedAzureLogRecipe!");
//			return;
//		}
//		Entity entity = (Entity) dependencies.get("entity");
//		int x = (int) dependencies.get("x");
//		int y = (int) dependencies.get("y");
//		int z = (int) dependencies.get("z");
//		World world = (World) dependencies.get("world");
//		ItemStack itemStack = ((LivingEntity) entity).getHeldItemMainhand();
//		if (((AzureLogBlock.block.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())
//				&& ((itemStack.getToolTypes().contains(ToolType.AXE))))) {
//			if (entity instanceof LivingEntity) {
//				((LivingEntity) entity).swingArm(Hand.MAIN_HAND);
//			}
//			if ((AzureLogBlock.block.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
//				world.playSound((PlayerEntity) null, x, y, z,
//						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.axe.strip")),
//						SoundCategory.NEUTRAL, (float) 1, (float) 1);
//			}
//			{
//				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
//				BlockState _bs = StrippedAzureLogBlock.block.getDefaultState();
//				BlockState _bso = world.getBlockState(_bp);
//				for (Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
//					IProperty _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
//					if (_bs.has(_property))
//						_bs = _bs.with(_property, (Comparable) entry.getValue());
//				}
//				world.setBlockState(_bp, _bs, 3);
//			}
//			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
//				{
//					ItemStack _ist = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
//					if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
//						_ist.shrink(1);
//						_ist.setDamage(0);
//					}
//				}
//			}
//		} else if (((AzureLogBlock.block.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())
//				&& ((itemStack.getToolTypes().contains(ToolType.AXE))))) {
//			if (entity instanceof LivingEntity) {
//				((LivingEntity) entity).swingArm(Hand.OFF_HAND);
//			}
//			if ((AzureLogBlock.block.getDefaultState().getBlock() == (world.getBlockState(new BlockPos((int) x, (int) y, (int) z))).getBlock())) {
//				world.playSound((PlayerEntity) null, x, y, z,
//						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.axe.strip")),
//						SoundCategory.NEUTRAL, (float) 1, (float) 1);
//			}
//			{
//				BlockPos _bp = new BlockPos((int) x, (int) y, (int) z);
//				BlockState _bs = StrippedAzureLogBlock.block.getDefaultState();
//				BlockState _bso = world.getBlockState(_bp);
//				for (Map.Entry<IProperty<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
//					IProperty _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
//					if (_bs.has(_property))
//						_bs = _bs.with(_property, (Comparable) entry.getValue());
//				}
//				world.setBlockState(_bp, _bs, 3);
//			}
//			if ((!((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).abilities.isCreativeMode : false))) {
//				{
//					ItemStack _ist = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
//					if (_ist.attemptDamageItem((int) 1, new Random(), null)) {
//						_ist.shrink(1);
//						_ist.setDamage(0);
//					}
//				}
//			}
//		}
//	}
//
//	@SubscribeEvent
//	public void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
//		PlayerEntity entity = event.getPlayer();
//		int i = event.getPos().getX();
//		int j = event.getPos().getY();
//		int k = event.getPos().getZ();
//		World world = event.getWorld();
//		java.util.HashMap<String, Object> dependencies = new java.util.HashMap<>();
//		dependencies.put("x", i);
//		dependencies.put("y", j);
//		dependencies.put("z", k);
//		dependencies.put("world", world);
//		dependencies.put("entity", entity);
//		dependencies.put("event", event);
//		this.executeProcedure(dependencies);
//	}
//}
