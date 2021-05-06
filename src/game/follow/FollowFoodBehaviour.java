package game.follow;

import edu.monash.fit2099.engine.*;
import game.PortableItem;
import game.dinosaurs.DinoActor;
import game.dinosaurs.DinoCapabilities;

import java.util.List;

public class FollowFoodBehaviour extends FollowBehaviour {

    private static final String DESCRIPTION = "find food";
    private final DinoCapabilities diet;

    public FollowFoodBehaviour(DinoCapabilities diet) {
        super(diet, DESCRIPTION);
        this.diet = diet;
    }

//    @Override
//    public Action getAction(Actor actor, GameMap map) {
//        if (actor instanceof DinoActor){
//            DinoCapabilities followPurpose = super.getFollowPurpose();
//            if (followPurpose == DinoCapabilities.HERBIVORE || followPurpose == DinoCapabilities.CARNIVORE){
//                super.getAction(actor, map);
//            }
//        }
//        return null;
//    }

    @Override
     Location follow(GameMap map, Location destination, DinoActor actorAsDino){
        Location returnDestination = null;
        List<Item> groundItems = map.locationOf(actorAsDino).getItems();

        for (Item item : groundItems){
            if (item instanceof PortableItem) {
                PortableItem currentItem = (PortableItem) item;
                if (tryToEatFruit() && currentItem.edibleByVegetarians() ||
                        tryToEatMeat() && currentItem.edibleByCarnivores()) {
                    returnDestination = destination;
                    break;
                }
            }
        }
        return returnDestination;
    }

    private boolean tryToEatMeat() {
        return diet == DinoCapabilities.CARNIVORE;
    }

    private boolean tryToEatFruit() {
        return diet == DinoCapabilities.HERBIVORE;
    }

}
