package game.follow;

import game.dinosaurs.DinoCapabilities;

public class FollowVictimBehaviour extends FollowActorBehaviour{

    public FollowVictimBehaviour() {
        super(DinoCapabilities.CAN_ATTACK);
    }
}