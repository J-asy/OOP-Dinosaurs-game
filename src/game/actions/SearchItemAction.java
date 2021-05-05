package game.actions;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.environment.Bush;
import game.environment.Fruit;
import game.environment.TerrainType;
import game.environment.Tree;
import game.utility.Probability;

public class SearchItemAction extends Action {

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
