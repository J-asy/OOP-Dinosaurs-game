package game.pregnancy;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Behaviour;
import game.dinosaurs.DinoActor;
import game.dinosaurs.Sex;

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
                    actorAsDino.setPregnant(false);
                    return new LayEggAction();
                }
            }
        }

        return null;
    }
}
