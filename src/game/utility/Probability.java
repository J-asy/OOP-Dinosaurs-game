package game.utility;

import java.util.Random;

/**
 * A class that contains one static method which is used to generate probability.
 */
public abstract class Probability {

    /**
     * Method to generate probability using random float.
     * @param probability
     * @return
     */
    public static boolean generateProbability(float probability){

        Random r = new Random();
        float randFloat =  r.nextFloat();

        return randFloat <= probability;
    }
}
