package game;

import java.util.Random;
import java.util.Scanner;

/**
 * A class that contains one static method which is used to generate probability.
 */
public abstract class Utility {

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

    /**
     * Validate the user input to ensure that it is an Integer.
     * @return a valid user input
     */
    public static int getIntegerInput(){
        Scanner scanner = new Scanner(System.in);
        int userInput = -1;
        boolean errorOccurred = true;
        do{
            try{
                userInput = Integer.parseInt(scanner.nextLine());
                errorOccurred = false;
            }
            catch (NumberFormatException e){
                System.out.println("Please enter a number.");
            }
        } while (errorOccurred);
        return userInput;
    }
}
