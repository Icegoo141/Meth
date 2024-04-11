package application.logic;

import application.sharedObject.RenderableHolder;

import java.util.ArrayList;

public class GameLogic {
    private static Player player;
    private ArrayList<BaseEntity> entities;

    public GameLogic() {
        player = new Player();
        player.setX(200);
        player.setY(200);

        Battleground bg = new Battleground();
        RenderableHolder.getInstance().add(bg);

        entities = new ArrayList<>();
        BaseGhost testGhost = new BaseGhost();
        testGhost.setX(600);
        testGhost.setY(600);
        addNewEntity(player);
        addNewEntity(testGhost);
    }

    private void addNewEntity(BaseEntity entity) {
        entities.add(entity);
        RenderableHolder.getInstance().add(entity);
    }

    public void update() {
        player.update();
        entities.forEach(entity -> {
            if (entity instanceof BaseGhost) ((BaseGhost) entity).update();
        });
    }

    public static Player getPlayer() {
        return player;
    }
}
