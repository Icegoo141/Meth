package utils;

import application.GameController;
import application.logic.entities.Monk;
import application.logic.entities.Ninja;
import application.logic.entities.Oni;
import application.logic.entities.Samurai;

import java.util.Random;

public class RandomSpawn {
    private static final Random rand = new Random();

    public static Samurai spawnEnemy() {
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

        switch (GameController.getInstance().getStage()) {
            case 1:
                samuraiChance = 50;
                ninjaChance = 35;
                oniChance = 15;
                monkChance = 0;
                break;
            case 2:
                samuraiChance = 20;
                ninjaChance = 50;
                oniChance = 20;
                monkChance = 5;
                break;
            case 3:
                samuraiChance = 0;
                ninjaChance = 60;
                oniChance = 30;
                monkChance = 10;
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
            return new Samurai(x, y);
        } else if (randSpawn < samuraiChance + ninjaChance) {
            return new Ninja(x, y);
        } else if (randSpawn < samuraiChance + ninjaChance + oniChance) {
            return new Oni(x, y);
        } else {
            return new Monk(x, y);
        }
    }
}
