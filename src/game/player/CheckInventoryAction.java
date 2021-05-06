package game.player;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

import java.util.List;

public class CheckInventoryAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        List<Item> inventoryItems = actor.getInventory();
        if (inventoryItems.size() > 0) {
            System.out.println("Here's your list of Inventory items.");
            for (int i = 0; i < inventoryItems.size(); i++) {
                System.out.println((i + 1) + ". " + inventoryItems.get(i).toString());
            }
            return "";
        }
        else
            return "Inventory is empty.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Inventory";
    }
}
