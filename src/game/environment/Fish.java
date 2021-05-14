package game.environment;

import game.FoodItem;

public class Fish extends FoodItem {

    private static final int FISH_HEAL_POINTS = 5;

    public Fish() {
        super("Fish", 'Q');
        setForCarnivores();
        setHealPoints(FISH_HEAL_POINTS);
    }


}

