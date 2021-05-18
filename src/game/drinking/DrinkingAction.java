package game.drinking;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.DinoActor;
import game.environment.DrinkingGround;

public class DrinkingAction extends Action {

    private final DrinkingGround drinkingGround;

    public DrinkingAction(DrinkingGround drinkingGround){
        this.drinkingGround = drinkingGround;
    }

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
