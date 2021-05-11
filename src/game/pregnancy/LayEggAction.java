package game.pregnancy;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.DinoActor;
import game.dinosaurs.Egg;

/**
 * Special Action for laying an Egg.
 */
public class LayEggAction extends Action {

    /**
     * Empty constructor.
     */
    public LayEggAction(){
    }

    /**
     * Creates and places an Egg object at the location of the actor
     * to simulate the actor laying an egg, and returns a description
     * of the process.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Description of actor laying an egg
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String executionDescription = null;
        if (actor instanceof DinoActor){
            map.locationOf(actor).addItem(new Egg(((DinoActor) actor).getDinoType()));
            executionDescription = menuDescription(actor) + " at (" + map.locationOf(actor).x() + ", " +
                    map.locationOf(actor).y() + ")";
        }

        return executionDescription;

    }

    /**
     * Returns a descriptive string of the lay egg action.
     * @param actor The actor performing the action.
     * @return Description of actor laying an egg
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " lays egg";
    }

}
