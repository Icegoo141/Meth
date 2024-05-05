package application.logic;

import application.GameController;
import application.logic.entities.*;
import application.sharedObject.RenderableHolder;
import utils.RandomSpawn;

import java.util.ArrayList;

public class GameLogic {
    private final Player player;
    private int lives;
    private final ArrayList<BaseEntity> entities;
    private Bullet bullet;
    private final HUD hud;
    private long prevSpawnTime;
    private long prevShootTime ;
    private long currTime;
    private long startTime;

    public GameLogic() {
        Field bg = new Field();
        RenderableHolder.getInstance().add(bg);

        hud = new HUD();
        RenderableHolder.getInstance().add(hud);

        entities = new ArrayList<>();

        player = new Player(400, 400);
        addNewEntity(player);

        startTime = -1;

        lives = 3;
    }

    private void addNewEntity(BaseEntity entity) {
        if (!(entity instanceof Player)) entities.add(entity);
        RenderableHolder.getInstance().add(entity);
    }

    public void update(long l) {
        currTime = l;
        if (startTime == -1) startTime = currTime;

        // Logic sequence
        player.update();
        entities.forEach(BaseEntity::update);
        handlePlayerEnemiesCollision();
        handleBulletEnemiesCollision();
        handleSpawnEnemy();
        handleDestroyedEntities();
        handleGameTimer();
    }


    private void handlePlayerEnemiesCollision() {
        for (BaseEntity entity : entities) {
            if (entity instanceof Samurai) {
                if (((Samurai) entity).collideWith(player)) {
                    handleEnemyHitPlayer();
                    return;
                }
            }
        }
    }

    private void handleEnemyHitPlayer() {
        entities.forEach(entity -> {
            if (entity instanceof Samurai) ((Samurai) entity).setHp(0);
        });
        player.setX(400);
        player.setY(400);
        if (bullet != null) {
            bullet.setDestroyed(true);
        }
        lives = lives - 1;
        if (lives == 0) GameController.getInstance().handleQuit("DefeatScene");
    }

    public void handleBulletEnemiesCollision() {
        if (bullet != null) {
            for (BaseEntity entity : entities) {
                if (entity instanceof Samurai) {
                    if (((Samurai) entity).collideWith(bullet) && !bullet.isDestroyed()) {
                        handleBulletHitEnemy((Samurai) entity);
                        break;
                    }
                }
            }
            if (bullet.isDestroyed()) bullet = null;
        }
    }


    private void handleBulletHitEnemy(Samurai ghost) {
        bullet.setDestroyed(true);
        ghost.setHp(ghost.getHp() - bullet.getDamage());
        if (ghost.isDestroyed()) {
            RenderableHolder.explosionSound.play();
            Explosion explosion = new Explosion(ghost.getX(), ghost.getY());
            addNewEntity(explosion);
        }
    }

    private void handleSpawnEnemy() {
        // Spawn an enemy every 0.8 second
        if (currTime - prevSpawnTime >= 8e8 && hud.getRemainingTime() != 0) {
            Samurai spawnedEnemy = RandomSpawn.spawnEnemy();
            entities.forEach(enemy -> {
                if (enemy instanceof Monk) spawnedEnemy.setHp(spawnedEnemy.getHp() + 1);
            });
            addNewEntity(spawnedEnemy);
            prevSpawnTime = currTime;
        }
    }

    private void handleDestroyedEntities() {
        // Remove destroyed enemies
        for (int i = entities.size() - 1; i >= 0; i--) {
            if (entities.get(i).isDestroyed()) entities.remove(i);
        }
    }

    private void handleGameTimer() {
        // Calculate elapsed time in seconds
        double elapsedTimeSeconds = (currTime - startTime) / 1_000_000_000.0;

        // Calculate remaining time
        double remainingTime = 60 - elapsedTimeSeconds;
        if (remainingTime <= 0) {
            remainingTime = 0; // Round timer to 0 if time's up
            boolean enemiesExist = false;
            for (BaseEntity entity : entities) {
                if (entity instanceof Samurai) {
                    enemiesExist = true;
                    break;
                }
            }
            if (!enemiesExist && GameController.getInstance().getGameLogic().getLives() != 0) {
                GameController.getInstance().changeStage(); // Stop the timer when time's up
            }
        }

        // Update the timer label
        hud.setRemainingTime(remainingTime);
    }

    public void handleShoot(int dirX, int dirY) {
        if (bullet != null || currTime - prevShootTime < 2e8) return;
        RenderableHolder.shootSound.play((double) GameController.getInstance().getSoundValue() / 100);
        bullet = new Bullet(player.getX() + dirX, player.getY() + dirY, dirX, dirY);
        addNewEntity(bullet);
        prevShootTime = currTime ;
    }

    public Player getPlayer() {
        return player;
    }

    public int getLives() {
        return lives;
    }

    public long getCurrTime() {
        return currTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
}
