package game;

import edu.monash.fit2099.engine.Item;

/**
 * Base class for any item that can be picked up and dropped.
 */
public abstract class PortableItem extends Item {

    /**
     * Constructor.
     * @param name name of the Item
     * @param displayChar display character of the Item to be shown on the terminal
     */
    public PortableItem(String name, char displayChar) {
        super(name, displayChar, true);
    }

}
