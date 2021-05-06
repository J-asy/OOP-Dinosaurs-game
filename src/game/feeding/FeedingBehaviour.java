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
            System.out.println("print3");
            DinoActor actorAsDino = (DinoActor) actor;
            Location actorLocation = map.locationOf(actor);
            Ground ground = actorLocation.getGround();

            for (Item item : actorLocation.getItems()) {
//                System.out.println("???");
                if (item instanceof PortableItem) {
                    PortableItem portableItem = (PortableItem) item;
                    if (portableItem.edibleByHerbivores() && actorAsDino.isHerbivorous()) {
//                        System.out.println("ener1");
                        return new FeedingAction(true, portableItem);
                    } else if (portableItem.edibleByCarnivores() && actorAsDino.isCarnivorous()) {
//                        System.out.println("enter 2");
                        return new FeedingAction(true, portableItem);
                    }
                }
            }

            System.out.println("print2!!!");
            if (ground instanceof CapableGround && actorAsDino.isHerbivorous()) {
                CapableGround capableGround = (CapableGround) ground;
                if (capableGround.hasFruits() && ((capableGround.isTree() && actorAsDino.canReachTree())
                        || (capableGround.isBush() && !actorAsDino.canReachTree()))) {
                    System.out.println("print!!");
                    return new FeedingAction(false, null);
                }
            }

        }
        return null;
    }
}
