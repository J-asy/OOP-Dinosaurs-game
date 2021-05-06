package game.feeding;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.attack.Corpse;
import game.FoodType;
import game.dinosaurs.DinoCapabilities;
import game.dinosaurs.Egg;
import game.Probability;
import game.dinosaurs.DinoEncyclopedia;
import game.environment.*;
//import game.environment.Bush;
//import game.environment.Fruit;
//import game.environment.Tree;

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
            if (actor.hasCapability(DinoCapabilities.HERBIVORE)) {
                if (item.hasCapability(FoodType.HERBIVORE)) {
                    actor.heal(10);
                    map.at(x,y).removeItem(item);
                    foodName += item.toString();
                }
            }
            else if (actor.hasCapability(DinoCapabilities.CARNIVORE)) {
                if (item.hasCapability((FoodType.CARNIVORE))) {
                    if (item instanceof Egg){
                        actor.heal(10);
                        map.at(x,y).removeItem(item);
                        foodName += item.toString();
                    }
                    else if (item instanceof Corpse) {
                        if ( ((Corpse) item).getParentChar() == DinoEncyclopedia.ALLOSAUR.getDisplayChar()){
                            actor.heal(50);
                            map.at(x,y).removeItem(item);
                            foodName += item.toString() + " Allosaur";
                        }
                        else if (((Corpse) item).getParentChar() == DinoEncyclopedia.STEGOSAUR.getDisplayChar()){
                            actor.heal(50);
                            map.at(x,y).removeItem(item);
                            foodName += item.toString() + " Stegosaur";
                        }
                        else if (((Corpse) item).getParentChar() == DinoEncyclopedia.BRACHIOSAUR.getDisplayChar()) {
                            actor.heal(100);
                            map.at(x,y).removeItem(item);
                            foodName += item.toString() + " Brachiosaur";
                        }
                    }
                }
            }

        }
        else {
            if (actor.hasCapability(DinoCapabilities.HERBIVORE)){
                if (map.at(x,y).getGround().hasCapability(TerrainType.TREE)) {
                    int noOfTreeFruits = ((Tree) map.at(x,y).getGround()).getTreeFruitsSize();
                    if (noOfTreeFruits > 0) {
                        for (int i = 0; i < noOfTreeFruits; i++) {
                            if (Probability.generateProbability(0.5f)) {
                                ((Tree) map.at(x, y).getGround()).decrementTreeItem();
                                actor.heal(10);
                                foodName += "Fruits";
                            }
                        }
                    }
                }
                else if (map.at(x,y).getGround().hasCapability(TerrainType.BUSH)) {
//                    if (((Bush) map.at(x,y).getGround()).decrementBushItem() != null) {
                        actor.heal(10);
                        foodName += "Fruit";
//                    }
                }
            }

        }

        if (foodName.length() == 0) {
            return menuDescription(actor) + " does not eat";
        }
        else{
            return menuDescription(actor) + " eats " + foodName;
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString();
    }
}
