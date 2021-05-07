package game.follow;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.DinoActor;

/**
 * Special class that simulates behaviour of a DinoActor following another
 * DinoActor to mate with it.
 */
public class FollowMateBehaviour extends FollowBehaviour {

    /**
     * Description of the motivation of the behaviour to be printed out
     * as a message in the console if the Actor actually follows a mate
     * in the end.
     */
    private static final String DESCRIPTION = "find mate";

    /**
     * Minimum number of squares from DinoActor to start searching for a mate to follow.
     */
    private static final int MIN_RADIUS = 2;

    /**
     * Maximum number of squares from DinoActor to search for a mate to follow.
     */
    private static final int MAX_RADIUS = 4;

    /**
     * Constructor.
     */
    public FollowMateBehaviour() {
        super(DESCRIPTION, MIN_RADIUS, MAX_RADIUS);
    }

    /**
     * Returns true if the DinoActor can breed to indicate that
     * it will attempt to follow another DinoActor to mate with it,
     * false otherwise.
     * @param dinoActor DinoActor that is acting
     * @return true if DinoActor can breed, false otherwise
     */
    @Override
    public boolean motivatedToFollow(DinoActor dinoActor) {
        return dinoActor.canBreed();
    }

    /**
     * Checks if there is a mate that the DinoActor can follow at the
     * location destination. If there is a mate that it can breed with,
     * the destination will be returned for the DinoActor to move towards it,
     * null will be returned otherwise.
     * @param map GameMap that the Actor is currently on
     * @param destination Location that is checked for a potential mate
     * @param actorAsDino DinoActor that is trying to follow a mate
     * @return A location if a potential mate to follow is on it, null otherwise
     */
    @Override
     Location findTarget(GameMap map, Location destination, DinoActor actorAsDino){
        Location returnDestination = null;

        if (map.isAnActorAt(destination)) {
            Actor nearbyActor = destination.getActor();

            if (nearbyActor instanceof DinoActor) {
                DinoActor targetAsDino = (DinoActor) nearbyActor;

                if (breedingPossible(actorAsDino, targetAsDino)) {
                    returnDestination = destination;
                }

            }
        }
        return returnDestination;
    }


    /**
     * Returns true if two DinoActors passed in are of different sex, same species and
     * both capable to breed to indicate it is possible for mating to happen, false otherwise.
     * @param actorAsDino DinoActor
     * @param anotherDino another DinoActor
     * @return true if the DinoActors can breed, false otherwise
     */
    private static boolean breedingPossible(DinoActor actorAsDino, DinoActor anotherDino){
        boolean differentSex = anotherDino.getSex() != actorAsDino.getSex();
        boolean sameSpecies = actorAsDino.getDinoType() == anotherDino.getDinoType();
        boolean bothCanBreed = anotherDino.canBreed() && actorAsDino.canBreed();
        return differentSex && sameSpecies && bothCanBreed;
    }


}