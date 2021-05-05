package game.dinosaurs;

/**
 *
 */
public class Brachiosaur extends DinoActor {

    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.BRACHIOSAUR;

    public Brachiosaur(DinoCapabilities sex, Boolean isMatured) {
        super(DINO_TYPE, sex, isMatured);
        addCapability(sex);
        initializeCapabilities();
    }

    public Brachiosaur(Boolean isMatured) {
        super(DINO_TYPE, isMatured);
    }

    @Override
    void initializeCapabilities(){
        addCapability(DinoCapabilities.BUSH_DESTROYER);
        addCapability(DinoCapabilities.HERBIVORE);
        addCapability(DinoCapabilities.CONSCIOUS);
    }

    // don't need to override getAllowableActions, cuz can only breed - dealt with by DinoActor
    // don't need attack action, since brachiosaur cannot be attacked

}
