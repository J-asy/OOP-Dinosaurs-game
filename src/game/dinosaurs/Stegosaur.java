package game.dinosaurs;


/**
 * A herbivorous dinosaur.
 *
 */
public class Stegosaur extends DinoActor {


    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.STEGOSAUR;

    public Stegosaur(Sex sex, Boolean isMatured) {
        super(DINO_TYPE, sex, isMatured);
    }

    public Stegosaur(Boolean isMatured) {
        super(DINO_TYPE, isMatured);
    }

    @Override
    void initializeCapabilities(){
        addCapability(DinoCapabilities.HERBIVORE);
        addCapability(DinoCapabilities.CAN_BE_ATTACKED);
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

