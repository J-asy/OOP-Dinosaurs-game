package game.environment;

import edu.monash.fit2099.engine.Actor;
import game.dinosaurs.DinoActor;

public class Lake extends CapableGround {
    /**
     * Constructor.
     */
    public Lake() {
        super('~');
        setLake();
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor instanceof DinoActor){
            return ((DinoActor)actor).canEnterWater();
        }
        return false;
    }

}
