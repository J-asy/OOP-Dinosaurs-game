package game.actions;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.environment.Bush;
import game.environment.Fruit;
import game.environment.TerrainType;
import game.environment.Tree;
import game.utility.Probability;

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
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Ground ground = map.locationOf(actor).getGround();

        if (Probability.generateProbability(0.4f)){
            if (ground.hasCapability(TerrainType.BUSH)){
                Fruit fruit = ((Bush) ground).decrementBushItem();
                actor.addItemToInventory(fruit);
                EcoPoints.incrementEcoPoints(10);
                System.out.println("Fruit found from bush!");
            }
            else if (ground.hasCapability(TerrainType.TREE)){
                Fruit fruit = ((Tree) ground).decrementTreeItem();
                actor.addItemToInventory(fruit);
                EcoPoints.incrementEcoPoints(10);
                System.out.println("Fruit found from tree!");
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
