package application.logic;

import application.input.InputUtility;
import application.sharedObject.RenderableHolder;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class GameLogic {
    private Player player;
    private final ArrayList<BaseGhost> enemies;

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
        if (entity instanceof BaseGhost) enemies.add((BaseGhost) entity);
        RenderableHolder.getInstance().add(entity);
    }

    public void update() {
        player.update();
        //dev only
        if (enemies.isEmpty()) {addNewEntity(new BaseGhost(600,600)); addNewEntity(new Ghost2(600,400));}
        else {
            enemies.forEach(BaseEntity::update);
            enemies.forEach(entity -> {
                if (entity.collideWith(player)) handleDieSequence();

            });
        }


        if (bullet != null) {
            enemies.forEach(entity -> {
                if(entity.collideWith(bullet) && !bullet.isDestroyed()) handleDieSequence2(entity) ;
            });
            bullet.update();
            if (bullet.isDestroyed()) bullet = null;
            //dev only
            else if (InputUtility.getKeyPressed(KeyCode.SPACE)) bullet.destroyed = true;
        }
        //enemy spawn sequence

        //remove unused enemies
        for (int i = enemies.size() - 1; i >= 0; i--) {
            if (enemies.get(i).isDestroyed())
                enemies.remove(i);
        }
    }

    public void handleShoot(double x, double y, int dirX, int dirY) {
        if (bullet != null) return;
        bullet = new Bullet(x, y, dirX, dirY);
        addNewEntity(bullet);
    }

    public void handleDieSequence() {
        enemies.forEach(entity->entity.destroyed = true);
        player.x = 200;
        player.y = 200;
    }

    public void handleDieSequence2(BaseGhost ghost) {
        bullet.destroyed = true;
        ghost.destroyed = true;
    }

    public Player getPlayer() {
        return player;
    }
}
