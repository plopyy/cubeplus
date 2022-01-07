package com.plop.cubeplus.common.item;

import net.minecraft.world.item.Item;

public class cpItem extends Item
{
    public cpItem(String name, Item.Properties properties)
    {
        super(properties);

        setRegistryName(name);

        cpItems.INSTANCE.items.add(this);
    }
}
