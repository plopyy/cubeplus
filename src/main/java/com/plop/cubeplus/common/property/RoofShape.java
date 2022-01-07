package com.plop.cubeplus.common.property;

import net.minecraft.util.StringRepresentable;

public enum RoofShape implements StringRepresentable
{
    NONE("none"),
    ONE("one"),
    TWO("two"),
    THREE("three"),
    FOUR("four"),
    SIDE("side");

    private final String name;

    private RoofShape(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return this.name;
    }

    public String getSerializedName() { return this.name; }
}