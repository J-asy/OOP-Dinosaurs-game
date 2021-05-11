package game.player;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.dinosaurs.DinoActor;

/**
 * Simulates the behaviour of the Player feeding the DinoActors - feeding may or may not
 * occur depending on whether certain criteria is met.
 */
public class FedByPlayerBehaviour implements Behaviour {

    /**
     * The Actor that is to be fed.
     */
    private final DinoActor target;

    /**
     * Constructor
     * @param target DinoActor to feed
     */
    public FedByPlayerBehaviour(DinoActor target) {
        this.target = target;
    }

    /**
     * Returns PlayerFeedAction if the actor is allowed to feed the target by checking
     * necessary conditions, otherwise null is returned.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return PlayerFeedAction if the actor can feed the target, otherwise return null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor instanceof Player) {
            return new PlayerFeedAction(target);
        }
        return null;
    }
}
