package game.environment;

import edu.monash.fit2099.engine.*;
import game.FoodType;
import game.PortableItem;

public class Fruit extends PortableItem {
    private int groundTime;

    public Fruit(Character displayChar){
        super("Fruit",displayChar);
        groundTime = 0;
        addCapability(FoodType.HERBIVORE);
    }

    public int getGroundTime() { return groundTime; }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        groundTime++;

    }

}
