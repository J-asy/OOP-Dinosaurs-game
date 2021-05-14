package game.follow;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.DinoActor;

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
     *
     * @param description purpose description of the motivation to perform following action
     * @param min_radius  minimum number of squares from DinoActor too start searching for
     *                    something to follow
     * @param max_radius  maximum number of squares from DinoActor to search for something
     */
    public FollowWaterBehaviour(String description, int min_radius, int max_radius) {
        super(description, min_radius, max_radius);
    }

    @Override
    boolean motivatedToFollow(DinoActor dinoActor) {
        return false;
    }

    @Override
    Location findTarget(GameMap map, Location destination, DinoActor actorAsDino) {
        return null;
    }
}
