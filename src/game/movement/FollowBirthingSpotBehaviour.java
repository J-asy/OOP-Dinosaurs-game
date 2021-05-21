package game.movement;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.DinoActor;

/**
 * Simulates behaviour of a pregnant DinoActor that needs to lay an egg searching
 * for a spot to lay its egg on.
 */
public class FollowBirthingSpotBehaviour extends FollowBehaviour {

    /**
     * Description of the motivation of the behaviour to be printed out
     * as a message in the console if the Actor actually searches for a
     * spot to lay an egg in the end.
     */
    private static final String DESCRIPTION = "find spot to lay egg";

    /**
     * Minimum number of squares from the DinoActor to start searching for a
     * spot to lay an egg on.
     */
    private static final int MIN_RADIUS = 1;

    /**
     * Maximum number of squares from the DinoActor to search for
     * a spot to lay an egg on.
     */
    private static final int MAX_RADIUS = 10;

    /**
     * Constructor.
     */
    public FollowBirthingSpotBehaviour() {
        super(DESCRIPTION, MIN_RADIUS, MAX_RADIUS);
    }

    /**
     * Returns true if a pregnant DinoActor is due to give birth, false otherwise.
     * @param dinoActor DinoActor that is acting
     * @return true if DinoActor is due to give birth, false otherwise
     */
    @Override
    boolean motivatedToFind(DinoActor dinoActor) {
        return dinoActor.isPregnant();
    }

    /**
     * Checks if the DinoActor can lay an egg on destination,
     * If yes, the destination will be returned for the DinoActor to move towards it,
     * null will be returned otherwise.
     * @param map GameMap that the Actor is currently on
     * @param destination Location that is checked whether its suitable to lay an egg on
     * @param dinoActor DinoActor that is trying to lay an egg
     * @return A location if the DinoActor can lay an egg there, false otherwise.
     */
    @Override
    Location findTarget(GameMap map, Location destination, DinoActor dinoActor) {
        if (destination.getGround().canLayEggHere(dinoActor)){
            return destination;
        }
        return null;
    }
}
