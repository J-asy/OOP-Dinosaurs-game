package game.pregnancy;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import game.Behaviour;
import game.dinosaurs.DinoActor;
import game.follow.FindBirthingSpotBehaviour;

/**
 * Simulates the pregnancy behaviour of an Actor.
 */
public class PregnancyBehaviour implements Behaviour {

    /**
     * Determines whether it is time for a pregnant DinoActor to lay an egg yet.
     * If yes, LayEggAction is returned, otherwise the pregnancy period of the
     * DinoActor is updated to the number of turns left for it to lay an egg.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return LayEggAction if it is time for a pregnant actor to lay an egg, null otherwise.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (actor instanceof DinoActor){
            DinoActor dinoActor = (DinoActor) actor;

            if (dinoActor.isPregnant()){
                if (dinoActor.getPregnancyPeriod() > 0){
                    dinoActor.decrementPregnancyPeriod();
                }
                else {
                    Ground ground = map.locationOf(actor).getGround();
                    if (ground.canLayEggHere(dinoActor)) {
                        dinoActor.setPregnant(false);
                        return new LayEggAction();
                    }
                    return new FindBirthingSpotBehaviour().getAction(actor, map);
                }
            }
        }

        return null;
    }
}
