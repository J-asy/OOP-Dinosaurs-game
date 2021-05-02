package game.follow;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.DinoActor;

import java.util.ArrayList;
import java.util.List;


public class FollowMateBehaviour implements Behaviour {


    public FollowMateBehaviour() {
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        // if actor is a player, player doesn't follow anything
        if(actor instanceof DinoActor) {
            Location actorLocation = map.locationOf(actor);
            List<Exit> visibleExits = FollowUtilities.lookAround(map, actor);  // exits that are two squares away from actor

            DinoActor actorAsDino = (DinoActor) actor;
            for (Exit exit : visibleExits) {
                Location destination = exit.getDestination();

                // if there is an actor two squares away
                if (map.isAnActorAt(destination)) {
                    Actor nearbyActor = destination.getActor();

                    if (nearbyActor instanceof DinoActor) {
                        DinoActor targetAsDino = (DinoActor) nearbyActor;
                        boolean conditionOne = targetAsDino.getSex() != actorAsDino.getSex(); // opposite sex
                        boolean conditionTwo = !targetAsDino.isPregnant() && !actorAsDino.isPregnant(); // not pregnant
                        boolean conditionThree = actorAsDino.getDinoType() == targetAsDino.getDinoType(); // same species

                        if (conditionOne && conditionTwo && conditionThree){
                            return FollowUtilities.moveCloser(actorLocation, destination, actor);
                        }
                    }

                }
            }
        }

        return null;

    }


}
