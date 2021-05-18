package game.follow;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.DinoActor;
import game.environment.DrinkingGround;

public class FollowWaterBehaviour extends FollowBehaviour{

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

    @Override
    boolean motivatedToFollow(DinoActor dinoActor) {
        return dinoActor.isThirsty();
    }

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
