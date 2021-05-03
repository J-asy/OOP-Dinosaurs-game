package game.follow;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.dinosaurs.DinoCapabilities;

public class FollowMateBehaviour extends FollowBehaviour {

    public FollowMateBehaviour() {
        super(DinoCapabilities.CAN_BREED);
    }

//    @Override
//    public Action getAction(Actor actor, GameMap map) {
////        if (actor)
//    }
}
