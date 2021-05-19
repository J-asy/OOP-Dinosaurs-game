package game.movement;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;


public class DynamicMoveAction extends MoveActorAction {

    // by default every Actor walks
    private String movementType = "walks";

    public DynamicMoveAction(Location moveToLocation, String direction) {
        super(moveToLocation, direction);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof DynamicMovement){
            DynamicMovement dynamicMover = (DynamicMovement)actor;
            movementType = dynamicMover.getMovement();

            if (dynamicMover.useSpecialMovement()) {
                dynamicMover.activityDuringSpecialMovement(map, moveToLocation);
                dynamicMover.depleteEnergy();
            }

        }

        return super.execute(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "%s %s %s".formatted(actor, movementType, direction);
    }
}