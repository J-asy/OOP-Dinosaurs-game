package game.follow;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.DinoActor;
import game.environment.CapableGround;

public class FollowFoodOnTreeBehaviour extends FollowBehaviour {

    private static final String DESCRIPTION = "find food on tree";

    public FollowFoodOnTreeBehaviour() {
        super(DESCRIPTION);
    }

    @Override
    Location follow(GameMap map, Location destination, DinoActor actorAsDino){
        Location returnDestination = null;

        Ground ground = destination.getGround();

        if (ground instanceof CapableGround){
            CapableGround currentGround = (CapableGround) ground;
            if ((currentGround.isTree() || currentGround.isBush()) && actorAsDino.isHerbivorous()) {
                returnDestination = destination;
            }
        }
        return returnDestination;
    }
}
