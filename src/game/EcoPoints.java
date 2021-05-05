package game;

public class EcoPoints {

    private static int ecoPoints = 0;

    public static int getEcoPoints(){return ecoPoints;}

    public static void incrementEcoPoints(int earnedPoints){ecoPoints += earnedPoints;}

    public static void decrementEcoPoints(int usedPoints){ecoPoints -= usedPoints;}

}
