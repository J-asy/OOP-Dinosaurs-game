package game.vendingmachine;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.EcoPoints;
import game.vendingmachine.VendingMachine;

import java.util.Scanner;

public class BuyAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        Scanner input = new Scanner(System.in);
        String strFormat = "%-2s %-20s %-19s|\n";
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Welcome to the Vending Machine.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("$$$ Items on SALE $$$");
        System.out.println("===========================================|");
        System.out.format("%-2s %-20s %-10s\n"," ", "Items", "| Cost (Eco Points)|");
        System.out.println("-------------------------------------------|");
        System.out.format(strFormat,"1","Fruit","| 30");
        System.out.format(strFormat,"2","Vegetarian Meal Kit","| 100");
        System.out.format(strFormat,"3","Carnivore Meal Kit","| 500");
        System.out.format(strFormat,"4","Stegosaur Egg","| 200");
        System.out.format(strFormat,"5","Brachiosaur Egg","| 500");
        System.out.format(strFormat,"6","Allosaur Egg","| 1000");
        System.out.format(strFormat,"7","Laser Gun","| 500");
        System.out.println("===========================================|");
        System.out.println("You currently have " + EcoPoints.getEcoPoints() + " Eco Points.");
        System.out.println("What would you like? Please enter a number from 1 to 7. (Enter 0 to Exit)");
        int choice = input.nextInt();
        boolean boughtItem = VendingMachine.choose(choice,actor);

        if (choice >= 1 && choice <= 7 && boughtItem)
            return actor + " bought item " + choice + "\n";
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " chooses an item to buy from Vending Machine.";
    }
}
