package game.player;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.environment.*;
import game.Probability;

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
        if (ground instanceof CapableGround && Probability.generateProbability(0.4f)){
            CapableGround capableGround = (CapableGround) ground;
            if (capableGround.isBush()){
                fruit = ((Bush) ground).decrementBushItem();
                groundDescription = "bush";
                earnedPoints = 10;
            }
            else if (capableGround.isTree()){
                fruit = ((Tree) ground).decrementTreeItem();
                groundDescription = "tree";
                earnedPoints = 10;
            }

            if (fruit != null){
                actor.addItemToInventory(fruit);
                EcoPoints.incrementEcoPoints(earnedPoints);
                System.out.println("Fruit found from " + groundDescription + "!");
            }
        }

        else
            System.out.println("Oops! No fruit found. Better luck next time ~");

        return "";
    }


    @Override
    public String menuDescription(Actor actor) {

        return actor + " searches for fruit.";
    }
}
