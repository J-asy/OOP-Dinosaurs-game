package game.player;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.EcoPoints;
import game.dinosaurs.CapableActor;
import game.dinosaurs.DinoEncyclopedia;
import game.dinosaurs.Egg;
import game.environment.Fruit;

/**
 * Class to represent the Vending Machine.
 */
public class VendingMachine extends Ground {

    /**
     * Constructor.
     */
    public VendingMachine() {
        super('X');
    }

    /**
     * A static method that checks whether a user is eligible to buy their chosen item and makes the purchase.
     * The EcoPoints are checked to ensure that the user is able to pay for the item they want to buy. The item is
     * added to the Player's inventory if purchase is successful and EcoPoints are deducted.
     * @param choice an int that indicates the item the user wants to buy
     * @param actor the actor performing the BuyAction that invokes this method.
     * @return a boolean indicating success or failure of purchase
     */
    public static boolean choose(int choice, Actor actor){

        int ecoPoints = EcoPoints.getEcoPoints();

        if (choice == 1 && ecoPoints >= 30){
            Fruit fruit = new Fruit();
            actor.addItemToInventory(fruit);
            EcoPoints.decrementEcoPoints(30);
            System.out.println("Fruit Get!");
        }
        else if (choice == 2 && ecoPoints >= 100){
            MealKitsItem vegeMealKit = new MealKitsItem("VegetarianMealKit");
            vegeMealKit.setForHerbivores();
            actor.addItemToInventory(vegeMealKit);
            EcoPoints.decrementEcoPoints(100);
            System.out.println("Vege meal kit Get!");
        }
        else if (choice == 3 && ecoPoints >= 500){
            MealKitsItem meatMealKit = new MealKitsItem("CarnivoreMealKit");
            meatMealKit.setForCarnivores();
            actor.addItemToInventory(meatMealKit);
            EcoPoints.decrementEcoPoints(500);
            System.out.println("Carnivore meal kit Get!");
        }
        else if (choice == 4 && ecoPoints >= 200){
            Egg stegosaurEgg = new Egg(DinoEncyclopedia.STEGOSAUR);
            actor.addItemToInventory(stegosaurEgg);
            EcoPoints.decrementEcoPoints(200);
            System.out.println("Stego egg Get!");
        }
        else if (choice == 5 && ecoPoints >= 500){
            Egg brachiosaurEgg = new Egg(DinoEncyclopedia.BRACHIOSAUR);
            actor.addItemToInventory(brachiosaurEgg);
            EcoPoints.decrementEcoPoints(500);
            System.out.println("Brachio egg Get!");
        }
        else if (choice == 6 && ecoPoints >= 1000){
            Egg allosaurEgg = new Egg(DinoEncyclopedia.ALLOSAUR);
            actor.addItemToInventory(allosaurEgg);
            EcoPoints.decrementEcoPoints(1000);
            System.out.println("Allo egg Get!");
        }
        else if (choice == 7 && ecoPoints >= 1000){
            Egg pterodactylEgg = new Egg(DinoEncyclopedia.PTERODACTYL);
            actor.addItemToInventory(pterodactylEgg);
            EcoPoints.decrementEcoPoints(1000);
            System.out.println("Ptero egg Get!");
        }
        else if (choice == 8 && ecoPoints >= 500){
            actor.addItemToInventory(new LaserGun());
            EcoPoints.decrementEcoPoints(500);
            System.out.println("Laser gun Get!");
        }
        else if (choice == 0){
            System.out.println("Thank you! Please come again!");
            return false;
        }
        else{
            System.out.println("Please check if your eco points are enough and if your input is from 1-8 or 0 to Exit.\nThank you.");
            return false;
        }
        return true;
    }

    /**
     * Returns false to indicate CapableActors cannot lay an Egg on this ground.
     * @param capableActor A capable actor
     * @return false
     */
    @Override
    public boolean canLayEggHere(CapableActor capableActor){
        return false;
    }

    /**
     * Returns false to indication CapableActors cannot breed on this ground.
     * @param capableActor A capable actor
     * @return false
     */
    @Override
    public boolean canBreedHere(CapableActor capableActor){
        return false;
    }


    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions list = super.allowableActions(actor, location, direction);
        if (actor instanceof Player)
            list.add(new BuyAction());
        return list;
    }
}
