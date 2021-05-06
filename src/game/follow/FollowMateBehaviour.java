package game.follow;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.DinoActor;

public class FollowMateBehaviour extends FollowBehaviour {

    private static final String DESCRIPTION = "find mate";
    private static final int MIN_RADIUS = 2;
    private static final int MAX_RADIUS = 4;

    public FollowMateBehaviour() {
        super(DESCRIPTION, MIN_RADIUS, MAX_RADIUS);
    }

    @Override
     Location follow(GameMap map, Location destination, DinoActor actorAsDino){
        Location returnDestination = null;

        // if there is an actor two squares away
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


    private static boolean breedingPossible(DinoActor actorAsDino, DinoActor targetAsDino){
        boolean differentSex = targetAsDino.getSex() != actorAsDino.getSex();
        boolean sameSpecies = actorAsDino.getDinoType() == targetAsDino.getDinoType();
        boolean bothCanBreed = targetAsDino.canBreed() && actorAsDino.canBreed();
        return differentSex && sameSpecies && bothCanBreed;
    }


}