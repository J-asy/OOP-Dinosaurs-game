package game.actions;

import edu.monash.fit2099.engine.*;
import game.EcoPoints;
import game.FoodType;
import game.dinosaurs.Allosaur;
import game.dinosaurs.Brachiosaur;
import game.dinosaurs.Stegosaur;
import game.environment.Fruit;

public class PlayerFeedAction extends Action {
    private final Actor target;

    public PlayerFeedAction(Actor target){ this.target=target; }

    @Override
    public String execute(Actor actor, GameMap map) {

        if (target instanceof Stegosaur || target instanceof Brachiosaur){
            for (Item item: actor.getInventory()){
                if (item.hasCapability(FoodType.HERBIVORE)) {
                    if (item instanceof Fruit)
                        EcoPoints.incrementEcoPoints(10);
                    actor.removeItemFromInventory(item);
                    target.heal(20);
                    return target + " at (" + map.locationOf(target).x() + ", " + map.locationOf(target).y() +
                            ") increases food level by 20!";
                }
            }
        }
        else if (target instanceof Allosaur){
            for (Item item: actor.getInventory()){
                if (item.hasCapability(FoodType.CARNIVORE)) {
                    actor.removeItemFromInventory(item);
                    target.heal(20);
                    return target + " at (" + map.locationOf(target).x() + ", " + map.locationOf(target).y() +
                            ") increases food level by 20!";
                }
            }
        }

        return "You have no food to feed " + target ;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " feeds " + target;
    }
}

//