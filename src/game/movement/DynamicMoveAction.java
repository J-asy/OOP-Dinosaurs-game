package game.movement;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

/**
 * Special Action class that simulates Actors moving using appropriate types of movement.
 */
public class DynamicMoveAction extends MoveActorAction {

    /**
     * Name of movement that the Actor uses.
     * The default mode of movement for all Actors is walking.
     */
    private String movementType = "walks";

    /**
     * Constructor.
     * @param moveToLocation location that the Actor will move to
     * @param direction direction that the Actor will move in
     */
    public DynamicMoveAction(Location moveToLocation, String direction) {
        super(moveToLocation, direction);
    }

    /**
     * Simulates the special movement of the Actor if it has energy to do so and
     * executes its special activities while using the special movement. If the actor
     * does not have special movements, the default "walking" is used.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return description of the moving action describing the type of movement used
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof DynamicMovement){
            DynamicMovement dynamicMover = (DynamicMovement)actor;
            movementType = dynamicMover.getMovement();

            if (dynamicMover.isSpecialMovementUsed()) {
                dynamicMover.activityDuringSpecialMovement(map, moveToLocation);
                dynamicMover.depleteEnergy();
            }

        }

        return super.execute(actor, map);
    }

    /**
     * Returns description of the moving action appropriate to the type of movement used.
     * @param actor The actor performing the action.
     * @return description of the moving action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "%s %s %s".formatted(actor, movementType, direction);
    }
}
