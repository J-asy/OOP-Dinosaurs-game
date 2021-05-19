package game.movement;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.attack.Corpse;
import game.dinosaurs.Pterodactyl;

public class EvadeDinoBehaviour implements Behaviour {

    public EvadeDinoBehaviour() {
    }

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

            if (!hasCorpse){
                return null;
            }

            boolean shouldMoveAway = false;
            Location moveToLocation = null;
            for (Exit exit: location.getExits()){
                if (location.containsAnActor()){
                    shouldMoveAway = true;
                }
                else {
                    moveToLocation = exit.getDestination();
                }

                if (shouldMoveAway && moveToLocation != null){
                    return new DynamicMoveAction(moveToLocation, exit.getName() + "to evade other dinosaurs.");
                }
            }


        }

        return null;
    }
}
