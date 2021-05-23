package game.drinking;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.DinoActor;
import game.environment.DrinkingGround;

/**
 * Special Action for drinking from lakes.
 */
public class DrinkingAction extends Action {

    /**
     * The lake that DinoActors can drink out of
     */
    private final DrinkingGround drinkingGround;

    /**
     * Constructor.
     *
     * @param drinkingGround the lake to drink from
     */
    public DrinkingAction(DrinkingGround drinkingGround){
        this.drinkingGround = drinkingGround;
    }

    /**
     * Performs the drinking action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Description of the drinking action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(actor instanceof DinoActor){
            DinoActor dinoAsActor = (DinoActor) actor;
            dinoAsActor.quench();
            drinkingGround.decrementNumberOfSips();
        }

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks some water";
    }
}
