package game.feeding;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.attack.Corpse;
import game.FoodType;
import game.dinosaurs.Egg;
import game.utility.Probability;
import game.dinosaurs.DinoEncyclopedia;
import game.environment.Bush;
import game.environment.Fruit;
import game.environment.Tree;

public class FeedingAction extends Action {

    Boolean foodOnGround;
    Item item;
    int x;
    int y;

    public FeedingAction (Boolean foodOnGround, Item item, int x, int y) {
        this.foodOnGround = foodOnGround;
        this.item = item;
        this.x = x;
        this.y = y;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String foodName = "";

        if (foodOnGround) {
            if (actor.hasCapability(FoodType.HERBIVORE)) {
                if (item.hasCapability(FoodType.HERBIVORE)) {
                    actor.heal(10);
                    map.at(x,y).removeItem(item);
                    foodName += item.toString();
                }
            }
            else if (actor.hasCapability(FoodType.CARNIVORE)) {
                if (item.hasCapability((FoodType.CARNIVORE))) {
                    if (item instanceof Egg){
                        actor.heal(10);
                        map.at(x,y).removeItem(item);
                        foodName += item.toString();
                    }
                    else if (item instanceof Corpse) {
                        if ( ((Corpse) item).getParent() == DinoEncyclopedia.ALLOSAUR.getDisplayChar() ||
                                ((Corpse) item).getParent() == DinoEncyclopedia.STEGOSAUR.getDisplayChar()){
                            actor.heal(50);
                            map.at(x,y).removeItem(item);
                            foodName += item.toString();
                        }
                        else if (((Corpse) item).getParent() == DinoEncyclopedia.BRACHIOSAUR.getDisplayChar()) {
                            actor.heal(100);
                            map.at(x,y).removeItem(item);
                            foodName += item.toString();
                        }
                    }
                }
            }

        }
        else {
            if (actor.hasCapability(FoodType.HERBIVORE)){
                if (map.at(x,y).getGround() instanceof Tree) {
                    int noOfTreeFruits = ((Tree) map.at(x,y).getGround()).getTreeFruits().size();
                    for (int i = 0; i < noOfTreeFruits; i++){
                        if (Probability.generateProbability(0.5f)) {
                            ((Tree) map.at(x,y).getGround()).decrementTreeItem();
                            actor.heal(10);
                        }
                    }
                    foodName += "Fruit";
                }
                else if (map.at(x,y).getGround() instanceof Bush) {
                    ((Bush) map.at(x,y).getGround()).decrementBushItem() ;
                    actor.heal(10);
                    foodName += "Fruit";
                }
                else if (map.at(x,y).getItems() instanceof Fruit) {
                    map.at(x,y).removeItem(item);
                    actor.heal(10);
                    foodName += "Fruit";
                }

            }

        }
        return menuDescription(actor) + foodName;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats ";
    }
}
