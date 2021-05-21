package game.environment;

import game.Utility;

/**
 * Class to represent Rain in the game.
 */
public class Rain {

    private static String status = "Clear Sky";
    private static int turns = 0;

    /**
     * Every 10 turns, there is a probability of 20% for rain to fall. If the 20% is hit, status of weather
     * will be "Raining", else, status will be "Clear Sky".
     * @return true if it rains, false otherwise
     */
    public static boolean rainingOrNot(){
        boolean rainingOccurs = false;
        turns++;
        if (turns == 10) {
            if(Utility.generateProbability(0.2f)) {
                turns = 0;

                for (int i = 0; i < 7; i++) {
                    String rain = "";
                    for (int j = 0; j < 40; j++) {
                        rain += "\\ ";
                    }
                    System.out.println(rain);
                }
                status = "Raining";
                rainingOccurs = true;
            }
            else {
                turns = 0;
                status = "Clear Sky";
            }
        }
        return rainingOccurs;
    }

    /**
     * Access the current weather status.
     * @return the status of the weather
     */
    public static String getStatus(){
        return status;
    }
}
