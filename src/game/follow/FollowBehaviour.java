package game.follow;

import edu.monash.fit2099.engine.*;
import game.Behaviour;
import game.dinosaurs.DinoActor;
import game.dinosaurs.DinoCapabilities;

import java.util.ArrayList;
import java.util.List;


/**
 * Behaviour class that simulates Actor following another Actor
 */
public abstract class FollowBehaviour implements Behaviour {

    private final DinoCapabilities followPurpose;
    private final String purposeDescription;
    private static final int MAX_RADIUS = 4;

    public FollowBehaviour(DinoCapabilities purpose, String description) {
        followPurpose = purpose;
        purposeDescription = description;
    }

     DinoCapabilities getFollowPurpose() {
            return followPurpose;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Action actionToReturn = null;
        int radius = 2;
        boolean found = false;
        if (actor instanceof DinoActor) {
            Location actorLocation = map.locationOf(actor);
            DinoActor actorAsDino = (DinoActor) actor;

            Location actualDestination = null;
            while (!found && radius <= FollowBehaviour.MAX_RADIUS) {
                List<Location> visibleExits = lookAround(map, actor, radius);
                for (Location destination : visibleExits) {

                    if (shouldFollowActor()) {
                        actualDestination = followActor(map, destination, actorAsDino);
                    } else if (shouldFollowItem()) {
                        actualDestination = followItem(map, destination, actorAsDino);
                    }

                    if (actualDestination != null) {
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

    private Location followItem(GameMap map, Location destination, DinoActor actorAsDino){
        Location returnDestination = null;
//        List<Item> groundItems = map.locationOf(actorAsDino).getItems();
//
//        for (Item currentItem : groundItems){
//            if (tryingToEatVegetables() && currentItem.hasCapability(FoodType.HERBIVORE)){
//
//            }
////            else if
//            {
//
//            }
//
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

                if (tryingToBreed() && breedingPossible(actorAsDino, targetAsDino)) {
                    returnDestination = destination;
                }
                else if (tryingToAttack() && attackingPossible(actorAsDino, targetAsDino)){
                    returnDestination = destination;
                }

            }
        }
        return returnDestination;
    }

    private boolean tryingToAttack() {
        return followPurpose == DinoCapabilities.CAN_ATTACK;
    }

    private boolean tryingToBreed() {
        return followPurpose == DinoCapabilities.CAN_BREED;
    }

    private boolean tryingToEatMeat() {
        return followPurpose == DinoCapabilities.CARNIVORE;
    }

    private boolean tryingToEatVegetables() {
        return followPurpose == DinoCapabilities.HERBIVORE;
    }

    private boolean shouldFollowActor(){
        return tryingToBreed() || tryingToAttack();
    }

    private boolean shouldFollowItem(){
        return tryingToEatVegetables() || tryingToEatMeat();
    }

    private boolean breedingPossible(DinoActor actorAsDino, DinoActor targetAsDino){
        boolean differentSex = targetAsDino.getSex() != actorAsDino.getSex();
        boolean sameSpecies = actorAsDino.getDinoType() == targetAsDino.getDinoType();
        boolean bothCanBreed = targetAsDino.canBreed() && actorAsDino.canBreed();
        return differentSex && sameSpecies && bothCanBreed;
    }

    private boolean attackingPossible(DinoActor actorAsDino, DinoActor nearbyActor){
        boolean canAttack = actorAsDino.canAttack();
        boolean canBeAttacked = nearbyActor.canBeAttacked();
        return canAttack && canBeAttacked;
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
                    String directionDescription = String.format("%s to %s.", exit.getName(), purposeDescription);
                    return new MoveActorAction(exit.getDestination(), directionDescription);
                }
            }
        }
        return null;
    }


    private static List<Location> getSpottedLocations(GameMap map, Location actorLocation, NumberRange range,
                                                      int offset, boolean isHorizontal) {
        List<Location> allLocations = new ArrayList<>();

        Location newLocation;
        int xOffset, yOffset;

        for (int i : range) {
            if (isHorizontal){
                xOffset = i;
                yOffset = offset;
            }
            else {
                xOffset = offset;
                yOffset = i;
            }

            int xValue = actorLocation.x() + xOffset;
            int yValue = actorLocation.y() + yOffset;

            if (map.getXRange().contains(xValue) && map.getYRange().contains(yValue)) {
                newLocation = map.at(xValue, yValue);
                allLocations.add(newLocation);
            }

        }
        return allLocations;
    }


    public static List<Location> lookAround(GameMap map, Actor actor, int radius) {
        Location actorLocation = map.locationOf(actor);
        List<Location> locationsInSight = new ArrayList<>();
        NumberRange horizontalRange = new NumberRange(-1*radius, 2*radius + 1);
        NumberRange verticalRange = new NumberRange(-1*radius + 1, 2*radius - 1);

        // get the horizontal locations
        locationsInSight.addAll(getSpottedLocations(map, actorLocation, horizontalRange, horizontalRange.min(), true));
        locationsInSight.addAll(getSpottedLocations(map, actorLocation, horizontalRange, horizontalRange.max(), true));

        // get the vertical locations
        locationsInSight.addAll(getSpottedLocations(map, actorLocation, verticalRange, horizontalRange.min(), false));
        locationsInSight.addAll(getSpottedLocations(map, actorLocation, verticalRange, horizontalRange.max(), false));

        return locationsInSight;
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
