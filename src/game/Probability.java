package game;

import java.util.Random;

class Probability {

    public static boolean generateProbability(float probability){

        Random r = new Random();
        float randFloat =  r.nextFloat();

        if (randFloat <= probability){
            return true;
        }
        return false;
    }
}
