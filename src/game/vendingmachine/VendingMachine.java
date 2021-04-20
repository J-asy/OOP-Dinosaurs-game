package game.vendingmachine;

import edu.monash.fit2099.engine.Actor;
import game.*;
import game.environment.Fruit;

class VendingMachine {

    public static boolean choose(int choice, Actor actor){

        int ecoPoints = EcoPoints.getEcoPoints();

        if (choice == 1 && ecoPoints >= 30){
            Fruit fruit = new Fruit("fruit",
                    actor.getDisplayChar(),true);
            fruit.addCapability(FoodType.HERBIVORE);
            actor.addItemToInventory(fruit);
            EcoPoints.decrementEcoPoints(30);
            System.out.println("Fruit Get!");
        }
        else if (choice == 2 && ecoPoints >= 100){
            MealKitsItem vegeMealKit = new MealKitsItem("VegetarianMealKit",
                    actor.getDisplayChar(), true);
            vegeMealKit.addCapability(FoodType.HERBIVORE);
            actor.addItemToInventory(vegeMealKit);
            EcoPoints.decrementEcoPoints(100);
            System.out.println("Vege meal kit Get!");
        }
        else if (choice == 3 && ecoPoints >= 500){
            MealKitsItem meatMealKit = new MealKitsItem("CarnivoreMealKit",
                    actor.getDisplayChar(), true);
            meatMealKit.addCapability(FoodType.CARNIVORE);
            actor.addItemToInventory(meatMealKit);
            EcoPoints.decrementEcoPoints(500);
            System.out.println("Carnivore meal kit Get!");
        }
        else if (choice == 4 && ecoPoints >= 200){
            StegosaurEgg stegosaurEgg = new StegosaurEgg("StegoEgg",
                    actor.getDisplayChar(),true);
            stegosaurEgg.addCapability(FoodType.CARNIVORE);
            actor.addItemToInventory(stegosaurEgg);
            EcoPoints.decrementEcoPoints(200);
            System.out.println("Stego egg Get!");
        }
        else if (choice == 5 && ecoPoints >= 500){
            BrachiosaurEgg brachiosaurEgg = new BrachiosaurEgg("BrachioEgg",
                    actor.getDisplayChar(),true);
            brachiosaurEgg.addCapability(FoodType.CARNIVORE);
            actor.addItemToInventory(brachiosaurEgg);
            EcoPoints.decrementEcoPoints(500);
            System.out.println("Brachio egg Get!");
        }
        else if (choice == 6 && ecoPoints >= 1000){
            AllosaurEgg allosaurEgg = new AllosaurEgg("AlloEgg",
                    actor.getDisplayChar(),true);
            allosaurEgg.addCapability(FoodType.CARNIVORE);
            actor.addItemToInventory(allosaurEgg);
            EcoPoints.decrementEcoPoints(1000);
            System.out.println("Allo egg Get!");
        }
        else if (choice == 7 && ecoPoints >= 500){
            actor.addItemToInventory(new LaserGun());
            EcoPoints.decrementEcoPoints(500);
            System.out.println("Laser gun Get!");
        }
        else if (choice == 0){
            System.out.println("Thank you! Please come again!");
            return false;
        }
        else{
            System.out.println("Please check if your eco points are enough and if your input is from 1-7 or 0 to Exit.\nThank you.");
            return false;
        }
        return true;
    }
}
