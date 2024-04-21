package application.logic;

import application.GameController;
import application.sharedObject.RenderableHolder;
import utils.RandomSpawn;

import java.util.ArrayList;

public class GameLogic {
    private int level;
    private Player player;
    private int lives;
    private final ArrayList<BaseGhost> enemies;
    private Bullet bullet;
    private HUD hud;
    private long prevSpawnTime;
    private long currTime;
    private long startTime;

    public GameLogic(int level) {
        Field bg = new Field();
        RenderableHolder.getInstance().add(bg);

        hud = new HUD();
        RenderableHolder.getInstance().add(hud);

        enemies = new ArrayList<>();

        player = new Player(400, 400);
        addNewEntity(player);

        this.level = level;

        startTime = -1;

        lives = 3;
    }

    private void addNewEntity(BaseEntity entity) {
        if (entity instanceof BaseGhost) enemies.add((BaseGhost) entity);
        RenderableHolder.getInstance().add(entity);
    }

    public void update(long l) {
        currTime = l;
        if (startTime == -1) startTime = l;

        player.update();
        for (BaseGhost ghost : enemies) {
            ghost.update();
            if (ghost.collideWith(player)) {
                handlePlayerDie();
                break;
            }
        }

        if (bullet != null) {
            enemies.forEach(entity -> {
                if (entity.collideWith(bullet) && !bullet.isDestroyed()) handleBulletHit(entity);
            });
            bullet.update();
            if (bullet.isDestroyed()) bullet = null;
        }

        //spawn an enemy every second
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

        // Calculate elapsed time in seconds
        double elapsedTimeSeconds = (l - startTime) / 1_000_000_000.0;

        // Calculate remaining time
        double remainingTime = 60 - elapsedTimeSeconds;
        if (remainingTime < 0) {
            remainingTime = 0; // Round timer to 0 if time's up
            GameController.getInstance().handleQuit(); // Stop the timer when time's up
        }

        // Update the timer label
        hud.setRemainingTime(remainingTime);
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
        if (bullet != null) {
            bullet.destroyed = true;
        }
        lives = lives - 1;
        if (lives == 0) GameController.getInstance().handleQuit();
    }

    public void handleBulletHit(BaseGhost ghost) {
        bullet.destroyed = true;
        ghost.setHp(ghost.getHp() - bullet.getDamage());
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
