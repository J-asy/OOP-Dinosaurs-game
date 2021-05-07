package game.player;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.dinosaurs.DinoActor;

public class FedByPlayerBehaviour implements Behaviour {

    /**
     * The Actor that is to be fed.
     */
    private final DinoActor target;

    public FedByPlayerBehaviour(DinoActor target) {
        this.target = target;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor instanceof Player) {
            return new PlayerFeedAction(target);
        }
        return null;
    }
}
