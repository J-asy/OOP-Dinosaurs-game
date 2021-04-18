package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		".....#######....................................................................",
		".....#_____#....................................................................",
		".....#_____#....................................................................",
		".....###.###....................................................................",
		"........+.......................................................................",
		"......................................+++.......................................",
		".......................................++++.....................................",
		"...................................+++++........................................",
		".....................................++++++.....................................",
		"......................................+++.......................................",
		".....................................+++........................................",
		"................................................................................",
		"............+++.................................................................",
		".............+++++..............................................................",
		"...............++........................................+++++..................",
		".............+++....................................++++++++....................",
		"............+++.......................................+++.......................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new GameMap(groundFactory, map);
		world.addGameMap(gameMap);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(9, 4));

		// Place a pair of stegosaurs in the middle of the map
		gameMap.at(9, 3).addActor(new Stegosaur("Stegosaur", 'f'));
		gameMap.at(32, 12).addActor(new Stegosaur("Stegosaur", 'm'));

		// Add a small herd of brachiosaur in the middle of the map
		ArrayList<Integer> x_placements = new ArrayList<>(Arrays.asList(50, 52, 40, 42));
		ArrayList<Integer> y_placements = new ArrayList<>(Arrays.asList(6, 6, 18, 18));
		char brachiosaurSex = 'm';
		for (int i = 0; i < x_placements.size(); i++){
			if (i < x_placements.size() / 2){
				brachiosaurSex = 'f';
			}
			gameMap.at(x_placements.get(i), y_placements.get(i)).addActor(new Brachiosaur("Brachiosaur", brachiosaurSex));
		}

		world.run();
	}


}
