package game.follow;

import edu.monash.fit2099.engine.*;
import game.dinosaurs.DinoActor;
import game.environment.CapableGround;

public class FollowFoodOnPlantBehaviour extends FollowBehaviour {

    private static final String DESCRIPTION = "find food on plants";
    private static final int MIN_RADIUS = 1;
    private static final int MAX_RADIUS = 5;

    public FollowFoodOnPlantBehaviour() {
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

        Ground ground = destination.getGround();

        if (ground instanceof CapableGround && actorAsDino.isHerbivorous()){
            CapableGround currentGround = (CapableGround) ground;
            if (currentGround.isTree() && currentGround.hasFruits() && actorAsDino.canReachTree()){
                returnDestination = destination;
            }
            else if (currentGround.isBush() && currentGround.hasFruits() && !actorAsDino.canReachTree()) {
                returnDestination = destination;
            }
        }

        return returnDestination;
    }
}
