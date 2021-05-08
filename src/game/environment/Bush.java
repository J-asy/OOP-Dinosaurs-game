package game.environment;

import edu.monash.fit2099.engine.Location;
import game.Probability;
import game.dinosaurs.DinoCapabilities;

/**
 * Class representing terrain of Bush type.
 */
public class Bush extends CapableGround {

    private static final char SMALL = '~';
    private static final char BIG = '*';
    private static final char FRUITY_BUSH = '^';


    /**
     * Constructor.
     */
    public Bush() {
        super(SMALL);
        addCapability(TerrainType.BUSH);
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
        if (location.getActor() != null && location.getActor().hasCapability(DinoCapabilities.BUSH_DESTROYER) &&
                Probability.generateProbability(0.5f)){
            location.setGround(new Dirt());
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

}