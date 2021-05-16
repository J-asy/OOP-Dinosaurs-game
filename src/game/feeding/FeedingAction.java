package game.feeding;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Food;
import game.dinosaurs.DinoActor;

public class FeedingAction extends Action {

    /**
     * portable item that can be eaten
     */
    private final Food food;

    public FeedingAction(Food food) {
        this.food = food;
    }

    /**
     * Performs the action
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a message string
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
     * Returns the string format of actor's name
     * @param actor The actor performing the action.
     * @return String of actor's name
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
