package com.plop.cubeplus.common.item;

import com.google.common.collect.Lists;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.List;

import static com.plop.cubeplus.CubePlus.TAB;

public class cpItems
{
	public static final cpItems INSTANCE = new cpItems();
	public static List<Item> items;

	public static Item TROWEL;

	public static Item WINDOW_SIMPLE_GLASS_PANE;
	public static Item WINDOW_SQUARE_GLASS_PANE;
	public static Item ACACIA_WINDOW_FRAME;
	public static Item BIRCH_WINDOW_FRAME;
	public static Item DARK_OAK_WINDOW_FRAME;
	public static Item JUNGLE_WINDOW_FRAME;
	public static Item OAK_WINDOW_FRAME ;
	public static Item SPRUCE_WINDOW_FRAME;
	public static Item CRIMSON_WINDOW_FRAME;
	public static Item WARPED_WINDOW_FRAME;

	@SubscribeEvent
    public static void init(IForgeRegistry<Item> registry)
	{
		items = Lists.newArrayList();

        final Item.Properties properties = new Item.Properties().tab(TAB);

		TROWEL = new cpTrowel("trowel", properties);
		WINDOW_SIMPLE_GLASS_PANE = new cpItem("window_simple_glass_pane", properties);
		WINDOW_SQUARE_GLASS_PANE = new cpItem("window_square_glass_pane", properties);
		ACACIA_WINDOW_FRAME = new cpItem("acacia_window_frame", properties);
		BIRCH_WINDOW_FRAME = new cpItem("birch_window_frame", properties);
		DARK_OAK_WINDOW_FRAME = new cpItem("dark_oak_window_frame", properties);
		JUNGLE_WINDOW_FRAME = new cpItem("jungle_window_frame", properties);
		OAK_WINDOW_FRAME = new cpItem("oak_window_frame", properties);
		SPRUCE_WINDOW_FRAME = new cpItem("spruce_window_frame", properties);
		CRIMSON_WINDOW_FRAME = new cpItem("crimson_window_frame", properties);
		WARPED_WINDOW_FRAME = new cpItem("warped_window_frame", properties);

        for(Item item : items)
            registry.register(item);
	}
}
