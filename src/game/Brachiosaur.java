package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class Brachiosaur extends DinoActor{

    private static final int HUNGRY_WHEN = 140;

    public Brachiosaur(String name, char sex) {
        super(name, 'b', 100, 160, sex);
    }

    @Override
    public void roarIfHungry(){
        if(getFoodLevel() < HUNGRY_WHEN){
            System.out.println(getName() + " getting hungry! ");  // add location?
        }
    }

    // cannot attack brachiosaur
//    @Override
//    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
//        return new Actions();
//    }

}
