package game.follow;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.DinoActor;
import game.environment.CapableGround;

public class FollowFoodOnTreeBehaviour extends FollowBehaviour {

    private static final String DESCRIPTION = "find food on plants";
    private static final int MIN_RADIUS = 1;
    private static final int MAX_RADIUS = 5;

    public FollowFoodOnTreeBehaviour() {
        super(DESCRIPTION, MIN_RADIUS, MAX_RADIUS);
    }

    @Override
    Location follow(GameMap map, Location destination, DinoActor actorAsDino){
        Location returnDestination = null;

        Ground ground = destination.getGround();

        if (ground instanceof CapableGround){
            CapableGround currentGround = (CapableGround) ground;
            if (currentGround.isTree() && actorAsDino.isHerbivorous() && actorAsDino.canReachTree()){
                returnDestination = destination;
            }
            else if (currentGround.isBush() && actorAsDino.isHerbivorous() && !actorAsDino.canReachTree()) {
                returnDestination = destination;
            }
        }
        return returnDestination;
    }
}
