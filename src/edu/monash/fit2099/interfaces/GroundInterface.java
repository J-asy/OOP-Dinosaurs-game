package edu.monash.fit2099.interfaces;


import game.dinosaurs.CapableActor;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface GroundInterface {

    /**
     * Returns true if the CapableActor can lay an egg on the ground, false otherwise.
     * @param capableActor A capable actor
     * @return true if the capable actor can lay an egg on the ground, false otherwise
     */
    boolean canLayEggHere(CapableActor capableActor);

    /**
     * Returns true if the CapableActor can breed on the ground, false otherwise.
     * @param capableActor A capable actor
     * @return true if the capable actor can breed on the ground, false otherwise
     */
    boolean canBreedHere(CapableActor capableActor);

}
