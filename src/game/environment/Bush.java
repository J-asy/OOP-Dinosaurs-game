package game.environment;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.*;
import game.dinosaurs.DinoCapabilities;
import game.utility.Probability;
import java.util.ArrayList;

/**
 * Class representing terrain of Bush type.
 */
public class Bush extends Ground {

    private int age = 0;  //the age of the bush
    private ArrayList<Fruit> bushFruits = new ArrayList<>(); //arrayList of the fruits that have grown on the bush

    /**
     * Constructor
     */
    public Bush() {
        super('~');
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
        super.tick(location);

        // If a Brachiosaur steps on the bush, 50% chance ground reverts back to dirt :(
        if (location.getActor().hasCapability(DinoCapabilities.BUSH_DESTROYER) && Probability.generateProbability(0.5f)){
            location.setGround(new Dirt());
        }
        else {
            // If it's a fully grown bush, it can grow fruits, if a fruit grown then add to the fruits list
            if (age > 10){
                if (Probability.generateProbability(0.1f)) {
                    Fruit fruit = new Fruit(displayChar);
                    EcoPoints.incrementEcoPoints(1);
                    bushFruits.add(fruit);
                }
            }
            else {
                // main reason why this part if in the else block is cuz it will be more efficient to only
                // keep incrementing the age until it matures - age hits 10
                // we change the displayChar then we never need to bother about age again
                age++;
                if (age == 10){
                    displayChar = '*';
                }
            }
        }
    }

    /**
     * Removes the first Fruit in list.
     *
     * @return the removed Fruit
     */
    public Fruit decrementBushItem(){

        if (bushFruits.size()>0) {
            return bushFruits.remove(0);
        }
        return null;
    }

    //is this necessary??
    /**
     * Removes the first Fruit in the List without returning anything.
     */
    public void removeBushItem(){
        if (bushFruits.size()>0)
            bushFruits.remove(0);
    }

}

