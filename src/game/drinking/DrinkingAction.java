package game.drinking;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.DinoActor;
import game.environment.DrinkingGround;

public class DrinkingAction extends Action {

    private DrinkingGround drinkingGround;

    public DrinkingAction(DrinkingGround drinkingGround){
        this.drinkingGround = drinkingGround;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        DinoActor dinoAsActor = (DinoActor) actor;

        if(dinoAsActor.canBeAttacked() || dinoAsActor.canAttack() || dinoAsActor.canTraverseWater()){
            dinoAsActor.quench(30);
        }
        else {
            dinoAsActor.quench(80);
        }
        drinkingGround.decrementNumberOfSips();
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " drinks some water";
    }
}
