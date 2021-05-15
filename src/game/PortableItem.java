package game;

import edu.monash.fit2099.engine.Item;

public abstract class PortableItem extends Item {

    public PortableItem(String name, char displayChar) {
        super(name, displayChar, true);
    }

}
