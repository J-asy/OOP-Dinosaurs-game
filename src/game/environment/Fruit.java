package game.environment;

import edu.monash.fit2099.engine.Location;
import game.FoodItem;
import game.dinosaurs.CapableActor;

/**
 * Class that represents the Fruit object.
 */
public class Fruit extends FoodItem {

    /**
     * The number of turns that the Fruit spent being on the ground.
     */
    private int groundTime;

    /**
     * The amount of food points that can be gained by eating
     * a fruit on the ground.
     */
    private final static int FRUIT_HEAL_POINTS = 10;

    /**
     * Constructor.
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

    /**
     *
     * @param capableActor A CapableActor
     * @param location Location of the Food
     * @return true if the CapableActor can eat a Fruit on the ground
     */
    @Override
    public boolean canEat(CapableActor capableActor, Location location) {
        return capableActor.isHerbivorous() && !capableActor.canReachTree();
    }

}
