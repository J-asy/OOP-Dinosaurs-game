package game.environment;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Food;
import game.Probability;
import game.dinosaurs.CapableActor;

public class Lake extends CapableGround implements Food, DrinkingGround {

    private int numberOfFish;
    private int waterLevel;
    private int capacity;

    /**
     * Constructor.
     */
    public Lake() {
        super('~');
        setLake();
        capacity = 25;
        numberOfFish = 5;
    }

    @Override
    public void tick(Location location){

       if (Rain.getStatus().equalsIgnoreCase("Raining")){

           double min = 0.1;
           double max = 0.6;
           double randDouble = Math.random()*(max-min + 1) + min;

           capacity += randDouble*20;

           System.out.println("\n" + capacity + " sips of water added to lake at (" +
                   location.x() + ", "+ location.y() + ")");
       }

        if (Probability.generateProbability(0.6f) && numberOfFish <= 25){
            numberOfFish++;
        }
    }



    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor instanceof CapableActor){
            return ((CapableActor)actor).canTraverseWater();
        }
        return false;
    }

    private void decrementNumberOfFish() {
        if (hasFish()) {
            numberOfFish--;
        }
    }

    private boolean hasFish(){
        return numberOfFish > 0;
    }

    @Override
    public boolean canEat(CapableActor capableActor, Location location) {
        return capableActor.isCarnivorous();
    }

    @Override
    public int eat(GameMap map, Location location, int biteSize) {
        int healPoints = 0;
        for (int i = 0; i < 2; i++) {
            if (hasFish() && Probability.generateProbability(0.5f)) {
                decrementNumberOfFish();
                healPoints += new Fish().getHealPoints();
            }
        }
        return healPoints;
    }

    @Override
    public String foodName(){
        return new Fish().foodName();
    }

    @Override
    public boolean canLayEggHere(CapableActor capableActor){
        return false;
    }

    @Override
    public boolean canBreedHere(CapableActor capableActor){
        return false;
    }

    @Override
    public boolean hasWater() {
        return waterLevel > 0;
    }
}
