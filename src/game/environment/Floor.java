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

	@Override
	public boolean canLayEggHere(CapableActor capableActor){
		return !capableActor.isArboreal();
	}

	@Override
	public boolean canBreedHere(CapableActor capableActor){
		return !capableActor.isArboreal();
	}

}
