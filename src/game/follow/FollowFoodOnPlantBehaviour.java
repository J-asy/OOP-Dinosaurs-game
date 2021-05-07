package game.follow;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.DinoActor;
import game.environment.CapableGround;

/**
 * Special class that simulates behaviour of a herbivorous DinoActor
 * moving towards a Tree or Bush to eat its fruits.
 */
public class FollowFoodOnPlantBehaviour extends FollowBehaviour {

    /**
     * Description of the motivation of the behaviour to be printed out
     * as a message in the console if the Actor actually moves towards
     * plants that have fruits in the end.
     */
    private static final String DESCRIPTION = "find food on plants";

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
    public FollowFoodOnPlantBehaviour() {
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
    public boolean motivatedToFollow(DinoActor dinoActor) {
        return dinoActor.isHungry() && dinoActor.isHerbivorous();
    }

    /**
     * Checks if there is a plant with fruits that the DinoActor can eat from
     * at location destination. If there is a plant that it can feed from,
     * the destination will be returned for the DinoActor to move towards it,
     * null will be returned otherwise.
     * @param map GameMap that the Actor is currently on
     * @param destination Location that is checked for a plant with fruits that DinoActor can eat from
     * @param actorAsDino DinoActor that is trying to find plants with fruits
     * @return A location if there is a plant with fruits that DinoActor can eat from, null otherwise
     */
    @Override
    Location findTarget(GameMap map, Location destination, DinoActor actorAsDino){
        Location returnDestination = null;

        Ground ground = destination.getGround();

        if (ground instanceof CapableGround){
            CapableGround currentGround = (CapableGround) ground;
            if (currentGround.isTree() && currentGround.hasFruits() && actorAsDino.canReachTree()){
                returnDestination = destination;
            }
            else if (currentGround.isBush() && currentGround.hasFruits() && !actorAsDino.canReachTree()) {
                returnDestination = destination;
            }
        }

        return returnDestination;
    }
}
