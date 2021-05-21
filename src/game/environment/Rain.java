package game.environment;

import game.Utility;

public class Rain {
    private static String status = "Clear Sky";
    private static int turns = 0;

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

    public static String getStatus(){
        return status;
    }
}
