package game.environment;

/**
 * Interface to be implemented by all ground that has water for DinoActors to drink from.
 */
public interface DrinkingGround {

    /**
     * Returns true if there is water on the drinking ground, false otherwise.
     * @return true if there is water on the drinking ground, false otherwise
     */
    boolean hasWater();

    /**
     * Decrements the amount of water left on the drinking ground.
     */
    void decrementNumberOfSips();


}
