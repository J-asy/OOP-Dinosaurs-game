package game.environment;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Probability;
import game.dinosaurs.CapableActor;


/**
 * A class that represents bare dirt and grows Bush on it.
 */
public class Dirt extends Ground {

	public Dirt() { super('.'); }

	/**
	 * Updates the dirt/bush every turn.
	 * On every tick, the surrounding Exits are checked to find out the terrain type of each Exits. If there are Tree
	 * surrounding the current location, no Bush is grown. If not, Bush has 0.5% chance to grow, if there are more than 1
	 * Bush surrounding it, the Dirt will have 5% to grown a new Bush.
	 *
	 * @param location the location of the Dirt
	 */
	@Override
	public void tick(Location location) {
		int surroundingBushes = 0;  // how many bushes are surrounding this piece of dirt
		boolean adjacentTree = false;  // true if there is at least one Tree surrounding this piece of dirt

		// Checks the ground around it (up, down, left, right, diagonal) to count the surrounding trees and bushes
		for (Exit elem : location.getExits()) {
			Ground groundType = elem.getDestination().getGround();
			if (groundType instanceof FertileGround) {
				FertileGround fertileGround = (FertileGround) groundType;
				if (fertileGround.isBush()) {
					surroundingBushes++;
				} else if (fertileGround.isTree()) {
					adjacentTree = true;
					break;
				}
			}
		}

		//if there are no trees and this square is still just bare dirt, grow the bushes according to the probability
		if (!adjacentTree) {
			if ((surroundingBushes >= 2 && Probability.generateProbability(0.05f)) ||
					Probability.generateProbability(0.005f)){
				location.setGround(new Bush());
			}
		}
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
