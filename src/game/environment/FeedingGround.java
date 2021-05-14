package game.environment;

import game.FoodItem;
import game.dinosaurs.CapableActor;

public interface FeedingGround {

    int eat();

    boolean canEat(CapableActor capableActor);

    FoodItem foodToEat();

}
