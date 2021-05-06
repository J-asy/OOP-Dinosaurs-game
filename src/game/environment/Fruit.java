package game.environment;

import edu.monash.fit2099.engine.Location;
import game.FoodType;
import game.PortableItem;

/**
 * Class that represents the Fruit object.
 */
public class Fruit extends PortableItem {
    private int groundTime;

    /**
     * Constructor
     *
     */
    public Fruit(){
        super("Fruit",'F');
        groundTime = 0;
        addCapability(FoodType.HERBIVORE);
    }

    /**
     * Used to access the groundTime.
     * @return the time the Fruit has spent on the ground
     */
    public int getGroundTime() { return groundTime; }

    /**
     * Informs the item on the ground about the passage of time.
     * The time where the Fruit is on the ground is being keep track of,
     * @param currentLocation the location of the ground on which the Fruit lies.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        groundTime++;

        if (groundTime > 15){
            currentLocation.removeItem(this);
        }

    }

//    public void setDisplayChar(Character displayChar){this.displayChar = displayChar;}
}
