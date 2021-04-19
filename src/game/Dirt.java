package game;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;


/**
 * A class that represents bare dirt and grow bushes on it.
 */
public class Dirt extends Ground {

	public Dirt() { super('.'); }

	/**
	 * Updates the dirt/bush every turn. "Lets the dirt/bush experience the joy of time XD \(^-^)/"
	 * @param location The location of the Ground
	 */
	@Override
	public void tick(Location location) {
		int surroundingBushes = 0;  // how many bushes are surrounding this piece of dirt
		boolean adjacentTree = false;  // true if there is at least one Tree surrounding this piece of dirt

		// Checks the ground around it (up, down, left, right, diagonal) to count the surrounding trees and bushes
		for (Exit elem : location.getExits()) {
			Ground groundType = elem.getDestination().getGround();

			if (groundType instanceof Bush) {
				surroundingBushes++;
			}
			else if (groundType instanceof Tree) {
				adjacentTree = true;
				break;
			}
		}

		//if there are no trees and this square is still just bare dirt, grow the bushes according to the probability
		if (!adjacentTree) {
			if ((surroundingBushes >= 2 && Probability.generateProbability(0.1f)) ||
					Probability.generateProbability(0.01f)){
				location.setGround(new Bush());
			}
		}
	}



}