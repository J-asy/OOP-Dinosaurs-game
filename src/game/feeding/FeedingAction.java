package game.feeding;

import edu.monash.fit2099.engine.*;
import game.FoodItem;
import game.dinosaurs.DinoActor;
import game.environment.FeedingGround;

public class FeedingAction extends Action {

    /**
     * Identifier if food is on ground or in plant
     */
    private final Boolean isFoodOnGround;

    /**
     * portable item that can be eaten
     */
    private final FoodItem foodItem;


    /**
     * Constructor
     *
     * @param foodOnGround true if food is on the ground, false if on tree or in bush
     * @param item         edible portable item
     */
    public FeedingAction(Boolean foodOnGround, FoodItem item) {
        this.isFoodOnGround = foodOnGround;
        this.foodItem = item;
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
            int healPoints = 0;
            DinoActor dinoActor = (DinoActor) actor;
            Location actorLocation = map.locationOf(actor);
            if (isFoodOnGround) {
                healPoints = foodItem.getHealPoints();
                actorLocation.removeItem(foodItem);
            }
            else {
                Ground ground = actorLocation.getGround();
                if (ground instanceof FeedingGround) {
                    FeedingGround feedingGround = (FeedingGround) ground;
                    if (feedingGround.canEat(dinoActor)){
                        healPoints = feedingGround.eat();
                    }
                }
            }

            if (healPoints > 0){
                actor.heal(healPoints);
            }

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
        if (foodItem == null){
            feedingDescription = actor.toString() + " does not eat anything.";
        }
        else {
            feedingDescription = actor.toString() + " eats " + foodItem.toString();
        }
        return feedingDescription;
    }

}
