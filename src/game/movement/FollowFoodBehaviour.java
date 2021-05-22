package game.movement;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.Food;
import game.dinosaurs.DinoActor;

/**
 * Special class that simulates behaviour of a DinoActor moving towards
 * an instance of Food that it can eat.
 */
public class FollowFoodBehaviour extends FollowBehaviour {

    /**
     * Description of the motivation of the behaviour to be printed out
     * as a message in the console if the Actor actually moves towards
     * Food in the end.
     */
    private static final String DESCRIPTION = "find food";

    /**
     * Minimum number of squares from DinoActor to start searching for
     * Food to move towards.
     */
    private static final int MIN_RADIUS = 1;

    /**
     * Maximum number of squares from DinoActor to search for
     * Food to move towards.
     */
    private static final int MAX_RADIUS = 10;

    /**
     * Constructor.
     */
    public FollowFoodBehaviour() {
        super(DESCRIPTION, MIN_RADIUS, MAX_RADIUS);
    }

    /**
     * Returns true if the DinoActor is hungry to indicate that it will
     * attempt to follow Food around it, false otherwise.
     * @param dinoActor DinoActor that is acting
     * @return true if DinoActor is hungry, false otherwise
     */
    @Override
     boolean motivatedToFind(DinoActor dinoActor) {
        return dinoActor.isHungry();
    }

    /**
     * Checks if there is Food that the DinoActor can eat at location destination.
     * If there is, the destination will be returned for the DinoActor to move towards it,
     * null will be returned otherwise.
     * @param map GameMap that the Actor is currently on
     * @param destination Location that is checked for Food
     * @param dinoActor DinoActor that is trying to find Food
     * @return A location if there is Food that DinoActor can eat, null otherwise
     */
    @Override
    Location findTarget(GameMap map, Location destination, DinoActor dinoActor){

        // check if there is food item on the ground
        for (Item item : destination.getItems()) {
            if (item instanceof Food) {
                if (((Food)item).canEat(dinoActor, destination)) {
                    return destination;
                }
            }
        }

        // check if there is food growing (fruit) / living (fish) on the ground
        Ground ground = destination.getGround();
        if (ground instanceof Food){
            if (((Food)ground).canEat(dinoActor, destination)) {
                return destination;
            }
        }

        // check if there is a food Actor (Pterodactyl) that carnivore can eat
        if (map.isAnActorAt(destination)){
            if (destination.getActor() instanceof Food){
                if (((Food)destination.getActor()).canEat(dinoActor, destination)){
                    return destination;
                }
            }
        }

        return null;
    }


}


