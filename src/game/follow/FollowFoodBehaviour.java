package game.follow;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.DinoActor;
import game.dinosaurs.DinoCapabilities;

public class FollowFoodBehaviour extends FollowBehaviour {

    public FollowFoodBehaviour(DinoCapabilities diet) {
        super(diet);
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor instanceof DinoActor){
            DinoCapabilities followPurpose = super.getFollowPurpose();
            if (followPurpose == DinoCapabilities.HERBIVORE || followPurpose == DinoCapabilities.CARNIVORE){
                super.getAction(actor, map);
            }
        }
        return null;
    }

}
