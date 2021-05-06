package game.follow;

import edu.monash.fit2099.engine.*;
import game.PortableItem;
import game.dinosaurs.DinoActor;

import java.util.List;

public class FollowFoodOnGroundBehaviour extends FollowBehaviour {

    private static final String DESCRIPTION = "find food on ground";

    public FollowFoodOnGroundBehaviour() {
        super(DESCRIPTION);
    }

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
