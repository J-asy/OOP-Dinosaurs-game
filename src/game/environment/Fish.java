package game.environment;

import edu.monash.fit2099.engine.Location;
import game.FoodItem;
import game.dinosaurs.CapableActor;

public class Fish extends FoodItem {

    private static final int FISH_HEAL_POINTS = 5;

    public Fish() {
        super("Fish", 'Q');
        setForCarnivores();
        setHealPoints(FISH_HEAL_POINTS);
    }

    @Override
    public boolean canEat(CapableActor capableActor, Location location) {
        return capableActor.isCarnivorous();
    }


}

