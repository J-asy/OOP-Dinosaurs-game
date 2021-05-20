package game.environment;

import game.Probability;

public class Rain {
    private static String status = "Clear Sky";
    private static int turns = 0;

    public static void rainingOrNot(){
        turns++;
        if (turns == 10) {
            if(Probability.generateProbability(0.2f)) {
                turns = 0;

                for (int i = 0; i < 7; i++) {
                    String rain = "";
                    for (int j = 0; j < 40; j++) {
                        rain += "\\ ";
                    }
                    System.out.println(rain);
                }
                status = "Raining";
            }
            else {
                turns = 0;
                status = "Clear Sky";
            }
        }

    }

    public static String getStatus(){
        return status;
    }
}
