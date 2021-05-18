package game.drinking;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.DinoActor;
import game.environment.DrinkingGround;

public class DrinkingBehaviour implements Behaviour {

    public DrinkingBehaviour(){}

    public Action getAction(Actor actor, GameMap map){

        if (actor instanceof DinoActor) {
            DinoActor dinoActor = (DinoActor) actor;
            Location actorLocation = map.locationOf(actor);
            if (dinoActor.isThirsty()) {
                for (Exit exit : actorLocation.getExits()) {
                    Ground ground = exit.getDestination().getGround();
                    if (ground instanceof DrinkingGround) {
                        DrinkingGround drinkingGround = (DrinkingGround)ground;
                        if (drinkingGround.hasWater()) {
                            return new DrinkingAction(drinkingGround);
                        }
                    }
                }
            }
        }
        return null;
    }

}
