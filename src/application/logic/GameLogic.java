package application.logic;


import application.GameController;
import application.input.InputUtility;
import application.sharedObject.RenderableHolder;
import utils.RandomSpawn;
import utils.SceneNav;

import java.util.ArrayList;

public class GameLogic {
    private int level;
    private Player player;
    private final ArrayList<BaseGhost> enemies;
    private final ArrayList<Explosion> explosions;
    private Life life ;

    private Bullet bullet;

    private long prevSpawnTime;
    private long currTime;

    public GameLogic() {
        Field bg = new Field();
        life = new Life() ;
        RenderableHolder.getInstance().add(bg);
        RenderableHolder.getInstance().add(life);

        enemies = new ArrayList<>();
        explosions = new ArrayList<>();

        player = new Player(400, 400);
        addNewEntity(player);

        level = 1;
    }

    private void addNewEntity(BaseEntity entity) {
        if (entity instanceof BaseGhost) enemies.add((BaseGhost) entity);
        RenderableHolder.getInstance().add(entity);
    }

    public void update(long l) {
        currTime = l;

        player.update();
        for (BaseGhost ghost : enemies) {
            ghost.update();
            if (ghost.collideWith(player)) {
                handlePlayerDie();
                break;
            }
        }
        for (Explosion explosion : explosions) {
            explosion.update();
        }

        if (bullet != null) {
            enemies.forEach(entity -> {
                if (entity.collideWith(bullet) && !bullet.isDestroyed()) handleBulletHit(entity);
            });
            bullet.update();
            if (bullet.isDestroyed()) bullet = null;
        }

        //enemy spawn sequence
        //spawn an enemy once every around 500 ms
        if (l - prevSpawnTime >= 1e9) {
            BaseGhost enemy = RandomSpawn.spawnGhost(1);
            addNewEntity(enemy);
            prevSpawnTime = l;
        }

        //remove unused enemies
        for (int i = enemies.size() - 1; i >= 0; i--) {
            if (enemies.get(i).isDestroyed())
                enemies.remove(i);
        }
        for (int i = explosions.size() - 1; i >= 0; i--) {
            if (explosions.get(i).isDestroyed())
                explosions.remove(i);
        }
    }

    public void handleShoot(int dirX, int dirY) {
        if (bullet != null) return;
        bullet = new Bullet(player.x + 30 * dirX, player.y + 30 * dirY, dirX, dirY, player.getDamage());
        addNewEntity(bullet);
    }

    public void handlePlayerDie() {
        enemies.forEach(entity -> entity.setHp(0));
        player.x = 400;
        player.y = 400;
        life.setLife(life.getLife() - 1);
        if (bullet != null) {
            bullet.destroyed = true ;
        }
        explosions.forEach(explosion -> explosion.destroyed = true) ;
        if (life.getLife() <= 0) {
            GameController.getInstance().getGameLoop().stop();
            RenderableHolder.getInstance().getEntities().clear();
            InputUtility.clearKeyPressed();
            SceneNav.setFXMLScene("MainMenu");
        }
    }

    public void handleBulletHit(BaseGhost ghost) {
        bullet.destroyed = true;
        ghost.setHp(ghost.getHp() - bullet.getDamage());
        if (ghost.isDestroyed()) {
            Explosion explosion = new Explosion(ghost.x,ghost.y) ;
            explosions.add(explosion) ;
            RenderableHolder.getInstance().add(explosion);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public long getCurrTime() {
        return currTime;
    }

}
