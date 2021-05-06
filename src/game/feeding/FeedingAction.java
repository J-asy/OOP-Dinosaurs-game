package game.feeding;

import edu.monash.fit2099.engine.*;
import game.PortableItem;
import game.attack.Corpse;
import game.FoodType;
import game.dinosaurs.DinoActor;
import game.dinosaurs.DinoCapabilities;
import game.dinosaurs.Egg;
import game.Probability;
import game.dinosaurs.DinoEncyclopedia;
import game.environment.*;

import java.util.Map;

import static java.util.Map.entry;
//import game.environment.Bush;
//import game.environment.Fruit;
//import game.environment.Tree;

public class FeedingAction extends Action {

    Boolean foodOnGround;
    Item item;
    int x;
    int y;
//
//    private final static Map<DinoEncyclopedia, Integer> FOOD_POINTS_DICTIONARY = Map.ofEntries(
//            entry(DinoEncyclopedia.STEGOSAUR, 3),
//            entry(DinoEncyclopedia.BRACHIOSAUR, 2),
//            entry(DinoEncyclopedia.ALLOSAUR, 1)
//    );

    public FeedingAction (Boolean foodOnGround, Item item) {
        this.foodOnGround = foodOnGround;
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String foodName = null;
        Location actorLocation = map.locationOf(actor);
        Ground ground = actorLocation.getGround();
        boolean feedingPossible = false;
        int healPoints = 0;

        if (actor instanceof DinoActor) {
            DinoActor actorAsDino = (DinoActor) actor;
            if (foodOnGround && item instanceof PortableItem) {
                    PortableItem portableItem = (PortableItem) item;

                    if (feedOnFruitPossible(actorAsDino, portableItem)) {
                        feedingPossible = true;
                        healPoints = 10;
                    } else if (feedOnMeatPossible(actorAsDino, portableItem)) {
                        feedingPossible = true;

                        if (item instanceof Egg) {
                            healPoints = 10;

                            foodName += item.toString();
                        } else if (item instanceof Corpse) {
                            if (((Corpse) item).getParent() == DinoEncyclopedia.ALLOSAUR ||
                                    ((Corpse) item).getParent() == DinoEncyclopedia.STEGOSAUR) {
                                healPoints = 50;
                                foodName += item.toString();
                            } else if (((Corpse) item).getParent() == DinoEncyclopedia.BRACHIOSAUR) {
                                healPoints = 100;
                            }
                        }
                    }

                    if (feedingPossible) {
                        actorLocation.removeItem(item);
                        actor.heal(healPoints);
                        foodName = item.toString();

                    }


            }

            else {
                if (actorAsDino.isHerbivorous() && ground instanceof CapableGround) {
                    CapableGround capableGround = (CapableGround) ground;
                    if (capableGround.isTree()) {
                        int noOfTreeFruits = ((Tree)ground).getTreeFruitsSize();
                        if (noOfTreeFruits > 0) {
                            for (int i = 0; i < noOfTreeFruits; i++) {
                                if (Probability.generateProbability(0.5f)) {
                                    ((Tree) map.at(x, y).getGround()).decrementTreeItem();
                                    actor.heal(10);
                                }
                            }
                            foodName = "Fruits";
                        }

                    } else if (capableGround.isBush()) {
                        if (((Bush) map.at(x, y).getGround()).decrementBushItem() != null) {
                            actor.heal(10);
                            foodName = "Fruit";
                        }
                    }
                }
            }
        }

        if (menuDescription(actor).length() == 0) {
            return menuDescription(actor) + " does not eat";
        } else {
            return menuDescription(actor) + " eats " + foodName;
        }
    }

    private boolean feedOnFruitPossible(DinoActor actorAsDino, PortableItem item) {
        return actorAsDino.isHerbivorous() && item.edibleByHerbivores();
    }

    private boolean feedOnFruitPossible(DinoActor actorAsDino, CapableGround ground) {
        return actorAsDino.isHerbivorous() && ground.isTree();
    }

    private boolean feedOnMeatPossible(DinoActor actorAsDino, PortableItem item) {
        return actorAsDino.isCarnivorous() && item.edibleByCarnivores();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString();
    }
}
