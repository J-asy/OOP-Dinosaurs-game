package game.attack;

import java.util.Random;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Weapon;
import game.dinosaurs.Allosaur;
import game.dinosaurs.DinoActor;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	private DinoActor target;
	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(DinoActor target) {
		this.target = target;
	}

	/**
	 * Performs the action
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a message string
	 */
	@Override
	public String execute(Actor actor, GameMap map) {

		Weapon weapon = actor.getWeapon();

		if (rand.nextBoolean()) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();

		// Adds stegosaur to Allosaur's victim HashMap only after attacking it
		if (actor instanceof Allosaur) {
			Allosaur actorAsAllosaur = (Allosaur) actor;
			if (!actorAsAllosaur.hasAttackedVictim(target)) {
				actorAsAllosaur.addAttackedVictims(target);
			}
		}

		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";

		actor.heal(damage);
		target.hurt(damage);
		if (target.getHitPoints() <= 0){
			Item corpse = new Corpse(target.getDinoType());
			map.locationOf(target).addItem(corpse);
			map.removeActor(target);

			Actions dropActions = new Actions();
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction());
			for (Action drop : dropActions)		
				drop.execute(target, map);
			map.removeActor(target);	
			
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}


	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target;
	}
}
