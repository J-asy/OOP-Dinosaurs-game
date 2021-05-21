package game.movement;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.attack.Corpse;
import game.dinosaurs.DinoActor;
import game.dinosaurs.Pterodactyl;

import java.util.List;

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
            if (landedOnFood(location)) {
                List<Exit> exits = location.getExits();
                if (surroundedByDino(location, map, actor)){

                    for (Exit exit: exits){
                        Location escapeLocation = exit.getDestination();
                        if (!escapeLocation.containsAnActor() && !surroundedByDino(escapeLocation, map, actor)){
                            return new DynamicMoveAction(escapeLocation, exit.getName() +
                                    " to evade other dinosaurs.");
                        }
                    }


                }
            }

        }
        return null;
    }

    private boolean landedOnFood(Location location){
        boolean hasCorpse = false;
        for (Item item : location.getItems()){
            if (item instanceof Corpse){
                hasCorpse = true;
                break;
            }
        }
        return hasCorpse;
    }

    private boolean surroundedByDino(Location location, GameMap map, Actor actor){
        for (Exit exit: location.getExits()){
            Location surroundingLocation = exit.getDestination();
            Actor surroundingActor = map.getActorAt(surroundingLocation);
            if (surroundingActor instanceof DinoActor && surroundingActor != actor) {
                return true;
            }
        }
        return false;
    }

}
