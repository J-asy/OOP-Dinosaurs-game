package game.actions;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.FoodType;
import game.dinosaurs.*;
import game.environment.Fruit;

import java.util.List;
import java.util.Scanner;

public class PlayerFeedAction extends Action {
    private final Actor target;

    public PlayerFeedAction(Actor target){ this.target=target; }

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


//        if (target.hasCapability(DinoEncyclopedia.STEGOSAUR) || target.hasCapability(DinoEncyclopedia.BRACHIOSAUR)){
//            for (Item item: actor.getInventory()){
//                if (item.hasCapability(FoodType.HERBIVORE)) {
//                    if (item instanceof Fruit)
//                        EcoPoints.incrementEcoPoints(10);
//                    actor.removeItemFromInventory(item);
//                    target.heal(20);
//                    return target + " at (" + map.locationOf(target).x() + ", " + map.locationOf(target).y() +
//                            ") increases food level by 20!";
//                }
//            }
//        }
//        else if (target.hasCapability(DinoEncyclopedia.ALLOSAUR)){
//            for (Item item: actor.getInventory()){
//                if (item.hasCapability(FoodType.CARNIVORE)) {
//                    actor.removeItemFromInventory(item);
//                    target.heal(20);
//                    return target + " at (" + map.locationOf(target).x() + ", " + map.locationOf(target).y() +
//                            ") increases food level by 20!";
//                }
//            }
//        }


    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds " + target;
    }
}

