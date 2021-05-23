package game.drinking;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.DinoActor;
import game.environment.DrinkingGround;

/**
 * Simulates drinking behaviour of DinoActors from lakes
 * depending on whether certain criteria is met.
 */
public class DrinkingBehaviour implements Behaviour {

    /**
     * Constructor.
     *
     */
    public DrinkingBehaviour(){}

    /**
     * Returns DrinkingAction if the actor is allowed to drink from a lake by checking
     * necessary conditions, otherwise null is returned.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return DrinkingAction if actor can drink from lake, otherwise null is returned
     */
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
