package com.sainttx.holograms.nms.v1_13_R2;

import net.minecraft.server.v1_13_R2.AxisAlignedBB;
import net.minecraft.server.v1_13_R2.Vec3D;

public class NullBoundingBox extends AxisAlignedBB {

    public NullBoundingBox() {
        super(0, 0, 0, 0, 0, 0);

    }

    @Override
    public AxisAlignedBB a(double arg0, double arg1, double arg2) {
        return this;
    }

    @Override
    public AxisAlignedBB b(double arg0, double arg1, double arg2) {
        return this;
    }

    @Override
    public AxisAlignedBB grow(double arg0, double arg1, double arg2) {
        return this;
    }

    @Override
    public AxisAlignedBB g(double var1) {
        return this;
    }

    @Override
    public AxisAlignedBB a(AxisAlignedBB arg0) {
        return this;
    }

    @Override
    public AxisAlignedBB b(AxisAlignedBB arg0) {
        return this;
    }

    @Override
    public boolean b(Vec3D arg0) {
        return false;
    }

    @Override
    public double a() {
        return 0.0;
    }

    @Override
    public AxisAlignedBB shrink(double arg0) {
        return this;
    }
}