package game.follow;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.PortableItem;
import game.dinosaurs.DinoActor;
import game.dinosaurs.DinoCapabilities;
import game.environment.CapableGround;

import java.util.ArrayList;
import java.util.List;


/**
 * Behaviour class that simulates Actor following another Actor
 */

public abstract class FollowBehaviour implements Behaviour {

    /**
     * Description to be inform player of the following action.
     */
    private final String purposeDescription;

    /**
     * Minimum number of squares from DinoActor to start searching for a target to follow.
     */
    private final int MIN_RADIUS;

    /**
     * Maximum number of squares from DinoActor to search for a target to follow.
     */
    private final int MAX_RADIUS;

    public FollowBehaviour(String description, int min_radius, int max_radius) {
        purposeDescription = description;
        MIN_RADIUS = min_radius;
        MAX_RADIUS = max_radius;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Action actionToReturn = null;
        int radius = MIN_RADIUS;
        boolean found = false;
        if (actor instanceof DinoActor) {
            Location actorLocation = map.locationOf(actor);
            DinoActor actorAsDino = (DinoActor) actor;

            Location actualDestination;
            while (!found && radius <= MAX_RADIUS) {
                List<Location> locationsInSight = lookAround(map, actor, radius);

                for (Location destination : locationsInSight) {
                        actualDestination = follow(map, destination, actorAsDino);

                    if (actualDestination != null) {
                        System.out.println("found rad: " + radius);
                        System.out.println("x: " + actualDestination.x() + "; y: " + actualDestination.y());
                        actionToReturn = moveCloser(actorLocation, actualDestination, actor);
                        found = true;
                        break;
                    }

                }
                radius += 1;
            }

        }
            return actionToReturn;
    }


    abstract Location follow(GameMap map, Location destination, DinoActor actorAsDino);

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
                    String directionDescription = String.format("%s to %s.", exit.getName(), purposeDescription);
                    return new MoveActorAction(exit.getDestination(), directionDescription);
                }
            }
        }
        return null;
    }

    private static List<Location> getSpottedLocations(GameMap map, Location actorLocation, NumberRange numRange,
                                                      int offset, boolean forHorizontal) {
        List<Location> allLocations = new ArrayList<>();

        Location newLocation;
        int xOffset, yOffset, xValue, yValue;

        for (int i : numRange) {
            if (forHorizontal){
                xOffset = i;
                yOffset = offset;
            }
            else {
                xOffset = offset;
                yOffset = i;
            }

            xValue = actorLocation.x() + xOffset;
            yValue = actorLocation.y() + yOffset;

            if (map.getXRange().contains(xValue) && map.getYRange().contains(yValue)) {
                newLocation = map.at(xValue, yValue);
                allLocations.add(newLocation);
            }

        }
        return allLocations;
    }

    /**
     * Get all locations from radius number of squares away from the Actor's location.
     * @param map GameMap that the Actor is currently on
     * @param actor Actor that is looking around for something to follow
     * @param radius The distance in number of squares away from the actor that needs to be returned.
     * @return all locations from radius number of squares away from the Actor's location
     */
    public static List<Location> lookAround(GameMap map, Actor actor, int radius) {
        Location actorLocation = map.locationOf(actor);
        List<Location> spottedLocations = new ArrayList<>();
        NumberRange horizontalRange = new NumberRange(-1*radius, 2*radius + 1);
        NumberRange verticalRange = new NumberRange(-1*radius + 1, 2*radius - 1);

        // get the horizontal locations
        spottedLocations.addAll(getSpottedLocations(map, actorLocation, horizontalRange, horizontalRange.min(), true));
        spottedLocations.addAll(getSpottedLocations(map, actorLocation, horizontalRange, horizontalRange.max(), true));

        // get the vertical locations
        spottedLocations.addAll(getSpottedLocations(map, actorLocation, verticalRange, horizontalRange.min(), false));
        spottedLocations.addAll(getSpottedLocations(map, actorLocation, verticalRange, horizontalRange.max(), false));

        return spottedLocations;
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
