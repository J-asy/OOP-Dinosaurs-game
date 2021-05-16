package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.CapableActor;

public interface Food {

    String foodName();

    boolean canEat(CapableActor capableActor, Location location);

    int eat(GameMap map, Location location, int biteSize);

}
