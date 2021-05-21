package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.CapableActor;

/**
 * Interface to be implemented by all classes that represent Food,
 * including FoodItem, Ground that can grow Food or DinoActors that are small enough to be eaten whole
 */
public interface Food {

    /**
     * Returns name of the Food
     * @return name of the Food
     */
    String foodName();

    /**
     * Returns true if Food at location can be eaten by CapableActor,
     * false otherwise.
     * @param capableActor A CapableActor
     * @param location Location of the Food
     * @return true if Food can be eaten by CapableActor, false otherwise
     */
    boolean canEat(CapableActor capableActor, Location location);

    /**
     * Simulates DinoActor eating Food, and makes the Food disappear if it is eaten completely,
     * finally returns the amount of food points gained by the eating the Food.
     * @param map map the Actor is on
     * @param location location the Actor is at
     * @param biteSize jaw size of the Actor that is trying to eat
     * @return amount of food points gained by eating the Food
     */
    int eat(GameMap map, Location location, int biteSize);

}
