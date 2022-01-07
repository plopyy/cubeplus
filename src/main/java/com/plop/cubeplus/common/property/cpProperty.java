package com.plop.cubeplus.common.property;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class cpProperty
{
    public static final EnumProperty<HSide> HSIDE = EnumProperty.create("hside", HSide.class);
    public static final BooleanProperty SNEAKING = BooleanProperty.create("sneak");
    public static final BooleanProperty SPRINTING = BooleanProperty.create("sprint");
    public static final EnumProperty<RoofShape> ROOF_SHAPE = EnumProperty.create("roofshape", RoofShape.class);
    public static final EnumProperty<RoofMat> ROOF_MAT = EnumProperty.create("roofmat", RoofMat.class);
}
