package game.feeding;

import edu.monash.fit2099.engine.*;
import game.PortableItem;
import game.attack.Corpse;
import game.dinosaurs.DinoActor;
import game.dinosaurs.Egg;
import game.Probability;
import game.dinosaurs.DinoEncyclopedia;
import game.environment.*;
//import game.environment.Bush;
//import game.environment.Fruit;
//import game.environment.Tree;

public class FeedingAction extends Action {

    /**
     * Identifier if food is on ground or in plant
     */
    Boolean foodOnGround;

    /**
     * portable item that can be eaten
     */
    PortableItem portableItem;

    /**
     * x-coordinate of item
     */
    int x;

    /**
     * y-coordinate of item
     */
    int y;
//
//    private final static Map<DinoEncyclopedia, Integer> FOOD_POINTS_DICTIONARY = Map.ofEntries(
//            entry(DinoEncyclopedia.STEGOSAUR, 3),
//            entry(DinoEncyclopedia.BRACHIOSAUR, 2),
//            entry(DinoEncyclopedia.ALLOSAUR, 1)
//    );

    /**
     * Constructor
     *
     * @param foodOnGround true if food is on the ground, false if on tree or in bush
     * @param item edible portable item
     */
    public FeedingAction (Boolean foodOnGround, PortableItem item) {
        this.foodOnGround = foodOnGround;
        this.portableItem = item;
    }

    /**
     * Performs the action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a message string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String foodName = null;
        Location actorLocation = map.locationOf(actor);
        Ground ground = actorLocation.getGround();
        boolean feedingPossible = false;
        int healPoints = 0;

        if (actor instanceof DinoActor) {
            DinoActor actorAsDino = (DinoActor) actor;
            if (foodOnGround) {
                    if (feedOnFruitPossible(actorAsDino, portableItem)) {
                        feedingPossible = true;
                        healPoints = 10;
                    } else if (feedOnMeatPossible(actorAsDino, portableItem)) {
                        feedingPossible = true;

                        if (portableItem instanceof Egg) {
                            healPoints = 10;
                        } else if (portableItem instanceof Corpse) {
                            Corpse corpse = ((Corpse) portableItem);
                            if (corpse.getParent() == DinoEncyclopedia.ALLOSAUR ||
                                    corpse.getParent() == DinoEncyclopedia.STEGOSAUR) {
                                healPoints = 50;
                            } else if (corpse.getParent() == DinoEncyclopedia.BRACHIOSAUR) {
                                healPoints = 100;
                            }
                        }
                    }

                    if (feedingPossible) {
                        foodName = portableItem.toString();
                        System.out.println(foodName);
                        actorLocation.removeItem(portableItem);
                        actor.heal(healPoints);
                    }
            }

            else {
                if (actorAsDino.isHerbivorous() && ground instanceof CapableGround) {
                    CapableGround capableGround = (CapableGround) ground;
                    if (capableGround.isTree()) {
                        int noOfTreeFruits = capableGround.getNumberOfFruits();
                        if (noOfTreeFruits > 0) {
                            for (int i = 0; i < noOfTreeFruits; i++) {
                                if (Probability.generateProbability(0.5f)) {
                                    capableGround.removeFruit();
                                    actor.heal(10);
                                }
                            }
                            foodName = "Fruits";
                        }

                    } else if (capableGround.isBush()) {
                        System.out.println("no of bush fruits: " + capableGround.getNumberOfFruits() );
                        if (capableGround.getNumberOfFruits() > 0) {
                            capableGround.removeFruit();
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

    /**
     * Is dino a herbivore and is item edible by only herbivores?
     * @param actorAsDino DinoActor doing the action
     * @param item edible portable item
     * @return true if dinosaur is a herbivore and item is edible by herbivores, false otherwise
     */
    private boolean feedOnFruitPossible(DinoActor actorAsDino, PortableItem item) {
        return actorAsDino.isHerbivorous() && item.edibleByHerbivores();
    }

    /**
     * Is dino a herbivore and is the ground contains a tree?
     * @param actorAsDino DinoActor doing the action
     * @param ground ground that contains item
     * @return true if dinosaur is a herbivore and ground contains a tree, otherwise false
     */
    private boolean feedOnFruitPossible(DinoActor actorAsDino, CapableGround ground) {
        return actorAsDino.isHerbivorous() && ground.isTree();
    }

    /**
     * Is dino a carnivore and is item edible by carnivores?
     * @param actorAsDino DinoActor doing the action
     * @param item edible portable item
     * @return true if dinosaur is a carnivore and item is edible by carnivores, false otherwise
     */
    private boolean feedOnMeatPossible(DinoActor actorAsDino, PortableItem item) {
        return actorAsDino.isCarnivorous() && item.edibleByCarnivores();
    }

    /**
     * Returns the string format of actor's name
     * @param actor The actor performing the action.
     * @return String of actor's name
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString();
    }
}
