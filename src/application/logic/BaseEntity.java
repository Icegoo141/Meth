package application.logic;

import application.sharedObject.IRenderable;

public abstract class BaseEntity implements IRenderable {
    protected double x, y;
    protected int z;

    protected boolean visible, destroyed;

    protected BaseEntity() {
        visible = true;
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

    @Override
    public boolean isVisible() {
        return visible;
    }

    public abstract void update();
}
