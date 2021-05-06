package game.follow;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.DinoActor;
import game.dinosaurs.DinoCapabilities;

public class FollowMateBehaviour extends FollowBehaviour {

    private static final String DESCRIPTION = "find mate";

    public FollowMateBehaviour() {
        super(DESCRIPTION);
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