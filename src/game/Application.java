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
//		World world = new World(new Display());
		JurassicWorld world = new JurassicWorld(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(),new VendingMachine(),new Lake());
		
		List<String> map = Arrays.asList(
		"................................................................................",
		"...............................................................~...~............",
		".....#######....................................................................",
		".....#__X__#....................................................................",
		".....#_____#....................................................................",
		".....###.###....................................................................",
		"...................................................................~............",
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
		world.addPlayer(player, gameMap.at(10, 4));

		// A small herd of Stegosaur added at the start of the game
//		gameMap.at(48,11).addActor(new Stegosaur(true));
//		gameMap.at(20,18).addActor(new Stegosaur(true));
//		gameMap.at(58,20).addActor(new Stegosaur(true));
//		gameMap.at(58,9).addActor(new Stegosaur(true));
//		gameMap.at(1,11).addActor(new Stegosaur(true));

		// Two pairs of Brachiosaur, male and female added at the start of the game
//		DinoActor b1 = new Brachiosaur(DinoCapabilities.FEMALE, true);
//		gameMap.at(36, 11).addActor(b1);
//		DinoActor b2 = new Brachiosaur(DinoCapabilities.MALE, true);
//		gameMap.at(35, 11).addActor(b2);
//		DinoActor b3 = new Brachiosaur(DinoCapabilities.FEMALE, true);
//		gameMap.at(10, 20).addActor(b3);
//		DinoActor b4 = new Brachiosaur(DinoCapabilities.MALE, true);
//		gameMap.at(30, 10).addActor(b4);

//		gameMap.at(62,1).addActor(new Stegosaur(true));
//		gameMap.at(62,1).addActor(new Pterodactyl(true));
//		gameMap.at(62,1).addActor(new Allosaur(true));
		gameMap.at(62,1).addActor(new Brachiosaur(true));



		world.run();

	}
}
