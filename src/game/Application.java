package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.*;
import game.attack.Corpse;
import game.dinosaurs.*;
import game.environment.Dirt;
import game.environment.Floor;
import game.environment.Fruit;
import game.environment.Tree;
import game.environment.Wall;
import game.player.Player;

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

//		DinoActor s1 = new Stegosaur(DinoCapabilities.MALE, true);
//		gameMap.at(32, 12).addActor(s1);

		DinoActor a1 = new Stegosaur(DinoCapabilities.MALE, true);
		gameMap.at(26, 8).addActor(a1); // Place an Allosaur in the map

//		gameMap.at(32,11).addItem(new Fruit()); //Add fruit next to stego

		//ADDING CORPSES BESIDE ALLO
//		gameMap.at(32,11).addItem(new Corpse(DinoEncyclopedia.STEGOSAUR));
//		gameMap.at(32, 11).addItem(new Corpse(DinoEncyclopedia.BRACHIOSAUR));
//		gameMap.at(32, 11).addItem(new Corpse(DinoEncyclopedia.ALLOSAUR));

//		DinoActor s2 = new Stegosaur(Sex.FEMALE, true);
//		gameMap.at(32, 10).addActor(s2);


//		gameMap.at(1,3).addActor(new Stegosaur(true));
//		DinoActor b = new Brachiosaur(Sex.FEMALE, true);
//		gameMap.at(1,1).addActor(b);
//		DinoActor a = new Allosaur(true);
//		gameMap.at(1,5).addActor(a);

//		gameMap.at(1, 11).addItem(new Egg(DinoEncyclopedia.STEGOSAUR));
//		gameMap.at(1, 15).addItem(new Egg(DinoEncyclopedia.BRACHIOSAUR));
//		gameMap.at(1, 18).addItem(new Egg(DinoEncyclopedia.ALLOSAUR));

		world.run();
	}
}
