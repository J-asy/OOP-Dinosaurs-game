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
            List<Exit> visibleExits = lookAround(map, actor);  // exits that are two squares away from actor

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
                            return moveCloser(actorLocation, destination, actor);
                        }
                    }

                }
            }
        }

        return null;

    }

    /**
     *  Returns the MoveActorAction that leads actor closer to target destination
     * @param actorLocation
     * @param destination
     * @param actor
     * @return
     */
    private Action moveCloser(Location actorLocation, Location destination, Actor actor) {
        int currentDistance = distance(actorLocation, destination);

        // for all adjacent squares, move to one that will make actor closer to target
        for (Exit exit: actorLocation.getExits()){
            Location possibleStep = exit.getDestination();
            if (possibleStep.canActorEnter(actor)) {
                int newDistance = distance(possibleStep, destination);
                if (newDistance < currentDistance) {
                    return new MoveActorAction(exit.getDestination(), exit.getName());
//                    return new MoveActorAction(exit.getDestination(), "fdsljfl");  // only for testing
                }
            }
        }
        return null;
    }


    /**
     * Returns a List of all Exits that are two squares away from actor
     * @param map
     * @param actor
     * @return
     */
    private List<Exit> lookAround(GameMap map, Actor actor){
        Location actorLocation = map.locationOf(actor);
        int[] seekOffset = {1, -1};
        ArrayList<Location> seekPoints = new ArrayList<>();
        List<Exit> exitsSeen = new ArrayList<>();
        List<Exit> returnExits = new ArrayList<>();

        // Since there are only two items in seekOffset, there's only 4 seekPoints
        for (int i : seekOffset){
            // if x cord - 1 or x cord + 1 is in range of the map size
            if (map.getXRange().contains(actorLocation.x() + i)){
                for (int j: seekOffset){
                    // if y cord - 1 or y cord + 1 is in range of the map size
                    if(map.getYRange().contains(actorLocation.y() + j)){
                        seekPoints.add(map.at(actorLocation.x() + i, actorLocation.y() + j));
                    }
                }
            }
        }

        // getting all exits of all seekPoints
        for (Location sp: seekPoints){
            exitsSeen.addAll(sp.getExits());
        }

        // in the end, in allExits we actually have some exits that we are not interested in -
        // those directly adjacent to actor and position of actor itself, but we'll filter them out
        for (Exit exit : exitsSeen) {
            Location d = exit.getDestination();

            boolean dontAdd = false;
            for (Exit aExits: actorLocation.getExits()){
                // condition true if the exit is already adjacent to actor or at actors location
                // since actor wont follow a target that's already beside it
                if (d.equals(aExits.getDestination()) || d.equals(actorLocation)){
                    dontAdd = true;
                    break;
                }
            }

            if (!dontAdd){
                returnExits.add(exit);
            }
        }

        return returnExits;

    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private static int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

}
