package application.logic.entities;

public abstract class BaseCollidable extends BaseEntity {
    protected double collisionRadius;

    public boolean collideWith(BaseCollidable other) {
        return Math.hypot(x - other.x, y - other.y) <= collisionRadius + other.collisionRadius;
    }
}
