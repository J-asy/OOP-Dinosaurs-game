package game;

import java.util.Random;
import java.util.Scanner;

/**
 * A class that contains static utility methods.
 */
public abstract class Utility {

    /**
     * Method that uses random float to determine whether an event occurs and returns true if it
     * does or returns false otherwise.
     * @param probability probability of an event occurring
     * @return true if event happens, false otherwise
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
