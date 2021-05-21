package game.player;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.FoodItem;
import game.dinosaurs.CapableActor;

/**
 * Class represent Meal Kits that can be fed to the dinosaurs.
 */
public class MealKitsItem extends FoodItem {

    /**
     * The amount of food points that should be incremented for the DinoActor
     * if a herbivore meal kit is eaten.
     */
    private final static int HERBIVORE_HEAL_POINTS = 100;

    /**
     * The amount of food points that should be incremented for the DinoActor
     * if a carnivore meal kit is eaten.
     */
    private final static int CARNIVORE_HEAL_POINTS = 500;

    /***
     * Constructor.
     *  @param name the name of this Item
     */
    public MealKitsItem(String name) {
        super(name, '=');
        setSmallSize();
    }

    /**
     * Initialize the heal points of the vegetarian meal kit for herbivores
     * and adds necessary capabilities by calling the super class's method
     */
    @Override
    public void setForHerbivores() {
        setHealPoints(HERBIVORE_HEAL_POINTS);
        super.setForHerbivores();
    }

    /**
     * Initialize the heal points of the carnivore meal kit for carnivores
     * and adds necessary capabilities by calling the super class's method
     */
    @Override
    public void setForCarnivores() {
        setHealPoints(CARNIVORE_HEAL_POINTS);
        super.setForCarnivores();
    }

    /**
     * Returns true if the meal kit can be eaten by the CapableActor with the
     * appropriate diet, false otherwise.
     * @param capableActor a CapableActor
     * @param location location that the CapableActor is on
     * @return true if meal kit suitable to be eaten by CapableActor, false otherwise
     */
    @Override
    public boolean canEat(CapableActor capableActor, Location location) {
        return capableActor.isHerbivorous() && edibleByHerbivores() ||
                capableActor.isCarnivorous() && edibleByCarnivores();
    }

}
