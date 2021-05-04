package game;

import edu.monash.fit2099.engine.*;
import game.vendingmachine.BuyAction;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

	private Menu menu = new Menu();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)

			return lastAction.getNextAction();

		actions.add(new BuyAction());
		System.out.println("ECO points: " + EcoPoints.getEcoPoints());

		return menu.showMenu(this, actions, display);
	}
}

//allowable actions ->  feed, -- ??
// 						pickup fruit from ground, /
// 						pick from bush or tree(60% fail), /
// 						kill, /
// 						buy from vending machine, /
//        class ->	eco points
