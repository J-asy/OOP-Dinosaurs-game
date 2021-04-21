package game.environment;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;
import game.*;
import game.actions.SearchItemAction;
import game.dinosaurs.Brachiosaur;
import game.utility.Probability;

import java.util.ArrayList;

public class Bush extends Ground {

    private int age = 0;  //the age of the bush
    private ArrayList<Fruit> bushFruits = new ArrayList<>(); //arrayList of the fruits that have grown on the bush

    public Bush() {
        super('~');
    }

    /**
     * Updates the bush every turn. "Lets the bush experience the joy of time XD"
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        super.tick(location);

        // If a Brachiosaur steps on the bush, 50% chance ground reverts back to dirt :(
        if (location.getActor() instanceof Brachiosaur && Probability.generateProbability(0.5f)){
            location.setGround(new Dirt());
        }
        else {
            // If it's a fully grown bush, it can grow fruits, if a fruit grown then add to the fruits list
            if (age > 10){
                if (Probability.generateProbability(0.1f)) {
                    Fruit fruit = new Fruit("fruit", displayChar, false);
                    fruit.addCapability(FoodType.HERBIVORE);
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

    public ArrayList<Fruit> getBushFruits(){return bushFruits;}

    //decrement the list after it gets eaten
    public Fruit decrementBushItem(){
        Fruit fruit = null;
        if (bushFruits.size()>0) {
            fruit = bushFruits.get(0);
            bushFruits.remove(0);
        }
        return fruit;
    }

    @Override
    public Actions allowableActions(Actor actor, Location location, String direction){
        Actions list = super.allowableActions(actor, location, direction);
        if (actor instanceof Player)
            list.add(new SearchItemAction(direction));
        return list;
    }

}


// change getFruits -> getFruit only since bush you can only get one fruit for dinos?
// important difference -> player can pick up multiple fruits from bushes, but dinos can only eat one fruit at each turn?
