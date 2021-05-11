package game.player;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.environment.CapableGround;

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

	/**
	 * Returns a menu with the list of Actions that the Player can perform and asks for user input to select an Action
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return menu with the list of Actions that the Plater can perform
	 */
	@Override
	public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		actions.add(new CheckInventoryAction());

		Ground ground = map.locationOf(this).getGround();

		if (ground instanceof CapableGround) {
			CapableGround capableGround = (CapableGround) ground;
			if (capableGround.isTree() || capableGround.isBush())
				actions.add(new SearchItemAction());
		}
		System.out.println("ECO points: " + EcoPoints.getEcoPoints());

		return menu.showMenu(this, actions, display);

	}
}
