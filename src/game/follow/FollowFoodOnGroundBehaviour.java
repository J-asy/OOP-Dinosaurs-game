package game.follow;

import edu.monash.fit2099.engine.*;
import game.PortableItem;
import game.dinosaurs.DinoActor;

import java.util.List;

public class FollowFoodOnGroundBehaviour extends FollowBehaviour {

    private static final String DESCRIPTION = "find food on ground";
    private static final int MIN_RADIUS = 1;
    private static final int MAX_RADIUS = 10;

    public FollowFoodOnGroundBehaviour() {
        super(DESCRIPTION, MIN_RADIUS, MAX_RADIUS);
    }

    @Override
    public boolean motivatedToFollow(DinoActor actor) {
        return actor.isHungry();
    }

    @Override
     Location findTarget(GameMap map, Location destination, DinoActor actorAsDino){
        Location returnDestination = null;
        List<Item> groundItems = destination.getItems();

        for (Item item : groundItems){
            if (item instanceof PortableItem) {
                PortableItem currentItem = (PortableItem) item;
                if (actorAsDino.isHerbivorous() && currentItem.edibleByHerbivores() && !actorAsDino.canReachTree()||
                        actorAsDino.isCarnivorous() && currentItem.edibleByCarnivores()) {
                    returnDestination = destination;
                    break;
                }
            }
        }
        
        return returnDestination;
    }


}
