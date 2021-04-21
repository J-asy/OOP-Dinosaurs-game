package game.pregnancy;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.DinoActor;
import game.dinosaurs.Egg;

// DONE
public class LayEggAction extends Action {


    public LayEggAction(){
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String executionDescription = null;
        if (actor instanceof DinoActor){
            map.locationOf(actor).addItem(new Egg(actor.getDisplayChar()));
            executionDescription = menuDescription(actor) + " at (" + map.locationOf(actor).x() + ", " +
                    map.locationOf(actor).y() + ")";
        }

        return executionDescription;

    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " lays egg!";
    }

}
