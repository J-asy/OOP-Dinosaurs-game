package game.feeding;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Food;
import game.dinosaurs.DinoActor;

/**
 * Simulates the DinoActor feeding on a FoodItem that is on the ground
 * or feeding on food that grows on the Ground.
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
     * Performs the feeding action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return description of feeding action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof DinoActor) {
            DinoActor dinoActor = (DinoActor) actor;
            Location actorLocation = map.locationOf(actor);
            int healPoints = food.eat(map, actorLocation, dinoActor.getBiteSize());
            actor.heal(healPoints);
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
        String feedingDescription;
        if (food == null){
            feedingDescription = actor.toString() + " does not eat anything.";
        }
        else {
            feedingDescription = actor.toString() + " eats " + food.foodName();
        }
        return feedingDescription;
    }

}
