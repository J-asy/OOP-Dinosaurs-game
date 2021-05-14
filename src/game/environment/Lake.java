package game.environment;

import edu.monash.fit2099.engine.Actor;
import game.FoodItem;
import game.Probability;
import game.dinosaurs.CapableActor;
import game.dinosaurs.DinoActor;

public class Lake extends CapableGround implements FeedingGround {

    private int numberOfFish;


    /**
     * Constructor.
     */
    public Lake() {
        super('~');
        setLake();
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor instanceof DinoActor){
            return ((DinoActor)actor).canEnterWater();
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
    public boolean canEat(CapableActor capableActor) {
        return capableActor.isCarnivorous();
    }

    @Override
    public int eat() {
        int healPoints = 0;
        for (int i = 0; i < 2; i++) {
            if (hasFish() && Probability.generateProbability(0.5f)) {
                decrementNumberOfFish();
                healPoints += foodToEat().getHealPoints();
            }
        }
        return healPoints;
    }

    @Override
    public FoodItem foodToEat(){
        return new Fish();
    }


}
