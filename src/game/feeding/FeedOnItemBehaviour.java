package game.feeding;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.Food;
import game.dinosaurs.DinoActor;

/**
 * Simulates the feeding behaviour of an Actor on FoodItems and Ground that has food living / growing on it.
 */
public class FeedOnItemBehaviour implements Behaviour {

    /**
     * Constructor.
     */
    public FeedOnItemBehaviour(){}

    /***
     * Returns FeedingAction if it is possible for a dinosaur to feed
     * on FoodItems and Ground that has food living / growing on it, returns
     * null otherwise.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a new FeedingAction if it is possible for a dinosaur to feed, null otherwise.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (actor instanceof DinoActor) {
            DinoActor dinoActor = (DinoActor) actor;
            Location actorLocation = map.locationOf(actor);

            // Check for food item on the ground
            for (Item item : actorLocation.getItems()) {
                if (item instanceof Food) {
                    Food food = (Food) item;
                    if (food.canEat(dinoActor, actorLocation)) {
                        return new FeedingAction(food);
                    }
                }
            }

            // Check for food growing / living on the ground, i.e. fruit or fish
            Ground ground = actorLocation.getGround();
            if (ground instanceof Food){
                Food food = (Food) ground;
                if (food.canEat(dinoActor, actorLocation)){
                    return new FeedingAction(food);
                }
            }

        }
        return null;
    }

}
