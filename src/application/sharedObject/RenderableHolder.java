package application.sharedObject;

import application.logic.BaseEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {
    private static final RenderableHolder instance = new RenderableHolder();

    private final ArrayList<IRenderable> entities;

    private final Comparator<IRenderable> comparator;
    private RenderableHolder() {
        entities = new ArrayList<>();
        comparator = Comparator.comparingInt(IRenderable::getZ);
    }

    public void add(BaseEntity entity) {
        entities.add(entity);
        entities.sort(comparator);
    }

    public static RenderableHolder getInstance() {
        return instance ;
    }

    public List<IRenderable> getEntities() {
        return this.entities;
    }
}
