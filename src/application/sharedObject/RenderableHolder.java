package application.sharedObject;

import application.logic.BaseEntity;

import java.util.ArrayList;
import java.util.Comparator;

public class RenderableHolder {
    private static final RenderableHolder instance = new RenderableHolder();

    private ArrayList<IRenderable> entities;

    private Comparator<IRenderable> comparator;
    private RenderableHolder() {
        entities = initGame();
        comparator = (IRenderable o1, IRenderable o2) -> {
            return o1.getZ() > o2.getZ() ? 1 : 0;
        }
    }
}
