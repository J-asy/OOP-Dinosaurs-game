package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public interface DynamicMovement {

    String getMovement();

    void depleteEnergy();

    boolean useSpecialMovement();

    void activityDuringSpecialMovement(GameMap map, Location location);

}
