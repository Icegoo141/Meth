package utils;

import application.logic.BaseGhost;
import application.logic.Ninja;
import application.logic.Oni;
import application.logic.Monk;

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
        int samuraiChance, ninjaChance, oniChance, monkChance;
        int randSpawn = rand.nextInt(100);

        switch (level) {
            case 1:
                samuraiChance = 40;
                ninjaChance = 30;
                oniChance = 20;
                monkChance = 10;
                break;
            case 2:
                samuraiChance = 0;
                ninjaChance = 35;
                oniChance = 30;
                monkChance = 15;
                break;
            default:
                samuraiChance = 10;
                ninjaChance = 30;
                oniChance = 40;
                monkChance = 20;
                break;
        }

        // Determine which type of ghost to spawn based on the random chance
        if (randSpawn < samuraiChance) {
            return new BaseGhost(x, y);
        } else if (randSpawn < samuraiChance + ninjaChance) {
            return new Ninja(x, y);
        } else if (randSpawn < samuraiChance + ninjaChance + oniChance) {
            return new Oni(x, y);
        } else {
            return new Monk(x, y);
        }
    }
}
