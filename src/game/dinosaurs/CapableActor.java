package game.dinosaurs;

import edu.monash.fit2099.engine.Actor;

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

    public boolean isCarnivorous() {
        return hasCapability(DinoCapabilities.CARNIVORE);
    }

    public boolean isHerbivorous() {
        return hasCapability(DinoCapabilities.HERBIVORE);
    }

    public boolean canBreed() {
        return hasCapability(DinoCapabilities.CAN_BREED);
    }

    /**
     * Returns true if the dinoActor is pregnant, false otherwise.
     * A dinoActor only has a chance to be pregnant after breeding.
     * @return true if the dinoActor is pregnant, false otherwise
     */
    public boolean isPregnant(){
        return hasCapability(DinoCapabilities.PREGNANT);
    }

    public boolean canReachTree(){return hasCapability(DinoCapabilities.CAN_REACH_TREE);}

    public boolean canAttack(){return hasCapability(DinoCapabilities.CAN_ATTACK); }

    public boolean canBeAttacked(){
        return hasCapability(DinoCapabilities.CAN_BE_ATTACKED);
    }

    public boolean canDestroyBush() {
        return hasCapability(DinoCapabilities.BUSH_DESTROYER);
    }


}
