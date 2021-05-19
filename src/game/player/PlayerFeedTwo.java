package game.player;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.FoodItem;
import game.dinosaurs.DinoActor;
import game.dinosaurs.Pterodactyl;
import game.environment.Fruit;

import java.util.List;
import java.util.Scanner;

/**
 * Special Action for Player to feed Actor.
 */
public class PlayerFeedTwo extends Action {

    /**
     * The Actor that is to be fed.
     */
    private final DinoActor target;

    private final static int INCREMENT_FOOD_LEVEL = 20;

    private final static int INCREMENT_ECO_POINTS = 10;

    /**
     * Constructor
     * @param target the Actor (DinoActor) to feed
     */
    public PlayerFeedTwo(DinoActor target){
        this.target = target;
    }

    /**
     * Perform the Action.
     * A list of items from the Player inventory will be shown to the user. User can choose the item they want to feed
     * to the target. Checking is done to ensure that the correct food type (food for Herbivore or Carnivore) is fed to
     * the correct DinoActor.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor (Player) is on.
     * @return a description of whether the feeding is successful that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        List<Item> inventoryItems = actor.getInventory();

        if (inventoryItems.size() <= 0)
            return "You currently have no item in your inventory.";

        System.out.println("0. Decided not to feed/No matching food to feed.");
        for (int i = 0; i < inventoryItems.size(); i++) {
            System.out.println((i + 1) + ". " + inventoryItems.get(i).toString());
        }

        System.out.println("Choose an inventory item to feed the dinosaur: ");
        int choice = getIntegerInput(inventoryItems.size());
        if (choice == 0)
            return "No food chosen.";
        Item item = inventoryItems.get(choice - 1);

        if (item instanceof FoodItem) {
            FoodItem chosenItem = (FoodItem) item;
            if (chosenItem.canEat(target)){
                actor.removeItemFromInventory(chosenItem);
                target.heal(INCREMENT_FOOD_LEVEL);
                if (isEcoPointEvent(chosenItem)){
                    EcoPoints.incrementEcoPoints(INCREMENT_ECO_POINTS);
                }
                return target + " at (" + map.locationOf(target).x() + ", " + map.locationOf(target).y() +
                        ") increases food level by " + INCREMENT_FOOD_LEVEL + "!";
            }
        }
        return "Chosen item is not suitable to feed.";
    }

    private boolean isEcoPointEvent(FoodItem foodItem){
        return foodItem instanceof Fruit || target instanceof Pterodactyl;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds " + target;
    }

    /**
     * Validate the user input to ensure that it is an Integer and within a certain range.
     * @param max the maximum number that is considered valid
     * @return a valid Integer from user input
     */
    private int getIntegerInput(int max){
        Scanner scanner = new Scanner(System.in);
        int userInput = -1;
        boolean errorOccurred = true;
        do{
            try{
                userInput = Integer.parseInt(scanner.nextLine());
                errorOccurred = userInput < 0 || userInput > max;
                if (errorOccurred)
                    System.out.println("Please enter a number within 0 to " + max + ".");
            }
            catch (NumberFormatException e){
                System.out.println("Please enter a number within 0 to " + max +".");
            }
        } while (errorOccurred);
        return userInput;
    }
}

