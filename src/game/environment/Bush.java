package game.environment;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.Probability;
import game.dinosaurs.CapableActor;

/**
 * Class representing terrain of Bush type.
 */
public class Bush extends FertileGround {

    /**
     * Display character of a small bush.
     */
    private static final char SMALL = '"';

    /**
     * Display character of a big bush.
     */
    private static final char BIG = '*';

    /**
     * Display character of a big bush that has fruits on it.
     */
    private static final char FRUITY_BUSH = '^';

    /**
     * Amount of food points gained by eating a Fruit from a bush.
     */
    private static final int FRUIT_HEAL_POINTS = 10;

    /**
     * Constructor.
     */
    public Bush() {
        super(SMALL);
        setBush();
    }

    /**
     * Updates the bush every turn.
     * On each tick, the current Location is checked to see if the Actor (if any) has the capability of a BUSH_DESTROYER
     * (a capability that only the Brachiosaur has). If yes, the Bush is reverted back to a Dirt as the Actor destroys
     * the Bush. There is only a 50% chance that this can happen. The age of the Bush also increases on each tick and
     * once it's over the age of 10, it has a 1% chance to grow fruit.
     *
     * @param location the current Location of the Bush
     */
    @Override
    public void tick(Location location){

        // If a Brachiosaur steps on the bush, 50% chance ground reverts back to dirt :(
        if (location.getActor() != null && location.getActor() instanceof CapableActor) {
            CapableActor capableActor = (CapableActor) location.getActor();
            if (capableActor.canDestroyBush() && Probability.generateProbability(0.5f)) {
                location.setGround(new Dirt());
            }
        }
        else {
            if (getAge() > 10){
                if (Probability.generateProbability(0.1f)) {
                   addFruit();
                   displayChar = FRUITY_BUSH;
                }

                if (getNumberOfFruits() <= 0){
                    displayChar = BIG;
                }

            }
            else {
                // main reason why this part if in the else block is cuz it will be more efficient to only
                // keep incrementing the age until it matures - age hits 10
                // we change the displayChar then we never need to bother about age again
                incrementAge();
                if (getAge() == 10){
                    displayChar = BIG;
                }
            }
        }

        adjustHasFruitCapability();
    }

    /**
     * Get the amount of food points gained by eating a Fruit from a Bush.
     * @return amount of food points gained by eating a Fruit from a Bush.
     */
    @Override
    int getHealPoints(){
        return FRUIT_HEAL_POINTS;
    }

    /**
     * Returns true if the CapableActor can eat a fruit growing on the Bush,
     * which is when the CapableActor is a short herbivore and the Bush has fruits growing on it,
     * returns false otherwise.
     * @param capableActor A CapableActor
     * @param location Location of the Bush
     * @return true if the CapableActor can eat fruits growing on the Bush
     */
    @Override
    public boolean canEat(CapableActor capableActor, Location location) {
        return capableActor.isHerbivorous() && !capableActor.canReachTree() && hasFruits();
    }

    /**
     * Simulates DinoActor eating a fruit from the Bush,
     * and returns the amount of food points gained by the eating the fruit.
     * @param map map the Actor is on
     * @param location location the Actor is at
     * @param biteSize jaw size of the Actor that is trying to eat
     * @return amount of food points that should be gained by eating a fruit
     */
    @Override
    public int eat(GameMap map, Location location, int biteSize) {
        return eatFruit();
    }

    /**
     * Returns true if the CapableActor can lay an Egg on Bush,
     * which is when the CapableActor is not arboreal (does not live on trees),
     * returns false otherwise.
     * @param capableActor A capable actor
     * @return true if the CapableActor can lay an Egg on Bush, false otherwise
     */
    @Override
    public boolean canLayEggHere(CapableActor capableActor){
        return !capableActor.isArboreal();
    }

    /**
     * Returns true if the CapableActor can breed on Bush,
     * which is when the CapableActor is not arboreal (does not live on trees),
     * returns false otherwise.
     * @param capableActor A capable actor
     * @return true if the CapableActor can breed on Bush, false otherwise
     */
    @Override
    public boolean canBreedHere(CapableActor capableActor){
        return !capableActor.isArboreal();
    }

}