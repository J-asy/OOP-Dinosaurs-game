package game.breed;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.dinosaurs.DinoActor;
import game.dinosaurs.Sex;

public class BreedingBehaviour implements Behaviour {

    protected DinoActor target;

    public BreedingBehaviour(DinoActor target) {
        this.target = target;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor instanceof DinoActor) {
            boolean conditionOne = target.getSex() != ((DinoActor) actor).getSex();  // different sex
            boolean conditionTwo = target.getClass().getName().equals(actor.getClass().getName());  // same species
            boolean conditionThree = target.hasCapability(BreedingCapability.CAN_BREED) &&
                    target.hasCapability(BreedingCapability.CAN_BREED); // both are adults and can breed

            if (conditionOne && conditionTwo && conditionThree) {
                DinoActor femaleDino;
                if (target.getSex() == Sex.FEMALE) {
                    femaleDino = target;
                } else {
                    femaleDino = (DinoActor) actor;
                }

                // if female dinosaur not already pregnant, breeding occurs
                if (!femaleDino.isPregnant()) {
                    return new BreedingAction(target);
                }
            }
        }
        return null;
    }

}
