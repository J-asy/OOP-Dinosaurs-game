package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;

import java.util.ArrayList;
import java.util.List;

public class SearchItemAction extends Action {

    String direction;

    public SearchItemAction(String direction) {
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        List<Exit> exits = map.locationOf(actor).getExits();
        if (Probability.generateProbability(0.4f)){

            for (Exit exit: exits) {

                if (exit.getName().equalsIgnoreCase(direction)) {
                    if (exit.getDestination().getGround() instanceof Bush) {
                        Fruit fruit = ((Bush) exit.getDestination().getGround()).decrementBushItem();
//                        if (fruit != null) {
                        actor.addItemToInventory(fruit);
                        EcoPoints.incrementEcoPoints(10);
                        System.out.println("Fruit found from bush!");
//                        }
                    }
                    else if (exit.getDestination().getGround() instanceof Tree){
                        Fruit fruit = ((Tree) exit.getDestination().getGround()).decrementTreeItem();
//                        if (fruit != null) {
                        actor.addItemToInventory(fruit);
                        EcoPoints.incrementEcoPoints(10);
                        System.out.println("Fruit found from tree!");
//                        }
                    }
                    break;
                }
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
