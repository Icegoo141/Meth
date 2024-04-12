package application.logic;

import application.input.InputUtility;
import application.sharedObject.RenderableHolder;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class GameLogic {
    private Player player;
    private final ArrayList<BaseEntity> enemies;

    private Bullet bullet;

    public GameLogic() {
        Battleground bg = new Battleground();
        RenderableHolder.getInstance().add(bg);

        enemies = new ArrayList<>();

        player = new Player(200, 200);
        addNewEntity(player);

        BaseGhost testGhost = new BaseGhost(600, 600);
        addNewEntity(testGhost);
    }

    private void addNewEntity(BaseEntity entity) {
        if (entity instanceof BaseGhost) enemies.add(entity);
        RenderableHolder.getInstance().add(entity);
    }

    public void update() {
        player.update();
        enemies.forEach(BaseEntity::update);
        if (bullet != null) {
            bullet.update();
            if (bullet.isDestroyed()) bullet = null;
            else if (InputUtility.getKeyPressed(KeyCode.SPACE)) bullet.destroyed = true;
        }
        //enemy spawn sequence
    }

    public void handleShoot(double x, double y, int dirX, int dirY) {
        if (bullet != null) return;
        bullet = new Bullet(x, y, dirX, dirY);
        addNewEntity(bullet);
    }

    public Player getPlayer() {
        return player;
    }
}
