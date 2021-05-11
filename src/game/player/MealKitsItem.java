package game.player;

import game.PortableItem;

/**
 * Class represent Meal Kits that can be fed to the dinosaurs.
 */
public class MealKitsItem extends PortableItem {
    /***
     * Constructor.
     *  @param name the name of this Item
     */
    public MealKitsItem(String name) {
        super(name, '=');
    }
}
