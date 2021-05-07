package game.follow;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.DinoActor;

/**
 * Special class that simulates behaviour of a DinoActor following another
 * DinoActor to attack it for food.
 */
public class FollowVictimBehaviour extends FollowBehaviour {

    /**
     * Description of the motivation of the behaviour to be printed out
     * as a message in the console if the Actor actually follows the victim
     * in the end.
     */
    private static final String DESCRIPTION = "find victim to attack";

    /**
     * Minimum number of squares from DinoActor to start searching for a victim to follow.
     */
    private static final int MIN_RADIUS = 2;

    /**
     * Maximum number of squares from DinoActor to search for a victim to follow.
     */
    private static final int MAX_RADIUS = 4;

    /**
     * Constructor.
     */
    public FollowVictimBehaviour() {
        super(DESCRIPTION, MIN_RADIUS, MAX_RADIUS);
    }

    /**
     * Returns true if the DinoActor is hungry to indicate that
     * it will attempt to follow another DinoActor to attack it for food,
     * false otherwise.
     * @param dinoActor DinoActor that is acting
     * @return true if DinoActor is hungry, false otherwise
     */
    @Override
    public boolean motivatedToFollow(DinoActor dinoActor) {
        return dinoActor.isHungry();
    }

    /**
     * Checks if there is a victim that the DinoActor can follow at the
     * location destination. If there is a victim that can be attacked,
     * the destination will be returned for the DinoActor to move towards it,
     * null will be returned otherwise.
     * @param map GameMap that the Actor is currently on
     * @param destination Location that is checked for a potential victim
     * @param actorAsDino DinoActor that is trying to follow a victim
     * @return A location if a potential victim to follow is on it, null otherwise
     */
    @Override
     Location findTarget(GameMap map, Location destination, DinoActor actorAsDino){
        Location returnDestination = null;

        if (map.isAnActorAt(destination)) {
            Actor nearbyActor = destination.getActor();

            if (nearbyActor instanceof DinoActor) {
                DinoActor targetAsDino = (DinoActor) nearbyActor;

                if (actorAsDino.canAttack() && targetAsDino.canBeAttacked()) {
                    returnDestination = destination;
                }

            }
        }
        return returnDestination;
    }

}
