package game;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
		int[] pointsMovesMode;

		do {
			JurassicWorld world = new JurassicWorld(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Tree(), new VendingMachine(), new Lake());

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
			gameMap.at(48, 11).addActor(new Stegosaur(true));
			gameMap.at(20, 18).addActor(new Stegosaur(true));
			gameMap.at(58, 20).addActor(new Stegosaur(true));
			gameMap.at(58, 9).addActor(new Stegosaur(true));
			gameMap.at(1, 11).addActor(new Stegosaur(true));

			// Two pairs of Brachiosaur, male and female added at the start of the game
			DinoActor b1 = new Brachiosaur(DinoCapabilities.FEMALE, true);
			gameMap.at(36, 11).addActor(b1);
			DinoActor b2 = new Brachiosaur(DinoCapabilities.MALE, true);
			gameMap.at(35, 11).addActor(b2);
			DinoActor b3 = new Brachiosaur(DinoCapabilities.FEMALE, true);
			gameMap.at(10, 20).addActor(b3);
			DinoActor b4 = new Brachiosaur(DinoCapabilities.MALE, true);
			gameMap.at(30, 10).addActor(b4);

			pointsMovesMode = chooseMode();
			if (pointsMovesMode[0] != 0 && pointsMovesMode[1] != 0 && pointsMovesMode[2] == 1) {
				world.setTargetEcoPoints(pointsMovesMode[0]);
				world.setTargetMoves(pointsMovesMode[1]);
				world.setMode(pointsMovesMode[2]);
				world.run();
			} else if (pointsMovesMode[2] == 2) {
				world.run();
			} else if (pointsMovesMode[2] == 3) {
				System.out.println("THANK YOU!");
			}

		} while (pointsMovesMode[2] != 3);

	}

	public static int[] chooseMode(){
		System.out.println("\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println(""" 
							    __
							   / _) -- WELCOME to JURASSIC WORLD!
				     _.----._/ /
				    /         /
				 __/ (  | (  |
				/__.- |_|--|_|\s""".indent(1));

		int[] pointsMovesMode = {0,0,0};
		System.out.println("=================================================================");
		System.out.println("""
				Instruction
				This is a game where you'll run a dinosaur park.
				You must care for the dinosaurs and maintain an ecological balance.
				
				There are two modes to choose from:
				
				 🔥 Challenge Mode 🔥 \s
				- You can choose a number of moves and a number of eco points.
				- You win if you get the specified number of eco points within the specified 
				  number of moves, and lose if you do not.
				  
				 ☀ SandBox Mode ☀ 
				- You can run the dinosaur park normally until you feel like quitting.
				  There is no win or lose in this mode.
				
				Note: You can quit the game whenever you like. 
				""");
		System.out.println("=================================================================");

		Scanner input = new Scanner(System.in);
		System.out.println("So adventurer, what mode would you like to play?");
		System.out.println("1. Challenge Mode ⚡");
		System.out.println("2. Sandbox Mode 🌈");
		System.out.println("3. Quit T.T");
		int mode = input.nextInt();

		if (mode == 1){
			System.out.println("Please enter a target Eco Point:");
			int targetEcoPoints = input.nextInt();
			System.out.println("Please enter number of moves:");
			int numOfMoves = input.nextInt();

			if (targetEcoPoints >= 0 && numOfMoves > 0) {
				pointsMovesMode[0] = targetEcoPoints;
				pointsMovesMode[1] = numOfMoves;
				pointsMovesMode[2] = 1;
			}

			return pointsMovesMode;
		}
		else if (mode == 2){
			pointsMovesMode[2] = 2;
		}

		else if (mode == 3){
			pointsMovesMode[2] = 3;
		}

		return pointsMovesMode;
	}
}
