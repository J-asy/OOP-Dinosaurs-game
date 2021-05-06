package game.follow;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.DinoActor;
import game.dinosaurs.DinoCapabilities;

public class FollowVictimBehaviour extends FollowBehaviour {

    private static final String DESCRIPTION = "find victim to attack";

    public FollowVictimBehaviour() {
        super(DESCRIPTION);
    }

    @Override
     Location follow(GameMap map, Location destination, DinoActor actorAsDino){
        Location returnDestination = null;

        if (map.isAnActorAt(destination)) {
            Actor nearbyActor = destination.getActor();

            if (nearbyActor instanceof DinoActor) {
                DinoActor targetAsDino = (DinoActor) nearbyActor;

                if (attackingPossible(actorAsDino, targetAsDino)) {
                    returnDestination = destination;
                }

            }
        }
        return returnDestination;
    }

    private static boolean attackingPossible(DinoActor actorAsDino, DinoActor nearbyActor){
        boolean canAttack = actorAsDino.canAttack();
        boolean canBeAttacked = nearbyActor.canBeAttacked();
        return canAttack && canBeAttacked;
    }

}
