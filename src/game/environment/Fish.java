package game.environment;

import edu.monash.fit2099.engine.Location;
import game.FoodItem;
import game.dinosaurs.CapableActor;

/**
 * Fish Item that can be eaten by carnivores.
 */
public class Fish extends FoodItem {

    /**
     * Amount of food points gained by eating a Fish.
     */
    private static final int FISH_HEAL_POINTS = 5;

    /**
     * Constructor.
     */
    public Fish() {
        super("Fish", 'Q');
        setForCarnivores();
        setHealPoints(FISH_HEAL_POINTS);
        setSmallSize();
    }

    /**
     * Returns true if Fish can be eaten, which is if the CapableActor is a carnivore,
     * returns false otherwise.
     * @param capableActor A CapableActor
     * @param location Location of the Food
     * @return true if the Fish can be eaten by the CapableActor, false otherwise
     */
    @Override
    public boolean canEat(CapableActor capableActor, Location location) {
        return capableActor.isCarnivorous();
    }


}

