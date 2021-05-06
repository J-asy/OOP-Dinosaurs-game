package game.feeding;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.PortableItem;
import game.dinosaurs.DinoActor;
import game.environment.*;

import java.util.List;

/**
 * Simulates the feeding behaviour of an Actor.
 */
public class FeedingBehaviour implements Behaviour {

    public FeedingBehaviour () { }

    /***
     * Checks if the Actor fulfills the following conditions:
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a new FeedingAction if it is possible for a dinosaur to feed, null otherwise.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (actor instanceof DinoActor) {
            DinoActor actorAsDino = (DinoActor) actor;
            Location actorLocation = map.locationOf(actor);

            for (Exit exit : actorLocation.getExits()) {
                Location destination = exit.getDestination();
                Ground ground = destination.getGround();

                for (Item item : destination.getItems()) {
                    if (item instanceof PortableItem) {
                        PortableItem portableItem = (PortableItem) item;
                        if (portableItem.edibleByHerbivores() && actorAsDino.isHerbivorous()) {
                            return new FeedingAction(true, portableItem);
                        } else if (portableItem.edibleByCarnivores() && actorAsDino.isCarnivorous()) {
                            return new FeedingAction(true, portableItem);
                        }
                    }
                }

                if (ground instanceof CapableGround && actorAsDino.isHerbivorous()) {
                    CapableGround capableGround = (CapableGround) ground;
                    if (capableGround.hasFruits() && ((capableGround.isTree() && actorAsDino.canReachTree())
                            || (capableGround.isBush() && !actorAsDino.canReachTree()))) {
                        return new FeedingAction(false, null);
                    }
                }
            }
        }
        return null;
    }
}
