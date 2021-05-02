package game.breed;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.dinosaurs.DinoActor;

public class BreedingBehaviour implements Behaviour {

    /**
     * Target dinoActor that another dinoActor might breed with.
     */
    protected DinoActor target;

    /**
     * Constructor
     * @param target
     */
    public BreedingBehaviour(DinoActor target) {
        this.target = target;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor instanceof DinoActor) {
            DinoActor actorAsDino = (DinoActor) actor;

            boolean conditionOne = target.getSex() != actorAsDino.getSex();  // different sex
            boolean conditionTwo = target.getDinoType() == actorAsDino.getDinoType(); // same species
            boolean conditionThree = target.hasCapability(BreedingCapability.CAN_BREED) &&
                    target.hasCapability(BreedingCapability.CAN_BREED); // both are adults and can breed

            if (conditionOne && conditionTwo && conditionThree) {
                return new BreedingAction(target);
            }
        }

        return null;
    }

}
