package game.breed;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
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
     * @return Description of the breeding action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // might or might not get pregnant
        String breedingDescription = null;
        if (actor instanceof DinoActor) {
            breedingDescription = menuDescription(actor);
            DinoActor actorAsDino = (DinoActor) actor;

            if (actorAsDino.getSex() == Sex.FEMALE && Probability.generateProbability(0.5F)) {
                actorAsDino.setPregnant(true);
                Location dinoLocation = map.locationOf(actorAsDino);
                breedingDescription += String.format("\n%s at (%d, %d) is pregnant!", actorAsDino, dinoLocation.x(),
                        dinoLocation.y());
            }
        }
        return breedingDescription;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " breeds with " + target;
    }

}

