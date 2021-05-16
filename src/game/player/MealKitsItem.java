package game.player;

import edu.monash.fit2099.engine.Location;
import game.FoodItem;
import game.dinosaurs.CapableActor;

/**
 * Class represent Meal Kits that can be fed to the dinosaurs.
 */
public class MealKitsItem extends FoodItem {

    private final static int HERBIVORE_HEAL_POINTS = 100;
    private final static int CARNIVORE_HEAL_POINTS = 500;

    /***
     * Constructor.
     *  @param name the name of this Item
     */
    public MealKitsItem(String name) {
        super(name, '=');
    }

    @Override
    public void setForHerbivores() {
        setHealPoints(HERBIVORE_HEAL_POINTS);
        super.setForHerbivores();
    }

    @Override
    public void setForCarnivores() {
        setHealPoints(CARNIVORE_HEAL_POINTS);
        super.setForCarnivores();
    }

    @Override
    public boolean canEat(CapableActor capableActor, Location location) {
        return capableActor.isHerbivorous() && edibleByHerbivores() ||
                capableActor.isCarnivorous() && edibleByCarnivores();
    }
}
