package game.player;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * Special Action for Player to choose to Quit Game.
 */
public class QuitGameAction extends Action {

    /**
     * Player is removed from the map and message to quit game is output.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message to indicate that Player chose to quit game
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        return "Player chose to " + menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "QUIT GAME";
    }
}
