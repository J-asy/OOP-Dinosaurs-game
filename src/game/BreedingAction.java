package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.DinoActor;
import game.utility.Probability;

// DONE
public class BreedingAction extends Action {

    protected DinoActor target;

    public BreedingAction(DinoActor target) {
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String executionDescription = null;
        if (actor instanceof DinoActor) {
            boolean conditionOne = target.getSex() != ((DinoActor)actor).getSex();  // different sex
            boolean conditionTwo = target.getClass().getName().equals(actor.getClass().getName());  // same species

            if (conditionOne && conditionTwo){
                DinoActor femaleDino;
                if (target.getSex() == Sex.FEMALE){
                    femaleDino = target;
                }
                else {
                    femaleDino = (DinoActor)actor;
                }

                // if female dinosaur not already pregnant, breeding occurs
                if (!femaleDino.isPregnant()) {
                    executionDescription = menuDescription(actor);
                    // but might or might not get pregnant later
                    if (Probability.generateProbability(0.5F)){
                        femaleDino.setPregnant(true);
                    }
                }

            }
        }
        return executionDescription;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " breeds with " + target;
    }

}

