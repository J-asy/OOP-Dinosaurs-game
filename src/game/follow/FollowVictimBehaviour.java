package game.follow;

import game.dinosaurs.DinoCapabilities;

public class FollowVictimBehaviour extends FollowBehaviour {

    public FollowVictimBehaviour() {
        super(DinoCapabilities.CAN_ATTACK);
    }
}
