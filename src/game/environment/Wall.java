package game.environment;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import game.dinosaurs.CapableActor;

/**
 * Represents a Wall that Actors cannot pass through.
 */
public class Wall extends Ground {

	/**
	 * Constructor.
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Returns false since no Actor can pass through a wall.
	 * @param actor the Actor to check
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * Returns true since objects cannot be thrown over it.
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	/**
	 * Returns false since no CapableActor should be able to lay an Egg on top of it.
	 * @param capableActor A capable actor
	 * @return false
	 */
	@Override
	public boolean canLayEggHere(CapableActor capableActor){
		return false;
	}

	/**
	 * Returns false since no CapableActor should be able to breed on top of it.
	 * @param capableActor A capable actor
	 * @return false
	 */
	@Override
	public boolean canBreedHere(CapableActor capableActor){
		return false;
	}
}
