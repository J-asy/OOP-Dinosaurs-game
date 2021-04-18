package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends DinoActor {

	private static final int HUNGRY_WHEN = 90;

	/**
	 * Constructor.
	 * All Stegosaurs are represented by a 's' and have 50 hit points.
	 *
	 * @param name the name of this Stegosaur
	 */
	public Stegosaur(String name, char sex) {
		super(name, 's', 50, 100, sex);
	}

	@Override
	public void roarIfHungry() {
		if (getFoodLevel() < HUNGRY_WHEN) {
			System.out.println(getName() + " getting hungry! ");  // add location?
		}
	}

	@Override
	public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
		Actions list = super.getAllowableActions(otherActor, direction, map);
		list.add(new AttackAction(this));
		return list;
	}
}

