package game.feeding;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Behaviour;
import game.Food;
import game.dinosaurs.DinoActor;

public class FeedOnActorBehaviour implements Behaviour {

    private final DinoActor target;

    public FeedOnActorBehaviour(DinoActor target) {
        this.target = target;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor instanceof DinoActor && target instanceof Food){
            DinoActor dinoActor = (DinoActor) actor;
            Food food = (Food) target;
            Location actorLocation = map.locationOf(actor);
            if (food.canEat(dinoActor, actorLocation)){
                return new FeedingAction(food);
            }
        }
        return null;
    }
}
