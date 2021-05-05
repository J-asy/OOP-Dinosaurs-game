package game.player;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.FoodType;
import game.dinosaurs.*;
import java.util.List;
import java.util.Scanner;

/**
 * Special Action for Player to feed Actor.
 */
public class PlayerFeedAction extends Action {

    /**
     * The Actor that is to be fed.
     */
    private final Actor target;

    /**
     * Constructor
     * @param target the Actor (DinoActor) to feed
     */
    public PlayerFeedAction(Actor target){ this.target=target; }

    /**
     * Perform the Action.
     * A list of items from the Player inventory will be shown to the user. User can choose the item they want to feed
     * to the target. Checking is done to ensure that the correct food type (food for Herbivore or Carnivore) is fed to
     * the correct dinosaur.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor (Player) is on.
     * @return a description of whether the feeding is successful that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        List<Item> inventoryItems = actor.getInventory();

        if (inventoryItems.size()>0) {
            System.out.println("0. Decided not to feed/No appropriate food to feed.");
            for (int i = 0; i < inventoryItems.size(); i++) {
                System.out.println((i + 1) + ". " + inventoryItems.get(i).toString() + "\n");
            }
            Scanner input = new Scanner(System.in);
            System.out.println("Choose an item to feed the dinosaur: ");
            int choice = input.nextInt();
            if (choice == 0)
                return "No food chosen.";
            Item chosenItem = inventoryItems.get(choice - 1);

            if (target.hasCapability(DinoCapabilities.HERBIVORE)) {
                if (chosenItem.hasCapability(FoodType.HERBIVORE)) {
                    if (chosenItem.toString().equalsIgnoreCase("Fruit"))
                        EcoPoints.incrementEcoPoints(10);
                    actor.removeItemFromInventory(chosenItem);
                    target.heal(20);
                    return target + " at (" + map.locationOf(target).x() + ", " + map.locationOf(target).y() +
                            ") increases food level by 20!";
                } else
                    return target + " is a Herbivore. Please choose the correct item to feed.";
            } else if (target.hasCapability(DinoCapabilities.CARNIVORE)) {
                if (chosenItem.hasCapability(FoodType.CARNIVORE)) {
                    actor.removeItemFromInventory(chosenItem);
                    target.heal(20);
                    return target + " at (" + map.locationOf(target).x() + ", " + map.locationOf(target).y() +
                            ") increases food level by 20!";
                } else
                    return target + " is a Carnivore. Please choose the correct item to feed.";
            } else
                return target + " is not a dinosaur";
        }
        else
            return "You currently have no item in your inventory.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds " + target;
    }
}

