package game.follow;

import edu.monash.fit2099.engine.*;
import game.PortableItem;
import game.dinosaurs.DinoActor;

import java.util.List;

public class FollowFoodBehaviour extends FollowBehaviour {

    private static final String DESCRIPTION = "find food";

    public FollowFoodBehaviour() {
        super(DESCRIPTION);
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
                if (actorAsDino.isHerbivorous() && currentItem.edibleByHerbivores() ||
                        actorAsDino.isCarnivorous() && currentItem.edibleByCarnivores()) {
                    returnDestination = destination;
                    break;
                }
            }
        }
        return returnDestination;
    }


}
