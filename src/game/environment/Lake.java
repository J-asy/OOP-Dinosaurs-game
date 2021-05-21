package game.environment;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.Food;
import game.Utility;
import game.dinosaurs.CapableActor;

/**
 * Class representing terrain of Lake type.
 */
public class Lake extends Ground implements Food, DrinkingGround {


    private int numberOfFish;
    private int waterLevel;
    private static final int CAPACITY = 25;

    /**
     * Constructor.
     */
    public Lake() {
        super('~');
        numberOfFish = 5;
        waterLevel = 25;
    }

    /**
     * Updates the lake every turn.
     * On each tick, status of Rain is checked to see if it is "Raining", if so, water level increases.
     * There is a probability of 60% for a new fish to be born every turn.
     *
     * @param location the current Location of the Lake
     */
    @Override
    public void tick(Location location){

       if (Rain.getStatus().equalsIgnoreCase("Raining")){

           double min = 0.1;
           double max = 0.6;
           double randDouble = Math.random()*(max-min + 1) + min;

           waterLevel += randDouble*20;
           waterLevel = Math.min(waterLevel, CAPACITY);

           System.out.println("\n" + waterLevel + " sips of water added to lake at (" +
                   location.x() + ", "+ location.y() + ")");
       }

        if (Utility.generateProbability(0.6f) && numberOfFish < CAPACITY){
            numberOfFish++;
        }
    }

    /**
     * Returns true if the actor has the capability to traverse water,
     * returns false otherwise.
     * @param actor the Actor to check
     * @return true if the actor has the capability to traverse water, returns false otherwise.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor instanceof CapableActor){
            return ((CapableActor)actor).canTraverseWater();
        }
        return false;
    }

    /**
     * Decrements the number of fish in the Lake.
     */
    private void decrementNumberOfFish() {
        if (hasFish()) {
            numberOfFish--;
        }
    }

    /**
     * Checks if the Lake has fish.
     * @return true if there are fish in the Lake, false otherwise
     */
    private boolean hasFish(){
        return numberOfFish > 0;
    }

    /**
     * Returns true if the CapableActor can eat Food in the Lake, which is Fish,
     * hence the CapableActor must be carnivorous, otherwise returns false.
     * @param capableActor A CapableActor
     * @param location Location of the Food
     * @return true if the CapableActor can eat Food in the Lake, false otherwise
     */
    @Override
    public boolean canEat(CapableActor capableActor, Location location) {
        return capableActor.isCarnivorous();
    }

    /**
     * Simulates eating 0,1 or 2 Fish from the Lake,
     * and returns the amount of food points gained by eating the Fish.
     * @param map map the Actor is on
     * @param location location the Actor is at
     * @param biteSize jaw size of the Actor that is trying to eat
     * @return amount of food points gained by eating the Fish.
     */
    @Override
    public int eat(GameMap map, Location location, int biteSize) {
        int healPoints = 0;
        for (int i = 0; i < 2; i++) {
            if (hasFish() && Utility.generateProbability(0.5f)) {
                decrementNumberOfFish();
                healPoints += new Fish().getHealPoints();
            }
        }
        return healPoints;
    }

    /**
     * Returns name of Food contained in the lake, which is Fish.
     * @return name of Fish object
     */
    @Override
    public String foodName(){
        return new Fish().foodName();
    }

    /**
     * Returns false since no CapableActor should be able to lay an Egg in a Lake.
     * @param capableActor A capable actor
     * @return false
     */
    @Override
    public boolean canLayEggHere(CapableActor capableActor){
        return false;
    }

    /**
     * Returns false since no CapableActor should be able to breed in a Lake.
     * @param capableActor A capable actor
     * @return false
     */
    @Override
    public boolean canBreedHere(CapableActor capableActor){
        return false;
    }

    /**
     * Returns true if there is water in the lake, false otherwise.
     * @return true if there is water in the lake, false otherwise
     */
    @Override
    public boolean hasWater() {
        return waterLevel > 0;
    }

    @Override
    public void decrementNumberOfSips(){
        if (hasWater())
            waterLevel--;
    }

}
