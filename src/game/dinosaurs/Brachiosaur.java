package game.dinosaurs;

import game.FoodType;


public class Brachiosaur extends DinoActor {

    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.BRACHIOSAUR;

    public Brachiosaur(Sex sex) {
        super(DINO_TYPE, sex);
        hasCapability(FoodType.HERBIVORE);
    }

    public Brachiosaur() {
        super(DINO_TYPE);
        hasCapability(FoodType.HERBIVORE);
    }

    // don't need to override getAllowableActions, cuz can only breed - dealt with by DinoActor
    // don't need attack action, since brachiosaur cannot be attacked

}
