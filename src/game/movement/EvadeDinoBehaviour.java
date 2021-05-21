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
            if (landedOnCorpse(location)) {
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

    /**
     * Returns true if there is a Corpse on the location, false otherwise.
     * @param location a Location
     * @return true if there is a Corpse on the location, false otherwise.
     */
    private boolean landedOnCorpse(Location location){
        boolean hasCorpse = false;
        for (Item item : location.getItems()){
            if (item instanceof Corpse){
                hasCorpse = true;
                break;
            }
        }
        return hasCorpse;
    }

    /**
     * Returns true if the location does not have any DinoActors around it,
     * apart from the Actor that is trying to flee itself, returns false otherwise.
     * @param location a Location
     * @param map map that the DinoActor is on
     * @param actor An Actor that is trying to flee from other DinoActors
     * @return true if the location does not have any DinoActors (that is not the actor)
     * around it, false otherwise.
     */
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
