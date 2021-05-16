package game.environment;

import edu.monash.fit2099.engine.Location;
import game.FoodItem;
import game.dinosaurs.CapableActor;

/**
 * Class that represents the Fruit object.
 */
public class Fruit extends FoodItem {
    private int groundTime;
    private final static int FRUIT_HEAL_POINTS = 10;

    /**
     * Constructor
     *
     */
    public Fruit(){
        super("Fruit",'F');
        groundTime = 0;
        setForHerbivores();
        setHealPoints(FRUIT_HEAL_POINTS);
    }

    /**
     * Informs the item on the ground about the passage of time.
     * The time when the Fruit is on the ground is being keep track of,
     * @param currentLocation the location of the ground on which the Fruit lies.
     */
    @Override
    public void tick(Location currentLocation) {
        groundTime++;

        if (groundTime > 15){
            currentLocation.removeItem(this);
        }

    }

    @Override
    public boolean canEat(CapableActor capableActor, Location location) {
        return capableActor.isHerbivorous() && !capableActor.canReachTree();
    }

}
