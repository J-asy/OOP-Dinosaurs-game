package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.FancyGroundFactory;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;
import game.attack.Corpse;
import game.dinosaurs.*;
import game.environment.*;
import game.player.Player;
import game.player.VendingMachine;

/**
 * The main class for the Jurassic World game.
 *
 */
public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(),new VendingMachine());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		".....#######....................................................................",
		".....#__X__#....................................................................",
		".....#_____#....................................................................",
		".....###.###....................................................................",
		"................................................................................",
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
		GameMap gameMap = new GameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(9, 4));
		
		// Place a pair of stegosaurs in the middle of the map
//		gameMap.at(30, 12).addActor(new Stegosaur(Sex.FEMALE));

		DinoActor s1 = new Allosaur(DinoCapabilities.MALE, true);
		gameMap.at(35, 13).addActor(s1);
		DinoActor s2 = new Allosaur(DinoCapabilities.FEMALE, true);
		gameMap.at(36, 13).addActor(s2);


//		gameMap.at(1,3).addActor(new Stegosaur(true));
//		DinoActor b = new Brachiosaur(DinoCapabilities.FEMALE, true);
//		gameMap.at(1,1).addActor(b);
//		DinoActor a = new Allosaur(true);
//		gameMap.at(1,5).addActor(a);

		gameMap.at(35, 13).addItem(new Egg(DinoEncyclopedia.BRACHIOSAUR));

//		gameMap.at(1, 11).addItem(new Egg(DinoEncyclopedia.STEGOSAUR));
//		gameMap.at(1, 15).addItem(new Egg(DinoEncyclopedia.BRACHIOSAUR));
//		gameMap.at(1, 18).addItem(new Egg(DinoEncyclopedia.ALLOSAUR));

		world.run();
	}
}
