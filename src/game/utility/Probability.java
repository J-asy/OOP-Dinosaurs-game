package game.utility;

import java.util.Random;

public abstract class Probability {

    public static boolean generateProbability(float probability){

        Random r = new Random();
        float randFloat =  r.nextFloat();

        return randFloat <= probability;
    }
}
