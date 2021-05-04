package game.feeding;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.FoodType;
import game.attack.Corpse;
import game.dinosaurs.Egg;
import game.environment.Bush;
import game.environment.Fruit;
import game.environment.Tree;

import java.util.List;

public class FeedingBehaviour implements Behaviour {

    public FeedingBehaviour () { }

    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location here = map.locationOf(actor);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            List<Item> items = destination.getItems();

            for (Item item: items){
                if (item.hasCapability(FoodType.HERBIVORE) &&
                        (actor.hasCapability(FoodType.HERBIVORE))) {
                    if (item instanceof Fruit) {
                        return new FeedingAction(true,item, destination.x(), destination.y());
                    }
                }
                else if (item.hasCapability(FoodType.CARNIVORE) && (actor.hasCapability(FoodType.CARNIVORE))) {
                    if (item instanceof Egg || item instanceof Corpse) {
                        return new FeedingAction(true,item, destination.x(), destination.y());
                    }
                }
            }

            if ((exit.getDestination().getGround() instanceof Tree) ||
                    (exit.getDestination().getGround() instanceof Bush) && actor.hasCapability(FoodType.HERBIVORE)) {
                return new FeedingAction(false,null, destination.x(), destination.y());

            }

        }



        return null;
    }
}
