package game.follow;

import game.dinosaurs.DinoCapabilities;

public class FollowMateBehaviour extends FollowActorBehaviour{

    public FollowMateBehaviour() {
        super(DinoCapabilities.CAN_BREED);
    }
}
