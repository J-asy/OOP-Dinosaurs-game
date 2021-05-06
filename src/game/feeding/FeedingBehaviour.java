package game.feeding;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.FoodType;
import game.attack.Corpse;
import game.dinosaurs.DinoCapabilities;
import game.dinosaurs.Egg;
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

        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            List<Item> items = destination.getItems();

            for (Item item: items){
                if (item.hasCapability(FoodType.HERBIVORE) &&
                        (actor.hasCapability(DinoCapabilities.HERBIVORE))) {
                    if (item instanceof Fruit) {
                        return new FeedingAction(true,item, destination.x(), destination.y());
                    }
                }
                else if (item.hasCapability(FoodType.CARNIVORE) && (actor.hasCapability(DinoCapabilities.CARNIVORE))) {
                    if (item instanceof Egg || item instanceof Corpse) {
                        return new FeedingAction(true,item, destination.x(), destination.y());
                    }
                }
            }

            if (((exit.getDestination().getGround().hasCapability(TerrainType.TREE)) || (exit.getDestination().getGround().hasCapability(TerrainType.BUSH))) && actor.hasCapability(DinoCapabilities.HERBIVORE)) {
                return new FeedingAction(false,null, destination.x(), destination.y());

            }

        }
        return null;
    }
}