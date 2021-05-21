package game.feeding;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Behaviour;
import game.Food;
import game.dinosaurs.DinoActor;

/**
 * Simulates a DinoActor feeding on another DinoActor that is small enough to be
 * devoured whole.
 */
public class FeedOnActorBehaviour implements Behaviour {

    /**
     * Target dinoActor that is potentially devoured whole.
     * It can be devoured whole if it is an instance of Food.
     */
    private final DinoActor target;

    /**
     * Constructor.
     * @param target target dinoActor that is potentially devoured whole
     */
    public FeedOnActorBehaviour(DinoActor target) {
        this.target = target;
    }

    /**
     * Returns FeedingAction if the target can be devoured whole (if it is an instance of Food),
     * returns null otherwise.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return FeedingAction if target can be devoured whole, null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor instanceof DinoActor && target instanceof Food){
            DinoActor dinoActor = (DinoActor) actor;
            Food food = (Food) target;
            Location targetLocation = map.locationOf(target);
            if (food.canEat(dinoActor, targetLocation)){
                return new FeedingAction(food);
            }
        }
        return null;
    }
}
