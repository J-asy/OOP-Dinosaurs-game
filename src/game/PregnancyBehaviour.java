package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.DinoActor;

// DONE
public class PregnancyBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (actor instanceof DinoActor){
            DinoActor actorAsDino = (DinoActor) actor;

            if (actorAsDino.getSex() == Sex.FEMALE && actorAsDino.isPregnant()){
                if (actorAsDino.getPregnancyPeriod() > 0){
                    actorAsDino.decrementPregnancyPeriod();
                }
                else {
                    return new LayEggAction();
                }
            }
        }

        return null;
    }
}
