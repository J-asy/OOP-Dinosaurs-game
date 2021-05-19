package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Item;

public class TravelItem extends Item {

    public TravelItem(String name, char displayChar){
        super(name, displayChar, false );
    }

    public void addAction(Action action) {
        this.allowableActions.add(action);
    }

}
