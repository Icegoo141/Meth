package utils;

import application.logic.BaseGhost;
import application.logic.Ghost2;
import application.logic.Ghost3;
import application.logic.Ghost4;

import java.util.Random;

public class RandomSpawn {
    public static BaseGhost spawnGhost(int level) {

        Random rand = new Random();
        int randSpawn = rand.nextInt(100);
        int randPos = rand.nextInt(16);

        double x,y ;
        if (randPos <= 3) {
            x = 20 ;
            if (randPos == 0) {
                y = 340 ;
            }
            else if (randPos == 1) {
                y = 380 ;
            }
            else if (randPos == 2) {
                y = 420 ;
            }
            else {
                y = 460 ;
            }
        }
        else if (randPos <= 7) {
            y = 20 ;
            if (randPos == 4) {
                x = 340 ;
            }
            else if (randPos == 5) {
                x = 380 ;
            }
            else if (randPos == 6) {
                x = 420 ;
            }
            else {
                x = 460 ;
            }
        }
        else if (randPos <= 11) {
            x = 780 ;
            if (randPos == 8) {
                y = 340 ;
            }
            else if (randPos == 9) {
                y = 380 ;
            }
            else if (randPos == 10) {
                y = 420 ;
            }
            else {
                y = 460 ;
            }
        }
        else {
            y = 780 ;
            if (randPos == 12) {
                x = 340 ;
            }
            else if (randPos == 13) {
                x = 380 ;
            }
            else if (randPos == 14) {
                x = 420 ;
            }
            else {
                x = 460 ;
            }
        }

        if (level == 1) {
            if (randSpawn <=39) {
                return new BaseGhost(x,y);
            }
            else if (randSpawn <= 69) {
                return new Ghost2(x,y);
            }
            else if (randSpawn <= 89) {
                return new Ghost3(x,y);
            }
            else {
                return new Ghost4(x,y);
            }
        }
        else if(level == 2) {
            if (randSpawn <=19) {
                return new BaseGhost(x,y);
            }
            else if (randSpawn <= 54) {
                return new Ghost2(x,y);
            }
            else if (randSpawn <= 84) {
                return new Ghost3(x,y);
            }
            else {
                return new Ghost4(x,y);
            }
        }
        else {
            if (randSpawn <=9) {
                return new BaseGhost(x,y);
            }
            else if (randSpawn <= 39) {
                return new Ghost2(x,y);
            }
            else if (randSpawn <= 79) {
                return new Ghost3(x,y);
            }
            else {
                return new Ghost4(x,y);
            }
        }
    }
}
