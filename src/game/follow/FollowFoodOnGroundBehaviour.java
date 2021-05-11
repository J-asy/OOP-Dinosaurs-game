package game.follow;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.PortableItem;
import game.dinosaurs.DinoActor;

import java.util.List;

/**
 * Special class that simulates behaviour of a DinoActor
 * moving towards food it spotted on the ground.
 */
public class FollowFoodOnGroundBehaviour extends FollowBehaviour {

    /**
     * Description of the motivation of the behaviour to be printed out
     * as a message in the console if the Actor actually moves towards
     * food on the ground in the end.
     */
    private static final String DESCRIPTION = "find food on ground";

    /**
     * Minimum number of squares from DinoActor to start searching for
     * food on the ground to move towards.
     */
    private static final int MIN_RADIUS = 1;

    /**
     * Maximum number of squares from DinoActor to search for
     * food on the ground to move towards.
     */
    private static final int MAX_RADIUS = 10;

    /**
     * Constructor.
     */
    public FollowFoodOnGroundBehaviour() {
        super(DESCRIPTION, MIN_RADIUS, MAX_RADIUS);
    }

    /**
     * Returns true if the DinoActor is hungry and
     * to indicate that it will attempt to move towards food on the ground,
     * false otherwise.
     * @param dinoActor DinoActor that is acting
     * @return true if DinoActor is hungry, false otherwise
     */
    @Override
     boolean motivatedToFollow(DinoActor dinoActor) {
        return dinoActor.isHungry();
    }

    /**
     * Checks if there is food on the ground that the DinoActor can eat
     * at location destination. If there is something it can eat,
     * the destination will be returned for the DinoActor to move towards it,
     * null will be returned otherwise.
     * @param map GameMap that the Actor is currently on
     * @param destination Location that is checked for food on the ground that the DinoActor can eat
     * @param actorAsDino DinoActor that is trying to find plants with fruits
     * @return A location if there is food on the ground that DinoActor can eat, null otherwise
     */
    @Override
    Location findTarget(GameMap map, Location destination, DinoActor actorAsDino){
        Location returnDestination = null;
        List<Item> groundItems = destination.getItems();

        for (Item item : groundItems){
            if (item instanceof PortableItem) {
                PortableItem currentItem = (PortableItem) item;
                if (actorAsDino.isHerbivorous() && currentItem.edibleByHerbivores() && !actorAsDino.canReachTree()||
                        actorAsDino.isCarnivorous() && currentItem.edibleByCarnivores()) {
                    returnDestination = destination;
                    break;
                }
            }
        }
        
        return returnDestination;
    }


}
