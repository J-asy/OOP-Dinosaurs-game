package game;

import edu.monash.fit2099.engine.Ground;

public interface DynamicMovement {

    String getMovement();

    void depleteEnergy();

    boolean useSpecialMovement();

    void activityDuringSpecialMovement(Ground ground);

}
