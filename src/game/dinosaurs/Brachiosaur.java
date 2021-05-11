package game.dinosaurs;

/**
 * A herbivorous dinosaur that can eat fruits from trees, can destroy bushes and
 * cannot be attacked by Allosaur.
 */
public class Brachiosaur extends DinoActor {

    /**
     * Enum type that can be referenced for a lot of useful constants
     * that are values for Brachiosaur attributes.
     */
    private static final DinoEncyclopedia DINO_TYPE = DinoEncyclopedia.BRACHIOSAUR;

    /**
     * Id to append to a Brachiosaur's name to be able to differentiate between
     * different Brachiosaurs.
     */
    private static int nextId = 1;

    /**
     * Constructor.
     * @param sex DinoCapabilities.MALE is Brachiosaur should be male,
     *            DinoCapabilities.FEMALE is Brachiosaur should be female,
     * @param isMatured true if Brachiosaur is grown up, false otherwise.
     */
    public Brachiosaur(DinoCapabilities sex, Boolean isMatured) {
        super(DINO_TYPE, sex, isMatured, nextId);
        nextId += 1;
    }

    /**
     * Constructor.
     * @param isMatured true if Brachiosaur is grown up, false otherwise.
     */
    public Brachiosaur(Boolean isMatured) {
        super(DINO_TYPE, isMatured, nextId);
        nextId += 1;
    }

    /**
     * Initialize necessary capabilities of a Brachiosaur.
     */
    @Override
    void initializeCapabilities(){
        super.initializeCapabilities();
        addCapability(DinoCapabilities.HERBIVORE);
        addCapability(DinoCapabilities.CAN_REACH_TREE);
        addCapability(DinoCapabilities.BUSH_DESTROYER);
    }

}
