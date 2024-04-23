package utils;

import application.GameController;
import application.logic.BaseGhost;
import application.logic.Ghost2;
import application.logic.Ghost3;
import application.logic.Ghost4;

import java.util.Random;

public class RandomSpawn {
    private static final Random rand = new Random();

    public static BaseGhost spawnEnemy(int level) {
        // Fixed spawn positions
        int randPos = rand.nextInt(16);
        double x, y;

        if (randPos < 4) {
            x = 20;
            y = 340 + 40 * randPos;
        } else if (randPos < 8) {
            x = 340 + 40 * (randPos - 4);
            y = 20;
        } else if (randPos < 12) {
            x = 780;
            y = 340 + 40 * (randPos - 8);
        } else {
            x = 340 + 40 * (randPos - 12);
            y = 780;
        }

        // Set chances of spawning different types of ghosts based on the level
        int baseChance, ghost2Chance, ghost3Chance, ghost4Chance;
        int randSpawn = rand.nextInt(100);

        switch (level) {
            case 1:
                baseChance = 40;
                ghost2Chance = 30;
                ghost3Chance = 20;
                ghost4Chance = 10;
                break;
            case 2:
                baseChance = 20;
                ghost2Chance = 35;
                ghost3Chance = 30;
                ghost4Chance = 15;
                break;
            default:
                baseChance = 10;
                ghost2Chance = 30;
                ghost3Chance = 40;
                ghost4Chance = 20;
                break;
        }

        // Determine which type of ghost to spawn based on the random chance
        if (randSpawn < baseChance) {
            return new BaseGhost(x, y);
        } else if (randSpawn < baseChance + ghost2Chance) {
            return new Ghost2(x, y);
        } else if (randSpawn < baseChance + ghost2Chance + ghost3Chance) {
            return new Ghost3(x, y);
        } else {
            return new Ghost4(x, y);
        }
    }
}
