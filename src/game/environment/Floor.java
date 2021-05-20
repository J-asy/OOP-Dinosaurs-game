package game.environment;

import edu.monash.fit2099.engine.Ground;
import game.dinosaurs.CapableActor;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	public Floor() {
		super('_');
	}

	/**
	 * Returns true if the CapableActor can lay an Egg on Floor,
	 * which is when the CapableActor is arboreal (does not live on trees),
	 * returns false otherwise.
	 * @param capableActor A capable actor
	 * @return true if the CapableActor can lay an Egg on Floor, false otherwise
	 */
	@Override
	public boolean canLayEggHere(CapableActor capableActor){
		return !capableActor.isArboreal();
	}

	/**
	 * Returns true if the CapableActor can breed on Floor,
	 * which is when the CapableActor is not arboreal (does not live on trees),
	 * returns false otherwise.
	 * @param capableActor A capable actor
	 * @return true if the CapableActor can breed on Floor, false otherwise
	 */
	@Override
	public boolean canBreedHere(CapableActor capableActor){
		return !capableActor.isArboreal();
	}

}
