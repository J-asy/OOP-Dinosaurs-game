package game.breed;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.dinosaurs.DinoActor;
import game.Utility;

/**
 * Special Action for breeding between DinoActors.
 */
public class BreedingAction extends Action {

    /**
     * The dinosaur that another DinoActor will breed with.
     */
     private DinoActor target;

    /**
     * Constructor.
     * @param target dinosaur that another DinoActor will breed with
     */
    public BreedingAction(DinoActor target) {
        this.target = target;
    }

    /**
     * Once the execute method is called, breeding occurs and a description
     * of the action is returned. However, there is a 50% chance that
     * the female DinoActor will get pregnant, in which case the description
     * of the pregnancy will be returned as well.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Description of the breeding action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String breedingDescription = null;
        if (actor instanceof DinoActor) {
            breedingDescription = menuDescription(actor);
            DinoActor dinoActor = (DinoActor) actor;

            if (dinoActor.isFemale() && Utility.generateProbability(0.5F)) {
                dinoActor.setPregnant(true);
                Location dinoLocation = map.locationOf(dinoActor);
                breedingDescription += String.format("\n%s at (%d, %d) is pregnant!", dinoActor, dinoLocation.x(),
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

