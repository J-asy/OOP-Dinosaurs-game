package game.pregnancy;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.dinosaurs.DinoActor;
import game.dinosaurs.Sex;

/**
 * Simulates the pregnancy behaviour of an Actor.
 */
public class PregnancyBehaviour implements Behaviour {

    /**
     * Checks if the Actor fulfills the following conditions:
     * (i) Actor is an instance of DinoActor
     * (ii) Actor is female
     * (iii) Actor is pregnant
     * (iv)
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a new DropItemAction if it is time for a pregnant actor to lay an egg, null otherwise.
     */
    //TODO: add lay egg capability?
    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (actor instanceof DinoActor){
            DinoActor actorAsDino = (DinoActor) actor;

            if (actorAsDino.getSex() == Sex.FEMALE && actorAsDino.isPregnant()){
                if (actorAsDino.getPregnancyPeriod() > 0){
                    actorAsDino.decrementPregnancyPeriod();
                }
                else {
                    actorAsDino.setPregnant(false);
                    return new LayEggAction();
                }
            }
        }

        return null;
    }
}
