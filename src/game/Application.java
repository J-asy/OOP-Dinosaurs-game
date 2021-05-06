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
		world.addPlayer(player, gameMap.at(7, 5));
		player.addItemToInventory(new Fruit());
		
		// Place a pair of stegosaurs in the middle of the map
//		gameMap.at(30, 12).addActor(new Stegosaur(Sex.FEMALE));

//		DinoActor s1 = new Allosaur(DinoCapabilities.FEMALE, true);
//		gameMap.at(36, 15).addActor(s1);
//		DinoActor s2 = new Stegosaur(DinoCapabilities.MALE, false);
//		System.out.println("hp: " + s2.getHitPoints());
//		gameMap.at(31, 15).addActor(s2);


		gameMap.at(30,3).addActor(new Brachiosaur(false));
		gameMap.at(30,2).addActor(new Stegosaur(true));
//		DinoActor b = new Brachiosaur(DinoCapabilities.FEMALE, true);
//		gameMap.at(1,1).addActor(b);
//		DinoActor a = new Allosaur(true);
//		gameMap.at(1,5).addActor(a);

//		gameMap.at(35, 4).addItem(new Fruit());

//		gameMap.at(30, 16).addItem(new Corpse(DinoEncyclopedia.ALLOSAUR));
//		gameMap.at(30, 16).addItem(new Egg(DinoEncyclopedia.BRACHIOSAUR));
//		gameMap.at(1, 18).addItem(new Egg(DinoEncyclopedia.ALLOSAUR));

		world.run();
	}
}
