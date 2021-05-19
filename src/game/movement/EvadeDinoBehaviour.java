package game.movement;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.attack.Corpse;
import game.dinosaurs.Pterodactyl;

/**
 * Simulates behaviour of DinoActor fleeing from other DinoActors.
 */
public class EvadeDinoBehaviour implements Behaviour {

    /**
     * Constructor.
     */
    public EvadeDinoBehaviour(){}

    /**
     * Returns a DynamicMoveAction for the Pterodactyl to flee from the other DinoActors
     * if the Pterodactyl landed on a corpse and there are other DinoActors around,
     * returns null otherwise.
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return DynamicMoveAction if the actor should flee, null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor instanceof Pterodactyl){
            Location location = map.locationOf(actor);
            boolean hasCorpse = false;
            for (Item item : location.getItems()){
                if(item instanceof Corpse){
                    hasCorpse = true;
                    break;
                }
            }

            // if Pterodactyl is standing on a corpse
            if (!hasCorpse){
                return null;
            }

            // finds a location to move to and flee from other DinoActors
            for (Exit exit: location.getExits()){
                if (!(exit.getDestination().containsAnActor())){
                    return new DynamicMoveAction(exit.getDestination(), exit.getName() +
                            " to evade other dinosaurs.");
                }
            }

        }
        return null;
    }
}
