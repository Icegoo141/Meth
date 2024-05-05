package application.logic.entities;

import application.sharedObject.IRenderable;

public abstract class BaseEntity implements IRenderable {
    protected double x, y;
    protected int z;

    protected boolean destroyed;

    protected BaseEntity() {
        destroyed = false;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    public abstract void update();
}
