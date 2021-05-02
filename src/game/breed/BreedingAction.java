package game.breed;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.Sex;
import game.dinosaurs.DinoActor;
import game.utility.Probability;

/**
 * Simulates breeding action of Actors
 */
public class BreedingAction extends Action {

    /**
     * The dinosaur that another Actor will breed with.
     */
    protected DinoActor target;

    /**
     * Constructor.
     * @param target dinosaur that another Actor will breed with
     */
    public BreedingAction(DinoActor target) {
        this.target = target;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // might or might not get pregnant
        if (Probability.generateProbability(0.5F)) {
            DinoActor femaleDino;
            if (target.getSex() == Sex.FEMALE){
                femaleDino = target;
            }
            else {
                femaleDino = (DinoActor) actor;
            }
            femaleDino.setPregnant(true);
        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " breeds with " + target;
    }

}

