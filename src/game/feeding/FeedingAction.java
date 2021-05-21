package game.feeding;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Food;
import game.dinosaurs.DinoActor;

/**
 * Special action for the DinoActor to feed on a FoodItem that is on the ground
 * or feed on food that grows on the Ground.
 */
public class FeedingAction extends Action {

    /**
     * Instance of Food, including FoodItem that can be eaten (Fruit, Egg, Corpse, Fish, MealKitsItem)
     * or Ground that DinoActor can feed from (Tree, Bush and Lake).
     */
    private final Food food;

    /**
     * Constructor.
     * @param food instance of Food
     */
    public FeedingAction(Food food) {
        this.food = food;
    }

    /**
     * Performs the feeding action, increments the food level of the DinoActor appropriately,
     * and returns a description of the feeding action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return description of feeding action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int healPoints = 0;
        if (actor instanceof DinoActor) {
            DinoActor dinoActor = (DinoActor) actor;
            Location actorLocation = map.locationOf(actor);
            healPoints = food.eat(map, actorLocation, dinoActor.getBiteSize());
            actor.heal(healPoints);
        }

        if (healPoints <= 0){
            return actor.toString() + " did not manage to eat " + food.foodName();
        }
        return menuDescription(actor);
    }

    /**
     * Returns the description of the feeding action if actor eats something.
     * @param actor The actor performing the feeding action.
     * @return description of feeding action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " eats " + food.foodName();
    }

}
