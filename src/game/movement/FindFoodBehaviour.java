package game.movement;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.Food;
import game.dinosaurs.DinoActor;

/**
 * Special class that simulates behaviour of a herbivorous DinoActor
 * moving towards a Tree or Bush to eat its fruits.
 */
public class FindFoodBehaviour extends FindBehaviour {

    /**
     * Description of the motivation of the behaviour to be printed out
     * as a message in the console if the Actor actually moves towards
     * plants that have fruits in the end.
     */
    private static final String DESCRIPTION = "find food";

    /**
     * Minimum number of squares from DinoActor to start searching for
     * plants that have fruits to move towards.
     */
    private static final int MIN_RADIUS = 1;

    /**
     * Maximum number of squares from DinoActor to search for plants
     * that have fruits to move towards.
     */
    private static final int MAX_RADIUS = 10;

    /**
     * Constructor.
     */
    public FindFoodBehaviour() {
        super(DESCRIPTION, MIN_RADIUS, MAX_RADIUS);
    }

    /**
     * Returns true if the DinoActor is hungry and is herbivorous
     * to indicate that it will attempt to follow a plant that has fruits,
     * false otherwise.
     * @param dinoActor DinoActor that is acting
     * @return true if DinoActor is hungry and herbivorous, false otherwise
     */
    @Override
     boolean motivatedToFind(DinoActor dinoActor) {
        return dinoActor.isHungry();
    }

    /**
     * Checks if there is a plant with fruits that the DinoActor can eat from
     * at location destination. If there is a plant that it can feed from,
     * the destination will be returned for the DinoActor to move towards it,
     * null will be returned otherwise.
     * @param map GameMap that the Actor is currently on
     * @param destination Location that is checked for a plant with fruits that DinoActor can eat from
     * @param dinoActor DinoActor that is trying to find plants with fruits
     * @return A location if there is a plant with fruits that DinoActor can eat from, null otherwise
     */
    @Override
    Location findTarget(GameMap map, Location destination, DinoActor dinoActor){

        // check if there is food item on the ground
        for (Item item : destination.getItems()) {
            if (item instanceof Food) {
                Food foodItem = (Food) item;
                if (foodItem.canEat(dinoActor, destination)) {
                    return destination;
                }
            }
        }

        // check if there is food growing (fruit) / living(fish) on the ground
        Ground ground = destination.getGround();
        if (ground instanceof Food){
            Food foodGround = (Food) ground;
            if (foodGround.canEat(dinoActor, destination)) {
                return destination;
            }
        }

        // check if there is a food Actor (Pterodactyl)
        if (map.isAnActorAt(destination)){
            if (destination.getActor() instanceof Food){
                Food foodActor = (Food) destination.getActor();
                if (foodActor.canEat(dinoActor, destination)){
                    return destination;
                }
            }
        }

        return null;
    }


}


