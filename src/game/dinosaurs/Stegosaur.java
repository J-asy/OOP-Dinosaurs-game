package game.dinosaurs;

//TODO: override initialize behaviours in child classes

/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends DinoActor {


    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.STEGOSAUR;

    public Stegosaur(DinoCapabilities sex, Boolean isMatured) {
        super(DINO_TYPE, sex, isMatured);
    }

    public Stegosaur(Boolean isMatured) {
        super(DINO_TYPE, isMatured);
    }

    @Override
    void initializeCapabilities(){
        super.initializeCapabilities();
        addCapability(DinoCapabilities.HERBIVORE);
        addCapability(DinoCapabilities.CAN_BE_ATTACKED);
        addCapability(DinoCapabilities.CONSCIOUS);
    }

//    @Override
//    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
//        Actions allowableActions = new Actions();
//        allowableActions.add(new AttackAction(this));
//        allowableActions.add(new BreedingAction(this));
//
//        return allowableActions;
//    }


}

