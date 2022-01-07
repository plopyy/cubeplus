package com.plop.cubeplus.common.property;

import net.minecraft.util.StringRepresentable;

public enum RoofMat implements StringRepresentable
{
    SLATES("one"),
    TILES("none"),
    THATCH("two");

    private final String name;

    private RoofMat(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return this.name;
    }

    public String getSerializedName() { return this.name; }

}
