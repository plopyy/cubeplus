package com.plop.cubeplus.common.property;

import net.minecraft.util.StringRepresentable;

public enum HSide implements StringRepresentable
{
    LEFT("left"),
    RIGHT("right");

    private final String name;

    private HSide(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return this.name;
    }

    public String getSerializedName() { return this.name; }
}