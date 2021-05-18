package game;

import edu.monash.fit2099.engine.*;
import game.environment.Rain;

import java.util.Scanner;

public class JurassicWorld extends World {

    private int targetMoves = 0;
    private int targetEcoPoints = 0;
    private int mode = 0;

    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public JurassicWorld(Display display) {
        super(display);
    }

    public GameMap getGameMap(int index){
        return this.gameMaps.get(index);
    }

    @Override
    public void run(){
        if (player == null)
            throw new IllegalStateException();

        // initialize the last action map to nothing actions;
        for (Actor actor : actorLocations) {
            lastActionMap.put(actor, new DoNothingAction());
        }

        int totalMoves = 0;
        // This loop is basically the whole game
        while (stillRunning()) {

            String clouds = "\n";
            for (int i = 0; i<57; i++){
                clouds+="☁";
            }
            System.out.println(clouds);

            //Rain or no rain
            Rain.rainingOrNot();

            GameMap playersMap = actorLocations.locationOf(player).map();
            playersMap.draw(display);

            //Show eco points
            System.out.println("ECO points: " + EcoPoints.getEcoPoints());

            // Process all the actors.
            for (Actor actor : actorLocations) {
                if (stillRunning())
                    processActorTurn(actor);
            }

            // Tick over all the maps. For the map stuff.
            for (GameMap gameMap : gameMaps) {
                gameMap.tick();
            }

            totalMoves++;
            if (mode == 1) {
                int ecoPoints = EcoPoints.getEcoPoints();
                if (totalMoves <= targetMoves && ecoPoints >= targetEcoPoints) {
                    //win
                    System.out.println("\n-------------TARGET MET! YOU WIN!!!-----------------");
                    actorLocations.remove(player);
                } else if (totalMoves >= targetMoves && ecoPoints < targetEcoPoints) {
                    //lose
                    System.out.println("\n-------------TARGET NO MET! YOU LOSE!-----------------");
                    actorLocations.remove(player);
                }
            }

            if (!stillRunning()){
                EcoPoints.decrementEcoPoints(EcoPoints.getEcoPoints());
            }

        }
        display.println(endGameMessage());
    }

    public void setTargetMoves(int targetMoves){
        if (targetMoves > 0){
            this.targetMoves = targetMoves;
        }
    }

    public void setTargetEcoPoints(int targetEcoPoints){
        if (targetEcoPoints >= 0){
            this.targetEcoPoints = targetEcoPoints;
        }
    }

    public void setMode(int mode){
        this.mode = mode;
    }
}
