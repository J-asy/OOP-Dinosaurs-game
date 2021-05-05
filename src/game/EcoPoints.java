package game;

/**
 * Class that represents the EcoPoints (currency of the game).
 */
public class EcoPoints {

    private static int ecoPoints = 0;

    /**
     * Access the current EcoPoints.
     * @return the current amount of EcoPoints
     */
    public static int getEcoPoints(){return ecoPoints;}

    /**
     * Increases EcoPoints.
     * @param earnedPoints the amount of points to increase
     */
    public static void incrementEcoPoints(int earnedPoints){ecoPoints += earnedPoints;}

    /**
     * Decrements EcoPoints.
     * @param usedPoints the amount of points to deduct
     */
    public static void decrementEcoPoints(int usedPoints){ecoPoints -= usedPoints;}

}
