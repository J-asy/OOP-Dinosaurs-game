package game.feeding;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.PortableItem;
import game.dinosaurs.DinoActor;
import game.environment.*;


/**
 * Simulates the feeding behaviour of an Actor.
 */
public class FeedingBehaviour implements Behaviour {

    public FeedingBehaviour() {}

    /***
     * Checks if the Actor fulfills the following conditions:
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a new FeedingAction if it is possible for a dinosaur to feed, null otherwise.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (actor instanceof DinoActor) {
            DinoActor dinoActor = (DinoActor) actor;
            Location actorLocation = map.locationOf(actor);
            Ground ground = actorLocation.getGround();

            for (Item item : actorLocation.getItems()) {
                if (item instanceof PortableItem) {
                    PortableItem portableItem = (PortableItem) item;
                    if (eatFruitOnGround(dinoActor, portableItem) || eatMeatOnGround(dinoActor, portableItem)) {
                        return new FeedingAction(true, portableItem);
                    }
                }
            }

            if (ground instanceof CapableGround && dinoActor.isHerbivorous()) {
                CapableGround capableGround = (CapableGround) ground;
                if (capableGround.hasFruits() && ((capableGround.isTree() && dinoActor.canReachTree())
                        || (capableGround.isBush() && !dinoActor.canReachTree()))) {
                    return new FeedingAction(false, new Fruit());
                }
            }
        }

        return null;
    }

    /**
     * Is dino a herbivore and is item edible by only herbivores?
     * @param dinoActor DinoActor doing the action
     * @param item edible portable item
     * @return true if dinosaur is a herbivore and item is edible by herbivores, false otherwise
     */
    private static boolean eatFruitOnGround(DinoActor dinoActor, PortableItem item) {
        return dinoActor.isHerbivorous() && item.edibleByHerbivores();
    }

    /**
     * Is dino a carnivore and is item edible by carnivores?
     * @param dinoActor DinoActor doing the action
     * @param item edible portable item
     * @return true if dinosaur is a carnivore and item is edible by carnivores, false otherwise
     */
    private static boolean eatMeatOnGround(DinoActor dinoActor, PortableItem item) {
        return dinoActor.isCarnivorous() && item.edibleByCarnivores();
    }

}
