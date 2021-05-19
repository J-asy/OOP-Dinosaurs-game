package game.dinosaurs;

import edu.monash.fit2099.engine.Actor;

/**
 * Class to store all methods that involve checking whether an Actor that extends it
 * has specific capabilities. This is helpful to prevent down casting.
 */
public abstract class CapableActor extends Actor {

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public CapableActor(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    public boolean isMale() {
        return hasCapability(DinoCapabilities.MALE);
    }

    public boolean isFemale() {
        return hasCapability(DinoCapabilities.FEMALE);
    }

    public boolean isConscious(){
        return hasCapability(DinoCapabilities.CONSCIOUS);
    }

    public boolean isHerbivorous() {
        return hasCapability(DinoCapabilities.HERBIVORE);
    }

    public boolean isCarnivorous() {
        return hasCapability(DinoCapabilities.CARNIVORE);
    }

    public boolean canBreed() {
        return hasCapability(DinoCapabilities.CAN_BREED);
    }

    public boolean isPregnant(){
        return hasCapability(DinoCapabilities.PREGNANT);
    }

    public boolean canReachTree(){
        return hasCapability(DinoCapabilities.CAN_REACH_TREE);
    }

    public boolean canAttack(){
        return hasCapability(DinoCapabilities.CAN_ATTACK);
    }

    public boolean canBeAttacked(){
        return hasCapability(DinoCapabilities.CAN_BE_ATTACKED);
    }

    public boolean canDestroyBush() {
        return hasCapability(DinoCapabilities.BUSH_DESTROYER);
    }

    public boolean canTraverseWater(){
        return hasCapability(DinoCapabilities.CAN_TRAVERSE_WATER);
    }

    public boolean isArboreal(){
        return hasCapability(DinoCapabilities.ARBOREAL);
    }


}

