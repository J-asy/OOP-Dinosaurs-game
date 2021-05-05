package game.player;

import game.PortableItem;

/**
 * Class represent Meal Kits that can be fed to the dinosaurs.
 */
public class MealKitsItem extends PortableItem {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public MealKitsItem(String name, char displayChar) {
        super(name, displayChar);
    }
}
