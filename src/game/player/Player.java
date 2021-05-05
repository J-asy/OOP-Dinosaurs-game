package game.player;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Menu;
import game.EcoPoints;
import game.player.SearchItemAction;
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

		if (map.locationOf(this).getGround().hasCapability(TerrainType.TREE)||
				map.locationOf(this).getGround().hasCapability(TerrainType.BUSH))
			actions.add(new SearchItemAction());

		System.out.println("ECO points: " + EcoPoints.getEcoPoints());

		return menu.showMenu(this, actions, display);

	}
}
