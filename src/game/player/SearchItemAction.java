package game.player;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import game.EcoPoints;
import game.Probability;
import game.environment.FertileGround;
import game.environment.Fruit;

/**
 * Special Action for Player to search for Fruit on specific terrain (Tree, Bush).
 */
public class SearchItemAction extends Action {

    /**
     * Perform the Action
     * A probability is generated to see if Player has the luck to find a fruit on the current location. If yes,
     * the type of the terrain on the current location is checked to ensure that it is either a Bush or a Tree. The
     * found fruit is then added to the inventory of Player and Player earns some EcoPoints.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return empty string
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Ground ground = map.locationOf(actor).getGround();

        Fruit fruit = null;
        int earnedPoints = 0;
        String groundDescription = null;
        if (ground instanceof FertileGround && Probability.generateProbability(0.4f)){

            FertileGround fertileGround = (FertileGround) ground;
            if (fertileGround.isBush()){
                fruit = fertileGround.getFruit();
                groundDescription = "bush";
                earnedPoints = 10;
            }
            else if (fertileGround.isTree()){
                fruit = fertileGround.getFruit();
                groundDescription = "tree";
                earnedPoints = 10;
            }
        }

        if (fruit != null){
            actor.addItemToInventory(fruit);
            EcoPoints.incrementEcoPoints(earnedPoints);
            return "Fruit found from " + groundDescription + "!";
        }
        else
            return "Oops! No fruit found. Better luck next time ~";
    }


    @Override
    public String menuDescription(Actor actor) {

        return actor + " searches for fruit.";
    }
}
