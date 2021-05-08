package game.breed;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.dinosaurs.DinoActor;

/**
 * Simulates breeding behaviour of DinoActors - breeding might or might not
 * occur depending on whether certain criteria is met.
 */
public class BreedingBehaviour implements Behaviour {

    /**
     * Target dinoActor that another dinoActor might breed with.
     */
     private DinoActor target;

    /**
     * Constructor.
     * @param target target that the actor intends to breed with
     */
    public BreedingBehaviour(DinoActor target) {
        this.target = target;
    }

    /**
     * Returns BreedingAction if the actor can breed with the target by checking
     * necessary conditions, otherwise null is returned.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return BreedingAction if the actor can breed with the target, otherwise null is returned
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor instanceof DinoActor) {
            DinoActor actorAsDino = (DinoActor) actor;
            boolean differentSex = target.getSex() != actorAsDino.getSex();
            boolean sameSpecies = target.getDinoType() == actorAsDino.getDinoType();
            boolean bothAbleToBreed = target.canBreed() && actorAsDino.canBreed();

            if (differentSex && sameSpecies && bothAbleToBreed) {
                return new BreedingAction(target);
            }

        }

        return null;
    }

}
