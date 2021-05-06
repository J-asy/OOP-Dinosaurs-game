package game.follow;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.DinoActor;

public class FollowVictimBehaviour extends FollowBehaviour {

    private static final String DESCRIPTION = "find victim to attack";
    private static final int MIN_RADIUS = 2;
    private static final int MAX_RADIUS = 4;

    public FollowVictimBehaviour() {
        super(DESCRIPTION, MIN_RADIUS, MAX_RADIUS);
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Action actionToReturn = null;
        if (actor instanceof DinoActor){
            if (((DinoActor) actor).isHungry()){
                actionToReturn = super.getAction(actor, map);
            }
        }
        return actionToReturn;
    }

    @Override
     Location follow(GameMap map, Location destination, DinoActor actorAsDino){
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
