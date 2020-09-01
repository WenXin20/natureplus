package net.wenxin.natureplus.procedures;

@NatureplusModElements.ModElement.Tag
public class ButtonCloseGUIProcedure extends NatureplusModElements.ModElement {

	public ButtonCloseGUIProcedure(NatureplusModElements instance) {
		super(instance, 806);

	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure ButtonCloseGUI!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		if (entity instanceof PlayerEntity)
			((PlayerEntity) entity).closeScreen();

	}

}
