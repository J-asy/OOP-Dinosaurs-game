package game.dinosaurs;

import game.DinoEncyclopedia;
import game.Sex;


public class Brachiosaur extends DinoActor {

    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.BRACHIOSAUR;

    public Brachiosaur(Sex sex) {
        super(DINO_TYPE, sex);
    }

    public Brachiosaur() {
        super(DINO_TYPE);
    }

    // don't need to override getAllowableActions, cuz can only breed - dealt with by DinoActor
    // don't need attack action, since brachiosaur cannot be attacked

}
