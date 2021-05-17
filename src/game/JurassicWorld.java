package game;

import edu.monash.fit2099.engine.*;
import game.environment.Rain;

public class JurassicWorld extends World {
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

        }
        display.println(endGameMessage());
    }
}
