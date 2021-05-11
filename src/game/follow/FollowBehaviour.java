package game.follow;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.DinoActor;

import java.util.ArrayList;
import java.util.List;


/**
 * Behaviour class that simulates Actor moving towards another Actor, Item or Ground.
 */

public abstract class FollowBehaviour implements Behaviour {

    /**
     * Description to be display on console of the purpose for the following action.
     */
    private final String purposeDescription;

    /**
     * Minimum number of squares from DinoActor to start searching for something to follow.
     */
    private final int MIN_RADIUS;

    /**
     * Maximum number of squares from DinoActor to search for something to follow.
     */
    private final int MAX_RADIUS;

    /**
     * Constructor.
     * @param description purpose description of the motivation to perform following action
     * @param min_radius minimum number of squares from DinoActor too start searching for
     *                   something to follow
     * @param max_radius maximum number of squares from DinoActor to search for something
     *                   to follow
     */
    public FollowBehaviour(String description, int min_radius, int max_radius) {
        purposeDescription = description;
        MIN_RADIUS = min_radius;
        MAX_RADIUS = max_radius;
    }

    /**
     * Returns true if the DinoActor has a reason / fulfills certain criteria
     * to be able to follow something
     * @param dinoActor DinoActor that is acting
     * @return true if the DinoActor should attempt to follow, false otherwise.
     */
    abstract boolean motivatedToFollow(DinoActor dinoActor);

    /**
     * Checks whether the Location destination has a target that the DinoActor
     * is trying to find and returns it if true, otherwise null is returned.
     * @param map GameMap that the DinoActor is on
     * @param destination Location that is checked for target
     * @param actorAsDino DinoActor that is finding its target to follow
     * @return a Location if the target is found on it, null otherwise.
     */
    abstract Location findTarget(GameMap map, Location destination, DinoActor actorAsDino);

    /**
     * Checks all locations that are within MIN_RADIUS and MAX_RADIUS away from the
     * actor's location. If there is something that the actor wants to follow on a location,
     * returns a MoveActorAction to that location immediately. If after searching through
     * all locations within the range and no suitable target to follow is found, return null.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a MoveActorAction if the actor found a target to follow within range MIN_RADIUS
     * and MAX_RADIUS squares away from Actor's location, null otherwise.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Action actionToReturn = null;
        int radius = MIN_RADIUS;
        boolean found = false;
        if (actor instanceof DinoActor) {
            DinoActor actorAsDino = (DinoActor) actor;
            if (motivatedToFollow(actorAsDino)) {
                Location actorLocation = map.locationOf(actor);
                Location actualDestination;
                while (!found && radius <= MAX_RADIUS) {
                    List<Location> locationsInSight = lookAround(map, actor, radius);

                    for (Location destination : locationsInSight) {
                        actualDestination = findTarget(map, destination, actorAsDino);

                        if (actualDestination != null) {
                            actionToReturn = moveCloser(actorLocation, actualDestination, actor);
                            found = true;
                            break;
                        }

                    }
                    radius += 1;
                }
            }
        }
            return actionToReturn;
    }

    /**
     * Returns the MoveActorAction that leads actor closer to target destination.
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

    /**
     * Gets locations that are in one cardinal direction of the actor and is
     * offset number of squares away from the actor's location.
     * @param map GameMap that the Actor is currently on
     * @param actorLocation Current location of the Actor
     * @param numRange NumberRange object to iterate through to get Locations needed
     * @param offset distance that the locations to be returned should be from the actor's location
     * @param forHorizontal true if want to retrieve horizontal locations (along x-axis of North or South
     *                      of actor's location), false if want to retrieve vertical locations
     *                      (along y-axis of East or West of actor's location)
     * @return A list of locations in one cardinal direction of the Actor that are offset number of squares
     * away
     */
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
     * Get all locations from radius number of squares away from the Actor's location for all four cardinal directions.
     * @param map GameMap that the Actor is currently on
     * @param actor Actor that is acting
     * @param radius The distance in number of squares away from the actor that needs to be returned.
     * @return all locations from radius number of squares away from the Actor's location
     */
    private static List<Location> lookAround(GameMap map, Actor actor, int radius) {
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
