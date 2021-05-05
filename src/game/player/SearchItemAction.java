package game.player;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.environment.*;
import game.Probability;

public class SearchItemAction extends Action {

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
