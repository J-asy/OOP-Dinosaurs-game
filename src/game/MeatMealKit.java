package game;

public class MeatMealKit extends MealKitsItem {
    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public MeatMealKit(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }
}
