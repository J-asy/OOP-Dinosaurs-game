package game.movement;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.*;
import game.Behaviour;

/**
 * Simulates the wandering behaviour of DinoActors - wandering may or may not
 * occur depending on whether certain criteria is met.
 */
public class WanderBehaviour implements Behaviour {

	/**
	 * An instance of Random class
	 */
	private Random random = new Random();


	/**
	 * Returns a DynamicMoveAction to wander to a random location, if possible.
	 * If no movement is possible, returns null.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<>();
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
            	actions.add(new DynamicMoveAction(destination, "around"));
            }
        }
		
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}

	}

}
