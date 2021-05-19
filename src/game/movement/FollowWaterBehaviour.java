package game.movement;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.DinoActor;
import game.environment.DrinkingGround;

/**
 * Simulates the behaviour of a DinoActor moving toward water when it is thirsty.
 */
public class FollowWaterBehaviour extends FollowBehaviour {

    /**
     * Description of the motivation of the behaviour to be printed out
     * as a message in the console if the Actor actually moves towards
     * water in the end.
     */
    private static final String DESCRIPTION = "find water";

    /**
     * Minimum number of squares from DinoActor to start searching for water to move towards.
     */
    private static final int MIN_RADIUS = 1;

    /**
     * Maximum number of squares from DinoActor to search for water to move towards.
     */
    private static final int MAX_RADIUS = 10;

    /**
     * Constructor.
     */
    public FollowWaterBehaviour() {
        super(DESCRIPTION, MIN_RADIUS, MAX_RADIUS);
    }

    /**
     * Returns true if the DinoActor is thirsty hence wants to find water,
     * returns false otherwise.
     * @param dinoActor DinoActor that is acting
     * @return true if DinoActor is thirsty, false otherwise
     */
    @Override
    boolean motivatedToFind(DinoActor dinoActor) {
        return dinoActor.isThirsty();
    }

    /**
     * Returns the Location destination if it is an instance of DrinkingGround,
     * hence potentially has water, otherwise returns null.
     * @param map GameMap that the DinoActor is on
     * @param destination Location that is checked for target
     * @param dinoActor DinoActor that is finding its target to follow
     * @return location that is an instance of DrinkingGround, null otherwise
     */
    @Override
    Location findTarget(GameMap map, Location destination, DinoActor dinoActor) {
        Location returnDestination = null;
        Ground ground = destination.getGround();
        if (ground instanceof DrinkingGround) {
            returnDestination = destination;
        }
        return returnDestination;
    }
}
