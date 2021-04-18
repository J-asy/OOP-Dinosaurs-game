package game;

import edu.monash.fit2099.engine.*;

public class PlayerFeedAction extends Action {
    private final Actor target;

    public PlayerFeedAction(Actor target){ this.target=target; }

    @Override
    public String execute(Actor actor, GameMap map) {

        if (target instanceof Stegosaur || target instanceof Brachiosaur){
            target.heal(20);
            for (Item item: actor.getInventory()){
                if (item.hasCapability(FoodType.HERBIVORE)) {
                    actor.removeItemFromInventory(item);
                    EcoPoints.incrementEcoPoints(10);
                    return target + " at (" + map.locationOf(target).x() + ", " + map.locationOf(target).y() +
                            ") increases food level by 20!";
                }
            }
        }
        else if (target instanceof Allosaur){
            target.heal(20);
            for (Item item: actor.getInventory()){
                if (item.hasCapability(FoodType.CARNIVORE)) {
                    actor.removeItemFromInventory(item);
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