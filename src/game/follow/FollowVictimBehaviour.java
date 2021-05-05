package game.follow;

import game.dinosaurs.DinoCapabilities;

public class FollowVictimBehaviour extends FollowBehaviour {

    private static final String DESCRIPTION = "find victim to attack";

    public FollowVictimBehaviour() {
        super(DinoCapabilities.CAN_ATTACK, DESCRIPTION);
    }
}
