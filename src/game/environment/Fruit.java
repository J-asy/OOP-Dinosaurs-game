package game.environment;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.FoodType;

public class Fruit extends Item {
    private int groundTime;

    public Fruit(String name, char displayChar, boolean portable){
        super(name,displayChar,portable);
        groundTime = 0;
        hasCapability(FoodType.HERBIVORE);
    }

    //used when fruit falls, portable will == true
    public void setPortability(boolean portability){
        this.portable = portability;
    }

    public int getGroundTime() { return groundTime; }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        groundTime++;

    }
}
