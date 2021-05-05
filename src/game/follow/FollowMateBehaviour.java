package game.follow;

import game.dinosaurs.DinoCapabilities;

public class FollowMateBehaviour extends FollowBehaviour {

    private static final String DESCRIPTION = "find mate";

    public FollowMateBehaviour() {
        super(DinoCapabilities.CAN_BREED, DESCRIPTION);
    }

}