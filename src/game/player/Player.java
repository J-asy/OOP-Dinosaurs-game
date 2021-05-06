package game.player;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.environment.CapableGround;
import game.environment.TerrainType;

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
