package game;

import edu.monash.fit2099.engine.Ground;

public interface DynamicMovement {

    String getMovement();

    void depleteEnergy();

    void rechargeEnergy(Ground ground);

    boolean useSpecialMovement();

}
