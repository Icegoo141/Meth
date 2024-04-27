package application.logic;

import application.GameController;
import application.sharedObject.RenderableHolder;
import utils.RandomSpawn;

import java.util.ArrayList;

public class GameLogic {
    private int stage;
    private Player player;
    private int lives;
    private final ArrayList<BaseGhost> enemies;
    private final ArrayList<Explosion> explosions;
    private Bullet bullet;
    private HUD hud;
    private long prevSpawnTime;
    private long currTime;
    private long startTime;

    public GameLogic(int stage) {
        Field bg = new Field();
        RenderableHolder.getInstance().add(bg);

        hud = new HUD();
        RenderableHolder.getInstance().add(hud);

        enemies = new ArrayList<>();
        explosions = new ArrayList<>();

        player = new Player(400, 400);
        addNewEntity(player);

        this.stage = stage;

        startTime = -1;

        lives = 3;
    }

    private void addNewEntity(BaseEntity entity) {
        if (entity instanceof BaseGhost) enemies.add((BaseGhost) entity);
        RenderableHolder.getInstance().add(entity);
    }

    public void update(long l) {
        currTime = l;

        if (startTime == -1) startTime = currTime;

        player.update();

        for (BaseGhost ghost : enemies) {
            ghost.update();
            if (ghost.collideWith(player)) {
                handleEnemyHitPlayer();
                break;
            }
        }

        explosions.forEach(Explosion::update);

        if (bullet != null) {
            enemies.forEach(enemy -> {
                if (enemy.collideWith(bullet) && !bullet.isDestroyed()) handleBulletHitEnemy(enemy);
            });
            bullet.update();
            if (bullet.isDestroyed()) bullet = null;
        }

        handleSpawnEnemy();

        handleDestroyedEntities();

        handleGameTimer();
    }

    public void handleShoot(int dirX, int dirY) {
        if (bullet != null) return;
        RenderableHolder.shootSound.play((double) GameController.getInstance().getSoundValue()/100);
        bullet = new Bullet(player.x + dirX, player.y + dirY, dirX, dirY, player.getDamage());
        addNewEntity(bullet);
    }

    private void handleEnemyHitPlayer() {
        enemies.forEach(entity -> entity.setHp(0));
        player.x = 400;
        player.y = 400;
        if (bullet != null) {
            bullet.destroyed = true;
        }
        lives = lives - 1;
        if (lives == 0) GameController.getInstance().handleQuit("defeatScene");
    }

    private void handleBulletHitEnemy(BaseGhost ghost) {
        bullet.destroyed = true;
        ghost.setHp(ghost.getHp() - bullet.getDamage());
        if (ghost.isDestroyed()) {
            RenderableHolder.explosionSound.play((double) GameController.getInstance().getSoundValue()/100) ;
            Explosion explosion = new Explosion(ghost.x, ghost.y);
            explosions.add(explosion);
            RenderableHolder.getInstance().add(explosion);
        }
    }

    private void handleSpawnEnemy() {
        // Spawn an enemy every second
        if (currTime - prevSpawnTime >= 1e9 && hud.getRemainingTime() != 0) {
            BaseGhost spawnedEnemy = RandomSpawn.spawnEnemy(stage);
            enemies.forEach(enemy-> {
                if (enemy instanceof Monk) spawnedEnemy.setHp(spawnedEnemy.getHp() + 1);
            });
            addNewEntity(spawnedEnemy);
            prevSpawnTime = currTime;
        }
    }

    private void handleDestroyedEntities() {
        // Remove destroyed enemies
        for (int i = enemies.size() - 1; i >= 0; i--) {
            if (enemies.get(i).isDestroyed())
                enemies.remove(i);
        }

        // Remove destroyed explosion effects
        for (int i = explosions.size() - 1; i >= 0; i--) {
            if (explosions.get(i).isDestroyed())
                explosions.remove(i);
        }
    }

    private void handleGameTimer() {
        // Calculate elapsed time in seconds
        double elapsedTimeSeconds = (currTime - startTime) / 1_000_000_000.0;

        // Calculate remaining time
        double remainingTime = 60 - elapsedTimeSeconds;
        if (remainingTime <= 0) {
            remainingTime = 0; // Round timer to 0 if time's up
            if (enemies.isEmpty()) {
                GameController.getInstance().handleQuit("VictoryScene"); // Stop the timer when time's up
            }
        }

        // Update the timer label
        hud.setRemainingTime(remainingTime);
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
}
