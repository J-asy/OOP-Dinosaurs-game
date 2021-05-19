package game;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import edu.monash.fit2099.engine.*;
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

			List<String> newMap = Arrays.asList(
					"................................................................................",
					"................................................................................",
					".......+++++.........................................................++++.......",
					"........+++++...............................................~.......++++........",
					".......++++........................................................+++++........",
					"......++++......................................................................",
					".......++.......................................................................",
					"................................................................................",
					"................................................................................",
					"................................................................................",
					"................+...............................................................",
					"...............+++..........................~...................................",
					"..............+++++.............................................................",
					"............++++++++...................................................+++......",
					"...........+++++++++....................................................+++.....",
					"..........++++++++++++.................................................+++......",
					"............+++++++++.................................................++++......",
					"...............++++.............................................................",
					".................+..................................................#######.....",
					".............................................++++...................#_____#.....",
					"...........................................+++++++..................._____#.....",
					".....~......................................+++++...................#__X__#.....",
					"............................................++++....................#######.....",
					"..........................................++++..................................",
					"................................................................................");
			GameMap secondMap = new GameMap(groundFactory, newMap);
			world.addGameMap(secondMap);

			Actor player = new Player("Player", '@', 100);
			world.addPlayer(player, gameMap.at(10, 0));

			// Bridges on first map
			TravelItem bridge1 = new TravelItem("Genesis Bridge", '!');
			bridge1.addAction(new MoveActorAction(secondMap.at(19,24), "to Acheulian Park" ));
			gameMap.at(19,0).addItem(bridge1);

			TravelItem bridge2 = new TravelItem("Onyx Bridge", '!');
			bridge2.addAction(new MoveActorAction(secondMap.at(39,24), "to Acheulian Park" ));
			gameMap.at(39,0).addItem(bridge2);

			TravelItem bridge3 = new TravelItem("Anzu Bridge", '!');
			bridge3.addAction(new MoveActorAction(secondMap.at(59,24), "to Acheulian Park" ));
			gameMap.at(59,0).addItem(bridge3);

			//Bridges on second map
			TravelItem bridgeA = new TravelItem("Genesis Bridge", '!');
			bridgeA.addAction(new MoveActorAction(gameMap.at(19, 0), "to Jurassic Park" ));
			secondMap.at(19,24).addItem(bridgeA);

			TravelItem bridgeB = new TravelItem("Onyx Bridge", '!');
			bridgeB.addAction(new MoveActorAction(gameMap.at(39, 0), "to Jurassic Park" ));
			secondMap.at(39,24).addItem(bridgeB);

			TravelItem bridgeC = new TravelItem("Anzu Bridge", '!');
			bridgeC.addAction(new MoveActorAction(gameMap.at(59, 0), "to Jurassic Park" ));
			secondMap.at(59,24).addItem(bridgeC);

			// A small herd of Stegosaur added at the start of the game (first map)
			gameMap.at(48, 11).addActor(new Stegosaur(true));
			gameMap.at(20, 18).addActor(new Stegosaur(true));
			gameMap.at(58, 20).addActor(new Stegosaur(true));
			gameMap.at(58, 9).addActor(new Stegosaur(true));
			gameMap.at(1, 11).addActor(new Stegosaur(true));

			// A small herd of Stegosaur added at the start of the game (second map)
			secondMap.at(48, 11).addActor(new Stegosaur(true));
			secondMap.at(20, 18).addActor(new Stegosaur(true));
			secondMap.at(58, 20).addActor(new Stegosaur(true));
			secondMap.at(58, 9).addActor(new Stegosaur(true));
			secondMap.at(1, 11).addActor(new Stegosaur(true));

			// Two pairs of Brachiosaur, male and female added at the start of the game (first map)
			DinoActor b1 = new Brachiosaur(DinoCapabilities.FEMALE, true);
			gameMap.at(36, 11).addActor(b1);
			DinoActor b2 = new Brachiosaur(DinoCapabilities.MALE, true);
			gameMap.at(35, 11).addActor(b2);
			DinoActor b3 = new Brachiosaur(DinoCapabilities.FEMALE, true);
			gameMap.at(10, 20).addActor(b3);
			DinoActor b4 = new Brachiosaur(DinoCapabilities.MALE, true);
			gameMap.at(30, 10).addActor(b4);

			// Two pairs of Brachiosaur, male and female added at the start of the game (second map)
			DinoActor b5 = new Brachiosaur(DinoCapabilities.FEMALE, true);
			secondMap.at(36, 11).addActor(b5);
			DinoActor b6 = new Brachiosaur(DinoCapabilities.MALE, true);
			secondMap.at(35, 11).addActor(b6);
			DinoActor b7 = new Brachiosaur(DinoCapabilities.FEMALE, true);
			secondMap.at(10, 20).addActor(b7);
			DinoActor b8 = new Brachiosaur(DinoCapabilities.MALE, true);
			secondMap.at(30, 10).addActor(b8);

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

	private static int[] chooseMode(){
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

//		Scanner input = new Scanner(System.in);
		System.out.println("So adventurer, what mode would you like to play?");
		System.out.println("1. Challenge Mode ⚡");
		System.out.println("2. Sandbox Mode 🌈");
		System.out.println("3. Quit T.T");
		int mode = getIntegerInput();

		if (mode == 1){
			System.out.println("Please enter a target Eco Point:");
			int targetEcoPoints = getIntegerInput();
			System.out.println("Please enter number of moves:");
			int numOfMoves = getIntegerInput();

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

	private static int getIntegerInput(){
		Scanner scanner = new Scanner(System.in);
		int userInput = -1;
		boolean errorOccurred = true;
		do{
			try{
				userInput = Integer.parseInt(scanner.nextLine());
				errorOccurred = false;
			}
			catch (NumberFormatException e){
				System.out.println("Please enter a number.");
			}
		} while (errorOccurred);
		return userInput;
	}
}
