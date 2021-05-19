package game.movement;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

/**
 * Interface to be implemented by Actors that have different ways of moving.
 */
public interface DynamicMovement {

    /**
     * Returns the name of the movement used.
     * @return name of movement
     */
    String getMovement();

    /**
     * Increments the energy required for special movement.
     * @param ground ground the actor is on
     */
    void rechargeEnergy(Ground ground);

    /**
     * Reduces the energy level after special movement is used.
     */
    void depleteEnergy();

    /**
     * Returns true if special movement should be used, false otherwise.
     * @return true if special movement should be used, false otherwise
     */
    boolean useSpecialMovement();

    /**
     * Executes any special activities the Actor can do while using special movement.
     * @param map map the actor is on
     * @param location location the actor will be on after using special movement
     */
    void activityDuringSpecialMovement(GameMap map, Location location);

}
