package game.follow;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
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

        if (returnDestination != null)
            System.out.println("x1: " + returnDestination.x() + "; y1: " + returnDestination.y());
        return returnDestination;
    }
}
