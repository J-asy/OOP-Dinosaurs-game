package game.follow;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.DinoActor;
import game.dinosaurs.DinoCapabilities;

import java.util.ArrayList;
import java.util.List;

// might need to change look around

/**
 * Behaviour class that simulates Actor following another Actor
 */
public abstract class FollowBehaviour implements Behaviour {

    private DinoCapabilities followPurpose;

    public FollowBehaviour(){
    }

    public FollowBehaviour(DinoCapabilities purpose) {
        followPurpose = purpose;
    }


    @Override
    public Action getAction(Actor actor, GameMap map) {
        // if actor is a player, player doesn't follow anything
        Action actionToReturn = null;
        if (actor instanceof DinoActor && followPurpose != null) {
            Location actorLocation = map.locationOf(actor);
            List<Exit> visibleExits = lookAround(map, actor);  // exits that are two squares away from actor

            DinoActor actorAsDino = (DinoActor) actor;
            for (Exit exit : visibleExits) {
                Location destination = exit.getDestination();
                Location actualDestination = null;
                if (followPurpose == DinoCapabilities.CAN_BREED || followPurpose == DinoCapabilities.CAN_ATTACK){
                    actualDestination = followActor(map, destination, actorAsDino);
                }
                else if (followPurpose == DinoCapabilities.HERBIVORE || followPurpose == DinoCapabilities.CARNIVORE){
                    actualDestination = followFoodItem(map, destination, actorAsDino);
                }

                if (actualDestination != null){
                    actionToReturn = moveCloser(actorLocation, actualDestination, actor);
                    break;
                }
            }
        }
            return actionToReturn;
    }

    private Location followFoodItem(GameMap map, Location destination, DinoActor actorAsDino){
        Location returnDestination = null;
        List<Item> groundItems = map.locationOf(actorAsDino).getItems();

//        for (Item currentItem : groundItems){
            // has capability of herbivore
            // has capability of carnivore
//        }



        return returnDestination;
    }

    private Location followActor(GameMap map, Location destination, DinoActor actorAsDino){
        Location returnDestination = null;

        // if there is an actor two squares away
        if (map.isAnActorAt(destination)) {
            Actor nearbyActor = destination.getActor();

            if (nearbyActor instanceof DinoActor) {
                DinoActor targetAsDino = (DinoActor) nearbyActor;

                if (followPurpose == DinoCapabilities.CAN_BREED &&
                        followForBreeding(actorAsDino, targetAsDino)) {
                    targetAsDino.setActionInMotion(new DoNothingAction());
                    returnDestination = destination;
                }
                else if (followPurpose == DinoCapabilities.CAN_ATTACK &&
                        followForAttacking(actorAsDino, targetAsDino)){
                    returnDestination = destination;
                }

            }
        }
        return returnDestination;

    }

    private boolean followForBreeding(DinoActor actorAsDino, DinoActor targetAsDino){
        boolean differentSex = targetAsDino.getSex() != actorAsDino.getSex();
        boolean sameSpecies = actorAsDino.getDinoType() == targetAsDino.getDinoType();
        boolean bothCanBreed = targetAsDino.canBreed() && actorAsDino.canBreed();
        return differentSex && sameSpecies && bothCanBreed;
    }

    private boolean followForAttacking(DinoActor actorAsDino, DinoActor nearbyActor){
        boolean isCarnivorous = actorAsDino.canAttack();
        boolean canBeAttacked = nearbyActor.canBeAttacked();
        return isCarnivorous && canBeAttacked;
    }

    /**
     *  Returns the MoveActorAction that leads actor closer to target destination.
     * @param actorLocation location of the actor
     * @param destination location of the destination that the actor wants to reach
     * @param actor actor that is trying to move closer to the target destination
     * @return MoveActorAction if there is a way to get closer to the destination, null otherwise
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
                }
            }
        }
        return null;
    }


    /**
     * Returns a List of all Exits that are two squares away from actor.
     * @param map GameMap that the actor is on
     * @param actor actor trying to look around
     * @return A List of Exits that are two squares away from the actor
     */
    private static List<Exit> lookAround(GameMap map, Actor actor){
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
